package G20.OO2.helpers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

public class Asignar {
	
	public static void asignarPerfil(ModelAndView mAV) {
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
        boolean audit = false;
        boolean anonimo = false;
        
        if(roleString.equals("[ROLE_ADMIN]")) admin=true;
		if(roleString.equals("[ROLE_AUDIT]")) audit=true;
		if (roleString.equals("[ROLE_ANONYMOUS]")) anonimo=true;
		
		mAV.addObject("admin",admin);
		mAV.addObject("audit",audit);
		mAV.addObject("anonimo",anonimo);
	}
}
