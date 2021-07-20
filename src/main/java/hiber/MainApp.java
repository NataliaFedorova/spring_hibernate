package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class, Car.class);

      // добавили 4 пользователя с машинами
      Car volvo = new Car("Volvo", 123);
      Car ford = new Car("Ford", 145);
      Car lada = new Car("Lada",111);
      Car nissan = new Car("Nissan",184);

      User ivanIvanov = new User("Ivan", "Ivanov", "IvanIvanov@gmail.com");
      User petrPetrov = new User("Petr", "Petrov", "PetrPetrov@gmail.com");
      User sidorSidorov = new User ("Sidor", "Sidorov", "SidorSidorov@gmail.com");
      User semenSemenov  = new User("Semen", "Semenov", "SemenSemenov@gmail.com");
      ivanIvanov.setCar(volvo);
      petrPetrov.setCar(ford);
      sidorSidorov.setCar(lada);
      semenSemenov.setCar(nissan);

      userService.add(ivanIvanov);
      userService.add(petrPetrov);
      userService.add(sidorSidorov);
      userService.add(semenSemenov);

      // достали 4 пользователей с машинами из бд

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+ user.getId());
         System.out.println("First Name = "+ user.getFirstName());
         System.out.println("Last Name = "+ user.getLastName());
         System.out.println("Email = "+ user.getEmail());
         System.out.println();
      }

      // получили пользователя по серии и номеру машины

      System.out.println("Получение пользователя по серии и номеру машины: " + userService.getUserByCar("Ford", 145));

      context.close();
   }
}
