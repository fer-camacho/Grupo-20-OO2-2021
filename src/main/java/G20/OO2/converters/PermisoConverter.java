package G20.OO2.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G20.OO2.entities.Permiso;
import G20.OO2.entities.PermisoDiario;
import G20.OO2.entities.PermisoPeriodo;
import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.models.PermisoModel;
import G20.OO2.models.PermisoPeriodoModel;

@Component("permisoConverter")
public class PermisoConverter {

	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	//PERMISO DIARIO
	public PermisoDiarioModel entityToModel(PermisoDiario entity) {
		return new PermisoDiarioModel(entity.getIdPermiso(), personaConverter.entityToModel(entity.getPedido()), entity.getFecha(), entity.getMotivo());
	}
	
	public PermisoDiario modelToEntity(PermisoDiarioModel model) {
		return new PermisoDiario(model.getIdPermiso(), personaConverter.modelToEntity(model.getPedido()), model.getFecha(), model.getMotivo());
	}
	
	//PERMISO PERIODO
	public PermisoPeriodoModel entityToModel(PermisoPeriodo entity) {
		return new PermisoPeriodoModel(entity.getIdPermiso(), personaConverter.entityToModel(entity.getPedido()), entity.getFecha(), entity.getCantDias(), entity.isVacaciones(), rodadoConverter.entityToModel(entity.getRodado()));
	}
	
	public PermisoPeriodo modelToEntity(PermisoPeriodoModel model) {
		return new PermisoPeriodo(model.getIdPermiso(), personaConverter.modelToEntity(model.getPedido()), model.getFecha(), model.getCantDias(), model.isVacaciones(), rodadoConverter.modelToEntity(model.getRodado()));
	}
	
}
