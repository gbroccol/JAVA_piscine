import java.util.UUID;

public class TransactionsService {

    private UsersList _usersList = new UsersArrayList();
    private TransactionsList _unpairedTransactions = new TransactionsLinkedList();

    public TransactionsService() {
    }

    public void addUser(User user) {
        _usersList.addUser(user);
    }

    public long getUserBalanceById(int userId) {
        return _usersList.getUserById(userId).getBalance();
    }

    public void addTransaction(int recipientId, int senderId, long amount) {
        if (amount == 0) {
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

    public void deleteTransaction (UUID transactionId, int userId) {
        Transaction[] unpTrArray = _unpairedTransactions.toArray();
        boolean noTransactionInUnpArray = true;

        for (int i = 0; unpTrArray.length > i; i++) {
            if (unpTrArray[i].get_id() == transactionId) {
                _unpairedTransactions.removeTransactionByID(transactionId);
                noTransactionInUnpArray = false;
                break;
            }
        }
        if (noTransactionInUnpArray)
        _unpairedTransactions.addTransaction(new Transaction(_usersList
                .getUserById(userId)
                .getUserTransactions()
                .getTransactionById(transactionId)));

        _usersList.getUserById(userId).getUserTransactions().removeTransactionByID(transactionId);
    }

    public Transaction[] getUnpairedTransactions() {
        return _unpairedTransactions.toArray();
    }
}
