
public class Program {

    public static void main(String[] args) {
        User firstUser = new User("Mark", 0, 2);

        firstUser.setBalance(1500);
        System.out.println("\u001B[33m --- FIRST USER ---\u001B[0m");
        System.out.println("1. NAME    - " + firstUser.getUserName());
        System.out.println("1. BALANCE - " + firstUser.getBalance());
        System.out.println("1. ID      - " + firstUser.getId() + "\n");

        User secondUser = new User("Vasya", 0, 3);

        secondUser.setBalance(2000);
        System.out.println("\u001B[33m --- SECOND USER ---\u001B[0m");
        System.out.println("NAME    - " + secondUser.getUserName());
        System.out.println("BALANCE - " + secondUser.getBalance());
        System.out.println("ID      - " + secondUser.getId() + "\n");
        System.out.println("\u001B[32m --- GOOD CASES ---\u001B[0m");

        Transaction firstTransDeb = new Transaction(firstUser, secondUser, tCategory.DEBIT, 42);

        System.out.println("\u001B[33m --- FIRST TRANSACTION DEBIT ---\u001B[0m");
        System.out.println("ID                - " + firstTransDeb.get_id());
        System.out.println("Recipient         - " + firstTransDeb.get_recipient().getUserName());
        System.out.println("Sender            - " + firstTransDeb.get_sender().getUserName());
        System.out.println("Transfer category - " + firstTransDeb.get_transferCategory());
        System.out.println("Transfer amount   - " + firstTransDeb.get_amount() + "\n");

        Transaction firstTransCred = new Transaction(secondUser, firstUser, tCategory.CREDIT, -42);

        System.out.println("\u001B[33m --- SECOND TRANSACTION CREDIT ---\u001B[0m");
        System.out.println("Recipient         - " + firstTransCred.get_recipient().getUserName());
        System.out.println("Sender            - " + firstTransCred.get_sender().getUserName());
        System.out.println("Transfer category - " + firstTransCred.get_transferCategory());
        System.out.println("Transfer amount   - " + firstTransCred.get_amount() + "\n");

        firstTransDeb.set_transferCategory(tCategory.CREDIT);
        firstTransDeb.set_amount(-21);
        System.out.println("\u001B[33m --- FIRST TRANSACTION DEBIT ---\u001B[0m");
        System.out.println("ID                - " + firstTransDeb.get_id());
        System.out.println("Recipient         - " + firstTransDeb.get_recipient().getUserName());
        System.out.println("Sender            - " + firstTransDeb.get_sender().getUserName());
        System.out.println("Transfer category - " + firstTransDeb.get_transferCategory());
        System.out.println("Transfer amount   - " + firstTransDeb.get_amount() + "\n");
        firstTransCred.set_transferCategory(tCategory.DEBIT);
        firstTransCred.set_amount(21);
        System.out.println("\u001B[33m --- SECOND TRANSACTION CREDIT ---\u001B[0m");
        System.out.println("Recipient         - " + firstTransCred.get_recipient().getUserName());
        System.out.println("Sender            - " + firstTransCred.get_sender().getUserName());
        System.out.println("Transfer category - " + firstTransCred.get_transferCategory());
        System.out.println("Transfer amount   - " + firstTransCred.get_amount() + "\n");
        System.out.println("\u001B[32m --- ERROR CASES ---\u001B[0m");

        User errorUser = new User("Petya", -1, 1);

        errorUser.setBalance(-100);
        System.out.println("NAME    - " + errorUser.getUserName());
        System.out.println("BALANCE - " + errorUser.getBalance());
        System.out.println("ID      - " + errorUser.getId() + "\n");

        Transaction TransErrorTwo = new Transaction(firstUser, secondUser, tCategory.DEBIT, 0);

        Transaction TransErrorThree = new Transaction(firstUser, secondUser, tCategory.CREDIT, 42);

        Transaction TransErrorFour = new Transaction(firstUser, secondUser, tCategory.DEBIT, -42);
    }
}