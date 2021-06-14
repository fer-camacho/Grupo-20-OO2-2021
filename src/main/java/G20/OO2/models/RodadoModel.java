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
		this.setDominio(dominio);
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
		//VALIDAR DOMINIO
		if(validarDominio(dominio)) {
			this.dominio = dominio;
		}
	}
	
	public String getVehiculo() {
		return vehiculo;
	}
	
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	@Override
	public String toString() {
		return "idRodado=" + idRodado + ", dominio=" + dominio + ", vehiculo=" + vehiculo + "";
	}
	
	public boolean validarDominio(String dominio) {
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
