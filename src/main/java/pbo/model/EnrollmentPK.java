package pbo.model;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentPK implements Serializable {

    private String student;  
    private String course;   

    public EnrollmentPK() {
    }

    public EnrollmentPK(String student, String course) {
        this.student = student;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrollmentPK)) return false;
        EnrollmentPK that = (EnrollmentPK) o;
        return Objects.equals(student, that.student) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }
}
