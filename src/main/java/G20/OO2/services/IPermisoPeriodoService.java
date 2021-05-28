package G20.OO2.services;

import java.util.List;

import G20.OO2.entities.PermisoPeriodo;
import G20.OO2.models.PermisoPeriodoModel;

public interface IPermisoPeriodoService {
	
	public List<PermisoPeriodoModel> traerPorPersona(int id);
	
	public List<PermisoPeriodoModel> traerPorRodado(int id);
	
}
