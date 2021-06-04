package G20.OO2.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G20.OO2.entities.User;
import G20.OO2.models.UserModel;

@Component("userConverter")
public class UserConverter {
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	@Autowired
	@Qualifier("userRoleConverter")
	private UserRoleConverter userRoleConverter;
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	public UserModel entityToModel(User entity) {
		return new UserModel(entity.getId(), entity.getUsername(), entity.getPassword(), entity.isEnabled(), userRoleConverter.entityToModel(entity.getUserRole()), personaConverter.entityToModel(entity.getPersona()));
	}
	
	public User modelToEntity(UserModel model) {
		return new User(model.getId(), model.getUsername(), model.getPassword(), model.isEnabled(), userRoleConverter.modelToEntity(model.getUserRole()), personaConverter.modelToEntity(model.getPersona()));
	}
}
