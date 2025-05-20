package pbo.model;

/*
 * 12S23001 Kevin Gultom
 * 12S23010 Tiffani Butar-butar
 */

import javax.persistence.*;
import java.util.*;

@Entity
public class Course {
    @Id
    private String code;
    private String name;
    private int semester;
    private int credit;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments = new ArrayList<>();

    public Course() {}

    public Course(String code, String name, int semester, int credit) {
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.credit = credit;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getSemester() { return semester; }
    public int getCredit() { return credit; }

    @Override
    public String toString() {
        return code + "|" + name + "|" + semester + "|" + credit;
    }
}
