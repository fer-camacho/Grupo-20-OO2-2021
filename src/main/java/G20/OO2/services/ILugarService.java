package G20.OO2.services;

import java.util.List;

import G20.OO2.entities.Lugar;
import G20.OO2.models.LugarModel;

public interface ILugarService {
	
	public List<LugarModel> getAll();
	
	public List<Lugar> getAll2();
}
