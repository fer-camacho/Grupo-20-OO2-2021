package G20.OO2.models;

import java.time.LocalDate;
import java.util.Set;

public class PermisoDiarioModel extends PermisoModel {
	private String motivo;

	public PermisoDiarioModel() {
		super();
	}

	public PermisoDiarioModel(int idPermiso, PersonaModel pedido, LocalDate fecha, String motivo) {
		super(idPermiso, pedido, fecha);
		this.motivo = motivo;
	}

	/*
	public PermisoDiarioModel(int idPermiso, PersonaModel pedido, LocalDate fecha, Set<LugarModel> desdeHasta,
			String motivo) {
		super(idPermiso, pedido, fecha, desdeHasta);
		this.motivo = motivo;
	}*/

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	};
	
	public boolean activo (LocalDate dia){
		return this.getFecha().equals(dia);
	}
	
}
