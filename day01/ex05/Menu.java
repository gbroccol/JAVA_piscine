import java.util.Scanner;
import java.util.UUID;

public class Menu {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    TransactionsService transactionService;
    Scanner in;
    int command = 0;

    String menuDev = ANSI_YELLOW +"\t1. Add a user\n" +
                        "\t2. View user balances\n" +
                        "\t3. Perform a transfer\n" +
                        "\t4. View all transactions for a specific user\n" +
                        "\t5. DEV - remove a transfer by ID\n" +
                        "\t6. DEV - check transfer validity\n" +
                        "\t7. Finish execution" + ANSI_RESET;

    String menuBase = ANSI_YELLOW + "\t1. Add a user\n" +
                        "\t2. View user balances\n" +
                        "\t3. Perform a transfer\n" +
                        "\t4. View all transactions for a specific user\n" +
                        "\t5. Finish execution" + ANSI_RESET;

    public Menu() {
        transactionService = new TransactionsService();
        in = new Scanner(System.in);
    }

    private void addUser() {
        String name;
        int balance;

        System.out.println("Enter a user name and a balance");
        while (true) {
            name = in.next();
            if (in.hasNextInt()) {
                balance = in.nextInt();
                transactionService.addUser(new User(name, balance));
                return;
            } else {
                System.out.println(ANSI_RED + "ERROR. Balance must be a number. Try again" + ANSI_RESET);
                in.next();
            }
        }
    }

    private void getUserBalance() {
        int id;

        System.out.println("Enter a user ID");
        while (true) {
            in.nextLine();
            if (in.hasNextInt()) {
                id = in.nextInt();
                System.out.println(transactionService.getUserBalanceById(id));
                return;
            } else {
                System.out.println(ANSI_RED + "ID must be a number. Try again" + ANSI_RESET);
            }
        }
    }

    private void perfomTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        int sender = 0, recepient = 0, amount = 0;
        while (true) {
            if (in.hasNextInt()) {
                sender = in.nextInt();
                if (in.hasNextInt()) {
                    recepient = in.nextInt();
                    if (in.hasNextInt()) {
                        amount = in.nextInt();
                        transactionService.addTransaction(recepient, sender, amount);
                        break;
                    } else {
                        System.out.println(ANSI_RED + "ERROR. Amount must be a number. Try again");
                        return;
                    }
                } else {
                    System.out.println(ANSI_RED + "ERROR. Recipient must be a number. Try again");
                    return;
                }
            } else {
                System.out.println(ANSI_RED + "ERROR. Sender must be a number. Try again");
                return;
            }
        }
    }

    private void getTransactionsByUserId() {
        System.out.println("Enter a user ID");
        int id = 0;
        while (true) {
            if (in.hasNextInt()) {
                id = in.nextInt();
                Transaction[] transactions = transactionService.getTransactionsArrayByUserId(id);
                for (int i = 0; i < transactions.length; ++i) {
                    System.out.println(transactions[i].toString());
                }
                if (transactions.length == 0)
                    System.out.println("User has no transactions");
                break;
            } else {
                System.out.println(ANSI_RED + "ID must be a number. Try again" + ANSI_RESET);
                in.next();
            }
        }
    }

    private void removeTransactionById() {
        System.out.println("Enter a user ID and a transfer ID");
        int id = 0;
        String transferId;
        while (true) {
            if (in.hasNextInt()) {
                id = in.nextInt();
                transferId = in.next();
                transactionService.deleteTransaction(UUID.fromString(transferId), id);
                break;
            } else {
                System.out.println(ANSI_RED + "ERROR. ID must be a number. Try again" + ANSI_RESET);
                in.next();
            }
        }
    }

    public void checkTransferValidity() {
        transactionService.printTransferValidity();
    }

    public void menuDevFunc() {

        while (true) {
            System.out.println(menuDev);
            try {
                if (in.hasNextInt()) {
                    command = in.nextInt();
                    if (command == 1) {
                        addUser();
                    }
                    else if (command == 2) {
                        getUserBalance();
                    }
                    else if (command == 3) {
                        perfomTransfer();
                    }
                    else if (command == 4) {
                        getTransactionsByUserId();
                    }
                    else if (command == 5) {
                        removeTransactionById();
                    }
                    else if (command == 6 ) {
                        checkTransferValidity();
                    }
                    else if (command == 7) {
                        break;
                    } else {
                        System.out.println(ANSI_RED + "ERROR. Write number from the list" + ANSI_RESET);
                    }
                    System.out.println("------------------------------------------------");
                } else {
                    System.out.println(ANSI_RED + "ERROR. Wrong command" + ANSI_RESET);
                    in.next();
                }
            }
            catch (RuntimeException exception) {
                System.out.println(ANSI_RED + "RUNTIME EXCEPTION. " + exception.getMessage() + ANSI_RESET);
                continue;
            }
        }
    }

    public void menuBaseFunc() {
        while (true) {
            System.out.println(menuBase);
            try {
                if (in.hasNextInt()) {
                    command = in.nextInt();
                    if (command == 1) {
                        addUser();
                    }
                    else if (command == 2) {
                        getUserBalance();
                    }
                    else if (command == 3) {
                        perfomTransfer();
                    }
                    else if (command == 4) {
                        getTransactionsByUserId();
                    }
                    else if (command == 5) {
                        break;
                    }  else {
                        System.out.println(ANSI_RED + "ERROR. Write number from the list" + ANSI_RESET);
                    }
                    System.out.println("------------------------------------------------");
                } else {
                    System.out.println(ANSI_RED + "ERROR. Wrong command" + ANSI_RESET);
                    in.next();
                }
            }
            catch (RuntimeException exception) {
                System.out.println(ANSI_RED + "RUNTIME EXCEPTION. " + exception.getMessage() + ANSI_RESET);
                continue;
            }
        }
    }
}