package G20.OO2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.PersonaModel;
import G20.OO2.models.UserModel;
import G20.OO2.models.UserRoleModel;
import G20.OO2.services.implementations.PersonaService;
import G20.OO2.services.implementations.UserRoleService;
import G20.OO2.services.implementations.UserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;	
	
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	
	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout,
						RedirectAttributes redirect) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "/user/login";
    }
    
    @GetMapping("/logout")
	public String logout(Model model) {
		SecurityContextHolder.clearContext();
		return "/user/logout";
    }
	
	@PostMapping("/loginsuccess")
	public ModelAndView loginCheckPost()  {
		return loginCheckBase();
	}
	
	@GetMapping("/loginsuccess")
	public ModelAndView loginCheckGet() {
		return loginCheckBase();
	}

	@GetMapping("/home")
	public ModelAndView home() {
		return loginCheckBase();
	}
	
	public ModelAndView loginCheckBase() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.INDEX);
		
		//compruebo si se logueo el admin y en tal caso muestro el menu correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
		boolean audit = false;
		boolean anonimo = false;
		
		switch(roleString){ 				  
			case "[ROLE_ADMIN]":				      
				System.out.println("cosas de admin");
				admin = true;
				break;
			case "[ROLE_AUDIT]":				      
				System.out.println("cosas de audit");
				audit = true;
				break;
			default:
				System.out.println("usuario anonimo");
				anonimo = true;
				break;
		}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		mAV.addObject("anonimo", anonimo);
		
		return mAV;
	}
	
	@GetMapping("/reporte/usuarios")
	public ModelAndView usuarios() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LIST_USERS);		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		List<UserModel> usuarios = userService.getAll();
		mAV.addObject("usuarios", usuarios);
		return mAV;
	}
	
	@GetMapping("/reporte/roles")
	public ModelAndView roles() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LIST_ROLES);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		List<UserRoleModel> roles = userRoleService.getAll();
		mAV.addObject("roles", roles);
		return mAV;
	}
	
	@GetMapping("/logueo")
	public ModelAndView test2() {
		ModelAndView mAV = new ModelAndView("home/index_2");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
			
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);

		return mAV;
	}
	
	@GetMapping("/role/list") 
	public ModelAndView roleList() {
		ModelAndView mAV = new ModelAndView("vendor/abm_roles");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		List<UserRoleModel> roles = userRoleService.getAll();
		mAV.addObject("roles", roles);
		return mAV;
	}
	
	@GetMapping("/user/list") 
	public ModelAndView userList() {
		ModelAndView mAV = new ModelAndView("vendor/abm_users");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		List<UserModel> usuarios = userService.getAll();
		mAV.addObject("usuarios", usuarios);
		return mAV;
	}
	
	//////////////////////////// NUEVO ROL ////////////////////////////
	
	@GetMapping("/rolenew")
	public ModelAndView newRole() {
		ModelAndView mAV = new ModelAndView("vendor/add_role");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		mAV.addObject("userRole", new UserRoleModel());
		return mAV;
	}
	
	@PostMapping("/rolesave")
	public String saveRole(@ModelAttribute("userRole") UserRoleModel userRoleModel, BindingResult result,
			RedirectAttributes redirect) {
		UserRoleModel u = new UserRoleModel();
		u = userRoleService.insertOrUpdate(userRoleModel);
		return "redirect:/role/list";
	}
	
	//////////////////////////// EDITAR ROL ////////////////////////////
	
	@GetMapping("/roleedit/{id}")
	public ModelAndView editRole(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("vendor/edit_role");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		mAV.addObject("userRole", userRoleService.listarId(id));
		return mAV;
	}
	
	@PostMapping("/rolesaveedit")
	public String editRole(@ModelAttribute("userRole") UserRoleModel userRoleModel, BindingResult result,
			RedirectAttributes redirect) {
		userRoleService.insertOrUpdate(userRoleModel);
		
		return "redirect:/role/list/";
	}
	
	//////////////////////////// ELIMINAR ROL ////////////////////////////
	
	@GetMapping("/roledelete/{id}")
	public String deleteRole(@PathVariable("id") int id, RedirectAttributes redirect) {
		userRoleService.delete(id);
		return "redirect:/role/list/";
	}
	
	//////////////////////////// NUEVO USER ////////////////////////////
	
	@GetMapping("/usernew")
	public ModelAndView newUser() {
		ModelAndView mAV = new ModelAndView("vendor/add_user");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		List<UserRoleModel> roles = userRoleService.getAll();
		List<PersonaModel> personas = personaService.getAll();
		mAV.addObject("roles", roles);
		mAV.addObject("personas", personas);
		mAV.addObject("user", new UserModel());
		return mAV;
	}
	
	@PostMapping("/usersave")
	public String saveuser(@ModelAttribute("user") UserModel userModel, BindingResult result,
			RedirectAttributes redirect) {
		UserModel u = new UserModel();
		BCryptPasswordEncoder p = new BCryptPasswordEncoder();
		userModel.setPassword(p.encode(userModel.getPassword()));
		u = userService.insertOrUpdate(userModel);
		return "redirect:/user/list";
	}
	
	//////////////////////////// EDITAR USER ////////////////////////////
	
	@GetMapping("/useredit/{id}")
	public ModelAndView editUser(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("vendor/edit_user");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		List<UserRoleModel> roles = userRoleService.getAll();
		List<PersonaModel> personas = personaService.getAll();
		mAV.addObject("roles", roles);
		mAV.addObject("personas", personas);
		mAV.addObject("user", userService.listarId(id));
		return mAV;
	}
	
	@PostMapping("/usersaveedit")
	public String editUser(@ModelAttribute("user") UserModel userModel, BindingResult result, RedirectAttributes redirect) {
		BCryptPasswordEncoder p = new BCryptPasswordEncoder();
		userModel.setPassword(p.encode(userModel.getPassword()));
		userService.insertOrUpdate(userModel);	
		return "redirect:/user/list";
	}
	
	////////////////////////////ELIMINAR USER ////////////////////////////
	
	@GetMapping("/userdelete/{id}")
	public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirect) {
		userService.delete(id);
		return "redirect:/user/list/";
	}
}
