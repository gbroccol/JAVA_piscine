
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
        this._transferCategory = transferCategory;
        this._amount = amount;

        if ((transferCategory == tCategory.DEBIT && recipient.getBalance() >= _amount) ||
                (transferCategory == tCategory.CREDIT && sender.getBalance() >= (-_amount))) {
            recipient.setBalance(recipient.getBalance() - _amount);
            sender.setBalance(sender.getBalance() + _amount);
            recipient.getUserTransactions().addTransaction(this);

            Transaction trForSender = new Transaction(this);
            trForSender.set_recipient(this._sender);
            trForSender.set_sender(this._recipient);
            trForSender.set_transferCategory(transferCategory == tCategory.DEBIT ? tCategory.CREDIT : tCategory.DEBIT);
            trForSender.set_amount(-(_amount));
            sender.getUserTransactions().addTransaction(trForSender);
            System.out.println("The transfer is completed");
        } else {
            System.err.println("\u001B[31m" + "Not enough money \u001B[0m");
        }
    }

    public Transaction(Transaction data) {
        this._id = data.get_id();
        this._recipient = data.get_recipient();
        this._sender = data.get_sender();
        this._transferCategory = data.get_transferCategory();
        this._amount = data.get_amount();
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
        }
        else {
            System.err.println("\u001B[31m" + "Incorrect Transaction" + "\u001B[0m");
        }
    }

    public void set_amount(long amount) {
        if (this._transferCategory == tCategory.DEBIT && amount > 0) {
            this._amount = amount;
        }
        else if (this._transferCategory == tCategory.CREDIT && amount < 0) {
            this._amount = amount;
        }
        else {
            System.err.println("\u001B[31m" + "Incorrect Sum" + "\u001B[0m");
        }
    }

    @Override
    public String toString() {
        String prepos = this._transferCategory == tCategory.DEBIT ? "To " : "From ";

        return prepos + this.get_sender().getUserName()
                + "(id = " + this.get_sender().getId() + ") "
                + (this._amount *= (-1)) + " with id = "
                + this._id.toString();
    }
}
