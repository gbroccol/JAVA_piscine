public class Program {

    private static final int NUMBER_START_POSITION = 8;
    private static int COUNT = -1;

    public static void main(String[] args) {

        if (validateFlag(args) < 1){
            System.err.println("Invalid flag!");
            System.exit(-1);
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
