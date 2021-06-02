package G20.OO2.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.UserRoleConverter;
import G20.OO2.entities.UserRole;
import G20.OO2.models.UserRoleModel;
import G20.OO2.models.UserModel;
import G20.OO2.repositories.IUserRoleRepository;
import G20.OO2.services.IUserRoleService;


@Service("userRoleService")
public class UserRoleService implements  IUserRoleService{

	@Autowired
	@Qualifier("userRoleConverter")
	private UserRoleConverter userRoleConverter;
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;	
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	public List<UserRoleModel> getAll() {
		List<UserRoleModel> roles = new ArrayList<>();
		for (G20.OO2.entities.UserRole r: userRoleRepository.findAll()) {
			roles.add(userRoleConverter.entityToModel(r));
		}
		return roles;
	}
	
	public UserRole insertOrUpdate(UserRole role) {		
		UserRole roleAux = userRoleRepository.save(role);
		return roleAux;
	}
	
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel) {
		UserRole role = userRoleRepository.save(userRoleConverter.modelToEntity(userRoleModel));
		if (!userRoleModel.isEnabled()) {
			for (UserModel u: userService.findByUserRole(userRoleModel.getId())) {
				u.setEnabled(false);
				userService.insertOrUpdate(u);
			}
		}
		return userRoleConverter.entityToModel(role);
	}
	
	public UserRoleModel listarId(int id) {
		UserRole rol = userRoleRepository.findById(id);
		return userRoleConverter.entityToModel(rol);
	}
	
	public UserRoleModel update(UserRoleModel userRoleModel) {
		UserRole role = userRoleConverter.modelToEntity(listarId(userRoleModel.getId()));
		//UserRole role = userRoleRepository.findById(userRoleModel.getId());
		role.setRole(userRoleModel.getRole());
		userRoleRepository.save(role);
		return userRoleConverter.entityToModel(role);
	}
	
	public String delete(int id) {
		userRoleRepository.deleteById(id);
		return "perfil Eliminado";
	}
	
	public int cantidad(String role) {
		return userRoleRepository.repetido(role);
	}
	
	
	
}
