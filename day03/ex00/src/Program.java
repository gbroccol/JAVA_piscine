
public class Program {

    private static final int NUMBER_START_POSITION = 8;
    private static int COUNT = -1;

    public static void main(String[] args) {

        COUNT = validateFlag(args);

        if (COUNT < 1){
            System.err.println("Invalid flag!");
            return;
        }

        ThreadHen threadHen = new ThreadHen(COUNT);
        ThreadEgg threadEgg = new ThreadEgg(COUNT);

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

