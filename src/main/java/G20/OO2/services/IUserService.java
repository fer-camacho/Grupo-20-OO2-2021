package G20.OO2.services;

import java.util.List;

import G20.OO2.models.UserModel;
import G20.OO2.models.UserRoleModel;

public interface IUserService {
    
	public List<UserModel> getAll();
	
	public List<UserModel> findByUserRole(int id);
	
	public String delete(int id);
	
	public UserModel insertOrUpdate(UserModel userModel);
	
	public UserModel listarId(int id);
	
	public List<UserRoleModel> findByRole(String role);
	
	public UserModel findByUsername(String username);
	
	public int cantidad(String username);
}
