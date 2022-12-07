import entity.Employee;

import javax.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            TypedQuery<Employee> empByDeptQuery = entityManager.createNamedQuery("Employee.byDept", Employee.class);
            empByDeptQuery.setParameter(1,"Java Advocacy");
            for (Employee employee : empByDeptQuery.getResultList()){
                System.out.println(employee);
            }

            Query countEmpByDept = entityManager.createNativeQuery("select count(*) from employee inner join department d on employee.dept = d.id where d.name=:deptName");
            countEmpByDept.setParameter("deptName","Java Advocacy");
            System.out.println("There are " + countEmpByDept.getSingleResult() + " Java Advocates.");


            Employee rosa = new Employee();
            rosa.setId(10);
            rosa.setFirstName("Rosa");
            rosa.setLastName("Castro");
            entityManager.persist(rosa);

            transaction.commit();
        } finally {
            if(transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
