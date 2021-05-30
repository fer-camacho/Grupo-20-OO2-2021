package G20.OO2.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G20.OO2.entities.PermisoDiario;

@Repository("permisoDiarioRepository")
public interface IPermisoDiarioRepository extends JpaRepository<PermisoDiario, Serializable> {
	
	@Query(nativeQuery=true,value="Select p.*, pd.* from permiso p, permiso_diario pd where p.id_permiso=pd.id_permiso and p.pedido_id=(:id)")
	public List<PermisoDiario> traerPorPersona(int id);
	
	
	@Query(nativeQuery=true,value="Select p.*, pd.* from permiso p, permiso_diario pd where p.id_permiso=pd.id_permiso and p.fecha between (:fechaInicio) and (:fechaFin)")
	public List<PermisoDiario> traerPorFecha(LocalDate fechaInicio, LocalDate fechaFin);
}
