package G20.OO2.services;

import G20.OO2.models.PermisoDiarioModel;

public interface IPermisoService {
	public PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel);
	
	public PermisoDiarioModel listarId(int id);
}
