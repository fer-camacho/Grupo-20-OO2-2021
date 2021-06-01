package G20.OO2.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import G20.OO2.entities.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {

	@Query("SELECT u FROM User u JOIN FETCH u.userRole WHERE u.username = (:username)")
	public abstract User findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);

	public User save(org.springframework.security.core.userdetails.User user);

	public abstract User findByUsername(User user);	
	
	@Query(nativeQuery=true,value="Select * from user u where u.user_role_id=(:id)")
	public abstract List<User> findByUserRole(int id);
	
	@Query(nativeQuery=true,value="Select * from user u where u.id=(:id)")
	public abstract User findById_(int id);
	
	@Query(nativeQuery=true,value="Select count(*) from user u where u.username=(:username)")
	public User findByUsername(String username);
	
	@Query(nativeQuery=true,value="Select count(*) from user u where u.username=(:username)")
	public int repetido(String username);
	
}
