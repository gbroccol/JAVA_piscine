class Farm { // Market
    private int counter = 0;

    public synchronized void printHen() {
        while (counter % 2 == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hen");
        counter++;
        notify();
    }

    public synchronized void printEgg() {
        while (counter % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Egg");
        counter++;
        notify();
    }
}