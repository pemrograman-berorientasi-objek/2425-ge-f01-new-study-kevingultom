package pbo.model;
//12S23001 - Kevin Gultom
//12S23010 - Tiffany Butar-butar

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enrollments")
public class Enrollment implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_nim", referencedColumnName = "nim")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "kode")
    private Course course;

    public Enrollment() {}

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Enrollment)) return false;
        Enrollment that = (Enrollment) obj;
        return student.equals(that.student) && course.equals(that.course);
    }

    @Override
    public int hashCode() {
        return 31 * student.hashCode() + course.hashCode();
    }
}
