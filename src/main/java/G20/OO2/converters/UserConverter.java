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
	
	public UserModel entityToModel(User entity) {
		return new UserModel(entity.getId(), entity.getNombre(), entity.getApellido(), entity.getTipo(), entity.getNroDocumento(), entity.getUsername(), entity.getPassword());
	}
	
	public User modelToEntity(UserModel model) {
		return new User(model.getId(), model.getNombre(), model.getApellido(), model.getTipo(), model.getNroDocumento(), model.getUsername(), model.getPassword(), model.isEnabled());
	}
}
