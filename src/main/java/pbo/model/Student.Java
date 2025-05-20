package pbo.model;

/*
 * 12S23001 Kevin Gultom
 * 12S23010 Tiffani Butar-butar
 */

import javax.persistence.*;
import java.util.*;

@Entity
public class Student {
    @Id
    private String nim;

    private String name;
    private String program;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments = new ArrayList<>();

    public Student() {}

    public Student(String nim, String name, String program) {
        this.nim = nim;
        this.name = name;
        this.program = program;
    }

    public String getNim() { return nim; }
    public String getName() { return name; }
    public String getProgram() { return program; }

    @Override
    public String toString() {
        return nim + "|" + name + "|" + program;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}
