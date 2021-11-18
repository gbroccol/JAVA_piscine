class Egg implements Runnable {

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