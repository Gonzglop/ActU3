package entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Comprador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int idComprador;

    @Column
    private String nombre;

    @Column
    private String telefono;

    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venta> articulos = new ArrayList<>();

    public Comprador() {
    }

    public Comprador(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Venta> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Venta> articulos) {
        this.articulos = articulos;
    }

    @Override
    public String toString() {
        return "Comprador{" +
                "idComprador=" + idComprador +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprador comprador = (Comprador) o;
        return idComprador == comprador.idComprador;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComprador);
    }

    public void addArticulo(Articulo articulo, int unidadesVendidas){
        Venta venta = new Venta(this, articulo, unidadesVendidas);
        articulos.add(venta);
        articulo.getCompradores().add(venta);
    }

    public void removeArticulo(Articulo articulo){
        Venta venta = new Venta(this,articulo);
        articulo.getCompradores().remove(venta);
        articulos.remove(venta);
    }
}
