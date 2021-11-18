public class Program {

    public static void main(String[] args) {

        UsersArrayList allUsers = new UsersArrayList();

        User testUser0 = new User("Mark", 0);

        allUsers.addUser(testUser0);
        System.out.println("Users amount - " + allUsers.getUsersAmount());

        User testUser1 = new User("Liam", 0);

        User testUser2 = new User("Noah", 0);

        User testUser3 = new User("Oliver", 0);

        User testUser4 = new User("Elijah", 0);

        User testUser5 = new User("William", 0);

        User testUser6 = new User("James", 0);

        User testUser7 = new User("Benjamin", 0);

        User testUser8 = new User("Lucas", 0);

        User testUser9 = new User("Alexander", 0);

        User testUser10 = new User("Vasya", 0);

        User testUser11 = new User("Kolya", 0);

        allUsers.addUser(testUser1);
        allUsers.addUser(testUser2);
        allUsers.addUser(testUser3);
        allUsers.addUser(testUser4);
        allUsers.addUser(testUser5);
        allUsers.addUser(testUser6);
        allUsers.addUser(testUser7);
        allUsers.addUser(testUser8);
        allUsers.addUser(testUser9);
        allUsers.addUser(testUser10);
        allUsers.addUser(testUser11);
        System.out.println("Users amount - " + allUsers.getUsersAmount());
        System.out.println("10. NAME - " + allUsers.getUserByIndex(10).getUserName());
        System.out.println("11. NAME - " + allUsers.getUserByIndex(11).getUserName());
        System.out.println("0. NAME - " + allUsers.getUserById(0).getUserName());
        System.out.println("10. NAME - " + allUsers.getUserById(10).getUserName());
        System.out.println("11. NAME - " + allUsers.getUserById(11).getUserName());
    }
}
