package entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Alumnos {
    private String dni;
    private String apenom;
    private String direc;
    private String pobla;
    private String telef;

    @Id
    @Column(name = "DNI")
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Basic
    @Column(name = "APENOM")
    public String getApenom() {
        return apenom;
    }

    public void setApenom(String apenom) {
        this.apenom = apenom;
    }

    @Basic
    @Column(name = "DIREC")
    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    @Basic
    @Column(name = "POBLA")
    public String getPobla() {
        return pobla;
    }

    public void setPobla(String pobla) {
        this.pobla = pobla;
    }

    @Basic
    @Column(name = "TELEF")
    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alumnos alumnos = (Alumnos) o;

        if (dni != null ? !dni.equals(alumnos.dni) : alumnos.dni != null) return false;
        if (apenom != null ? !apenom.equals(alumnos.apenom) : alumnos.apenom != null) return false;
        if (direc != null ? !direc.equals(alumnos.direc) : alumnos.direc != null) return false;
        if (pobla != null ? !pobla.equals(alumnos.pobla) : alumnos.pobla != null) return false;
        if (telef != null ? !telef.equals(alumnos.telef) : alumnos.telef != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dni != null ? dni.hashCode() : 0;
        result = 31 * result + (apenom != null ? apenom.hashCode() : 0);
        result = 31 * result + (direc != null ? direc.hashCode() : 0);
        result = 31 * result + (pobla != null ? pobla.hashCode() : 0);
        result = 31 * result + (telef != null ? telef.hashCode() : 0);
        return result;
    }
}
