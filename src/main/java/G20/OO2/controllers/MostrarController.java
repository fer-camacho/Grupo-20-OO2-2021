package G20.OO2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("https://fer-camacho.github.io/Grupo-20-OO2-2021/")
public class MostrarController {
	
	@GetMapping("permiso/diario/{id}")
	public ModelAndView mostrarPermiso(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("C:\\Users\\camac\\OneDrive\\Escritorio\\Facultad\\OO2\\Proyecto\\Grupo-20-OO2-2021\\index");
		return mAV;
	}
}
