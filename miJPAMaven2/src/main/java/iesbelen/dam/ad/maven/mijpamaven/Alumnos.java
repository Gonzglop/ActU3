package iesbelen.dam.ad.maven.mijpamaven;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;



@Entity

public class Alumnos {
	
	@Id
	private String dni;
	
	@Column(table = "Alumnos")
	private String apeNom;
	
	@Column
	private String direc;
	
	@Column
	private String pobla;
	
	@Column
	private String telef;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApeNom() {
		return apeNom;
	}

	public void setApeNom(String apeNom) {
		this.apeNom = apeNom;
	}

	public String getDirec() {
		return direc;
	}

	public void setDirec(String direc) {
		this.direc = direc;
	}

	public String getPobla() {
		return pobla;
	}

	public void setPobla(String pobla) {
		this.pobla = pobla;
	}

	public String getTelef() {
		return telef;
	}

	public void setTelef(String telef) {
		this.telef = telef;
	}
	
	

}

/*
INSERT IGNORE INTO ALUMNOS VALUES ('12344345','Alcalde García, Elena', 'C/Las Matas, 24','Madrid','917766545');

INSERT IGNORE INTO ALUMNOS VALUES('4448242','Cerrato Vela, Luis', 'C/Mina 28 - 3A', 'Madrid','916566545');

INSERT  IGNORE INTO ALUMNOS VALUES('56882942','Díaz Fernández, María', 'C/Luis Vives 25', 'Móstoles','915577545');
 */
