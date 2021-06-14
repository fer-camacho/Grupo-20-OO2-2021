package G20.OO2.services;

import java.util.List;

import G20.OO2.models.PersonaModel;
import G20.OO2.models.UserModel;

public interface IPersonaService {
	public List<PersonaModel> getAll();
	
	public PersonaModel insertOrUpdate(PersonaModel personaModel);
	
	public int cantidad (long dni);
	
	public PersonaModel findById(int id);

}
