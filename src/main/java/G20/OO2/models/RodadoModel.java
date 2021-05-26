package G20.OO2.models;

public class RodadoModel {
	private int idRodado;
	private String dominio;
	private String vehiculo;
	
	public RodadoModel() {
		super();
	}
	
	public RodadoModel(int idRodado, String dominio, String vehiculo) {
		super();
		this.idRodado = idRodado;
		this.dominio = dominio;
		this.vehiculo = vehiculo;
	}
	
	public int getIdRodado() {
		return idRodado;
	}
	
	public void setIdRodado(int idRodado) {
		this.idRodado = idRodado;
	}
	
	public String getDominio() {
		return dominio;
	}
	
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
	public String getVehiculo() {
		return vehiculo;
	}
	
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	
}
