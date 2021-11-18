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