
public class Program {

    public static void main(String[] args) {

        User firstUser = new User("Mark", 0);

        User secondUser = new User("Vasya", 0);

        User thirdUser = new User("Kolya", 0);

        System.out.println("ID - " + firstUser.getId());
        System.out.println("ID - " + secondUser.getId());
        System.out.println("ID - " + thirdUser.getId());
    }
}
