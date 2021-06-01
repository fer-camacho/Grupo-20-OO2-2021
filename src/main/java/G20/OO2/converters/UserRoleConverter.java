package G20.OO2.converters;

import org.springframework.stereotype.Component;

import G20.OO2.entities.UserRole;
import G20.OO2.models.UserRoleModel;

@Component("userRoleConverter")
public class UserRoleConverter {
	
	public UserRoleModel entityToModel(UserRole entity) {
		return new UserRoleModel(entity.getId(), entity.getRole(), entity.isEnabled());
	}
	
	public UserRole modelToEntity(UserRoleModel model) {
		return new UserRole(model.getId(), model.getRole(), model.isEnabled());
	}
}
