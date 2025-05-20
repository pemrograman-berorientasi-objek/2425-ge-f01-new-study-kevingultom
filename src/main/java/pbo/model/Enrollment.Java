package pbo.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "enrollments")
@IdClass(EnrollmentPK.class)
public class Enrollment implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_nim", referencedColumnName = "nim")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "kode")
    private Course course;

    public Enrollment() {
    }

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Enrollment)) return false;

        Enrollment other = (Enrollment) obj;
        return student.equals(other.student) && course.equals(other.course);
    }

    @Override
    public int hashCode() {
        return 31 * student.hashCode() + course.hashCode();
    }
}
