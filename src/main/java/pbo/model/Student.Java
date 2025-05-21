package pbo.model;

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

    // Ubah ManyToMany jadi OneToMany ke Enrollment
    @OneToMany(mappedBy = "peserta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    public Student() {}

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

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    // Optional helper to add enrollment
    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
        enrollment.setStudent(this);
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
