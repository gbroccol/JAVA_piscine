public class Program {

    public static void error(String error) {
        System.err.println(error);
        System.exit(1);
    }

    public static void main(String[] args) throws InterruptedException {

        if (args.length == 0 || !args[0].startsWith("--threadsCount=")) {
            error("Flag --threadsCount= is required");
        }

        int countThreads = 0;

        try {
            countThreads = Integer.parseInt(args[0].substring(15));
        } catch (NumberFormatException e) {
            error("threadsCount must be a number");
        }
        if (countThreads < 1) {
            error("Must be more then 0 threads");
        }

        Download download = new Download();
        download.startThreads(Math.min(countThreads, download.getCountFiles()));
        download.joinThreads();
    }
}