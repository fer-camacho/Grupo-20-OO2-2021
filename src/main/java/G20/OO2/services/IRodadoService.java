package G20.OO2.services;

import java.util.List;

import G20.OO2.models.RodadoModel;

public interface IRodadoService {
	
	public List<RodadoModel> getAll();
	
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel);
	
	public int cantidad(String dominio);
	
	public boolean dominioValido(String dominio);
	
}
