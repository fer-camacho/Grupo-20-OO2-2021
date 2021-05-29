package G20.OO2.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G20.OO2.entities.Permiso;
import G20.OO2.entities.PermisoDiario;

@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permiso, Serializable>{
	
	//@Query(nativeQuery=true,value="Select * from permiso p where p.id_permiso=(:id_permiso)")
	//public Permiso findById_(int id_permiso);
	
	@Query("from Permiso p where p.idPermiso=(:id_permiso)")
	public Permiso findById_(int id_permiso);
}
