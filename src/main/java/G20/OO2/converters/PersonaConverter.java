package G20.OO2.converters;

import org.springframework.stereotype.Component;

import G20.OO2.entities.Persona;
import G20.OO2.models.PersonaModel;

@Component("personaConverter")
public class PersonaConverter {
	public PersonaModel entityToModel(Persona entity) {
		return new PersonaModel(entity.getId(), entity.getNombre(), entity.getApellido(), entity.getTipo(), entity.getNroDocumento(), entity.getEmail());
	}
	
	public Persona modelToEntity(PersonaModel model) {
		return new Persona(model.getId(), model.getNombre(), model.getApellido(), model.getTipo(), model.getNroDocumento(), model.getEmail());
	}
}
