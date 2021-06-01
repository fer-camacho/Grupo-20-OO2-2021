package G20.OO2.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import G20.OO2.helpers.ViewRouteHelper;

@Controller
@RequestMapping("")
public class HomeController {
	
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
	public ModelAndView logout(Model model) {
		SecurityContextHolder.clearContext();
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.INDEX);
		mAV.addObject("anonimo", true);
		return mAV;	
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
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.INDEX);
		
		//compruebo si se logueo el admin y en tal caso muestro el menu correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

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
}
