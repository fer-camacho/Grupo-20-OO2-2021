package G20.OO2.models;

public class UserRoleModel {
	private int id;
	private UserModel user;
	private String role;
	
	public UserRoleModel() {
		super();
	}
	
	public UserRoleModel(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public UserRoleModel(int id, UserModel user, String role) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
