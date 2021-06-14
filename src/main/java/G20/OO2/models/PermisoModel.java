package G20.OO2.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import G20.OO2.entities.Lugar;

public abstract class PermisoModel {
	protected int idPermiso;
	protected PersonaModel pedido;
	protected LocalDate fecha;
	protected Set<LugarModel> desdeHasta = new HashSet<LugarModel>();
	
	public PermisoModel() {
		super();
	}

	public PermisoModel(int idPermiso, PersonaModel pedido, LocalDate fecha) {
		super();
		this.idPermiso = idPermiso;
		this.pedido = pedido;
		this.fecha = fecha;
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

	@Override
	public String toString() {
		return "PermisoModel [idPermiso=" + idPermiso + ", pedido=" + pedido + ", fecha=" + fecha + ", desdeHasta="
				+ desdeHasta + "]";
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

	public Set<LugarModel> getDesdeHasta() {
		return desdeHasta;
	}
	
}
