
public class Program {

    public static void main(String[] args) throws InterruptedException {

        RealMultithreading realMultithreading = new RealMultithreading(args);
        realMultithreading.createArray();
        realMultithreading.printBaseSum();
        realMultithreading.startThreads();
        realMultithreading.printThreadSum();
    }
}
