package G20.OO2.services;

import java.time.LocalDate;
import java.util.List;

import G20.OO2.models.PermisoDiarioModel;

public interface IPermisoDiarioService {
	
	public List<PermisoDiarioModel> traerPorPersona(int id);
	
	public PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel);
	
	public List<PermisoDiarioModel> traerPorFecha(LocalDate fechaInicio, LocalDate fechaFin);
	
	public List<PermisoDiarioModel> traerPorFechaYLugar(LocalDate fechaInicio, LocalDate fechaFin, String lugar);
}
