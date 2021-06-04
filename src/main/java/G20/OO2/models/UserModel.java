package G20.OO2.models;

public class UserModel {
	private int id;
	private String username;
	private String password;
	private boolean enabled;
	private UserRoleModel userRole;
	private PersonaModel persona;
	
	public UserModel() {
		super();
	}

	public UserModel(int id, String username, String password, boolean enabled, UserRoleModel userRole) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	public UserModel(int id, String username, String password, boolean enabled, UserRoleModel userRole,
			PersonaModel persona) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
		this.persona = persona;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserRoleModel getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleModel userRole) {
		this.userRole = userRole;
	}

	public PersonaModel getPersona() {
		return persona;
	}

	public void setPersona(PersonaModel persona) {
		this.persona = persona;
	}
}
