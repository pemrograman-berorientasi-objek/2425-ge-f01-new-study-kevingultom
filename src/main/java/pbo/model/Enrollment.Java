package pbo.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@IdClass(EnrollmentId.class)
@Table(name = "enrollments")
public class Enrollment implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_nim", referencedColumnName = "nim")
    private Student peserta;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "kode")
    private Course matakuliah;

    public Enrollment() {}

    public Enrollment(Student peserta, Course matakuliah) {
        this.peserta = peserta;
        this.matakuliah = matakuliah;
    }

    public Student getStudent() {
        return peserta;
    }

    public void setStudent(Student peserta) {
        this.peserta = peserta;
    }

    public Course getCourse() {
        return matakuliah;
    }

    public void setCourse(Course matakuliah) {
        this.matakuliah = matakuliah;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Enrollment)) return false;

        Enrollment other = (Enrollment) obj;
        return peserta.equals(other.peserta) && matakuliah.equals(other.matakuliah);
    }

    @Override
    public int hashCode() {
        return 31 * peserta.hashCode() + matakuliah.hashCode();
    }
}
