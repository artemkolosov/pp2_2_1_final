package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(new User("Иван", "Иванов", "ivanov@mail.ru", new Car ("ВАЗ", 2105)));
      userService.add(new User("Петр", "Петров", "petrov@mail.ru", new Car("BMV", 325)));
      userService.add(new User("Николай", "Николаев", "nikolaev@mail.ru", new Car("Mazda", 3)));
      userService.add(new User("Семен", "Семенов", "semenov@mail.ru", new Car("ВОЛГА", 24)));
      userService.add(new User("Степан", "Степанов", "stepanov@mail.ru", new Car("Mercedes", 160)));




      List<User> users = userService.getListUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println();
      }

      Car carForGetByModelSeries = new Car("ВАЗ", 2105);
      User user = userService.getUserByCar(carForGetByModelSeries.getModel(), carForGetByModelSeries.getSeries());
      System.out.println();

      System.out.println("На машине " + carForGetByModelSeries.getModel() + " " + carForGetByModelSeries.getSeries() + " ездит");
      System.out.println(user.getFirstName() + " " + user.getLastName());

      context.close();
   }
}