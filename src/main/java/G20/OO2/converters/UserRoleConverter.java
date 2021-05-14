package G20.OO2.converters;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G20.OO2.entities.UserRole;
import G20.OO2.models.UserRoleModel;

@Component("userRoleConverter")
public class UserRoleConverter {
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	public UserRoleModel entityToModel(UserRole entity) {
		return new UserRoleModel(entity.getId(), userConverter.entityToModel(entity.getUser()), entity.getRole());
		//int id, UserModel user, String role
	}
	
	public UserRole modelToEntity(UserRoleModel model) {
		return new UserRole(model.getId(), userConverter.modelToEntity(model.getUser()), model.getRole());
	}
}
