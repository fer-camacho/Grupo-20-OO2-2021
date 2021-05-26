package G20.OO2.helpers;

public class ViewRouteHelper {
	/**** Views ****/
	//HOME
	public final static String INDEX = "/home/index";
	public final static String HELLO = "home/hello";
	
	//ROLE
	public final static String ROLE_ABM = "role/abm_roles";
	public final static String ROLE_ADD = "role/add_role";
	public final static String ROLE_EDIT = "role/edit_role";
	public final static String ROLE_LIST = "role/list_roles";
	
	//USER
	public final static String USER_ABM = "user/abm_users";
	public final static String USER_ADD = "user/add_user";
	public final static String USER_EDIT = "user/edit_user";
	public final static String USER_LIST = "user/list_users";
	public final static String USER_LOGIN = "user/login";
	public final static String USER_LOGOUT = "user/logout";
	
	//PËRSONA
	public final static String PERSONA_ADD = "persona/add_persona";
	
	//RODADO
	public final static String RODADO_ADD = "rodado/add_rodado";
	
	/**** Redirects ****/
	public final static String ROUTE = "/index";
	public final static String DEGREE_ROOT = "/degrees/";
	public final static String PERSON_ROOT = "/person";
}
