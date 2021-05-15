package G20.OO2.models;

import java.util.Set;

public class UserRoleModel {
	private int id;
	private String role;
	private Set<UserModel> users;
	
	public UserRoleModel() {
		super();
	}
	
	public UserRoleModel(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public UserRoleModel(int id, String role, Set<UserModel> users) {
		super();
		this.id = id;
		this.role = role;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<UserModel> getUsers() {
		return users;
	}

	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}
}
