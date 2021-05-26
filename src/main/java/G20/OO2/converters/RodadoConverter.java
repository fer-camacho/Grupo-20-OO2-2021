package G20.OO2.converters;

import org.springframework.stereotype.Component;

import G20.OO2.entities.Rodado;
import G20.OO2.models.RodadoModel;

@Component("rodadoConverter")
public class RodadoConverter {
	
	public RodadoModel entityToModel(Rodado entity) {
		return new RodadoModel(entity.getIdRodado(), entity.getDominio(), entity.getVehiculo());
	}
	
	public Rodado modelToEntity(RodadoModel model) {
		return new Rodado(model.getIdRodado(), model.getDominio(), model.getVehiculo());
	}
}
