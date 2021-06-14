package G20.OO2.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.RodadoConverter;
import G20.OO2.entities.Rodado;
import G20.OO2.models.PersonaModel;
import G20.OO2.models.RodadoModel;
import G20.OO2.repositories.IRodadoRepository;
import G20.OO2.services.IRodadoService;

@Service("rodadoService")
public class RodadoService implements IRodadoService {
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	@Autowired
	@Qualifier("rodadoRepository")
	private IRodadoRepository rodadoRepository;
	
	public List<RodadoModel> getAll() {
		List<RodadoModel> rodados = new ArrayList<>();
		for(G20.OO2.entities.Rodado r: rodadoRepository.findAll()) {
			rodados.add(rodadoConverter.entityToModel(r));
		}
		return rodados;
	}
	
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel) {
		Rodado rodado = rodadoRepository.save(rodadoConverter.modelToEntity(rodadoModel));
		return rodadoConverter.entityToModel(rodado);
	}
	
	public int cantidad(String dominio) {
		return  rodadoRepository.repetido(dominio);
	}
	
	@Override
	public List<Rodado> getAll2() {
		return rodadoRepository.findAll();
	}
	
	public RodadoModel findById(int id) {
		return rodadoConverter.entityToModel(rodadoRepository.findById(id));
	}
}
