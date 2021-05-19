package G20.OO2.services;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import G20.OO2.models.UserModel;

public interface IUserService {
    
	public List<UserModel> getAll();
	
	public List<UserModel> findByUserRole(int id);
	
	public String delete(int id);
	
	public UserModel insertOrUpdate(UserModel userModel);
}
