package G20.OO2.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G20.OO2.entities.Permiso;
import G20.OO2.entities.PermisoPeriodo;

@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permiso, Serializable> {

	// Traer Permiso por Rodado (se efecturá la busca en base al dominio del mismo)
	@Query("SELECT p FROM Permiso p JOIN FETCH p.rodado r WHERE r.idRodado = (:idRodado)")
	public abstract PermisoPeriodo findByRodadoDominio(int idRodado);
}
