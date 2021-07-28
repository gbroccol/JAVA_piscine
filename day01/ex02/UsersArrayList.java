public class UsersArrayList implements UsersList {

    private final int START_CAPACITY = 10;

    private User[] _usersArray;

    private int _size;

    private int _capacity;

    public UsersArrayList() {
        this._usersArray = new User[START_CAPACITY];
        _size = 0;
        _capacity = START_CAPACITY;
    }

    private boolean isUserById(int id) {
        for (int i = 0; i < _size; i++) {
            if (id == _usersArray[i].getId()) {
                return true;
            }
        }

        return false;
    }

    public void addUser(User user) throws UserAlreadyExists {
        if (isUserById(user.getId()) == true) {
            throw new UserAlreadyExists();
        }

        if (_capacity == _size) {
            _capacity *= 2;
            User[] tmp = new User[_capacity];
            for (int i = 0; i < _size; i++) {
                tmp[i] = this._usersArray[i];
            }

            this._usersArray = tmp;
        }
        this._usersArray[_size] = user;
        _size++;
    }

    public User getUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < _size; i++) {
            if (id == _usersArray[i].getId()) {
                return _usersArray[i];
            }
        }

        throw new UserNotFoundException("User not found by id " + id);
    }

    public User getUserByIndex(int index) throws UserNotFoundException {
        if (_size <= index) {
            throw new UserNotFoundException("User not found by index " + index);
        }

        return _usersArray[index];
    }

    public int getUsersAmount() {
        return _size;
    }
}
