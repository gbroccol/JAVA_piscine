public class User {

    private String _userName;

    private long _balance;

    private int _id;

    public User(String userName, int balance, int id) {
        this._userName = userName;
        if (balance < 0) {
            this._balance = 0;
            System.err.println("\u001B[31m" + "Incorrect Start Balance" + "\u001B[0m");
        } else {
            this._balance = balance;
        }
        this._id = id;
    }

    public String getUserName() {
        return _userName;
    }

    public long getBalance() {
        return _balance;
    }

    public int getId() {
        return _id;
    }

    public void setUserName(String userName) {
        this._userName = userName;
    }

    public void setBalance(long balance) {
        if (balance < 0) {
            System.err.println("\u001B[31m" + "Incorrect Balance" + "\u001B[0m");
        } else {
            this._balance = balance;
        }
    }
}
