package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public class NotasPK implements Serializable {
    private int idAlumno;
    private int cod;

    @Column(name = "id_alumno")
    @Id
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Column(name = "COD")
    @Id
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotasPK notasPK = (NotasPK) o;

        if (idAlumno != notasPK.idAlumno) return false;
        if (cod != notasPK.cod) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAlumno;
        result = 31 * result + cod;
        return result;
    }
}
