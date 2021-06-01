package G20.OO2.services;

import java.time.LocalDate;
import java.util.List;

import G20.OO2.models.PermisoPeriodoModel;

public interface IPermisoPeriodoService {
	
	public List<PermisoPeriodoModel> traerPorPersona(int id);
	
	public List<PermisoPeriodoModel> traerPorRodado(int id);
	
	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodoModel permisoPeriodoModel);
	
	public List<PermisoPeriodoModel> traerPorFecha(LocalDate fechaInicio, LocalDate fechaFin);
	
	public List<PermisoPeriodoModel> traerPorFechaYLugar(LocalDate fechaInicio, LocalDate fechaFin, String lugar);
	
}
