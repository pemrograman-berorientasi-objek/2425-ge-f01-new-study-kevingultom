    package pbo.model;

    /*
    * 12S23001 Kevin Gultom
    * 12S23010 Tiffani Butar-butar
    */

    import javax.persistence.*;
    import java.util.List;

    public class Executor {
        private final EntityManager em;

        public Executor(EntityManager em) {
            this.em = em;
        }

        public void addStudent(String nim, String name, String program) {
            em.getTransaction().begin();
            if (em.find(Student.class, nim) == null) {
                em.persist(new Student(nim, name, program));
            }
            em.getTransaction().commit();
        }

        public void showAllStudents() {
            List<Student> students = em.createQuery("SELECT s FROM Student s ORDER BY s.nim", Student.class).getResultList();
            for (Student s : students) System.out.println(s);
        }

        public void addCourse(String code, String name, int semester, int credit) {
            em.getTransaction().begin();
            if (em.find(Course.class, code) == null) {
                em.persist(new Course(code, name, semester, credit));
            }
            em.getTransaction().commit();
        }

        public void showAllCourses() {
            List<Course> courses = em.createQuery("SELECT c FROM Course c ORDER BY c.semester, c.code", Course.class).getResultList();
            for (Course c : courses) System.out.println(c);
        }

        public void enrollStudent(String nim, String code) {
            em.getTransaction().begin();
            Student student = em.find(Student.class, nim);
            Course course = em.find(Course.class, code);
            if (student != null && course != null) {
                TypedQuery<Enrollment> query = em.createQuery(
                    "SELECT e FROM Enrollment e WHERE e.student.nim = :nim AND e.course.code = :code", Enrollment.class);
                query.setParameter("nim", nim);
                query.setParameter("code", code);
                if (query.getResultList().isEmpty()) {
                    em.persist(new Enrollment(student, course));
                }
            }
            em.getTransaction().commit();
        }

        public void showStudentDetail(String nim) {
            Student student = em.find(Student.class, nim);
            if (student != null) {
                System.out.println(student);
                List<Enrollment> enrollments = em.createQuery(
                    "SELECT e FROM Enrollment e WHERE e.student.nim = :nim ORDER BY e.course.semester, e.course.code", Enrollment.class)
                    .setParameter("nim", nim).getResultList();
                for (Enrollment e : enrollments) {
                    System.out.println(e.getCourse());
                }
            }
        }
    }
