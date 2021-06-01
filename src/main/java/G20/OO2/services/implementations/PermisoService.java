package G20.OO2.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.services.IPermisoService;
import G20.OO2.converters.PermisoConverter;
import G20.OO2.entities.Permiso;
import G20.OO2.models.PermisoModel;
import G20.OO2.models.PermisoPeriodoModel;
import G20.OO2.repositories.IPermisoRepository;

@Service("permisoService")
public class PermisoService implements IPermisoService{

	@Autowired
	@Qualifier("permisoRepository")
	private IPermisoRepository permisoRepository;	
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	public PermisoModel findPermisoByRodado(int idRodado) {
		return permisoConverter.entityToModel(permisoRepository.findByRodadoDominio(idRodado));
	}

}