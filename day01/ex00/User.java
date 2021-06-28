
import java.util.UUID;

public class User {

    private String  _userName;
    private long    _balance;
    private UUID    _id;

    public User(String userName) {
        this._userName = userName;
        this._balance = 0;
//        this.id = ;
    }

    /*
     * GET
     */

    public String getUserName() {
        return _userName;
    }

    public long getBalance() {
        return _balance;
    }

    public long getId() {
        return _id;
    }

    /*
     * SET
     */

    public void setUserName(String userName) {
        this._userName = userName;
//        sout
    }

    public void setBalance(long balance) {
        this._balance = balance;
    }

    public void setId(long id) {
        this._id = id;
    }
}
