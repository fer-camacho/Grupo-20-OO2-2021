package G20.OO2.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G20.OO2.converters.RodadoConverter;
import G20.OO2.entities.Rodado;
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
	
	public boolean dominioValido(String dominio) {
		boolean valido = false;
		if (dominio.length()==7) {
			if ((dominio.substring(0, 3).toUpperCase().matches("^[A-Z]{3}")) && (dominio.charAt(3) == ' ') && (dominio.substring(4).toUpperCase().matches("^[0-9]{3}"))) {
				valido = true;
			}
		} else if (dominio.length()==9) {
			if ((dominio.substring(0, 2).toUpperCase().matches("^[A-Z]{2}")) && (dominio.charAt(2) == ' ') && (dominio.substring(3, 6).matches("^[0-9]{3}")) && (dominio.charAt(6) == ' ') && (dominio.substring(7).toUpperCase().matches("^[A-Z]{2}"))) {
				valido = true;
			}
		}
		return valido;
	}
}
