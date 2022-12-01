import entity.Alumnos;
import entity.Asignaturas;
import entity.Notas;
import entity.NotasPK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            /*

            Alumnos alum4 = new Alumnos();
            alum4.setApenom("Gonzalo López");
            alum4.setDni("25734222M");
            alum4.setDirec("Calle Luis Barahona de Soto");
            alum4.setPobla("Málaga");
            alum4.setTelef("611460666");
            entityManager.persist(alum4);

            Asignaturas asig6 = new Asignaturas();
            asig6.setCod(6);
            asig6.setNombre("Acceso a Datos");
            entityManager.persist(asig6);



             */
            Notas nota7 = new Notas(new NotasPK("25734222M",7));
            nota7.setNota(10);
            //nota7.setDni("25734222M");
            //nota7.setCod(7);

            entityManager.persist(nota7);

            transaction.commit() ;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
