package G20.OO2.models;

import java.util.Set;

public class UserModel {
	private int id;
	private String nombre;
	private String apellido;
	private String tipo;
	private long nroDocumento;
	private String username;
	private String password;
	private boolean enabled;
	private Set<UserRoleModel> userRoles;
	
	public UserModel() {
		super();
	}

	public UserModel(int id, String nombre, String apellido, String tipo, long nroDocumento, String username,
			String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo = tipo;
		this.nroDocumento = nroDocumento;
		this.username = username;
		this.password = password;
		this.enabled = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(long nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRoleModel> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoleModel> userRoles) {
		this.userRoles = userRoles;
	}
	
	
	
	
}
