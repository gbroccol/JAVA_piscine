public class UserIdsGenerator {

    private int globalId = 0;

    private static UserIdsGenerator instance;

    private UserIdsGenerator() {
    }

    public static synchronized UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }

        return instance;
    }

    public int generateId() {
        return globalId++;
    }
}
