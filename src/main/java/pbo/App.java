package pbo;

/*
 * 12S23001 Kevin Gultom
 * 12S23010 Tiffani Butar-butar
 */

import javax.persistence.*;
import java.util.Scanner;
import pbo.model.Executor;

public class App {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("f01");
    private static final EntityManager em = emf.createEntityManager();
    private static final Executor executor = new Executor(em);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("---")) break;

            if (input.startsWith("student-add#")) {
                String[] tokens = input.split("#");
                executor.addStudent(tokens[1], tokens[2], tokens[3]);
            } else if (input.equals("student-show-all")) {
                executor.showAllStudents();
            } else if (input.startsWith("course-add#")) {
                String[] tokens = input.split("#");
                executor.addCourse(tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
            } else if (input.equals("course-show-all")) {
                executor.showAllCourses();
            } else if (input.startsWith("enroll#")) {
                String[] tokens = input.split("#");
                executor.enrollStudent(tokens[1], tokens[2]);
            } else if (input.startsWith("student-show#")) {
                String nim = input.split("#")[1];
                executor.showStudentDetail(nim);
            }
        }
        em.close();
        emf.close();
    }
}
