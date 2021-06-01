package G20.OO2.services.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.services.IPermisoService;
import G20.OO2.converters.PermisoConverter;
import G20.OO2.entities.Permiso;
import G20.OO2.entities.PermisoPeriodo;
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
	
	public List<PermisoPeriodoModel> findPermisoByRodado(int idRodado) {
		List<PermisoPeriodoModel> models = new ArrayList<PermisoPeriodoModel>();
		for (PermisoPeriodo permiso : permisoRepository.findByRodado(idRodado)) {
			models.add(permisoConverter.entityToModel(permiso));
		}
		return models;
	}
	
	public List<PermisoPeriodoModel> findPermisoByLugaryFechas(int idLugar, LocalDate fechaDesde, LocalDate fechaHasta) {
		List<PermisoPeriodoModel> models = new ArrayList<PermisoPeriodoModel>();
		for (PermisoPeriodo permiso : permisoRepository.findByLugaryFechas(idLugar, fechaDesde, fechaHasta)) {
				models.add(permisoConverter.entityToModel(permiso));
		}
		return models;
	}
	
}