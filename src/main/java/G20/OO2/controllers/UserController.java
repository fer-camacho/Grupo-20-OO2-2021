package G20.OO2.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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

import G20.OO2.converters.UserConverter;
import G20.OO2.helpers.Asignar;
import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.PersonaModel;
import G20.OO2.models.UserModel;
import G20.OO2.models.UserRoleModel;
import G20.OO2.services.implementations.MailService;
import G20.OO2.services.implementations.PersonaService;
import G20.OO2.services.implementations.UserRoleService;
import G20.OO2.services.implementations.UserService;

@Controller
@RequestMapping("/usuario")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;	
	
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	@Autowired
	@Qualifier("mailService")
	private MailService mailService;
	
	@PreAuthorize("hasRole('ROLE_AUDIT')")
	@GetMapping("/lista")
	public ModelAndView usuarios() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_LIST);		
		Asignar.asignarPerfil(mAV);
		
		List<UserModel> usuarios = userService.getAll();
		mAV.addObject("usuarios", usuarios);
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/abm")
	public ModelAndView userList() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ABM);
		Asignar.asignarPerfil(mAV);
		
		List<UserModel> usuarios = userService.getAll();
		mAV.addObject("usuarios", usuarios);
		return mAV;
	}
	
	//////////////////////////// NUEVO USER ////////////////////////////
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/new")
	public ModelAndView newUser() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ADD);
		Asignar.asignarPerfil(mAV);
		
		List<UserRoleModel> roles = userRoleService.getAll();
		List<PersonaModel> personas = personaService.getAll();
		mAV.addObject("roles", roles);
		mAV.addObject("personas", personas);
		mAV.addObject("user", new UserModel());
		return mAV;
	}
	
	@PostMapping("/save")
	public ModelAndView saveUser(@ModelAttribute("user") UserModel userModel, BindingResult result,
			RedirectAttributes redirect) throws UnsupportedEncodingException, MessagingException {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ADD);
		Asignar.asignarPerfil(mAV);
		
		List<UserRoleModel> roles = userRoleService.getAll();
		List<PersonaModel> personas = personaService.getAll();
		
		mAV.addObject("roles", roles);
		mAV.addObject("personas", personas);
		
		String username = userModel.getUsername();
		
		if (userService.cantidad(username)==0) { //si puedo insertar
			if (userModel.getPassword().length() >= 5) {
				UserModel u = new UserModel();
				BCryptPasswordEncoder p = new BCryptPasswordEncoder();
				userModel.setPassword(p.encode(userModel.getPassword()));
				u = userService.insertOrUpdate(userModel);
				mandarMailAltaUser(userModel);
				mAV.addObject("user", new UserModel()); //limpia el formulario
				mAV.addObject("agregado", true);
			} else {
				mAV.addObject("formatoIncorrecto", true);
			}	
		} else mAV.addObject("repetido", true);
		return mAV;
	}
	
	//////////////////////////// EDITAR USER ////////////////////////////
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/edit/{id}")
	public ModelAndView editUser(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_EDIT);
		Asignar.asignarPerfil(mAV);
		
		List<UserRoleModel> roles = userRoleService.getAll();
		List<PersonaModel> personas = personaService.getAll();
		mAV.addObject("roles", roles);
		mAV.addObject("personas", personas);
		mAV.addObject("user", userService.listarId(id));
		return mAV;
	}
	
	@PostMapping("/save/edit")
	public ModelAndView editUser(@ModelAttribute("user") UserModel userModel, BindingResult result, RedirectAttributes redirect) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_EDIT);
		Asignar.asignarPerfil(mAV);
		List<UserRoleModel> roles = userRoleService.getAll();
		List<PersonaModel> personas = personaService.getAll();
		mAV.addObject("roles", roles);
		mAV.addObject("personas", personas);

		String username = userModel.getUsername();
		if ((userService.cantidad(username)==0) || (username.equals(userService.listarId(userModel.getId()).getUsername()))){
			if (userModel.getPassword().length() >= 5) {
				//si no coincide el nombre en la lista de usuario o si dejo el mismo
				BCryptPasswordEncoder p = new BCryptPasswordEncoder();
				userModel.setPassword(p.encode(userModel.getPassword()));
				userService.insertOrUpdate(userModel);
				mAV.addObject("modificado", true);
			} else {
				mAV.addObject("formatoIncorrecto", true);
			}
			
		} else mAV.addObject("repetido", true);
		return mAV;
	}
	
	////////////////////////////ELIMINAR USER ////////////////////////////
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/delete/{id}")
	public ModelAndView deleteUser(@PathVariable("id") int id, RedirectAttributes redirect) {
		//bloquea al usuario seleccionado
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ABM);
		Asignar.asignarPerfil(mAV);
		
		UserModel userModel = userService.listarId(id);
		userModel.setEnabled(false);
		userService.insertOrUpdate(userModel);
		mandarMailBajaUser(userModel);
		List<UserModel> usuarios = userService.getAll();
		mAV.addObject("usuarios", usuarios);
		mAV.addObject("eliminado", true);
		return mAV;
	}
	
	private void mandarMailAltaUser(UserModel userModel) {
		String email = personaService.traerEmailPorId(userModel.getPersona().getId());
		//String email = userModel.getEmail();
		String username = userModel.getUsername();
		String enabled;
		if (userModel.isEnabled()) enabled = "desbloqueado";
		else enabled = "bloqueado";
        String mailSubject = "Alta de usuario";
        String mailContent = "Le informamos que ha sido dado de alta en nuestra plataforma \n\n";
        //mailContent += "Email: " + email + "\n";
        mailContent += "Username: " + username + "\n";
        mailContent += "Estado: " + enabled + "\n\n";
        mailService.sendMail(email, mailSubject, mailContent);
	}
	
	private void mandarMailBajaUser(UserModel userModel) {
		String email = personaService.traerEmailPorId(userModel.getPersona().getId());
		//String email = userModel.getEmail();
		String username = userModel.getUsername();
		String mailSubject = "Baja de usuario";
		String mailContent = "Le informamos que su usuario " + username + " ha sido bloqueado.\n\n";
		mailService.sendMail(email, mailSubject, mailContent);
	}
}
