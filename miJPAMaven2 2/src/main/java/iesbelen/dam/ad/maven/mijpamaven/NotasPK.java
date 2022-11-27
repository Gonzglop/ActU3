package iesbelen.dam.ad.maven.mijpamaven;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Embeddable
public class NotasPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "dni")
	private Alumnos alumnos;
	
	@OneToOne
	@JoinColumn(name = "cod")
	private Asignaturas asignaturas;
	
	
	public Alumnos getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Alumnos alumnos) {
		this.alumnos = alumnos;
	}

	public Asignaturas getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(Asignaturas asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	public NotasPK(Alumnos alumnos, Asignaturas asignaturas) {
		super();
		this.alumnos = alumnos;
		this.asignaturas = asignaturas;
	}
	public NotasPK() {
		super();
	}



	@Override
	public int hashCode() {
		return Objects.hash(alumnos, asignaturas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotasPK other = (NotasPK) obj;
		return Objects.equals(alumnos, other.alumnos) && Objects.equals(asignaturas, other.asignaturas);
	}


	
	//, foreignKey = @ForeignKey(name="ALUMNO_DNI_FK")
	//, foreignKey = @ForeignKey(name="NOTA_COD_FK")
}