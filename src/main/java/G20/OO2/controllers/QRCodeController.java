package G20.OO2.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import G20.OO2.helpers.Asignar;
import G20.OO2.helpers.QRCodeGenerator;
import G20.OO2.helpers.ViewRouteHelper;
import G20.OO2.models.LugarModel;
import G20.OO2.models.PermisoDiarioModel;
import G20.OO2.models.PermisoPeriodoModel;
import G20.OO2.models.PersonaModel;
import G20.OO2.models.RodadoModel;
import G20.OO2.services.implementations.LugarService;
import G20.OO2.services.implementations.MailService;
import G20.OO2.services.implementations.PermisoService;
import G20.OO2.services.implementations.PersonaService;
import G20.OO2.services.implementations.RodadoService;

@RestController
public class QRCodeController {
private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";


	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;

	@Autowired
	@Qualifier("mailService")
	private MailService mailService;

	@Autowired
	@Qualifier("lugarService")
	private LugarService lugarService;
	
	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	
	@Autowired
	@Qualifier("rodadoService")
	private RodadoService rodadoService;

    @GetMapping(value = "/genrateAndDownloadQRCode/{codeText}/{width}/{height}")
		public void download(
				@PathVariable("codeText") String codeText,
				@PathVariable("width") Integer width,
				@PathVariable("height") Integer height)
			    throws Exception {
			        QRCodeGenerator.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
			    }

    @GetMapping(value = "/genrateQRCode/{codeText}/{width}/{height}")
   	public ResponseEntity<byte[]> generateQRCode(
   			@PathVariable("codeText") String codeText,
   			@PathVariable("width") Integer width,
   			@PathVariable("height") Integer height)
   		    throws Exception {
   		        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, width, height));
   		    }
   	
   	@GetMapping(value = "/permiso/diario/new/{codeText}")
	public ModelAndView downloadDiarioOK(
			@PathVariable("codeText") String codeText)
		    throws Exception {

    			PermisoDiarioModel pD = (PermisoDiarioModel) permisoService.listarId(Integer.parseInt(codeText));
    			String text = "permiso=1&nombre="+pD.getPedido().getNombre()+"&apellido="+pD.getPedido().getApellido()+"&dni="+pD.getPedido().getNroDocumento()+"&fecha="+pD.getFecha()+"&desde="+pD.getLugarSalida().getLugar()+"&hasta="+pD.getLugarLlegada().getLugar()+"&motivo="+pD.getMotivo();
		        QRCodeGenerator.generarQRCodeImage(text, 400, 400, QR_CODE_IMAGE_PATH);
		        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ADD_DIARIO);		
				Asignar.asignarPerfil(mAV);
				String email = personaService.traerEmailPorId(permisoService.listarId(Integer.parseInt(codeText)).getPedido().getId());
				mailService.mail(email);
				
				List<PersonaModel> personas = personaService.getAll();
				List<LugarModel> lugares = lugarService.getAll();
				mAV.addObject("personas", personas);
				mAV.addObject("lugares", lugares);
				mAV.addObject("agregado", true);
				mAV.addObject("permisoDiario", new PermisoDiarioModel());
				return mAV;
		    }
    
    @GetMapping(value = "/permiso/periodo/new/{codeText}")
	public ModelAndView downloadPeriodoOK(
			@PathVariable("codeText") String codeText)
		    throws Exception {

    			PermisoPeriodoModel pP = (PermisoPeriodoModel) permisoService.listarId(Integer.parseInt(codeText));
    			String vacaciones = "Si";
    			if (!pP.isVacaciones()) vacaciones = "No";
    			
    			LocalDate fechaFin = pP.getFecha().plusDays(pP.getCantDias());
				String text = "permiso=2&nombre="+pP.getPedido().getNombre()+"&apellido="+pP.getPedido().getApellido()+"&dni="+pP.getPedido().getNroDocumento()+"&fecha="+pP.getFecha()+"&desde="+pP.getLugarSalida().getLugar()+"&hasta="+pP.getLugarLlegada().getLugar()+"&fechaFin="+fechaFin+"&vacaciones="+vacaciones+"&dominio="+pP.getRodado().getDominio()+"&vehiculo="+pP.getRodado().getVehiculo();
    			
		        QRCodeGenerator.generarQRCodeImage(text, 400, 400, QR_CODE_IMAGE_PATH);
		        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ADD_PERIODO);		
		        Asignar.asignarPerfil(mAV);
		        String email = personaService.traerEmailPorId(permisoService.listarId(Integer.parseInt(codeText)).getPedido().getId());
		        mailService.mail(email);
		        
				List<PersonaModel> personas = personaService.getAll();
				List<LugarModel> lugares = lugarService.getAll();
				List<RodadoModel> rodados = rodadoService.getAll();
				
				mAV.addObject("personas", personas);
				mAV.addObject("lugares", lugares);
				mAV.addObject("rodados", rodados);
				mAV.addObject("agregado", true);
				mAV.addObject("permisoPeriodo", new PermisoPeriodoModel());
				return mAV;
		    }
    
}
