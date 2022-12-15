package entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int idArticulo;

    @Column
    private String descripcion;

    @Column
    private Long precioVenta;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venta> compradores = new ArrayList<>();

    public Articulo() {
    }

    public Articulo(String descripcion, Long precioVenta) {
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Long precioVenta) {
        this.precioVenta = precioVenta;
    }

    public List<Venta> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Venta> compras) {
        this.compradores = compras;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "idArticulo=" + idArticulo +
                ", descripcion='" + descripcion + '\'' +
                ", precioVenta=" + precioVenta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articulo articulo = (Articulo) o;
        return idArticulo == articulo.idArticulo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticulo);
    }

    public void addComprador(Comprador comprador, int unidadesVendidas){
        Venta venta = new Venta(comprador, this, unidadesVendidas);
        compradores.add(venta);
        comprador.getArticulos().add(venta);
    }

    public void removeComprador(Comprador comprador){
        Venta venta = new Venta(comprador,this);
        comprador.getArticulos().remove(venta);
        compradores.remove(venta);
    }
}
