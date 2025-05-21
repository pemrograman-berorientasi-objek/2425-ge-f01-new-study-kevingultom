package pbo.model;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentId implements Serializable {
    private String peserta;     // nama field harus sama dengan nama field di Enrollment.java
    private String matakuliah;

    public EnrollmentId() {}

    public EnrollmentId(String peserta, String matakuliah) {
        this.peserta = peserta;
        this.matakuliah = matakuliah;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrollmentId)) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(peserta, that.peserta) && Objects.equals(matakuliah, that.matakuliah);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peserta, matakuliah);
    }
}
