package G20.OO2.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre", nullable = false, length = 50)
	private String nombre;
	
	@Column(name="apellido", nullable = false, length = 50)
	private String apellido;
	
	@Column(name="tipo", nullable = false)
	private String tipo;
	
	@Column(name="nroDocumento", nullable = false)
	private long nroDocumento;
	
	@Column(name="email", nullable=false, length=90)
	private String email;
	
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="persona")
	private Set<User> users;

	public Persona() {
		super();
	}

	public Persona(int id, String nombre, String apellido, String tipo, long nroDocumento, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo = tipo;
		this.nroDocumento = nroDocumento;
		this.email = email;
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
