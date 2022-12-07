import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        //CREAR
        Employee employee = new Employee();
        employee.setfName("Pepe");
        employee.setlName("López");
        entityManager.persist(employee);


        /*
        //MODIFICAR
        Long id = Long.valueOf(1);
        Employee miObjeto = entityManager.find(Employee.class, id);
        miObjeto.setfName("Gonzalo");
        entityManager.persist(miObjeto);
         */


        /*
        //ELIMINAR
        Long id = 1L;
        Employee miObjeto = entityManager.find(Employee.class, id);
        entityManager.remove(miObjeto);

         */

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
