package G20.OO2.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="permisoPeriodo")
@PrimaryKeyJoinColumn(referencedColumnName ="idPermiso")
public class PermisoPeriodo extends Permiso {
	
	@Column(name="cantDias", nullable = false)
	private int cantDias;
	
	@Column(name="vacaciones", nullable = false)
	private boolean vacaciones;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rodado_id", nullable=false)
	private Rodado rodado;

	public PermisoPeriodo() {
		super();
	}
	
	public PermisoPeriodo(int idPermiso, Persona pedido, LocalDate fecha, Lugar lugarSalida, Lugar lugarLlegada,
			int cantDias, boolean vacaciones, Rodado rodado) {
		super(idPermiso, pedido, fecha, lugarSalida, lugarLlegada);
		this.cantDias = cantDias;
		this.vacaciones = vacaciones;
		this.rodado = rodado;
	}

	public int getCantDias() {
		return cantDias;
	}

	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}

	public boolean isVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(boolean vacaciones) {
		this.vacaciones = vacaciones;
	}

	public Rodado getRodado() {
		return rodado;
	}

	public void setRodado(Rodado rodado) {
		this.rodado = rodado;
	}
	
	
}
