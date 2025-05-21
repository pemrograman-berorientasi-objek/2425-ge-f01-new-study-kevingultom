package pbo.model;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentPK implements Serializable {
    private String peserta;    // harus sama nama dan tipe dengan pk di Enrollment
    private String matakuliah;

    public EnrollmentPK() {}

    public EnrollmentPK(String peserta, String matakuliah) {
        this.peserta = peserta;
        this.matakuliah = matakuliah;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrollmentPK)) return false;
        EnrollmentPK that = (EnrollmentPK) o;
        return Objects.equals(peserta, that.peserta) && Objects.equals(matakuliah, that.matakuliah);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peserta, matakuliah);
    }
}
