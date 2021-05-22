package G20.OO2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.UserRoleModel;
import G20.OO2.services.implementations.UserRoleService;

@Controller
@RequestMapping("/perfil")
public class UserRoleController {
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;

	@GetMapping("/lista")
	public ModelAndView roles() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_LIST);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
		boolean audit = false;

		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		if (roleString.equals("[ROLE_AUDIT]")) {
			audit = true;
		}

		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);

		List<UserRoleModel> roles = userRoleService.getAll();
		mAV.addObject("roles", roles);
		return mAV;
	}

	@GetMapping("/abm")
	public ModelAndView roleList() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_ABM);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
		boolean audit = false;

		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		if (roleString.equals("[ROLE_AUDIT]")) {
			audit = true;
		}

		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);

		List<UserRoleModel> roles = userRoleService.getAll();
		mAV.addObject("roles", roles);
		return mAV;
	}

////////////////////////////NUEVO ROL ////////////////////////////

	@GetMapping("/new")
	public ModelAndView newRole() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_ADD);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
		boolean audit = false;

		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		if (roleString.equals("[ROLE_AUDIT]")) {
			audit = true;
		}

		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);

		mAV.addObject("userRole", new UserRoleModel());
		return mAV;
	}

	@PostMapping("/save")
	public String saveRole(@ModelAttribute("userRole") UserRoleModel userRoleModel, BindingResult result,
			RedirectAttributes redirect) {
		UserRoleModel u = new UserRoleModel();
		u = userRoleService.insertOrUpdate(userRoleModel);
		return "redirect:/perfil/abm";
	}

	//////////////////////////// EDITAR ROL ////////////////////////////

	@GetMapping("/edit/{id}")
	public ModelAndView editRole(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ROLE_EDIT);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
		boolean audit = false;

		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		if (roleString.equals("[ROLE_AUDIT]")) {
			audit = true;
		}

		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);

		mAV.addObject("userRole", userRoleService.listarId(id));
		return mAV;
	}

	@PostMapping("/save/edit")
	public String editRole(@ModelAttribute("userRole") UserRoleModel userRoleModel, BindingResult result,
			RedirectAttributes redirect) {
		userRoleService.insertOrUpdate(userRoleModel);
		return "redirect:/perfil/abm";
	}

	//////////////////////////// ELIMINAR ROL ////////////////////////////

	@GetMapping("/delete/{id}")
	public String deleteRole(@PathVariable("id") int id, RedirectAttributes redirect) {
		userRoleService.delete(id);
		return "redirect:/perfil/abm";
	}
}