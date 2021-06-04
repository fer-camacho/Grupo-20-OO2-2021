package G20.OO2.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.PersonaConverter;
import G20.OO2.entities.Persona;
import G20.OO2.models.PersonaModel;
import G20.OO2.repositories.IPersonaRepository;
import G20.OO2.services.IPersonaService;

@Service("personaService")
public class PersonaService implements IPersonaService {

	@Autowired
	@Qualifier("personaRepository")
	private IPersonaRepository personaRepository;	
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	public List<PersonaModel> getAll() {
		List<PersonaModel> personas = new ArrayList<>();
		for (Persona p: personaRepository.findAll()) {
			personas.add(personaConverter.entityToModel(p));
		}
		return personas;
	}
	
	public PersonaModel insertOrUpdate(PersonaModel personaModel) {
		Persona persona = personaRepository.save(personaConverter.modelToEntity(personaModel));
		return personaConverter.entityToModel(persona);
	}
	
	public int cantidad (long dni) {
		return personaRepository.repetido(dni);
	}
	
	public PersonaModel listarId(int id) {
		Persona p = personaRepository.findById_(id);
		return personaConverter.entityToModel(p);
	}
	
	public String traerEmailPorId(int id) {
		return personaRepository.traerEmailPorId(id);
	}
}
