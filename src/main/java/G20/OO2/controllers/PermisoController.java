package G20.OO2.controllers;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import G20.OO2.entities.Lugar;
import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.LugarModel;
import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.models.PermisoModel;
import G20.OO2.models.PermisoPeriodoModel;
import G20.OO2.models.PersonaModel;
import G20.OO2.models.RodadoModel;
import G20.OO2.models.UserModel;
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
	
	@GetMapping("/diarionew")
	public ModelAndView newTest() {
		ModelAndView mAV = new ModelAndView("permiso/test");		
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
	
	@PostMapping("save")
	public String saveTest(@ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiario, BindingResult result,
			RedirectAttributes redirect) throws UnsupportedEncodingException, MessagingException {
		PermisoDiarioModel PD = permisoDiario;
				
		permisoService.insertOrUpdate(permisoDiario);
		return "redirect:/permiso/diarionew";
	}
	
	
	
	/**********************************************************************************************************/
	
	
	@GetMapping("/diario/new")
	public ModelAndView newPermisoDiario() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_ADD);		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PersonaModel> personas = personaService.getAll();
	
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("step", 1);
		mAV.addObject("personas", personas);
		mAV.addObject("permisoDiario", new PermisoDiarioModel());
		return mAV;
	}
	
	@PostMapping("/diario/save")
	public String savePermisoDiario(@ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiarioModel, BindingResult result,
			RedirectAttributes redirect) {
		System.out.println(permisoDiarioModel.getFecha());
		PermisoDiarioModel pD = permisoDiarioModel;
		pD = permisoService.insertOrUpdate(permisoDiarioModel);
		return "redirect:/permiso/diario/newLugar/" + pD.getIdPermiso();
	}
	
	@GetMapping("/diario/newLugar/{id}")
	public ModelAndView newLugar(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_ADD);		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<LugarModel> lugares = lugarService.getAll();
		LugarModel lugar = new LugarModel();
		Set<PermisoModel> permisos = lugar.getPermisos();
		permisos.add(permisoService.listarId(id));
		lugar.setPermisos(permisos);
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("step", 2);
		mAV.addObject("lugares", lugares);
		mAV.addObject("lugar", lugar);
		return mAV;
	}
	
	@PostMapping("/lugar/save")
	public String saveLugar(@ModelAttribute("lugar") LugarModel lugarModel, BindingResult result,
			RedirectAttributes redirect) {
		lugarService.insertOrUpdate(lugarModel);
		List<PermisoModel> permisos = (List<PermisoModel>) lugarModel.getPermisos();
		PermisoModel pM = permisos.get(permisos.size()-1);
		return "redirect:/permiso/newLugar/" + pM.getIdPermiso();
	}
	
	/*
	@GetMapping("/new")
	public ModelAndView newPermiso() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_ADD);		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<PersonaModel> personas = personaService.getAll();
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("step", 1);
		mAV.addObject("personas", personas);
		mAV.addObject("persona", new PersonaModel());
		return mAV;
	}
	
	
	@PostMapping("/savePedido")
	public String savePedido(@Valid @ModelAttribute("persona") PersonaModel personaModel, BindingResult result,
			RedirectAttributes redirect) {
		PersonaModel pM =  personaService.listarId(personaModel.getId());
		return "redirect:/permiso/newPermisoDiario/" + pM.getId();
	}
	
	@GetMapping("/newPermisoDiario/{id}")
	public ModelAndView newPermisoDiario(@PathVariable("id") int id) { //el id del pedido (PERSONA)
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_ADD);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		PersonaModel persona = personaService.listarId(id);
		
		PermisoDiarioModel permisoDiario =  new PermisoDiarioModel();
		permisoDiario.setPedido(persona);
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("step", 2);
		mAV.addObject("permisoDiario", permisoDiario);
		return mAV;
	}
	
	@PostMapping("/savePermisoDiario")
	public String savePermisoDiario(@ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiarioModel, BindingResult result,
			RedirectAttributes redirect) {
		PermisoDiarioModel pD = permisoService.insertOrUpdate(permisoDiarioModel);
		return "redirect:/permiso/newLugar/" + pD.getIdPermiso();
	}
	
	
	@GetMapping("/newLugar/{id}")
	public ModelAndView newLugar(@PathVariable("id") int id) { //el id del permiso
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_ADD);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<LugarModel> lugares = lugarService.getAll();
		LugarModel lugar = new LugarModel();
		//a la lista de permisos del lugar, le tengo que agregar el permiso con el id
		
		PermisoDiarioModel permiso = permisoService.listarId(id);
		lugar.getPermisos().add(permiso);
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("step", 3);
		mAV.addObject("lugares", lugares);
		mAV.addObject("lugar", new LugarModel());
		
		return mAV;
	}
	
	@PostMapping("/saveLugar")
	public ModelAndView saveLugar(@ModelAttribute("lugar") LugarModel lugarModel, BindingResult result,
			RedirectAttributes redirect) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_ADD);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		List<LugarModel> lugares = lugarService.getAll();
		LugarModel lugar = new LugarModel();
		
		//PermisoDiarioModel permiso = permisoService.listarId(id);
		//lugar.getPermisos().add(permiso);
		
		asignarPerfil(mAV, roleString);
		
		lugarService.insertOrUpdate(lugarModel);
		
		mAV.addObject("anonimo", anonimo);
		mAV.addObject("step", 3);
		mAV.addObject("lugares", lugares);
		mAV.addObject("lugar", lugar);
		
		
		return mAV;
	}
	*/
	
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
	
}
