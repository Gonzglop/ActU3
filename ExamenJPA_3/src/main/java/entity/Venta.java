package entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "ventas",query = "select v from Venta v")
@IdClass(VentaId.class)
public class Venta {

    @Id
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Comprador comprador;

    @Id
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Articulo articulo;

    @Column
    private int unidadesVendidas;


    public Venta() {
    }

    public Venta(Comprador comprador, Articulo articulo) {
        this.comprador = comprador;
        this.articulo = articulo;
    }

    public Venta(Comprador comprador, Articulo articulo, int unidadesVendidas) {
        this.comprador = comprador;
        this.articulo = articulo;
        this.unidadesVendidas = unidadesVendidas;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "comprador=" + comprador +
                ", articulo=" + articulo +
                ", unidadesVendidas=" + unidadesVendidas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return comprador.equals(venta.comprador) && articulo.equals(venta.articulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comprador, articulo);
    }
}
