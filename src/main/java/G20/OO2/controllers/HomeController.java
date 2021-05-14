package G20.OO2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import G20.OO2.entities.User;
import G20.OO2.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {
	
	/*
	@Autowired
	@Qualifier("personService")
	private IPersonService personService;
	*/
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.INDEX);
		
		//compruebo si se logueo el admin y en tal caso muestro el menu correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

		boolean admin = false;
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		mAV.addObject("admin", admin);
		return mAV;
	}
	/*
	@GetMapping("index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//modelAndView.addObject("username", user.getUsername());
		//modelAndView.addObject("persons", personService.getAll());
		return modelAndView;
	}
	
	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.ROUTE);
	}
	*/
}
