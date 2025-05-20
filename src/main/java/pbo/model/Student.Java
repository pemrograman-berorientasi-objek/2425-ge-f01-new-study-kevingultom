package pbo.model;

//12S23001 - Kevin Gultom
//12S23010 - Tiffany Butar-butar

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "nim", nullable = false)
    private String NIM;

    @Column(name = "nama", nullable = false)
    private String Nama;

    @Column(name = "prodi", nullable = false)
    private String Programstudi;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "enrollments",
        joinColumns = @JoinColumn(name = "student_nim"),
        inverseJoinColumns = @JoinColumn(name = "course_code")
    )
    private List<Course> courses = new ArrayList<>();

    public Student(String NIM, String Nama, String Programstudi) {
        this.NIM = NIM;
        this.Nama = Nama;
        this.Programstudi = Programstudi;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getProgramstudi() {
        return Programstudi;
    }

    public void setProgramstudi(String Programstudi) {
        this.Programstudi = Programstudi;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courseList) {
        this.courses = courseList;
    }

    public boolean enrollCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return NIM + "|" + Nama + "|" + Programstudi;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return NIM.equals(other.NIM);
    }

    @Override
    public int hashCode() {
        return NIM.hashCode();
    }
}
    