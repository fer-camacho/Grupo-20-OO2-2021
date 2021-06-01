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

import G20.OO2.converters.LugarConverter;
import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.LugarModel;
import G20.OO2.services.implementations.LugarService;

@Controller
@RequestMapping("/lugar")
public class LugarController {
	
	@Autowired
	@Qualifier("lugarService")
	private LugarService lugarService;
	
	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	public void asignarPerfil(ModelAndView mAV, String roleString) {
		boolean admin = false;
        boolean audit = false;
		if(roleString.equals("[ROLE_ADMIN]")) admin=true;
		if(roleString.equals("[ROLE_AUDIT]")) audit=true;
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
	}
	
	@GetMapping("/new")
	public ModelAndView newLugar() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LUGAR_ADD);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("lugar", new LugarModel());
		return mAV;
	}
	
	@PostMapping("/save")
	public ModelAndView saveLugar(@ModelAttribute("lugar") LugarModel lugarModel, BindingResult result,
			RedirectAttributes redirect) {
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LUGAR_ADD);
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		
		if (lugarService.cantidad(lugarModel.getLugar()) == 0) {
			LugarModel l = new LugarModel();
			l = lugarService.insertOrUpdate(lugarModel);
			mAV.addObject("lugar", new LugarModel());
			mAV.addObject("agregado", true);
		} else mAV.addObject("repetido", true);
		return mAV;
	}
	
	
}
