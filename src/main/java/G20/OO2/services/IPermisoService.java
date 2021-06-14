package G20.OO2.services;

import java.time.LocalDate;
import java.util.List;

import G20.OO2.entities.Permiso;
import G20.OO2.entities.PermisoDiario;
import G20.OO2.entities.PermisoPeriodo;
import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.models.PermisoPeriodoModel;

public interface IPermisoService {
	
	public List<PermisoPeriodoModel> findPeriodoByPersona(long nroDocumento);
	
	public List<PermisoDiarioModel> findDiarioByPersona(long nroDocumento);
	
	public List<PermisoPeriodoModel> findPermisoByRodado(int idRodado);
	
	public List<PermisoPeriodoModel> findPermisoByLugaryFechas(int idLugar, LocalDate fechaDesde, LocalDate fechaHasta);

	public List<PermisoDiarioModel> findPermisoDiarioByLugaryFechas(int idLugar, LocalDate fechaDesde, LocalDate fechaHasta);
	
	public PermisoDiarioModel insertOrUpdate(PermisoDiario permisoDiario);

	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodo permisoPeriodo);
	
	public List<Permiso> getAll();

	public Permiso findById(int id);
}
