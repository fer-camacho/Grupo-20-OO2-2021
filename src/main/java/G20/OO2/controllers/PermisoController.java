package G20.OO2.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import G20.OO2.models.PermisoModel;
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
	public ModelAndView newTest() {
		ModelAndView mAV = new ModelAndView("permiso/add_diario");		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PersonaModel> personas = personaService.getAll();
	
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("personas", personas);
		mAV.addObject("permisoDiario", new PermisoDiarioModel());
		return mAV;
	}
	
	@PostMapping("/diario/save")
	public String saveTest(@ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiario, BindingResult result,
			RedirectAttributes redirect) throws UnsupportedEncodingException, MessagingException {	
		PermisoDiarioModel pD = permisoDiarioService.insertOrUpdate(permisoDiario);
		//retorno a la vista para agregar lugares
		return "redirect:/permiso/newLugar/" + pD.getIdPermiso();
	}
	
	@GetMapping("/newLugar/{id}")
	public ModelAndView newLugar(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView("permiso/add_permiso_lugar");		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		PermisoModel permiso = permisoService.listarId(id);
		List<LugarModel> lugares = lugarService.getAll();
		
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("permiso", permiso);
		mAV.addObject("lugares", lugares);
		mAV.addObject("idPermiso", id);
		//mAV.addObject("lugar", new LugarModel());
		return mAV;
	}
	
	@GetMapping("/agregar/{idPermiso}/{idLugar}")
	public String saveLugarPermiso(@PathVariable("idPermiso") int idPermiso, @PathVariable("idLugar") int idLugar) {
		ModelAndView mAV = new ModelAndView("permiso/add_permiso_lugar");		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		PermisoModel permiso = permisoService.listarId(idPermiso);
		LugarModel lugar = lugarService.findById(idLugar);
		
		Set<PermisoModel> per = lugar.getPermisos();
		if (per == null) {
			per = new HashSet<PermisoModel>();
		}
		per.add(permiso);
		lugar.setPermisos(per);
		System.out.println(lugar.getPermisos());
		
		lugarService.insertOrUpdate(lugar);
		
		List<LugarModel> lugares = lugarService.getAll();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("permiso", permiso);
		mAV.addObject("lugares", lugares);
		mAV.addObject("agregado", true);
		return "redirect:/permiso/newLugar/"+ idPermiso;
		
	}
	/*
	@PostMapping("/lugar/save")
	public String saveTest(@ModelAttribute("lugar") LugarModel lugar, BindingResult result,
			RedirectAttributes redirect) throws UnsupportedEncodingException, MessagingException {
		//lugar.getPermisos().add(permiso);
		lugarService.insertOrUpdate(lugar);
		return "redirect:/permiso/newLugar/";
	}
	*/
	
	/**********************************************************************************************************/
	
	@GetMapping("/periodo/new")
	public ModelAndView newPeriodo() {
		ModelAndView mAV = new ModelAndView("permiso/add_periodo");		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PersonaModel> personas = personaService.getAll();
		List<RodadoModel> rodados = rodadoService.getAll();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("personas", personas);
		mAV.addObject("rodados", rodados);
		mAV.addObject("permisoPeriodo", new PermisoPeriodoModel());
		return mAV;
	}
	
	@PostMapping("/periodo/save")
	public String saveTest(@ModelAttribute("permisoPeriodo") PermisoPeriodoModel permisoPeriodo, BindingResult result,
			RedirectAttributes redirect) throws UnsupportedEncodingException, MessagingException {	
		PermisoPeriodoModel pP = permisoPeriodoService.insertOrUpdate(permisoPeriodo);
		//retorno a la vista para agregar lugares
		return "redirect:/permiso/newLugar/" + pP.getIdPermiso();
	}
	
	
	/**********************************************************************************************************/
	
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
	
	/**********************************************************************************************************/
	
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
	
	/**********************************************************************************************************/
	
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
	public ModelAndView saveTest(@ModelAttribute("rangoFechas") RangoFechasModel rangoFechas, BindingResult result,
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

}
