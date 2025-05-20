package pbo;

//12S23001 - Kevin Gultom
//12S23010 - Tiffany Butar-butar
import java.util.*;

import pbo.model.Course;
import pbo.model.Enrollment;
import pbo.model.Student;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Student> students = new TreeMap<>();
        Map<String, Course> courses = new TreeMap<>();
        List<Enrollment> enrollments = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) break;

            String[] parts = line.split("#");
            String command = parts[0];

            switch (command) {
                case "student-add":
                    String nim = parts[1];
                    String nama = parts[2];
                    String prodi = parts[3];

                    if (!students.containsKey(nim)) {
                        students.put(nim, new Student(nim, nama, prodi));
                    }
                    break;

                case "student-show-all":
                    for (Student student : students.values()) {
                        System.out.println(student.getNim() + "|" + student.getNama() + "|" + student.getProdi());
                    }
                    break;

                case "course-add":
                    String kode = parts[1];
                    String namaMatkul = parts[2];
                    int semester = Integer.parseInt(parts[3]);
                    int kredit = Integer.parseInt(parts[4]);

                    if (!courses.containsKey(kode)) {
                        courses.put(kode, new Course(kode, namaMatkul, semester, kredit));
                    }
                    break;

                case "course-show-all":
                    for (Course course : courses.values()) {
                        System.out.println(course.getKode() + "|" + course.getNama() + "|" + course.getSemester() + "|" + course.getKredit());
                    }
                    break;

                case "enroll":
                    String enrollNim = parts[1];
                    String enrollKode = parts[2];
                    Student enrollStudent = students.get(enrollNim);
                    Course enrollCourse = courses.get(enrollKode);

                    if (enrollStudent != null && enrollCourse != null) {
                        boolean alreadyEnrolled = false;
                        for (Enrollment e : enrollments) {
                            if (e.getStudent().getNim().equals(enrollNim) &&
                                e.getCourse().getKode().equals(enrollKode)) {
                                alreadyEnrolled = true;
                                break;
                            }
                        }
                        if (!alreadyEnrolled) {
                            enrollments.add(new Enrollment(enrollStudent, enrollCourse));
                        }
                    }
                    break;

                case "student-show":
                    String targetNim = parts[1];
                    Student student = students.get(targetNim);
                    if (student != null) {
                        System.out.println(student.getNim() + "|" + student.getNama() + "|" + student.getProdi());

                        List<Course> studentCourses = new ArrayList<>();
                        for (Enrollment e : enrollments) {
                            if (e.getStudent().getNim().equals(targetNim)) {
                                Course c = e.getCourse();
                                if (c != null) {
                                    studentCourses.add(c);
                                }
                            }
                        }

                        studentCourses.sort(Comparator.comparing(Course::getKode));

                        for (Course c : studentCourses) {
                            System.out.println(c.getKode() + "|" + c.getNama() + "|" + c.getSemester() + "|" + c.getKredit());
                        }
                    }
                    break;

                default:
                    break;
            }
        }
        scanner.close();
    }
}