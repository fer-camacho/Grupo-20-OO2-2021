package G20.OO2.services.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.PermisoConverter;
import G20.OO2.entities.PermisoDiario;
import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.repositories.IPermisoDiarioRepository;
import G20.OO2.repositories.IPermisoRepository;
import G20.OO2.services.IPermisoDiarioService;

@Service("permisoDiarioService")
public class PermisoDiarioService implements IPermisoDiarioService {
	
	@Autowired
	@Qualifier("permisoRepository")
	private IPermisoRepository permisoRepository;
	
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
	
	public PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel) {
		PermisoDiario permiso = permisoRepository.save(permisoConverter.modelToEntity(permisoDiarioModel));
		return permisoConverter.entityToModel(permiso);
	}
	
	public List<PermisoDiarioModel> traerPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		List<PermisoDiarioModel> permisos = new ArrayList<>();
		for (PermisoDiario p: permisoDiarioRepository.traerPorFecha(fechaInicio, fechaFin)) {
			permisos.add(permisoConverter.entityToModel(p));
		}
		return permisos;
	}
	
	public List<PermisoDiarioModel> traerPorFechaYLugar(LocalDate fechaInicio, LocalDate fechaFin, String lugar) {
		List<PermisoDiarioModel> permisos = new ArrayList<>();
		for (PermisoDiarioModel p: traerPorFecha(fechaInicio, fechaFin)) {
			if ((p.getLugarSalida().getLugar().equals(lugar)) || (p.getLugarLlegada().getLugar().equals(lugar))) {
				permisos.add(p);
			}
		}
		return permisos;
	}
}
