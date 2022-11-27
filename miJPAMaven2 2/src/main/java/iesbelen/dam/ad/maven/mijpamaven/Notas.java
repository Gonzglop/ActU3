package iesbelen.dam.ad.maven.mijpamaven;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;



@Entity
@Table(name = "notas")
public class Notas {
	
	@EmbeddedId
	private NotasPK notasPk;
	
	@Column
	private int nota;

	public NotasPK getNotasPk() {
		return notasPk;
	}

	public void setNotasPk(NotasPK notasPk) {
		this.notasPk = notasPk;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Notas() {
	}
	
	
	

}

/* 
 * Rellenar Datos 
INSERT IGNORE INTO NOTAS VALUES('12344345', 1,6);
INSERT IGNORE INTO NOTAS VALUES('12344345', 2,5);

INSERT IGNORE INTO NOTAS VALUES('4448242', 4,6);
INSERT IGNORE INTO NOTAS VALUES('4448242', 5,8);

INSERT IGNORE INTO NOTAS VALUES('56882942', 1,8);
INSERT IGNORE INTO NOTAS VALUES('56882942', 3,7);
*/


