package G20.OO2.controllers;

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

import G20.OO2.converters.RodadoConverter;
import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.RodadoModel;
import G20.OO2.services.implementations.RodadoService;

@Controller
@RequestMapping("/rodado")
public class RodadoController {
	
	@Autowired
	@Qualifier("rodadoService")
	private RodadoService rodadoService;
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	public void asignarPerfil(ModelAndView mAV, String roleString) {
		boolean admin = false;
        boolean audit = false;
		if(roleString.equals("[ROLE_ADMIN]")) admin=true;
		if(roleString.equals("[ROLE_AUDIT]")) audit=true;
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
	}
	
	@GetMapping("/new")
	public ModelAndView newRodado() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.RODADO_ADD);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("rodado", new RodadoModel());
		return mAV;
	}
	
	@PostMapping("/save")
	public ModelAndView saveRodado(@ModelAttribute("rodado") RodadoModel rodadoModel, BindingResult result,
			RedirectAttributes redirect) {
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.RODADO_ADD);
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		
		if (rodadoService.cantidad(rodadoModel.getDominio()) == 0) {
			RodadoModel r = new RodadoModel();
			r = rodadoService.insertOrUpdate(rodadoModel);
			mAV.addObject("rodado", new RodadoModel());
			mAV.addObject("agregado", true);
		} else mAV.addObject("repetido", true);
		return mAV;
	}
}
