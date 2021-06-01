package G20.OO2.models;

import java.time.LocalDate;

public class PermisoDiarioModel extends PermisoModel {
	private String motivo;

	public PermisoDiarioModel() {
		super();
	}

	public PermisoDiarioModel(int idPermiso, PersonaModel pedido, LocalDate fecha, LugarModel lugarSalida,
			LugarModel lugarLlegada, String motivo) {
		super(idPermiso, pedido, fecha, lugarSalida, lugarLlegada);
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	};
	
	
}
