package G20.OO2.services.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.PermisoConverter;
import G20.OO2.models.PermisoPeriodoModel;
import G20.OO2.repositories.IPermisoPeriodoRepository;
import G20.OO2.repositories.IPermisoRepository;
import G20.OO2.services.IPermisoPeriodoService;
import G20.OO2.entities.PermisoPeriodo;

@Service("permisoPeriodoService")
public class PermisoPeriodoService implements IPermisoPeriodoService {
	
	@Autowired
	@Qualifier("permisoRepository")
	private IPermisoRepository permisoRepository;
	
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
	
	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodoModel permisoPeriodoModel) {
		PermisoPeriodo permiso = permisoRepository.save(permisoConverter.modelToEntity(permisoPeriodoModel));
		return permisoConverter.entityToModel(permiso);
	}
	
	public List<PermisoPeriodoModel> traerPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		List<PermisoPeriodoModel> permisos = new ArrayList<>();
		LocalDate fechaI, fechaF;
		for (PermisoPeriodo p: permisoPeriodoRepository.findAll()) {
			fechaI = p.getFecha();
			fechaF = fechaI.plusDays(p.getCantDias());
			if ((!fechaF.isBefore(fechaInicio))&&(!fechaF.isAfter(fechaFin))) {
				permisos.add(permisoConverter.entityToModel(p));
			} else if ((!fechaI.isBefore(fechaInicio))&&(!fechaI.isAfter(fechaFin))) {
				permisos.add(permisoConverter.entityToModel(p));
			} else if ((!fechaI.isAfter(fechaInicio))&&(!fechaF.isBefore(fechaFin))) {
				permisos.add(permisoConverter.entityToModel(p));
			}
		}
		return permisos;
	}
	
	public List<PermisoPeriodoModel> traerPorFechaYLugar(LocalDate fechaInicio, LocalDate fechaFin, String lugar) {
		List<PermisoPeriodoModel> permisos = new ArrayList<>();
		for (PermisoPeriodoModel p: traerPorFecha(fechaInicio, fechaFin)) {
			if ((p.getLugarSalida().getLugar().equals(lugar)) || (p.getLugarLlegada().getLugar().equals(lugar))){
				permisos.add(p);
			}
		}
		return permisos;
	}
}
