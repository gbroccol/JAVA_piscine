public interface UsersList {

    void addUser(User user) throws UserAlreadyExists;

    User getUserById(int id) throws UserNotFoundException;

    User getUserByIndex(int index) throws UserNotFoundException;

    int getUsersAmount();
}
