
public class Program {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";


    private static User user0 = new User("Mark", 210);
    private static User user1 = new User("Liam", 0);
    private static User user2 = new User("Vika", 400);

    private static void printAllUsers() {
        user0.printUserData();
        user1.printUserData();
        user2.printUserData();
    }

    private static void printTransactions(Transaction[] trArray, int id) {
        int len = trArray.length;
        String categor;
        System.out.println(ANSI_YELLOW + "Transactions (User " + id + "):" + ANSI_RESET);
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

    private static void printAllTransactionsList(TransactionsService ts) {

        int userId = user0.getId();
        printTransactions(ts.getTransactionsArrayByUserId(userId), userId);
        userId = user1.getId();
        printTransactions(ts.getTransactionsArrayByUserId(userId), userId);
        userId = user2.getId();
        printTransactions(ts.getTransactionsArrayByUserId(userId), userId);
    }

    private static void testTransaction(TransactionsService ts, User recipient, User sender, int amount) {
        int flag = 1;
        char firstOperation = '+';
        char secondOperation = '-';
        if (amount < 0) {
            firstOperation = '-';
            secondOperation = '+';
            amount *= -1;
            flag = -1;
        }

        tCategory category = tCategory.CREDIT;
        if (amount > 0)
            category = tCategory.DEBIT;

        System.out.println(ANSI_BLUE + category.name() + "\t"
                                + recipient.getUserName()
                                + " (" + ts.getUserBalanceById(recipient.getId()) + firstOperation + amount + ") "
                                + sender.getUserName()
                                + " (" + ts.getUserBalanceById(sender.getId()) + secondOperation + amount + ")"
                                + ANSI_RESET);

        ts.addTransaction(recipient.getId(), sender.getId(), amount * flag); // delete category
        printAllUsers();
    }

    public static void main(String[] args) {

        TransactionsService ts = new TransactionsService();
        ts.addUser(user0);
        ts.addUser(user1);
        ts.addUser(user2);

        System.out.println(ANSI_BLUE + "COMMON BALANCE = " + (user0.getBalance() + user1.getBalance() + user2.getBalance()) + ANSI_RESET);
        printAllUsers();
        testTransaction(ts, user0, user1, -211);
        testTransaction(ts, user0, user1, 1); // дебет умножать на -1
        testTransaction(ts, user0, user1, -60);
        testTransaction(ts, user0, user1, 10);
        testTransaction(ts, user0, user2, 200);
        testTransaction(ts, user0, user1, -360);
        printAllTransactionsList(ts);

        System.out.println(ANSI_BLUE + "------------------- DELETE TRANSACTIONS ------------------" + ANSI_RESET);
        int userId = user0.getId();
        ts.deleteTransaction(user0.getUserTransactions().toArray()[0].get_id(), userId);
        ts.deleteTransaction(user0.getUserTransactions().toArray()[0].get_id(), userId);
        ts.deleteTransaction(user0.getUserTransactions().toArray()[0].get_id(), userId);
        ts.deleteTransaction(user0.getUserTransactions().toArray()[0].get_id(), userId);
        userId = user1.getId();
        ts.deleteTransaction(user1.getUserTransactions().toArray()[0].get_id(), userId);
        printAllTransactionsList(ts);

        System.out.println(ANSI_BLUE + "------------------- PRINT UNPAIRED TRANSACTIONS ------------------" + ANSI_RESET);
        Transaction[] trUnpaired = ts.getUnpairedTransactions();
        for (int i = 0; trUnpaired.length > i; i++) {
            System.out.println(trUnpaired[i].get_id());
        }
    }
}
