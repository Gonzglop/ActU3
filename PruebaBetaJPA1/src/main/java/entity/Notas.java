package entity;

import jakarta.persistence.*;

@Entity
@IdClass(NotasPK.class)
public class Notas {
    private String dni;
    private int cod;
    private Integer nota;

    public Notas() {

    }

    @Id
    @Column(name = "DNI")
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public Notas(NotasPK nPK) {
        this.dni = nPK.getDni();
        this.cod = nPK.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notas notas = (Notas) o;

        if (cod != notas.cod) return false;
        if (dni != null ? !dni.equals(notas.dni) : notas.dni != null) return false;
        if (nota != null ? !nota.equals(notas.nota) : notas.nota != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dni != null ? dni.hashCode() : 0;
        result = 31 * result + cod;
        result = 31 * result + (nota != null ? nota.hashCode() : 0);
        return result;
    }
}
