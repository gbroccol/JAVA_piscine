public class Program {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {

        User user0 = new User("Mark", 210);

        User user1 = new User("Liam", 0);

        User user2 = new User("Vika", 400);

        System.out.println(ANSI_BLUE + "COMMON BALANCE = " + (user0.getBalance() + user1.getBalance() + user2.getBalance()) + ANSI_RESET);
        user0.printUserData();
        user1.printUserData();
        user2.printUserData();
        Transaction transactions0 = new Transaction(user0, user1, tCategory.CREDIT, -211);
        Transaction transactions1 = new Transaction(user0, user1, tCategory.DEBIT, 1);
        System.out.println();
        Transaction transactions2 = new Transaction(user0, user1, tCategory.CREDIT, -60);
        user0.printUserData();
        user1.printUserData();
        System.out.println(ANSI_BLUE + "-------------------------------------" + ANSI_RESET);
        Transaction transactions3 = new Transaction(user0, user1, tCategory.DEBIT, 10);
        user0.printUserData();
        user1.printUserData();
        System.out.println(ANSI_BLUE + "-------------------------------------" + ANSI_RESET);
        Transaction transactions4 = new Transaction(user0, user2, tCategory.DEBIT, 200);
        user0.printUserData();
        user2.printUserData();
        user0.printTransactionsList();
        user1.printTransactionsList();
        user2.printTransactionsList();
        System.out.println(ANSI_BLUE + "------------------- DELETE ------------------" + ANSI_RESET);
        user0.printUserData();
        user1.printUserData();
        user2.printUserData();
        user0.getUserTransactions().removeTransactionByID(user0.getUserTransactions().toArray()[0].get_id());
        user0.getUserTransactions().removeTransactionByID(user0.getUserTransactions().toArray()[0].get_id());
        user0.getUserTransactions().removeTransactionByID(user0.getUserTransactions().toArray()[0].get_id());
        user0.printTransactionsList();
        user1.printTransactionsList();
        user2.printTransactionsList();
        user0.printUserData();
        user1.printUserData();
        user2.printUserData();
        System.out.println(ANSI_BLUE + "COMMON BALANCE = " + (user0.getBalance() + user1.getBalance() + user2.getBalance()) + ANSI_RESET);
    }
}
