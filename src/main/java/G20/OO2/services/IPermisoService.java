package G20.OO2.services;

import java.util.List;

import G20.OO2.models.PermisoPeriodoModel;

public interface IPermisoService {
	
	public List<PermisoPeriodoModel> findPermisoByRodado(int idRodado);
	

}
