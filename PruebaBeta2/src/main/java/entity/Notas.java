package entity;

import jakarta.persistence.*;

@Entity
@IdClass(NotasPK.class)
public class Notas {
    private int idAlumno;
    private int cod;
    private Integer nota;

    @Id
    @Column(name = "id_alumno")
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Id
    @Column(name = "COD")
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Basic
    @Column(name = "NOTA")
    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notas notas = (Notas) o;

        if (idAlumno != notas.idAlumno) return false;
        if (cod != notas.cod) return false;
        if (nota != null ? !nota.equals(notas.nota) : notas.nota != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAlumno;
        result = 31 * result + cod;
        result = 31 * result + (nota != null ? nota.hashCode() : 0);
        return result;
    }
}
