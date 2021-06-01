package G20.OO2.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import G20.OO2.models.LugarModel;
import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.models.PermisoPeriodoModel;
import G20.OO2.models.PersonaModel;
import G20.OO2.models.RangoFechasModel;
import G20.OO2.models.RodadoModel;
import G20.OO2.services.implementations.LugarService;
import G20.OO2.services.implementations.PermisoDiarioService;
import G20.OO2.services.implementations.PermisoPeriodoService;
import G20.OO2.services.implementations.PermisoService;
import G20.OO2.services.implementations.PersonaService;
import G20.OO2.services.implementations.RodadoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {
	
	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	
	@Autowired
	@Qualifier("permisoDiarioService")
	private PermisoDiarioService permisoDiarioService;
		
	@Autowired
	@Qualifier("permisoPeriodoService")
	private PermisoPeriodoService permisoPeriodoService;
	
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	
	@Autowired
	@Qualifier("lugarService")
	private LugarService lugarService;
	
	@Autowired
	@Qualifier("rodadoService")
	private RodadoService rodadoService;
	
	public void asignarPerfil(ModelAndView mAV, String roleString) {
		boolean admin = false;
        boolean audit = false;
		if(roleString.equals("[ROLE_ADMIN]")) admin=true;
		if(roleString.equals("[ROLE_AUDIT]")) audit=true;
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
	}
	
	@GetMapping("/diario/new")
	public ModelAndView newDiario() {
		ModelAndView mAV = new ModelAndView("permiso/add_diario");		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PersonaModel> personas = personaService.getAll();
		List<LugarModel> lugares = lugarService.getAll();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("personas", personas);
		mAV.addObject("lugares", lugares);
		mAV.addObject("permisoDiario", new PermisoDiarioModel());
		return mAV;
	}
	
	@PostMapping("/diario/save")
	public ModelAndView saveDiario(@ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiario, BindingResult result,
			RedirectAttributes redirect) throws UnsupportedEncodingException, MessagingException {	
		PermisoDiarioModel pD = permisoDiarioService.insertOrUpdate(permisoDiario);
		
		ModelAndView mAV = new ModelAndView("permiso/add_diario");		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PersonaModel> personas = personaService.getAll();
		List<LugarModel> lugares = lugarService.getAll();
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("personas", personas);
		mAV.addObject("lugares", lugares);
		mAV.addObject("agregado", true);
		mAV.addObject("permisoDiario", new PermisoDiarioModel());
		return mAV;
	}
	
	@GetMapping("/periodo/new")
	public ModelAndView newPeriodo() {
		ModelAndView mAV = new ModelAndView("permiso/add_periodo");		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PersonaModel> personas = personaService.getAll();
		List<LugarModel> lugares = lugarService.getAll();
		List<RodadoModel> rodados = rodadoService.getAll();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("personas", personas);
		mAV.addObject("lugares", lugares);
		mAV.addObject("rodados", rodados);
		mAV.addObject("permisoPeriodo", new PermisoPeriodoModel());
		return mAV;
	}
	
	@PostMapping("/periodo/save")
	public ModelAndView savePeriodo(@ModelAttribute("permisoPeriodo") PermisoPeriodoModel permisoPeriodo, BindingResult result,
			RedirectAttributes redirect) throws UnsupportedEncodingException, MessagingException {	
		PermisoPeriodoModel pP = permisoPeriodoService.insertOrUpdate(permisoPeriodo);
		
		ModelAndView mAV = new ModelAndView("permiso/add_periodo");		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PersonaModel> personas = personaService.getAll();
		List<LugarModel> lugares = lugarService.getAll();
		List<RodadoModel> rodados = rodadoService.getAll();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("personas", personas);
		mAV.addObject("lugares", lugares);
		mAV.addObject("rodados", rodados);
		mAV.addObject("agregado", true);
		mAV.addObject("permisoPeriodo", new PermisoPeriodoModel());
		return mAV;
	}
	
	@GetMapping("/persona")
	public ModelAndView permisosPorPersona() {
		ModelAndView mAV = new ModelAndView("permiso/personas");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PersonaModel> personas = personaService.getAll();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("personas", personas);
		return mAV;
	}
	
	@GetMapping("/persona/{id}")
	public ModelAndView permisosPorPersona(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("permiso/permisos");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PermisoDiarioModel> diario = permisoDiarioService.traerPorPersona(id);
		List<PermisoPeriodoModel> periodo = permisoPeriodoService.traerPorPersona(id);
		
		boolean hayDiario = !diario.isEmpty();
		boolean hayPeriodo = !periodo.isEmpty();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("diario", diario);
		mAV.addObject("periodo", periodo);
		mAV.addObject("hayDiario", hayDiario);
		mAV.addObject("hayPeriodo", hayPeriodo);
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@GetMapping("/rodado")
	public ModelAndView permisosPorRodado() {
		ModelAndView mAV = new ModelAndView("permiso/rodados");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<RodadoModel> rodados = rodadoService.getAll();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("rodados", rodados);
		return mAV;
	}
	
	@GetMapping("/rodado/{id}")
	public ModelAndView permisosPorRodado(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("permiso/permisos");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PermisoPeriodoModel> periodo = permisoPeriodoService.traerPorRodado(id);
		boolean hayPeriodo = !periodo.isEmpty();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("periodo", periodo);
		mAV.addObject("hayDiario", false);
		mAV.addObject("hayPeriodo", hayPeriodo);
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@GetMapping("/fechas")
	public ModelAndView permisosEntreFechas() {
		ModelAndView mAV = new ModelAndView("permiso/fechas");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("rangoFechas", new RangoFechasModel());
		return mAV;
	}
	
	
	@PostMapping("/fechas/grilla")
	public ModelAndView permisosEntreFechas(@ModelAttribute("rangoFechas") RangoFechasModel rangoFechas, BindingResult result,
			RedirectAttributes redirect) throws UnsupportedEncodingException, MessagingException {
		ModelAndView mAV = new ModelAndView("permiso/permisos");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
	
		List<PermisoDiarioModel> diario = permisoDiarioService.traerPorFecha(rangoFechas.getFechaInicio(), rangoFechas.getFechaFin().plusDays(1));
		List<PermisoPeriodoModel> periodo = permisoPeriodoService.traerPorFecha(rangoFechas.getFechaInicio(), rangoFechas.getFechaFin());
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("diario", diario);
		mAV.addObject("periodo", periodo);
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@GetMapping("/fechas/lugar")
	public ModelAndView permisosEntreFechasYLugar() {
		ModelAndView mAV = new ModelAndView("permiso/fechas_lugar");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("rangoFechas", new RangoFechasModel());
		return mAV;
	}
	
	@PostMapping("/fechas/lugar/grilla")
	public ModelAndView permisosEntreFechasYLugar(@ModelAttribute("rangoFechas") RangoFechasModel rangoFechas, BindingResult result,
			RedirectAttributes redirect) throws UnsupportedEncodingException, MessagingException {
		ModelAndView mAV = new ModelAndView("permiso/permisos");
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
	
		List<PermisoDiarioModel> diario = permisoDiarioService.traerPorFechaYLugar(rangoFechas.getFechaInicio(), rangoFechas.getFechaFin().plusDays(1), rangoFechas.getLugar());
		List<PermisoPeriodoModel> periodo = permisoPeriodoService.traerPorFechaYLugar(rangoFechas.getFechaInicio(), rangoFechas.getFechaFin(), rangoFechas.getLugar());
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("diario", diario);
		mAV.addObject("periodo", periodo);
		return mAV;
	}

}
