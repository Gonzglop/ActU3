package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public class NotasPK implements Serializable {
    private String dni;
    private int cod;

    @Column(name = "DNI")
    @Id
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Column(name = "COD")
    @Id
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public NotasPK(String dni, int cod) {
        this.dni = dni;
        this.cod = cod;
    }

    public NotasPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotasPK notasPK = (NotasPK) o;

        if (cod != notasPK.cod) return false;
        if (dni != null ? !dni.equals(notasPK.dni) : notasPK.dni != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dni != null ? dni.hashCode() : 0;
        result = 31 * result + cod;
        return result;
    }
}
