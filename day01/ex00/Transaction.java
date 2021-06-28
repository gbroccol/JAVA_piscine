
import java.util.UUID;

//public enum transferCategory {
//
//    DEBIT,
//    CREDIT
//
//}

public class Transaction {

    private UUID    _id;
    private User    _recipient;
    private User    _sender;
    private boolean _transferCategory; //  = transferCategory.CREDIT;
    private long    _amount;


//    session.setId(UUID.randomUUID());


    /*
     * GET
     */
    public long get_id() {
        return _id;
    }

    public User get_recipient() {
        return _recipient;
    }

    public User get_sender() {
        return _sender;
    }

    public boolean is_transferCategory() {
        return _transferCategory;
    }

    public long get_amount() {
        return _amount;
    }

    /*
     * SET
     */

    public void set_id(long _id) {
        this._id = _id;
    }

    public void set_recipient(User _recipient) {
        this._recipient = _recipient;
    }

    public void set_sender(User _sender) {
        this._sender = _sender;
    }

    public void set_transferCategory(boolean _transferCategory) {
        this._transferCategory = _transferCategory;
    }

    public void set_amount(long _amount) {
        this._amount = _amount;
    }
}
