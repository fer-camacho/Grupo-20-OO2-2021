package G20.OO2.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G20.OO2.entities.Permiso;
import G20.OO2.entities.PermisoDiario;
import G20.OO2.entities.PermisoPeriodo;

@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permiso, Serializable> {

	// Traer Permiso por Rodado (se efecturá la busca en base al dominio del mismo)
	@Query("SELECT p FROM Permiso p JOIN FETCH p.rodado r WHERE r.idRodado = (:idRodado)")
	public abstract List<PermisoPeriodo> findByRodado(int idRodado);
	
	@Query(value="select * from permiso \r\n"
			+ "join permiso_periodo on permiso_periodo.id_permiso = permiso.id_permiso\r\n"
			+ "join permiso_lugar on permiso_lugar.permiso_id = permiso.id_permiso\r\n"
			+ "join lugar on lugar.id_lugar = permiso_lugar.lugar_id\r\n"
			+ "where lugar.id_lugar = (:idLugar)"
			+ "and permiso.fecha between (:fechaDesde) and (:fechaHasta)",
			nativeQuery = true)
	public abstract List<PermisoPeriodo> findByLugaryFechas(int idLugar, LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query(value="select * from permiso \r\n"
			+ "join permiso_diario on permiso_diario.id_permiso = permiso.id_permiso\r\n"
			+ "join permiso_lugar on permiso_lugar.permiso_id = permiso.id_permiso\r\n"
			+ "join lugar on lugar.id_lugar = permiso_lugar.lugar_id\r\n"
			+ "where lugar.id_lugar = (:idLugar)"
			+ "and permiso.fecha between (:fechaDesde) and (:fechaHasta)",
			nativeQuery = true)
	public abstract List<PermisoDiario> findDiarioByLugaryFechas(int idLugar, LocalDate fechaDesde, LocalDate fechaHasta);
		
	// Traer Permiso por Persona (se efecturá la busca en base a su nroDocumento)
	//@Query("SELECT p FROM Permiso p JOIN FETCH p.pedido pers WHERE pers.nroDocumento = (:nroDocumento)")
	@Query(value="select * from permiso \r\n"
			+ "join permiso_periodo on permiso_periodo.id_permiso = permiso.id_permiso\r\n"
			+ "join persona on persona.id = permiso.pedido_id\r\n"
			+ "where persona.nro_documento = (:nroDocumento)",
			nativeQuery = true)
	public abstract List<PermisoPeriodo> findPeriodoByPersona(long nroDocumento);
	
	
	@Query(value="select * from permiso \r\n"
			+ "join permiso_diario on permiso_diario.id_permiso = permiso.id_permiso\r\n"
			+ "join persona on persona.id = permiso.pedido_id\r\n"
			+ "where persona.nro_documento = (:nroDocumento)",
			nativeQuery = true)
	//@Query("SELECT p FROM Permiso p JOIN FETCH p.pedido pers WHERE pers.nroDocumento = (:nroDocumento)")
	public abstract List<PermisoDiario> findDiarioByPersona(long nroDocumento);
	
	@Query("SELECT p FROM Permiso p WHERE p.idPermiso = (:id)")
	public abstract Permiso findById(int id);
}
