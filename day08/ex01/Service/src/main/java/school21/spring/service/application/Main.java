package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.List;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private static void test(UsersRepository ur, String testName) {

        System.out.println(ANSI_YELLOW + "\n" + "---------------------------- " + testName + " ------------------------------" + ANSI_RESET);

        System.out.println(ANSI_GREEN + "findAll()" + ANSI_RESET);
        System.out.println(ur.findAll());

        System.out.println(ANSI_GREEN + "\n" + "---- findById = User{Identifier=2, Email='london92@hotmail.com'} ----" + ANSI_RESET);
        System.out.println(ur.findById(2l));

        System.out.println(ANSI_GREEN + "\n" + "---- findById = 0 ----" + ANSI_RESET);
        System.out.println(ur.findById(0l));

        System.out.println(ANSI_GREEN + "\n" + "---- findByEmail = User{Identifier=3, Email='andrew.romaguera@prosacco.com'} ---" + ANSI_RESET);
        System.out.println(ur.findByEmail("andrew.romaguera@prosacco.com"));

        System.out.println(ANSI_GREEN + "\n" + "---- findByEmail = blablabla ---" + ANSI_RESET);
        System.out.println(ur.findByEmail("blablabla"));

        System.out.println(ANSI_GREEN + "\n" + "---- save(newemail@com) ---" + ANSI_RESET);
        ur.save(new User("newemail@com"));
        System.out.println(ur.findAll());

        System.out.println(ANSI_GREEN + "\n" + "--- save(45, newemail@com)  ----" + ANSI_RESET);
        ur.save(new User(45l, "newemail45@com"));
        System.out.println(ur.findAll());

        System.out.println(ANSI_GREEN + "\n" + "---- update(2, newemail@com) ---" + ANSI_RESET);
        ur.update(new User(2l, "newemail@com"));
        System.out.println(ur.findAll());
    }

    private  static void prepareDataForTests(UsersRepository ur) {
        List <User> listUser = ur.findAll();
        if (listUser != null) {
            for (int i = 0; i < listUser.size(); i++) {
                ur.delete(listUser.get(i).getIdentifier());
            }
        }
        ur.save(new User(1l, "dlindgren@carter.com"));
        ur.save(new User(2l, "london92@hotmail.com"));
        ur.save(new User(3l, "andrew.romaguera@prosacco.com"));
        ur.save(new User(4l, "jennie.wunsch@welch.net"));
        ur.save(new User(5l, "legros.idell@gmail.com"));
        ur.save(new User(6l, "belle54@gmail.com"));
        ur.save(new User(7l, "lcollins@nicolas.com"));
        ur.save(new User(8l, "xfisher@davis.net"));
        ur.save(new User(9l, "sporer.jamar@hotmail.com"));
        ur.save(new User(10l, "evans.langworth@schroeder.biz"));
    }

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        prepareDataForTests(usersRepositoryJdbc);
        test(usersRepositoryJdbc, "TEST usersRepositoryJdbc");

        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        prepareDataForTests(usersRepositoryJdbcTemplate);
        test(usersRepositoryJdbcTemplate, "TEST usersRepositoryJdbcTemplate");
    }
}
