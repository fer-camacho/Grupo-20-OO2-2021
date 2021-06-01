package G20.OO2.services;

import java.time.LocalDate;
import java.util.List;

import G20.OO2.models.PermisoModel;
import G20.OO2.models.PermisoPeriodoModel;

public interface IPermisoService {
	
	public List<PermisoPeriodoModel> findPermisoByRodado(int idRodado);
	
	public List<PermisoPeriodoModel> findPermisoByLugaryFechas(int idLugar, LocalDate fechaDesde, LocalDate fechaHasta);
}
