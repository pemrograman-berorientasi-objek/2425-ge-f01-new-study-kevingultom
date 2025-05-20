package pbo.model;
//12S23001 - Kevin Gultom
//12S23010 - Tiffany Butar-butar

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "nim", nullable = false)
    private String nim;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "prodi", nullable = false)
    private String prodi;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "enrollments",
        joinColumns = @JoinColumn(name = "student_nim"),
        inverseJoinColumns = @JoinColumn(name = "course_code")
    )
    private List<Course> courses = new ArrayList<>();

    public Student(String nim, String nama, String prodi) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
    }

    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getProdi() { return prodi; }
    public void setProdi(String prodi) { this.prodi = prodi; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    public boolean enrollCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return nim + "|" + nama + "|" + prodi;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student student = (Student) obj;
        return nim.equals(student.nim);
    }

    @Override
    public int hashCode() {
        return nim.hashCode();
    }
}
