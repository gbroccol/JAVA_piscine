public class User {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_YELLOW = "\u001B[33m";

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

    public void printTransactionsList() {
        Transaction[] trArray = _userTransactions.toArray();

        int len = trArray.length;

        String categor;

        System.out.println(ANSI_YELLOW + "Transactions (User " + _id + "):" + ANSI_RESET);
        for (int i = 0; i < len; i++) {
            categor = trArray[i].get_transferCategory() == tCategory.DEBIT ? "debit" : "credit";
            System.out.print((i + 1) + ". ");
            System.out.print("ID = " + trArray[i].get_id());
            System.out.print(" RECIPIENT = " + trArray[i].get_recipient().getUserName());
            System.out.print(" SENDER = " + trArray[i].get_sender().getUserName());
            System.out.print(" CATEGORY = " + categor);
            System.out.println(" AMOUNT = " + trArray[i].get_amount());
        }
        System.out.println();
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
