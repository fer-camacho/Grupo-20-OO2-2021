package G20.OO2.services;

import java.util.List;

import G20.OO2.entities.UserRole;
import G20.OO2.models.UserRoleModel;

public interface IUserRoleService {
	public List<UserRoleModel> getAll();
	
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel);
	
	public UserRole insertOrUpdate(UserRole role);
}
