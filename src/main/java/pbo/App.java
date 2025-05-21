package pbo;

import java.util.*;
import javax.persistence.*;

import pbo.model.*;

public class App {

    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        factory = Persistence.createEntityManagerFactory("study_plan_pu");
        entityManager = factory.createEntityManager();

        // Bersihkan data lama dari Enrollment, Student, dan Course
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Enrollment").executeUpdate();
        entityManager.createQuery("DELETE FROM Student").executeUpdate();
        entityManager.createQuery("DELETE FROM Course").executeUpdate();
        entityManager.getTransaction().commit();

        while (scanner.hasNextLine()) {
            String commandLine = scanner.nextLine().trim();
            if (commandLine.equals("---")) break;

            String[] parts = commandLine.split("#");
            String command = parts[0];

            switch (command) {
                case "student-add": {
                    String nim = parts[1];
                    String nama = parts[2];
                    String prodi = parts[3];

                    entityManager.getTransaction().begin();
                    Student student = entityManager.find(Student.class, nim);
                    if (student == null) {
                        student = new Student(nim, nama, prodi);
                        entityManager.persist(student);
                    }
                    entityManager.getTransaction().commit();
                    break;
                }

                case "course-add": {
                    String kode = parts[1];
                    String namaMK = parts[2];
                    int semester = Integer.parseInt(parts[3]);
                    int kredit = Integer.parseInt(parts[4]);

                    entityManager.getTransaction().begin();
                    Course course = entityManager.find(Course.class, kode);
                    if (course == null) {
                        course = new Course(kode, namaMK, semester, kredit);
                        entityManager.persist(course);
                    }
                    entityManager.getTransaction().commit();
                    break;
                }

                case "enroll": {
                    String nimEnroll = parts[1];
                    String kodeEnroll = parts[2];

                    entityManager.getTransaction().begin();
                    Student s = entityManager.find(Student.class, nimEnroll);
                    Course c = entityManager.find(Course.class, kodeEnroll);

                    if (s != null && c != null) {
                        // Cek apakah sudah pernah enroll
                        String jpql = "SELECT e FROM Enrollment e WHERE e.peserta = :student AND e.matakuliah = :course";
                        List<Enrollment> existingEnrollments = entityManager.createQuery(jpql, Enrollment.class)
                            .setParameter("student", s)
                            .setParameter("course", c)
                            .getResultList();

                        if (existingEnrollments.isEmpty()) {
                            Enrollment enrollment = new Enrollment(s, c);
                            entityManager.persist(enrollment);
                        }
                        entityManager.getTransaction().commit();
                    } else {
                        entityManager.getTransaction().rollback();
                    }
                    break;
                }

                case "student-show": {
                    String targetNIM = parts[1];
                    entityManager.getTransaction().begin();
                    Student stu = entityManager.find(Student.class, targetNIM);
                    if (stu != null) {
                        System.out.println(stu);
                        List<Enrollment> enrolled = stu.getEnrollments();
                        enrolled.stream()
                            .map(Enrollment::getCourse)
                            .sorted(Comparator.comparing(Course::getKode))
                            .forEach(System.out::println);
                    }
                    entityManager.getTransaction().commit();
                    break;
                }

                case "student-show-all": {
                    String sqlStudents = "SELECT s FROM Student s ORDER BY s.NIM";
                    List<Student> allStudents = entityManager.createQuery(sqlStudents, Student.class).getResultList();
                    for (Student st : allStudents) {
                        System.out.println(st);
                    }
                    break;
                }

                case "course-show-all": {
                    String sqlCourses = "SELECT c FROM Course c ORDER BY c.semester, c.kode";
                    List<Course> allCourses = entityManager.createQuery(sqlCourses, Course.class).getResultList();
                    for (Course co : allCourses) {
                        System.out.println(co);
                    }
                    break;
                }

                default:
                    System.out.println("Invalid Input!");
            }
        }

        scanner.close();
        entityManager.close();
        factory.close();
    }
}
