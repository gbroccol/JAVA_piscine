import java.util.UUID;

public interface TransactionsList {

    void addTransaction(Transaction transaction);

    void removeTransactionByID(UUID id) throws TransactionNotFoundException;

    Transaction getTransactionById(UUID id) throws TransactionNotFoundException;

    Transaction[] toArray();
}
