package G20.OO2.services;

import java.util.List;

import G20.OO2.models.PermisoDiarioModel;

public interface IPermisoDiarioService {
	
	public List<PermisoDiarioModel> traerPorPersona(int id);
}
