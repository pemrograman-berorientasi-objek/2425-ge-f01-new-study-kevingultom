package pbo;

/**
 *
 * 12S23001 Kevin Gultom
 * 12S23010 Tiffani Butar-butar
 * 
 */

import java.util.*;
import javax.persistence.*;
import pbo.model.*;

public class App {

  private static EntityManagerFactory factory;
  private static EntityManager entityManager;

  public static void main(String[] args) {
    factory = Persistence.createEntityManagerFactory("study_plan_pu");
    entityManager = factory.createEntityManager();
    drivApp.initializeEntityManager();
    drivApp.cleanTableStudent();
    drivApp.cleanTableCourse();
    drivApp.cleanTableEnrollment();

    String str;

    Scanner scanner = new Scanner(System.in);

    while (true) {
      str = scanner.nextLine();

      if (str.equals("---")) {
        break;
      } else {
        String split[] = str.split("#");

        switch (split[0]) {
          case "student-add":
            drivApp.CreateStudent(split[1], split[2], split[3]);
            break;
          case "student-show-all":
            drivApp.ShowStudent();
            break;
          case "course-add":
            drivApp.createCourse(split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]));
            break;
          case "course-show-all":   
            drivApp.ShowCourse();
            break;
          case "enroll":
            drivApp.createEnroll(split[1], split[2]);
            break;
          case "student-show":
            drivApp.ShowStudentDetail(split[1]);
            break;
          default:
            break;
        }
      }

    }

  }
}