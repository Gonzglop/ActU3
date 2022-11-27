package iesbelen.dam.ad.maven.mijpamaven;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity

public class Asignaturas {
	
	@Id
	@OneToOne
	private int cod;
	
	@Column(table = "Asignaturas")
	private String nombre;

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

/* Rellenar Datos 
INSERT IGNORE INTO ASIGNATURAS VALUES (1,'Prog. Leng. Estr.');
INSERT IGNORE INTO ASIGNATURAS VALUES (2,'Sist. Informáticos');
INSERT IGNORE INTO ASIGNATURAS VALUES (3,'Análisis');
INSERT IGNORE INTO ASIGNATURAS VALUES (4,'FOL');
INSERT IGNORE INTO ASIGNATURAS VALUES (5,'RET');
*/


