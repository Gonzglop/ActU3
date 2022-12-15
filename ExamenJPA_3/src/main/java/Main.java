import entity.Articulo;
import entity.Comprador;
import entity.Venta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Main {
    public static void main(String[] args) {

        Comprador comprador = new Comprador("Gonzalo","611460666");
        Articulo articulo = new Articulo("ordenador",550L);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(articulo);
        comprador.addArticulo(articulo,4);
        entityManager.persist(comprador);


        TypedQuery<Venta> ventasQuery = entityManager.createNamedQuery("ventas", Venta.class);
        for (Venta v : ventasQuery.getResultList()){
            System.out.println(v);
        }


        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}

