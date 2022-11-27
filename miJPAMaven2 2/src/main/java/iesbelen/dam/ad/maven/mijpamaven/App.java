package iesbelen.dam.ad.maven.mijpamaven;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      //Configuramos el EMF a través de la unidad de persistencia
      		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miJPAMaven");

      		//Generamos un EntityManager
      		EntityManager em = emf.createEntityManager();

      		//Iniciamos una transacción
      		em.getTransaction().begin();

      		// Construimos un objeto de tipo Alumnos
      		Alumnos alum1 = new Alumnos();
      		alum1.setDni("12344345");
      		alum1.setApeNom("Alcalde García, Elena");
      		alum1.setDirec("C/Las Matas, 24");
      		alum1.setPobla("Madrid");
      		alum1.setTelef("917766545");

      		// Construimos otro objeto de tipo Alumnos
      		Alumnos alum2 = new Alumnos();
      		alum2.setDni("4448242");
      		alum2.setApeNom("Cerrato Vela, Luis");
      		alum2.setDirec("C/Mina 28 - 3A");
      		alum2.setPobla("Madrid");
      		alum2.setTelef("916566545");
      		
      	// Construimos otro objeto de tipo Alumnos
      		Alumnos alum3 = new Alumnos();
      		alum3.setDni("56882942");
      		alum3.setApeNom("Díaz Fernández, María");
      		alum3.setDirec("C/Luis Vives 25");
      		alum3.setPobla("Móstoles");
      		alum3.setTelef("915577545");
      		
      		//Persistimos los objetos
      		em.persist(alum1);
      		em.persist(alum2);
      		em.persist(alum3);
      		
      	// Construimos un objeto de tipo Asignaturas
      		Asignaturas asig1 = new Asignaturas();
      		asig1.setCod(1);
      		asig1.setNombre("Prog. Leng. Estr.");
      		
      	// Construimos un objeto de tipo Asignaturas
      		Asignaturas asig2 = new Asignaturas();
      		asig2.setCod(2);
      		asig2.setNombre("Sist. Informáticos");
      		
      	// Construimos un objeto de tipo Asignaturas
      		Asignaturas asig3 = new Asignaturas();
      		asig3.setCod(3);
      		asig3.setNombre("Análisis");
      		
      	// Construimos un objeto de tipo Asignaturas
      		Asignaturas asig4 = new Asignaturas();
      		asig4.setCod(4);
      		asig4.setNombre("FOL");
      		
      	// Construimos un objeto de tipo Asignaturas
      		Asignaturas asig5 = new Asignaturas();
      		asig5.setCod(5);
      		asig5.setNombre("RET");
      		
      	//Persistimos los objetos
      		em.persist(asig1);
      		em.persist(asig2);
      		em.persist(asig3);
      		em.persist(asig4);
      		em.persist(asig5);
      		
      		
      	 // Construimos un objeto de tipo Notas
      		Notas not1 = new Notas();   		
      		not1.setNotasPk(new NotasPK(alum1,asig1));
      		not1.setNota(6);
      	// Construimos un objeto de tipo Notas
      		Notas not2 = new Notas();
      		not2.setNotasPk(new NotasPK(alum1,asig2));
      		not2.setNota(5);
      	// Construimos un objeto de tipo Notas
      		Notas not3 = new Notas();
      		not3.setNotasPk(new NotasPK(alum2,asig4));
      		not3.setNota(6);
      	// Construimos un objeto de tipo Notas
      		Notas not4 = new Notas();
      		not4.setNotasPk(new NotasPK(alum2,asig5));
      		not4.setNota(8);
      	// Construimos un objeto de tipo Notas
      		Notas not5 = new Notas();
      		not5.setNotasPk(new NotasPK(alum3,asig1));
      		not5.setNota(8);
      	// Construimos un objeto de tipo Notas
      		Notas not6 = new Notas();
      		not6.setNotasPk(new NotasPK(alum3,asig3));
      		not6.setNota(7);
      	
      		
      		//Commiteamos la transacción
      		em.getTransaction().commit();
      		
      		//Cerramos el EntityManager
      		em.close();
        
        
    }
}
