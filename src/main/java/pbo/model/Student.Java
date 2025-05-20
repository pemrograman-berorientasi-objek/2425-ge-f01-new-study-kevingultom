package pbo.model;

public class Student {
    private String NIM;
    private String Nama;
    private String Programstudi;

    public Student(String NIM, String Nama, String Programstudi) {
        this.NIM = NIM;
        this.Nama = Nama;
        this.Programstudi = Programstudi;
    }

    public String getNIM() {
        return NIM;
    }

    public String getNama() {
        return Nama;
    }

    public String getProgramstudi() {
        return Programstudi;
    }
}
