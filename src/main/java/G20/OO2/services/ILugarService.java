package G20.OO2.services;

import java.util.List;

import G20.OO2.models.LugarModel;

public interface ILugarService {
	
	public List<LugarModel> getAll();
	
	public LugarModel insertOrUpdate(LugarModel lugarModel);
	
	public LugarModel findById(int id);
	
	public int cantidad(String lugar);
}
