package G20.OO2.controllers;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import G20.OO2.converters.LugarConverter;
import G20.OO2.converters.PermisoConverter;
import G20.OO2.converters.RodadoConverter;
import G20.OO2.entities.Lugar;
import G20.OO2.entities.PermisoPeriodo;
import G20.OO2.entities.Rodado;
import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.LugarModel;
import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.models.PermisoModel;
import G20.OO2.models.PermisoPeriodoModel;
import G20.OO2.models.PersonaModel;
import G20.OO2.models.RodadoModel;
import G20.OO2.models.UserModel;
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
	
	public void asignarPerfil(ModelAndView mAV, String roleString) {
		boolean admin = false;
        boolean audit = false;
		if(roleString.equals("[ROLE_ADMIN]")) admin=true;
		if(roleString.equals("[ROLE_AUDIT]")) audit=true;
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
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
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		asignarPerfil(mAV, roleString);

		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@PostMapping("/by_Lugar_Fechas")
	public ModelAndView traerPermisoPorLugaryFechas(@RequestParam(name="lugar") String lugar, 
			@RequestParam("fechaDesde") @DateTimeFormat(pattern= "yyyy-MM-dd") LocalDate fechaDesde, 
			@RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_BY_LUGARYFECHAS);
				
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		asignarPerfil(mAV, roleString);
		
		mAV.addObject("permisosPeriodo", permisoService.findPermisoByLugaryFechas(lugar, fechaDesde, fechaHasta));
		mAV.addObject("permisosDiario", permisoService.findPermisoDiarioByLugaryFechas(lugar, fechaDesde, fechaHasta));

		return mAV;
	}
}
