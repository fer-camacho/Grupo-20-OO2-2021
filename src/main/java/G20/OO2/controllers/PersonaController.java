package G20.OO2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import G20.OO2.converters.PersonaConverter;
import G20.OO2.helpers.Asignar;
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
	
	@GetMapping("/new")
	public ModelAndView newPersona() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSONA_ADD);		
		Asignar.asignarPerfil(mAV);
		mAV.addObject("persona", new PersonaModel());
		return mAV;
	}
	
	@PostMapping("/save")
	public ModelAndView savePersona(@ModelAttribute("persona") PersonaModel personaModel, BindingResult result,
			RedirectAttributes redirect) {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSONA_ADD);
		Asignar.asignarPerfil(mAV);
		
		if (personaService.cantidad(personaModel.getNroDocumento()) == 0) {
			PersonaModel p = new PersonaModel();
			p = personaService.insertOrUpdate(personaModel);
			mAV.addObject("persona", new PersonaModel());
			mAV.addObject("agregado", true);
		} else mAV.addObject("repetido", true);
		
		return mAV;
	}
}
