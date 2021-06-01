package G20.OO2.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="permiso")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Permiso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idPermiso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pedido_id", nullable=false)
	protected Persona pedido;
	
	@Column(name="fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected LocalDate fecha;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lugar_salida_id", nullable=false)
	protected Lugar lugarSalida;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lugar_llegada_id", nullable=false)
	protected Lugar lugarLlegada;

	public Permiso() {
		super();
	}

	public Permiso(int idPermiso, Persona pedido, LocalDate fecha, Lugar lugarSalida, Lugar lugarLlegada) {
		super();
		this.idPermiso = idPermiso;
		this.pedido = pedido;
		this.fecha = fecha;
		this.lugarSalida = lugarSalida;
		this.lugarLlegada = lugarLlegada;
	}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Persona getPedido() {
		return pedido;
	}

	public void setPedido(Persona pedido) {
		this.pedido = pedido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Lugar getLugarSalida() {
		return lugarSalida;
	}

	public void setLugarSalida(Lugar lugarSalida) {
		this.lugarSalida = lugarSalida;
	}

	public Lugar getLugarLlegada() {
		return lugarLlegada;
	}

	public void setLugarLlegada(Lugar lugarLlegada) {
		this.lugarLlegada = lugarLlegada;
	}
}
