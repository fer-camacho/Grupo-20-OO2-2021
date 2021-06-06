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

import G20.OO2.converters.RodadoConverter;
import G20.OO2.helpers.Asignar;
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
	
	@GetMapping("/new")
	public ModelAndView newRodado() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.RODADO_ADD);
		Asignar.asignarPerfil(mAV);
		mAV.addObject("rodado", new RodadoModel());
		return mAV;
	}
	
	@PostMapping("/save")
	public ModelAndView saveRodado(@ModelAttribute("rodado") RodadoModel rodadoModel, BindingResult result,
			RedirectAttributes redirect) {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.RODADO_ADD);
		Asignar.asignarPerfil(mAV);
		
		if (rodadoService.cantidad(rodadoModel.getDominio()) == 0) {
			if (rodadoService.dominioValido(rodadoModel.getDominio())) {
				RodadoModel r = new RodadoModel();
				r = rodadoService.insertOrUpdate(rodadoModel);
				mAV.addObject("rodado", new RodadoModel());
				mAV.addObject("agregado", true);
			} else mAV.addObject("dominioInvalido",true);
		} else mAV.addObject("repetido", true);
		return mAV;
	}
}
