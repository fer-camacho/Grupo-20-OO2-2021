package G20.OO2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import G20.OO2.converters.PersonaConverter;
import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.PersonaModel;
import G20.OO2.services.implementations.PersonaService;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	public void asignarPerfil(ModelAndView mAV, String roleString) {
		boolean admin = false;
        boolean audit = false;
		if(roleString.equals("[ROLE_ADMIN]")) admin=true;
		if(roleString.equals("[ROLE_AUDIT]")) audit=true;
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
	}
	
	@GetMapping("/new")
	public ModelAndView newPersona() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSONA_ADD);		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("persona", new PersonaModel());
		return mAV;
	}
	
	@PostMapping("/save")
	public ModelAndView savePersona(@ModelAttribute("persona") PersonaModel personaModel, BindingResult result,
			RedirectAttributes redirect) {
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSONA_ADD);
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		
		if (personaService.cantidad(personaModel.getNroDocumento()) == 0) {
			PersonaModel p = new PersonaModel();
			p = personaService.insertOrUpdate(personaModel);
			mAV.addObject("persona", new PersonaModel());
			mAV.addObject("agregado", true);
		} else mAV.addObject("repetido", true);
		
		return mAV;
	}
}
