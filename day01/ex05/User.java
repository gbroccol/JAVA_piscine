public class User {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GREEN = "\u001B[32m";

    private final int id;

    private String userName;
    private long balance;

    private TransactionsList userTransactions;
    private TransactionsList unpairedTransactions;

    public User(String userName, int balance) {

        this.id = UserIdsGenerator.getInstance().generateId();

        this.userName = userName;

        if (balance < 0) {
            this.balance = 0;
            System.out.println("\u001B[31m" + "Incorrect Start Balance" + "\u001B[0m");
        } else {
            this.balance = balance;
        }
        userTransactions = new TransactionsLinkedList();
        unpairedTransactions = new TransactionsLinkedList();
    }

    public void printUserData() {
        System.out.println(ANSI_GREEN + "User " + id + ":" + ANSI_RESET);
        System.out.println("NAME          " + userName);
        System.out.println("BALANCE       " + balance + "\n");
    }

    public String getUserName() {
        return userName;
    }

    public long getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public TransactionsList getUserTransactions() {
        return userTransactions;
    }

    public TransactionsList getUnpairedTransactions() {
        return unpairedTransactions;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBalance(long balance) {
        if (balance < 0) {
            System.out.println("\u001B[31m" + "Incorrect Balance" + "\u001B[0m");
        } else {
            this.balance = balance;
        }
    }

    public void setUnpairedTransactions(TransactionsList _unpairedTransactions) {
        this.unpairedTransactions = _unpairedTransactions;
    }
}
