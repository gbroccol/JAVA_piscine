
public class Program {

    private static final int NUMBER_START_POSITION = 8;
    private static int COUNT = -1;

    public static void main(String[] args) {

        if (validateFlag(args) < 1){
            System.err.println("flag --count='int > 0' is required");
            System.exit(-1);
        }

        Hen threadHen = new Hen(COUNT);
        Egg threadEgg = new Egg(COUNT);

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
        if (args.length != 1 || !args[0].startsWith("--count="))
            return COUNT;

        try {
            COUNT = Integer.parseInt(args[0].substring(NUMBER_START_POSITION));
        } catch (NumberFormatException e){
            return -1;
        }
        return COUNT;
    }
}

