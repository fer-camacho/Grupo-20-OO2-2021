package G20.OO2.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G20.OO2.entities.Lugar;

@Repository("lugarRepository")
public interface ILugarRepository extends JpaRepository<Lugar, Serializable>{  
	
	@Query(nativeQuery=true,value="Select * from lugar")
	public List<Lugar> traerTodos();
	
}
