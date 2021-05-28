package G20.OO2.services;

import java.util.List;

import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.models.PermisoModel;

public interface IPermisoService {
	public PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel);
	
	public PermisoDiarioModel listarId(int id);
}
