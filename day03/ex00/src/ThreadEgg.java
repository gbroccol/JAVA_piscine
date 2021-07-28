public class ThreadEgg extends Thread {

    int _countIteration;

    ThreadEgg(int countIteration) {
        this._countIteration = countIteration;
    }

    @Override
    public void run() {
        for (int i = 0; i <= _countIteration ; i++) {
            try {
                Thread.sleep((long)(( Math.random() * (500-100) ) + 100));
                System.out.println("Egg");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
