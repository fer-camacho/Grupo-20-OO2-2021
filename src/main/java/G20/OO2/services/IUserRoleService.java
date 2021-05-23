package G20.OO2.services;

import java.util.List;

import G20.OO2.entities.UserRole;
import G20.OO2.models.UserRoleModel;

public interface IUserRoleService {
	public List<UserRoleModel> getAll();
	
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel);
	
	public UserRole insertOrUpdate(UserRole role);
	
	public UserRoleModel listarId(int id);
	
	public UserRoleModel update(UserRoleModel userRoleModel);
	
	public String delete(int id);

	public int cantidad(String role);

}
