package pbo;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pbo.model.Course;
import pbo.model.Enrollment;
import pbo.model.Student;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("study_plan_pu");
        EntityManager em = emf.createEntityManager();

        List<String> commands = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) break;
            commands.add(line);
        }

        em.getTransaction().begin();

        for (String line : commands) {
            String[] parts = line.split("#");
            String command = parts[0];

            switch (command) {
                case "student-add":
                    String NIM = parts[1];
                    String Nama = parts[2];
                    String Programstudi = parts[3];

                    Student existingStudent = em.find(Student.class, NIM);
                    if (existingStudent == null) {
                        Student newStudent = new Student(NIM, Nama, Programstudi);
                        em.persist(newStudent);
                    }
                    break;

                case "course-add":
                    String kode = parts[1];
                    String namaMatkul = parts[2];
                    int semester = Integer.parseInt(parts[3]);
                    int kredit = Integer.parseInt(parts[4]);

                    Course existingCourse = em.find(Course.class, kode);
                    if (existingCourse == null) {
                        Course newCourse = new Course(kode, namaMatkul, semester, kredit);
                        em.persist(newCourse);
                    }
                    break;

                case "enroll":
                    String enrollNIM = parts[1];
                    String enrollKode = parts[2];

                    Student enrollStudent = em.find(Student.class, enrollNIM);
                    Course enrollCourse = em.find(Course.class, enrollKode);

                    if (enrollStudent != null && enrollCourse != null) {
                        boolean alreadyEnrolled = em.createQuery(
                            "SELECT e FROM Enrollment e WHERE e.student.nim = :nim AND e.course.kode = :kode", Enrollment.class)
                            .setParameter("nim", enrollNIM)
                            .setParameter("kode", enrollKode)
                            .getResultList().size() > 0;

                        if (!alreadyEnrolled) {
                            Enrollment newEnrollment = new Enrollment(enrollStudent, enrollCourse);
                            em.persist(newEnrollment);
                        }
                    }
                    break;
            }
        }

        em.getTransaction().commit();

        for (String line : commands) {
            String[] parts = line.split("#");
            String command = parts[0];

            switch (command) {
                case "student-show-all":
                    List<Student> allStudents = em.createQuery("SELECT s FROM Student s ORDER BY s.nim", Student.class).getResultList();
                    for (Student s : allStudents) {
                        System.out.println(s.getNIM() + "|" + s.getNama() + "|" + s.getProgramstudi());
                    }
                    break;

                case "course-show-all":
                    List<Course> allCourses = em.createQuery("SELECT c FROM Course c ORDER BY c.kode", Course.class).getResultList();
                    for (Course c : allCourses) {
                        System.out.println(c.getKode() + "|" + c.getNama() + "|" + c.getSemester() + "|" + c.getKredit());
                    }
                    break;

                case "student-show":
                    String targetNIM = parts[1];
                    Student student = em.find(Student.class, targetNIM);
                    if (student != null) {
                        System.out.println(student.getNIM() + "|" + student.getNama() + "|" + student.getProgramstudi());

                        List<Course> studentCourses = em.createQuery(
                            "SELECT e.course FROM Enrollment e WHERE e.student.nim = :nim ORDER BY e.course.kode", Course.class)
                            .setParameter("nim", targetNIM)
                            .getResultList();

                        for (Course c : studentCourses) {
                            System.out.println(c.getKode() + "|" + c.getNama() + "|" + c.getSemester() + "|" + c.getKredit());
                        }
                    }
                    break;

                default:
                    break;
            }
        }

        em.close();
        emf.close();
        scanner.close();
    }
}
