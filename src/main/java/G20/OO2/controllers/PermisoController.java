package G20.OO2.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import G20.OO2.converters.LugarConverter;
import G20.OO2.converters.PermisoConverter;
import G20.OO2.converters.PersonaConverter;
import G20.OO2.converters.RodadoConverter;
import G20.OO2.entities.Lugar;
import G20.OO2.entities.PermisoDiario;
import G20.OO2.entities.PermisoPeriodo;
import G20.OO2.entities.Rodado;
import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.models.PermisoPeriodoModel;
import G20.OO2.models.PersonaModel;
import G20.OO2.models.RodadoModel;
import G20.OO2.services.implementations.PersonaService;
import G20.OO2.services.implementations.RodadoService;
import G20.OO2.services.implementations.LugarService;
import G20.OO2.services.implementations.PermisoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {
	
	@Autowired
	@Qualifier("rodadoService")
	private RodadoService rodadoService;
	
	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	@Autowired
	@Qualifier("lugarService")
	private LugarService lugarService;
	
	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	public void asignarPerfil(ModelAndView mAV, String roleString) {
		boolean admin = false;
        boolean audit = false;
        boolean anonimo = false;
		if(roleString.equals("[ROLE_ADMIN]")) admin=true;
		if(roleString.equals("[ROLE_AUDIT]")) audit=true;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		mAV.addObject("anonimo", anonimo);
	}
	
	//TRAER PERMISO POR PERSONA
	@GetMapping("/by_Persona")
	public ModelAndView traerPermisoPorPersona() {
		ModelAndView mAV= new ModelAndView(ViewRouteHelper.PERMISO_BY_PERSONA);
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		asignarPerfil(mAV, roleString);

		return mAV;
	}
	
	//TRAER PERMISO POR PERSONA
	@PostMapping("/by_Persona")
	public ModelAndView traerPermisoPorPersona(@RequestParam(name="dni") long dni) {
		ModelAndView mAV= new ModelAndView(ViewRouteHelper.PERMISO_BY_PERSONA);
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		asignarPerfil(mAV, roleString);
		
		mAV.addObject("permisosPeriodo", permisoService.findPeriodoByPersona(dni));
		mAV.addObject("permisosDiario", permisoService.findDiarioByPersona(dni));
		return mAV;
	}
	
	//TODOS LOS PERMISOS
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@GetMapping("/all")
	public ModelAndView traerTodos() {
		ModelAndView mAV= new ModelAndView(ViewRouteHelper.PERMISO_INDEX);
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		asignarPerfil(mAV, roleString);
		
		mAV.addObject("permisos", permisoService.getAll());
		return mAV;
	}
	
	//CONTROLLER PARA CASO DE USO 5 : TRAER PERMISO POR RODADO
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@GetMapping("/by_Rodado")
	public ModelAndView traerPermisoPorRodado() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_BY_RODADO);
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		asignarPerfil(mAV, roleString);
		
		List<Rodado> rodados = rodadoService.getAll2();
		mAV.addObject("rodados", rodados);
		
		mAV.addObject("permiso", new PermisoPeriodoModel());
		return mAV;
	}
	
	//CONTROLLER PARA CASO DE USO 5 : TRAER PERMISO POR RODADO
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@PostMapping("/by_Rodado")
	public ModelAndView traerPermisoPorRodado(@RequestParam(name="rodado.idRodado") int rodado) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_BY_RODADO);
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		asignarPerfil(mAV, roleString);
		
		List<Rodado> rodados = rodadoService.getAll2();
		mAV.addObject("rodados", rodados);
		
		mAV.addObject("permiso", new PermisoPeriodoModel());
		
		mAV.addObject("permisos", permisoService.findPermisoByRodado(rodado));
		return mAV;
	}
	
	//CONTROLLER PARA CASO DE USO 7 : TRAER PERMISO ENTRE FECHAS Y LUGAR
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@GetMapping("/by_Lugar_Fechas")
	public ModelAndView traerPermisoPorLugaryFechas() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_BY_LUGARYFECHAS);
		
		List<Lugar> lugares = lugarService.getAll2();
		mAV.addObject("lugares", lugares);
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		asignarPerfil(mAV, roleString);

		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@PostMapping("/by_Lugar_Fechas")
	public ModelAndView traerPermisoPorLugaryFechas(@RequestParam(name="lugar") int idLugar, 
			@RequestParam("fechaDesde") @DateTimeFormat(pattern= "yyyy-MM-dd") LocalDate fechaDesde, 
			@RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_BY_LUGARYFECHAS);
				
		
		List<Lugar> lugares = lugarService.getAll2();
		mAV.addObject("lugares", lugares);
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		asignarPerfil(mAV, roleString);
		
		mAV.addObject("permisosPeriodo", permisoService.findPermisoByLugaryFechas(idLugar, fechaDesde, fechaHasta));
		mAV.addObject("permisosDiario", permisoService.findPermisoDiarioByLugaryFechas(idLugar, fechaDesde, fechaHasta));

		return mAV;
	}
	
	@GetMapping("/diarioNew")
	public ModelAndView newPermisoDiario() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_DIARIO_NEW);		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		
		List<PersonaModel> personas = personaService.getAll();
		List<Lugar> lugares = lugarService.getAll2();
		
		mAV.addObject("personas", personas);
		mAV.addObject("lugares", lugares);
		mAV.addObject("permisoDiario", new PermisoDiarioModel());
		return mAV;
	}
	
	@PostMapping("/diarioSave")
	public RedirectView saveDiario(
			@RequestParam(name="pedido.id") int idPedido,
			@RequestParam(name="lugarDesde") int idLugarDesde,
			@RequestParam(name="lugarHasta") int idLugarHasta,
			@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
			@RequestParam(name="motivo") String motivo
			){
	
		PermisoDiario p = new PermisoDiario();
		
		System.out.println("PROBANDO");

		Lugar lugarDesde = lugarService.findById(idLugarDesde);
		Lugar lugarHasta = lugarService.findById(idLugarHasta);
		PersonaModel persona = personaService.findById(idPedido);
		
		p.setPedido(personaConverter.modelToEntity(persona));
		p.getDesdeHasta().add(lugarDesde); 
		p.getDesdeHasta().add(lugarHasta);
		p.setFecha(fecha);	
		p.setMotivo(motivo);

		permisoService.insertOrUpdate(p);
				
		return new RedirectView(ViewRouteHelper.PERMISO_ROOT);
	}
	
	@GetMapping("/periodoNew")
	public ModelAndView newPermisoPeriodo() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_PERIODO_NEW);		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean anonimo = false;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		asignarPerfil(mAV, roleString);
		mAV.addObject("anonimo", anonimo);
		
		List<PersonaModel> personas = personaService.getAll();
		List<Lugar> lugares = lugarService.getAll2();
		
		List<Rodado> rodados = rodadoService.getAll2();
		
		mAV.addObject("personas", personas);
		mAV.addObject("lugares", lugares);
		mAV.addObject("rodados", rodados);
		mAV.addObject("permisoPeriodo", new PermisoPeriodoModel());
		return mAV;
	}
	
	@PostMapping("/periodoSave")
	public RedirectView savePersona(
			@RequestParam(name="pedido.id") int idPedido,
			@RequestParam(name="lugarDesde") int idLugarDesde,
			@RequestParam(name="lugarHasta") int idLugarHasta,
			@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
			@RequestParam(name="cantDias") int cantDias,
			@RequestParam(name="rodado.idRodado") int rodado,
			@RequestParam(value = "vacaciones", required = false) String checkboxValue
			){
	
		PermisoPeriodo p = new PermisoPeriodo();
		
		Lugar lugarDesde = lugarService.findById(idLugarDesde);
		Lugar lugarHasta = lugarService.findById(idLugarHasta);
		PersonaModel persona = personaService.findById(idPedido);
		RodadoModel rodadoModel = rodadoService.findById(rodado); 
				
		p.setPedido(personaConverter.modelToEntity(persona));
		p.getDesdeHasta().add(lugarDesde); 
		p.getDesdeHasta().add(lugarHasta);
		p.setFecha(fecha);
		p.setCantDias(cantDias);
		boolean vacacionesValue = false;
		
		if(checkboxValue != null)
		  {
		    vacacionesValue = true;
		}
		p.setVacaciones(vacacionesValue);
		p.setRodado(rodadoConverter.modelToEntity(rodadoModel));

		permisoService.insertOrUpdate(p);
				
		return new RedirectView(ViewRouteHelper.PERMISO_ROOT);
	}
}
