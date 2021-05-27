package G20.OO2.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G20.OO2.entities.PermisoDiario;
import G20.OO2.models.PermisoDiarioModel;

@Component("permisoConverter")
public class PermisoConverter {
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	public PermisoDiarioModel entityToModel(PermisoDiario entity) {
		return new PermisoDiarioModel(entity.getIdPermiso(), personaConverter.entityToModel(entity.getPedido()), entity.getFecha(), entity.getMotivo());
	}
	
	public PermisoDiario modelToEntity(PermisoDiarioModel model) {
		return new PermisoDiario(model.getIdPermiso(), personaConverter.modelToEntity(model.getPedido()), model.getFecha(), model.getMotivo());
	}
}
