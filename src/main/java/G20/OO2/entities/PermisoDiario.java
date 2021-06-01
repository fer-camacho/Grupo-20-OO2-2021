package G20.OO2.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="permisoDiario")
@PrimaryKeyJoinColumn(referencedColumnName ="idPermiso")
public class PermisoDiario extends Permiso {
	
	@Column(name="motivo", nullable = false)
	private String motivo;

	public PermisoDiario() {
		super();
	}

	public PermisoDiario(int idPermiso, Persona pedido, LocalDate fecha, Lugar lugarSalida, Lugar lugarLlegada,
			String motivo) {
		super(idPermiso, pedido, fecha, lugarSalida, lugarLlegada);
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
