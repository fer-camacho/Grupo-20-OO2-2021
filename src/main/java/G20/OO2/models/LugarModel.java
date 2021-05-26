package G20.OO2.models;

import java.util.Set;

public class LugarModel {
	private int idLugar;
	private String lugar;
	private String codigoPostal;
	private Set<PermisoModel> permisos;
	
	public LugarModel() {
		super();
	}
	
	public LugarModel(int idLugar, String lugar, String codigoPostal) {
		super();
		this.idLugar = idLugar;
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
	}
	
	public LugarModel(int idLugar, String lugar, String codigoPostal, Set<PermisoModel> permisos) {
		super();
		this.idLugar = idLugar;
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
		this.permisos = permisos;
	}

	public int getIdLugar() {
		return idLugar;
	}

	public void setIdLugar(int idLugar) {
		this.idLugar = idLugar;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Set<PermisoModel> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<PermisoModel> permisos) {
		this.permisos = permisos;
	}
	
}
