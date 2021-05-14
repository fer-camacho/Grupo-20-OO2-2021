package G20.OO2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.UserModel;
import G20.OO2.models.UserRoleModel;
import G20.OO2.services.implementations.UserRoleService;
import G20.OO2.services.implementations.UserService;

@Controller
public class UserController {
	/* 
	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout,
						RedirectAttributes redirect) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
    }
	
	@GetMapping("/logout")
	public String logout(Model model) {
		SecurityContextHolder.clearContext();
		return ViewRouteHelper.USER_LOGOUT;
	}
	
	@PostMapping("/loginsuccess")
	public String loginCheckPost()  {
		//return loginCheckBase();
		return "redirect:index";
	} 
	*/

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;	
	
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
        System.out.println(roleString);

		boolean admin = false;
		switch(roleString){ 				  
			case "[ROLE_ADMIN]":				      
				System.out.println("cosas de admin");
				admin = true;
				break;
		}

		mAV.addObject("admin", admin);

		return mAV;
	}
	
	@GetMapping("/reporte/usuarios")
	public ModelAndView usuarios() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LIST_USERS);		

		//compruebo si se logueo el admin y en tal caso muestro el menu correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

        boolean admin = false;
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		mAV.addObject("admin", admin);
		
		List<UserModel> usuarios = userService.getAll();
		mAV.addObject("usuarios", usuarios);
		return mAV;
	}
	
	@GetMapping("/reporte/roles")
	public ModelAndView roles() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LIST_ROLES);		

		//compruebo si se logueo el admin y en tal caso muestro el menu correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

        boolean admin = false;
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		mAV.addObject("admin", admin);
		
		List<UserRoleModel> roles = userRoleService.getAll();
		mAV.addObject("roles", roles);
		return mAV;
	}
	
	@GetMapping("/logueo")
	public String test2() {
		return "user/test";
	}
}
