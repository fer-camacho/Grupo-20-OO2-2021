package G20.OO2.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.PermisoConverter;
import G20.OO2.models.PermisoPeriodoModel;
import G20.OO2.repositories.IPermisoDiarioRepository;
import G20.OO2.repositories.IPermisoPeriodoRepository;
import G20.OO2.services.IPermisoPeriodoService;
import G20.OO2.entities.PermisoPeriodo;

@Service("permisoPeriodoService")
public class PermisoPeriodoService implements IPermisoPeriodoService {
	
	@Autowired
	@Qualifier("permisoPeriodoRepository")
	private IPermisoPeriodoRepository permisoPeriodoRepository; 
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	public List<PermisoPeriodoModel> traerPorPersona(int id) {
		List<PermisoPeriodoModel> permisos = new ArrayList<>();
		for (PermisoPeriodo p: permisoPeriodoRepository.traerPorPersona(id)) {
			permisos.add(permisoConverter.entityToModel(p));
		}
		return permisos;
	}
	
	public List<PermisoPeriodoModel> traerPorRodado(int id) {
		List<PermisoPeriodoModel> permisos = new ArrayList<>();
		for (PermisoPeriodo p: permisoPeriodoRepository.traerPorRodado(id)) {
			permisos.add(permisoConverter.entityToModel(p));
		}
		return permisos;
	}
	
	
}
