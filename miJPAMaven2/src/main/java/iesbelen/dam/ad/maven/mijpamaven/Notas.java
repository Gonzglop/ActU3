package iesbelen.dam.ad.maven.mijpamaven;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;



@Entity

public class Notas {
	
	@Id
	private int cod;
	
	@Column
	private String dni;
	
	@Column(table = "Notas")
	private int nota;

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	
}

/* Rellenar Datos 
 * 
INSERT IGNORE INTO NOTAS VALUES('12344345', 1,6);
INSERT IGNORE INTO NOTAS VALUES('12344345', 2,5);

INSERT IGNORE INTO NOTAS VALUES('4448242', 4,6);
INSERT IGNORE INTO NOTAS VALUES('4448242', 5,8);

INSERT IGNORE INTO NOTAS VALUES('56882942', 1,8);
INSERT IGNORE INTO NOTAS VALUES('56882942', 3,7);
*/


