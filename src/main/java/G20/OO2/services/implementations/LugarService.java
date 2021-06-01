package G20.OO2.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.LugarConverter;
import G20.OO2.entities.Lugar;
import G20.OO2.entities.Rodado;
import G20.OO2.models.LugarModel;
import G20.OO2.repositories.ILugarRepository;
import G20.OO2.services.ILugarService;

@Service("lugarService")
public class LugarService implements ILugarService{

	@Autowired
	@Qualifier("lugarRepository")
	private ILugarRepository lugarRepository;
	
	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	@Override
	public List<LugarModel> getAll() {
		List<LugarModel> lugares = new ArrayList<>();
		for (Lugar l: lugarRepository.findAll()) {
			lugares.add(lugarConverter.entityToModel(l));
		}
		return lugares;
	}
	
	@Override
	public List<Lugar> getAll2() {
		return lugarRepository.findAll();
	}
}