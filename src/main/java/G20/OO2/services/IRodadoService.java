package G20.OO2.services;

import java.util.List;

import G20.OO2.entities.Rodado;
import G20.OO2.models.RodadoModel;
import G20.OO2.models.UserModel;

public interface IRodadoService {
	
	public List<RodadoModel> getAll();
	
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel);
	
	public int cantidad(String dominio);
	
	public List<Rodado> getAll2();
	
}
