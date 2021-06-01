package G20.OO2.models;

import java.time.LocalDate;

public class PermisoPeriodoModel extends PermisoModel {
	private int cantDias;
	private boolean vacaciones;
	private RodadoModel rodado;
	
	public PermisoPeriodoModel() {
		super();
	}

	public PermisoPeriodoModel(int idPermiso, PersonaModel pedido, LocalDate fecha, LugarModel lugarSalida,
			LugarModel lugarLlegada, int cantDias, boolean vacaciones, RodadoModel rodado) {
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

	public RodadoModel getRodado() {
		return rodado;
	}

	public void setRodado(RodadoModel rodado) {
		this.rodado = rodado;
	}
}
