package G20.OO2.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.PermisoConverter;
import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.repositories.IPermisoRepository;
import G20.OO2.services.IPermisoService;
import G20.OO2.entities.PermisoDiario;

@Service("permisoService")
public class PermisoService implements IPermisoService {
	
	@Autowired
	@Qualifier("permisoRepository")
	private IPermisoRepository permisoRepository;
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	public PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel) {
		PermisoDiario permiso = permisoRepository.save(permisoConverter.modelToEntity(permisoDiarioModel));
		return permisoConverter.entityToModel(permiso);
	}
	
	public PermisoDiarioModel listarId(int id) {
		PermisoDiario p = permisoRepository.findById_(id);
		return permisoConverter.entityToModel(p);
	}
}
