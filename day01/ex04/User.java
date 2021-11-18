public class User {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GREEN = "\u001B[32m";

    private final int _id;

    private String _userName;
    private long _balance;

    private TransactionsList _userTransactions;

    public User(String userName, int balance) {

        this._id = UserIdsGenerator.getInstance().generateId();

        this._userName = userName;

        if (balance < 0) {
            this._balance = 0;
            System.out.println("\u001B[31m" + "Incorrect Start Balance" + "\u001B[0m");
        } else {
            this._balance = balance;
        }

        _userTransactions = new TransactionsLinkedList();
    }

    public void printUserData() {
        System.out.println(ANSI_GREEN + "User " + _id + ":" + ANSI_RESET);
        System.out.println("NAME          " + _userName);
        System.out.println("BALANCE       " + _balance + "\n");
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

    public TransactionsList getUserTransactions() {
        return _userTransactions;
    }

    public void setUserName(String userName) {
        this._userName = userName;
    }

    public void setBalance(long balance) {
        if (balance < 0) {
            System.out.println("\u001B[31m" + "Incorrect Balance" + "\u001B[0m");
        } else {
            this._balance = balance;
        }
    }
}
