package G20.OO2.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G20.OO2.entities.Persona;

@Repository("personaRepository")
public interface IPersonaRepository extends JpaRepository<Persona, Serializable> {
	
	@SuppressWarnings("unchecked")
	public Persona save(Persona persona);
	
	@Query(nativeQuery=true,value="Select * from persona")
	public List<Persona> traerTodos();
}
