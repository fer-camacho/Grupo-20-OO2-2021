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
	
	//@Query("SELECT p FROM Permiso p  WHERE p.desdeHasta = (:idLugar) AND p.fecha between (:fechaDesde) AND (:fechaHasta) ")
	@Query(value="select * from permiso \r\n"
			+ "join permiso_periodo on permiso_periodo.id_permiso = permiso.id_permiso\r\n"
			+ "join permiso_lugar on permiso_lugar.permiso_id = permiso.id_permiso\r\n"
			+ "join lugar on lugar.id_lugar = permiso_lugar.lugar_id\r\n"
			+ "where lugar.lugar = (:lugar)"
			+ "and permiso.fecha between (:fechaDesde) and (:fechaHasta)",
			nativeQuery = true)
	public abstract List<PermisoPeriodo> findByLugaryFechas(String lugar, LocalDate fechaDesde, LocalDate fechaHasta);
	
	
	//@Query("SELECT p FROM Permiso p  WHERE p.desdeHasta = (:idLugar) AND p.fecha between (:fechaDesde) AND (:fechaHasta) ")
		@Query(value="select * from permiso \r\n"
				+ "join permiso_diario on permiso_diario.id_permiso = permiso.id_permiso\r\n"
				+ "join permiso_lugar on permiso_lugar.permiso_id = permiso.id_permiso\r\n"
				+ "join lugar on lugar.id_lugar = permiso_lugar.lugar_id\r\n"
				+ "where lugar.lugar = (:lugar)"
				+ "and permiso.fecha between (:fechaDesde) and (:fechaHasta)",
				nativeQuery = true)
		public abstract List<PermisoDiario> findDiarioByLugaryFechas(String lugar, LocalDate fechaDesde, LocalDate fechaHasta);
}
