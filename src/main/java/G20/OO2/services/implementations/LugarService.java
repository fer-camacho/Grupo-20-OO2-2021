package G20.OO2.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.LugarConverter;
import G20.OO2.entities.Lugar;
import G20.OO2.models.LugarModel;
import G20.OO2.repositories.ILugarRepository;
import G20.OO2.services.ILugarService;

@Service("lugarService")
public class LugarService implements ILugarService{

	@Autowired
	@Qualifier("lugarRepository")
	private ILugarRepository lugarReopository;
	
	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	@Override
	public List<LugarModel> getAll() {
		List<LugarModel> lugares = new ArrayList<>();
		for (Lugar l: lugarReopository.findAll()) {
			lugares.add(lugarConverter.entityToModel(l));
		}
		return lugares;
	}
	
	public LugarModel insertOrUpdate(LugarModel lugarModel) {
		Lugar lugar = lugarReopository.save(lugarConverter.modelToEntity(lugarModel));
		return lugarConverter.entityToModel(lugar);
	}
	
	public LugarModel findById(int id) {
		return lugarConverter.entityToModel(lugarReopository.findById_(id));
	}
	
	public int cantidad(String lugar) {
		return lugarReopository.repetido(lugar);
	}
}
