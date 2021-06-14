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
import G20.OO2.entities.PermisoDiario;
import G20.OO2.entities.PermisoPeriodo;
import G20.OO2.models.PermisoDiarioModel;
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
	
	//TRAER PERMISO POR RODADO
	public List<PermisoPeriodoModel> findPermisoByRodado(int idRodado) {
		List<PermisoPeriodoModel> models = new ArrayList<PermisoPeriodoModel>();
		for (PermisoPeriodo permiso : permisoRepository.findByRodado(idRodado)) {
			models.add(permisoConverter.entityToModel(permiso));
		}
		return models;
	}
	
	//TRAER PERMISO PERIODO POR LUGAR ENTRE FECHAS
	public List<PermisoPeriodoModel> findPermisoByLugaryFechas(int idLugar, LocalDate fechaDesde, LocalDate fechaHasta) {
		List<PermisoPeriodoModel> models = new ArrayList<PermisoPeriodoModel>();
		for (PermisoPeriodo permiso : permisoRepository.findByLugaryFechas(idLugar, fechaDesde, fechaHasta)) {
			if(permiso.activo(fechaHasta) ) {
				models.add(permisoConverter.entityToModel(permiso));
			}
		}
		return models;
	}
	
	//TRAER PERMISO DIARIO POR LUGAR ENTRE FECHAS
	public List<PermisoDiarioModel> findPermisoDiarioByLugaryFechas(int idLugar, LocalDate fechaDesde, LocalDate fechaHasta) {
		List<PermisoDiarioModel> models = new ArrayList<PermisoDiarioModel>();
		for (PermisoDiario permiso : permisoRepository.findDiarioByLugaryFechas(idLugar, fechaDesde, fechaHasta)) {
				models.add(permisoConverter.entityToModel(permiso));
		}
		return models;
	}
	
	//INSERT OR UPDATE PERMISO DIARIO
	public PermisoDiarioModel insertOrUpdate(PermisoDiario permisoDiario) {
		PermisoDiario permiso = permisoRepository.save(permisoDiario);
		return permisoConverter.entityToModel(permiso);
	}
	//INSERT OR UPDATE PERMISO PERIODO
	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodo permisoPeriodo) {
		PermisoPeriodo permiso = permisoRepository.save(permisoPeriodo);
		return permisoConverter.entityToModel(permiso);
	}
	
	//TRAER TODOS LOS PERMISOS
	
	@Override
	public List<Permiso> getAll(){
		return permisoRepository.findAll();
	}	
	
	//TRAER PERMISO PERIODO POR PERSONA
	public List<PermisoPeriodoModel> findPeriodoByPersona(long nroDocumento){
		List<PermisoPeriodoModel> models = new ArrayList<PermisoPeriodoModel>();
		for (PermisoPeriodo permiso : permisoRepository.findPeriodoByPersona(nroDocumento)) {
				models.add(permisoConverter.entityToModel(permiso));
		}
		return models;
	}
	
	//TRAER PERMISO DIARIO POR PERSONA
	public List<PermisoDiarioModel> findDiarioByPersona(long nroDocumento){
		List<PermisoDiarioModel> models = new ArrayList<PermisoDiarioModel>();
		for (PermisoDiario permiso : permisoRepository.findDiarioByPersona(nroDocumento)) {
				models.add(permisoConverter.entityToModel(permiso));
		}
		return models;
	}
	
	public Permiso findById(int id) {
		return permisoRepository.findById(id);
	}


}