package G20.OO2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import G20.OO2.helpers.Asignar;
import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.UserModel;
import G20.OO2.models.UserRoleModel;
import G20.OO2.services.implementations.UserRoleService;

@Controller
@RequestMapping("/perfil")
public class UserRoleController {
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@GetMapping("/lista")
	public ModelAndView roles() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_LIST);
		Asignar.asignarPerfil(mAV);
		List<UserRoleModel> roles = userRoleService.getAll();
		mAV.addObject("roles", roles);
		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/abm")
	public ModelAndView roleList() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_ABM);
		Asignar.asignarPerfil(mAV);
		List<UserRoleModel> roles = userRoleService.getAll();
		mAV.addObject("roles", roles);
		return mAV;
	}

	////////////////////////////NUEVO ROL ////////////////////////////
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/new")
	public ModelAndView newRole() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_ADD);
		Asignar.asignarPerfil(mAV);
		mAV.addObject("userRole", new UserRoleModel());
		return mAV;
	}
	
	@PostMapping("/save")
	public ModelAndView saveRole(@ModelAttribute("userRole") UserRoleModel userRoleModel, BindingResult result,
			RedirectAttributes redirect) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_ADD);
		Asignar.asignarPerfil(mAV);
		
		String nombreRol = userRoleModel.getRole();
		
		if (userRoleService.cantidad(nombreRol) == 0) {
			UserRoleModel u = new UserRoleModel();
			u = userRoleService.insertOrUpdate(userRoleModel);
			mAV.addObject("userRole", new UserRoleModel());
			mAV.addObject("agregado", true);
			return mAV;
		} else mAV.addObject("repetido", true);
		return mAV;
	}
	
	//////////////////////////// EDITAR ROL ////////////////////////////
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/edit/{id}")
	public ModelAndView editRole(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_EDIT);
		Asignar.asignarPerfil(mAV);
		mAV.addObject("userRole", userRoleService.listarId(id));
		return mAV;
	}

	@PostMapping("/save/edit")
	public ModelAndView editRole(@ModelAttribute("userRole") UserRoleModel userRoleModel, BindingResult result,
			RedirectAttributes redirect) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_EDIT);
		Asignar.asignarPerfil(mAV);
		
		String nombreRol = userRoleModel.getRole();
		
		if ((userRoleService.cantidad(nombreRol) == 0) || (nombreRol.equals(userRoleService.listarId(userRoleModel.getId()).getRole()))){
			userRoleService.insertOrUpdate(userRoleModel);
			mAV.addObject("modificado", true);
		} else mAV.addObject("repetido", true);
		return mAV;
	}
	
	//////////////////////////// ELIMINAR ROL ////////////////////////////
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/delete/{id}")
	public ModelAndView deleteRole(@PathVariable("id") int id, RedirectAttributes redirect) {
		UserRoleModel userRoleModel = userRoleService.listarId(id);
		userRoleModel.setEnabled(false);
		userRoleService.insertOrUpdate(userRoleModel);
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_ABM);
		Asignar.asignarPerfil(mAV);
		List<UserRoleModel> roles = userRoleService.getAll();
		mAV.addObject("roles", roles);
		mAV.addObject("eliminado", true);
		return mAV;
	}
}
