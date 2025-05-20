package pbo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "nim", nullable = false)
    private String nim;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "prodi", nullable = false)
    private String programStudi;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "enrollments",
        joinColumns = @JoinColumn(name = "student_nim"),
        inverseJoinColumns = @JoinColumn(name = "course_code")
    )
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(String nim, String nama, String programStudi) {
        this.nim = nim;
        this.nama = nama;
        this.programStudi = programStudi;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(String programStudi) {
        this.programStudi = programStudi;
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
        return nim + "|" + nama + "|" + programStudi;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return nim.equals(other.nim);
    }

    @Override
    public int hashCode() {
        return nim.hashCode();
    }
}
