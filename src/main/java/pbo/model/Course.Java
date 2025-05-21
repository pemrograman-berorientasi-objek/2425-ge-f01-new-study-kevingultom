package pbo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "kode", nullable = false)
    private String kode;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "semester", nullable = false)
    private int semester;

    @Column(name = "kredit", nullable = false)
    private int kredit;

    @OneToMany(mappedBy = "matakuliah", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    public Course() {}

    public Course(String kode, String nama, int semester, int kredit) {
        this.kode = kode;
        this.nama = nama;
        this.semester = semester;
        this.kredit = kredit;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    // Optional helper
    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
        enrollment.setCourse(this);
    }

    @Override
    public String toString() {
        return kode + "|" + nama + "|" + semester + "|" + kredit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course)) return false;
        Course other = (Course) obj;
        return kode.equals(other.kode);
    }

    @Override
    public int hashCode() {
        return kode.hashCode();
    }
}
