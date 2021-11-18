
import java.util.UUID;

enum tCategory {DEBIT, CREDIT};

public class Transaction {

    private UUID _id;

    private User _recipient;

    private User _sender;

    private tCategory _transferCategory;

    private long _amount;

    public Transaction(User recipient, User sender, tCategory transferCategory, long amount) {

        this._id = UUID.randomUUID();

        this._recipient = recipient;

        this._sender = sender;

        if (transferCategory == tCategory.DEBIT || transferCategory == tCategory.CREDIT) {
            this._transferCategory = transferCategory;
            if (transferCategory == tCategory.DEBIT && amount > 0) {
                this._amount = amount;
            } else if (transferCategory == tCategory.CREDIT && amount < 0) {
                this._amount = amount;
            } else {
                System.err.println("\u001B[31m" + "Incorrect Sum" + "\u001B[0m");
            }
        } else {
            System.err.println("\u001B[31m" + "Incorrect Transaction" + "\u001B[0m");
        }
    }

    public UUID get_id() {
        return _id;
    }

    public User get_recipient() {
        return _recipient;
    }

    public User get_sender() {
        return _sender;
    }

    public tCategory get_transferCategory() {
        return _transferCategory;
    }

    public long get_amount() {
        return _amount;
    }

    public void set_id(UUID id) {
        this._id = id;
    }

    public void set_recipient(User recipient) {
        this._recipient = recipient;
    }

    public void set_sender(User sender) {
        this._sender = sender;
    }

    public void set_transferCategory(tCategory transferCategory) {
        if (transferCategory == tCategory.DEBIT || transferCategory == tCategory.CREDIT) {
            this._transferCategory = transferCategory;
        } else {
            System.err.println("\u001B[31m" + "Incorrect Transaction" + "\u001B[0m");
        }
    }

    public void set_amount(long amount) {
        if (this._transferCategory == tCategory.DEBIT && amount > 0) {
            this._amount = amount;
        } else if (this._transferCategory == tCategory.CREDIT && amount < 0) {
            this._amount = amount;
        } else {
            System.err.println("\u001B[31m" + "Incorrect Sum" + "\u001B[0m");
        }
    }
}
