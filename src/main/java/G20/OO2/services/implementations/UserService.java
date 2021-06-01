package G20.OO2.services.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import G20.OO2.entities.UserRole;
import G20.OO2.models.UserModel;
import G20.OO2.repositories.IUserRepository;
import G20.OO2.converters.UserConverter;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		G20.OO2.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return (UserDetails) buildUser(user, buildGrantedAuthorities(user.getUserRole()));
	}
	
	private User buildUser(G20.OO2.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
						true, true, true, 
						grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(UserRole userRole2) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userRole2.getRole()));
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}
	
	@SuppressWarnings("unused")
	private GrantedAuthority buildGrantedAuthority(UserRole userRole2) {
		return new SimpleGrantedAuthority(userRole2.getRole());
	}
	
	public G20.OO2.entities.User save(G20.OO2.entities.User user) {	
		return userRepository.save(user);
	}
	
	public boolean existe(G20.OO2.entities.User user) {
		G20.OO2.entities.User userAux = userRepository.findByUsername(user);
		if ( userAux == null) {
			return false;
		} else {
		  return true;	
		}
	}
	
	public List<UserModel> getAll(){ 
		List<UserModel> usuarios = new ArrayList<>();
		for (G20.OO2.entities.User u: userRepository.findAll()) {
			usuarios.add(userConverter.entityToModel(u));
		}
		return usuarios;
	}
	
	public List<UserModel> findByUserRole(int id) {
		List<UserModel> usuarios = new ArrayList<>();
		for (G20.OO2.entities.User u: userRepository.findByUserRole(id)) {
			usuarios.add(userConverter.entityToModel(u));
		}
		return usuarios;
	}
	
	public String delete(int id) {
		userRepository.deleteById(id);
		return "usuario Eliminado";
	}
	
	public UserModel insertOrUpdate(UserModel userModel) {
		G20.OO2.entities.User user = userRepository.save(userConverter.modelToEntity(userModel));
		return userConverter.entityToModel(user);
	}
	
	public UserModel listarId(int id) {
		G20.OO2.entities.User user = userRepository.findById_(id);
		return userConverter.entityToModel(user);
	}
	
	public int cantidad(String username) {
		return userRepository.repetido(username);
	}
}
	
