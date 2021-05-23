package G20.OO2.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@Qualifier("mailService")
	private MailService mailService;
	
	@GetMapping("/lista")
	public ModelAndView usuarios() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_LIST);		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        boolean admin = false;
        boolean audit = false;
        
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		List<UserModel> usuarios = userService.getAll();
		mAV.addObject("usuarios", usuarios);
		return mAV;
	}
	
	@GetMapping("/abm")
	public ModelAndView userList() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ABM);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		List<UserModel> usuarios = userService.getAll();
		mAV.addObject("usuarios", usuarios);
		return mAV;
	}
	
	//////////////////////////// NUEVO USER ////////////////////////////
	
	@GetMapping("/new")
	public ModelAndView newUser() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ADD);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		List<UserRoleModel> roles = userRoleService.getAll();
		List<PersonaModel> personas = personaService.getAll();
		mAV.addObject("roles", roles);
		mAV.addObject("personas", personas);
		mAV.addObject("user", new UserModel());
		return mAV;
	}
	
	@PostMapping("/save")
	public String saveuser(@ModelAttribute("user") UserModel userModel, BindingResult result,
			RedirectAttributes redirect) throws UnsupportedEncodingException, MessagingException {
		UserModel u = new UserModel();
		BCryptPasswordEncoder p = new BCryptPasswordEncoder();
		userModel.setPassword(p.encode(userModel.getPassword()));
		u = userService.insertOrUpdate(userModel);
		
		//(userModel);
		return "redirect:/usuario/abm";
	}
	
	//////////////////////////// EDITAR USER ////////////////////////////
	
	@GetMapping("/edit/{id}")
	public ModelAndView editUser(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_EDIT);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

		boolean admin = false;
        boolean audit = false;
        
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		if(roleString.equals("[ROLE_AUDIT]")) {audit=true;}
		
		mAV.addObject("admin", admin);
		mAV.addObject("audit", audit);
		
		List<UserRoleModel> roles = userRoleService.getAll();
		List<PersonaModel> personas = personaService.getAll();
		mAV.addObject("roles", roles);
		mAV.addObject("personas", personas);
		mAV.addObject("user", userService.listarId(id));
		return mAV;
	}
	
	@PostMapping("/save/edit")
	public String editUser(@ModelAttribute("user") UserModel userModel, BindingResult result, RedirectAttributes redirect) {
		BCryptPasswordEncoder p = new BCryptPasswordEncoder();
		userModel.setPassword(p.encode(userModel.getPassword()));
		userService.insertOrUpdate(userModel);
		return "redirect:/usuario/abm";
	}
	
	////////////////////////////ELIMINAR USER ////////////////////////////
	/*
	@GetMapping("/userdelete/{id}")
	public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirect) {
		userService.delete(id);
		return "redirect:/user/list/";
	}
	*/
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirect) {
		//bloquea al usuario seleccionado
		UserModel userModel = userService.listarId(id);
		userModel.setEnabled(false);
		userService.insertOrUpdate(userModel);
		//mandarMailBajaUser(userModel);
		return "redirect:/usuario/abm/";
	}
	
	private void mandarMailAltaUser(UserModel userModel) {
		String email = userModel.getEmail();
		String username = userModel.getUsername();
		String enabled;
		
		if (userModel.isEnabled()) enabled = "desbloqueado";
		else enabled = "bloqueado";
		
        String mailSubject = "Alta de usuario";
        String mailContent = "Le informamos que ha sido dado de alta en nuestra plataforma \n\n";
        
        mailContent += "Email: " + email + "\n";
        mailContent += "Username: " + username + "\n";
        mailContent += "Estado: " + enabled + "\n\n";
        
        mailService.sendMail(email, mailSubject, mailContent);
	}
	
	private void mandarMailBajaUser(UserModel userModel) {
		String email = userModel.getEmail();
		String username = userModel.getUsername();
		
		String mailSubject = "Baja de usuario";
		String mailContent = "Le informamos que su usuario " + username + " ha sido bloqueado.\n\n";
		
		mailService.sendMail(email, mailSubject, mailContent);
	}
}
