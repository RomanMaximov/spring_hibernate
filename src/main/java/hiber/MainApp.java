package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User john = new User("John", "Connor", "connor_j@gmail.com");
      User bobby = new User("Bobby", "Smith", "smith_b@gmail.com");
      User jimm = new User("Jimm", "Simmons", "simmons_j@gmail.com");
      User tomas = new User("Tomas", "Brane", "brane_t@gmail.com");

      Car mers = new Car("Mercedes", 500);
      Car bmw = new Car("BMW", 545);
      Car toyota = new Car("Toyota", 200);
      Car audi = new Car("Audi", 8);

      userService.add(john.setCar(mers).setUser(john));
      userService.add(bobby.setCar(bmw).setUser(bobby));
      userService.add(jimm.setCar(toyota).setUser(jimm));
      userService.add(tomas.setCar(audi).setUser(tomas));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Audi", 8));

      context.close();
   }
}
