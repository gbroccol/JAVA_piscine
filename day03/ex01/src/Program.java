
public class Program {

    private static final int NUMBER_START_POSITION = 8;
    private static int COUNT = -1;

    static int counter = 0;

    public static void main(String[] args) {

        COUNT = validateFlag(args);

        if (COUNT < 1){
            System.err.println("Invalid flag!");
            return;
        }

        Farm farm = new Farm();
        Thread threadHen = new Thread(new Hen(COUNT, farm));
        Thread threadEgg = new Thread(new Egg(COUNT, farm));

        threadHen.start();
        threadEgg.start();

        try {
            threadHen.join();
            threadEgg.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < COUNT ; i++) {
            System.out.println("Human");
        }
    }

    public static int validateFlag(String[] args)
    {
        int count = -1;

        if (args.length != 1 || !args[0].startsWith("--count="))
            return count;

        try {
            count = Integer.parseInt(args[0].substring(NUMBER_START_POSITION));
        } catch (NumberFormatException e){
            return -1;
        }
        return count;
    }
}

class Hen implements Runnable { // Customer

    int _countIteration;
    Farm _farm;

    Hen(int countIteration, Farm farm) {
        this._countIteration = countIteration;
        this._farm = farm;
    }

    @Override
    public void run() {
        for (int i = 0; i < _countIteration ; i++) {
            _farm.printHen();
        }
    }
}

class Egg implements Runnable { // Producer

    int _countIteration;
    Farm _farm;

    Egg(int countIteration, Farm farm) {
        this._countIteration = countIteration;
        this._farm = farm;
    }

    @Override
    public void run() {
        for (int i = 0; i < _countIteration ; i++) {
            _farm.printEgg();
        }
    }
}

class Farm { // Market
    private int counter = 0;

    public synchronized void printHen() {

        while (counter == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hen");
    }

    public synchronized void printEgg() {
        System.out.println("Egg");
        counter++;
        notify();
    }
}