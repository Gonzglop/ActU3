package entity;

import java.io.Serializable;
import java.util.Objects;

public class VentaId implements Serializable {

    private int comprador;
    private int articulo;

    public VentaId() {
    }

    public int getComprador() {
        return comprador;
    }

    public void setComprador(int comprador) {
        this.comprador = comprador;
    }

    public int getArticulo() {
        return articulo;
    }

    public void setArticulo(int articulo) {
        this.articulo = articulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VentaId ventaId = (VentaId) o;
        return comprador == ventaId.comprador && articulo == ventaId.articulo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comprador, articulo);
    }
}
