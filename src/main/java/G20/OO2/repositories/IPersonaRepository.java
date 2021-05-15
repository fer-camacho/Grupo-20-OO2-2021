package G20.OO2.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import G20.OO2.entities.Persona;

public interface IPersonaRepository extends JpaRepository<Persona, Serializable> {
	
	@SuppressWarnings("unchecked")
	public Persona save(Persona persona);
}
