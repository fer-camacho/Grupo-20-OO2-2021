package G20.OO2.models;

import java.util.Set;

public class PersonaModel {
	
	private int id;
	private String nombre;
	private String apellido;
	private String tipo;
	private long nroDocumento;
	private String email;
	private Set<UserModel> users;
	
	public PersonaModel() {
		super();
	}

	public PersonaModel(int id, String nombre, String apellido, String tipo, long nroDocumento, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo = tipo;
		this.nroDocumento = nroDocumento;
		this.email = email;
	}

	public PersonaModel(int id, String nombre, String apellido, String tipo, long nroDocumento, String email,
			Set<UserModel> users) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo = tipo;
		this.nroDocumento = nroDocumento;
		this.email = email;
		this.users = users;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserModel> getUsers() {
		return users;
	}

	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}
}
