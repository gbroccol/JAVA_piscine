package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.repositories.UsersRepository;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll());
    }
}

//public class Main {
//
//    public static final String ANSI_RESET = "\u001B[0m";
//    public static final String ANSI_BLACK = "\u001B[30m";
//    public static final String ANSI_RED = "\u001B[31m";
//    public static final String ANSI_GREEN = "\u001B[32m";
//    public static final String ANSI_YELLOW = "\u001B[33m";
//    public static final String ANSI_BLUE = "\u001B[34m";
//    public static final String ANSI_PURPLE = "\u001B[35m";
//    public static final String ANSI_CYAN = "\u001B[36m";
//    public static final String ANSI_WHITE = "\u001B[37m";

//    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
//        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
//        System.out.println(usersRepository.findAll());

//        System.out.println(ANSI_GREEN + "---- findById = User{Identifier=2, Email='chramova@gmail.com'} ----" + ANSI_RESET);
//        System.out.println(usersRepository.findById(2l));
//
//        System.out.println(ANSI_GREEN + "---- findById = 0 ----" + ANSI_RESET);
//        System.out.println(usersRepository.findById(0l));

//        System.out.println(ANSI_GREEN + "---- findByEmail = User{Identifier=3, Email='ivanov@gmail.com'} ---" + ANSI_RESET);
//        System.out.println(usersRepository.findByEmail("ivanov@gmail.com"));
//
//        System.out.println(ANSI_GREEN + "---- findByEmail = blablabla ---" + ANSI_RESET);
//        System.out.println(usersRepository.findByEmail("blablabla"));

//        System.out.println(ANSI_GREEN + "---- newemail@com ---" + ANSI_RESET);
//        usersRepository.save(new User("newemail@com"));
//        System.out.println(usersRepository.findAll());

//        System.out.println(ANSI_GREEN + "--- check id 45 ----" + ANSI_RESET);
//        usersRepository.save(new User(45l, "newemail45@com"));
//        System.out.println(usersRepository.findAll());
//
//        System.out.println(ANSI_GREEN + "---- newemail@com (id = 2) ---" + ANSI_RESET);
//        usersRepository.update(new User(2l, "newemail@com"));
//        System.out.println(usersRepository.findAll());
//
//        System.out.println(ANSI_GREEN + "--- check id (id = 45) ----" + ANSI_RESET);
//        usersRepository.update(new User(45l, "newemail2@com"));
//        System.out.println(usersRepository.findAll());
//
//        for (long i = 0; i <= 10; i++) {
//            System.out.println(ANSI_GREEN + "--- delete " + i + " ----" + ANSI_RESET);
//            usersRepository.delete(i);
//            System.out.println(usersRepository.findAll());
//        }
//    }
//}