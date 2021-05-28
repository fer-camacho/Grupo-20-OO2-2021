package G20.OO2.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.PermisoConverter;
import G20.OO2.entities.PermisoDiario;
import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.repositories.IPermisoDiarioRepository;
import G20.OO2.services.IPermisoDiarioService;

@Service("permisoDiarioService")
public class PermisoDiarioService implements IPermisoDiarioService {
	
	@Autowired
	@Qualifier("permisoDiarioRepository")
	private IPermisoDiarioRepository permisoDiarioRepository; 
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
			
			
			
	public List<PermisoDiarioModel> traerPorPersona(int id) {
		List<PermisoDiarioModel> permisos = new ArrayList<>();
		for (PermisoDiario p: permisoDiarioRepository.traerPorPersona(id)) {
			permisos.add(permisoConverter.entityToModel(p));
		}
		return permisos;
	}
}
