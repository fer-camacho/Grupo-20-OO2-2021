package G20.OO2.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public abstract class PermisoModel {
	protected int idPermiso;
	protected PersonaModel pedido;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected LocalDate fecha;
	protected LugarModel lugarSalida;
	protected LugarModel lugarLlegada;
	
	public PermisoModel() {
		super();
	}

	public PermisoModel(int idPermiso, PersonaModel pedido, LocalDate fecha, LugarModel lugarSalida,
			LugarModel lugarLlegada) {
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

	public PersonaModel getPedido() {
		return pedido;
	}

	public void setPedido(PersonaModel pedido) {
		this.pedido = pedido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LugarModel getLugarSalida() {
		return lugarSalida;
	}

	public void setLugarSalida(LugarModel lugarSalida) {
		this.lugarSalida = lugarSalida;
	}

	public LugarModel getLugarLlegada() {
		return lugarLlegada;
	}

	public void setLugarLlegada(LugarModel lugarLlegada) {
		this.lugarLlegada = lugarLlegada;
	}
}
