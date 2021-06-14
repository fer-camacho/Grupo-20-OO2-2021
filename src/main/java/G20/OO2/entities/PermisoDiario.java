package G20.OO2.entities;

import java.time.LocalDate;
import java.util.Set;

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

	public PermisoDiario(int idPermiso, Persona pedido, LocalDate fecha, String motivo) {
		super(idPermiso, pedido, fecha);
		this.motivo = motivo;
	}

	/*
	public PermisoDiario(int idPermiso, Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta, String motivo) {
		super(idPermiso, pedido, fecha, desdeHasta);
		this.motivo = motivo;
	}*/

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
