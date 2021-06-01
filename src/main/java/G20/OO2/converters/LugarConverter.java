package G20.OO2.converters;

import org.springframework.stereotype.Component;

import G20.OO2.entities.Lugar;
import G20.OO2.models.LugarModel;

@Component("lugarConverter")
public class LugarConverter {
	
	public LugarModel entityToModel(Lugar entity) {
		return new LugarModel(entity.getIdLugar(), entity.getLugar(), entity.getCodigoPostal());
	}
	
	public Lugar modelToEntity(LugarModel model) {
		return new Lugar(model.getIdLugar(), model.getLugar(), model.getCodigoPostal());
	}
}
