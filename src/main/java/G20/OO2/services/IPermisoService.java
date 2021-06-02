package G20.OO2.services;

import java.time.LocalDate;
import java.util.List;

import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.models.PermisoPeriodoModel;

public interface IPermisoService {
	
	public List<PermisoPeriodoModel> findPermisoByRodado(int idRodado);
	
	public List<PermisoPeriodoModel> findPermisoByLugaryFechas(String lugar, LocalDate fechaDesde, LocalDate fechaHasta);

	public List<PermisoDiarioModel> findPermisoDiarioByLugaryFechas(String lugar, LocalDate fechaDesde, LocalDate fechaHasta);

}
