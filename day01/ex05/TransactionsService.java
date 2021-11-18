import java.util.UUID;

public class TransactionsService {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    private UsersList _usersList = new UsersArrayList();

    public TransactionsService() {
    }

    public void addUser(User user) {
        _usersList.addUser(user);
        System.out.println("User with id = " + user.getId() + " is added");
    }

    public String getUserBalanceById(int userId) {
        User user = _usersList.getUserById(userId);
        return user.getUserName() + " - " + user.getBalance();
    }

    public void addTransaction(int recipientId, int senderId, long amount) {
        if (amount == 0) {
            System.out.println(ANSI_RED + "ERROR. Amount must not be 0" + ANSI_RESET);
            return;
        }
        User recipient = _usersList.getUserById(recipientId);
        User sender = _usersList.getUserById(senderId);
        tCategory category = tCategory.CREDIT;
        if (amount > 0)
            category = tCategory.DEBIT;
        Transaction tr = new Transaction(recipient, sender, category, amount);
    }

    public Transaction[] getTransactionsArrayByUserId (int userId) {
        return _usersList.getUserById(userId).getUserTransactions().toArray();
    }

    public void deleteTransaction (UUID trId, int userIdX) {
        boolean flag = true;

        User userX = _usersList.getUserById(userIdX);
        Transaction trX = userX.getUserTransactions().getTransactionById(trId);

        int userIdY = trX.get_recipient().getId() == userIdX ? trX.get_sender().getId() : trX.get_recipient().getId();
        User userY = _usersList.getUserById(userIdY);

        // delete unp trans
        Transaction[] unpTrArrayX = userX.getUnpairedTransactions().toArray();
        for (int i = 0; unpTrArrayX.length > i; i++) {
            if (unpTrArrayX[i].get_id().equals(trId)) { // !!! .toString()
                userX.getUnpairedTransactions().removeTransactionByID(trId);
                flag = false;
                break;
            }
        }

        // add unp trans
        if (flag == true) {
            Transaction trY = userY.getUserTransactions().getTransactionById(trId);
            userY.getUnpairedTransactions().addTransaction(trY);
        }

        long amount = trX.get_amount();
        String prepos = trX.get_transferCategory() == tCategory.DEBIT ? "To " : "From ";
        userX.getUserTransactions().removeTransactionByID(trId);
        System.out.println("Transfer " + prepos + userY.getUserName() + "(id = " + userIdY + ") " + (amount *= -1) + " removed");
    }

    public void printTransferValidity() {
        User userX;
        Transaction [] trArrayX;

        int userIdY;
        User userY;

        System.out.println("Check results:");

        for (int i = 1; i <= _usersList.getUsersAmount(); i++) {
            userX = _usersList.getUserById(i);
            trArrayX = userX.getUnpairedTransactions().toArray();
            for (int z = 0; z < trArrayX.length; z++) {

                userIdY = trArrayX[z].get_recipient().getId() == userX.getId() ? trArrayX[z].get_sender().getId() : trArrayX[z].get_recipient().getId();
                userY = _usersList.getUserById(userIdY);

                System.out.println(userX.getUserName() + "(id = " + userX.getId()
                        + ") has an unacknowledged transfer id = "
                        + trArrayX[z].get_id().toString()
                        + " from " + userY.getUserName() + "(id = " + userY.getId() + ") for " + trArrayX[z].get_amount());
            }
        }
    }
}
