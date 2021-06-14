package G20.OO2.controllers;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import G20.OO2.entities.Lugar;
import G20.OO2.entities.Permiso;
import G20.OO2.entities.PermisoDiario;
import G20.OO2.entities.PermisoPeriodo;
import G20.OO2.helpers.QRCodeGenerator;
import G20.OO2.services.implementations.PermisoService;

@Controller
@RequestMapping("/qr")
public class QRCodeController {
	
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QR/QRCode.png";
	
	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	
	@RequestMapping(value = "/generateAndDownloadQRCode", method = RequestMethod.GET)
	public @ResponseBody String generarQR(
			@RequestParam(name="codeText") int codeText
			)
			throws Exception {
    	System.out.println(codeText);
    	String text = "";
    	Permiso p = permisoService.findById(codeText);
    	System.out.println("PERMISO BUSCADO: "+ p.toString());
    	String lugar[] = new String[2]; int i=0;
		for (Lugar l:  p.getDesdeHasta()) {	
			lugar[i] = l.getLugar();
			i++;
		}
		System.out.println("Lugar1" + lugar[0]);
		System.out.println("Lugar2" + lugar[1]);

    	if(p instanceof PermisoPeriodo) {
    		//TEXTO PERMISO PERIODO
    		PermisoPeriodo permisoPeriodo = (PermisoPeriodo) p;	

    		text = "tipo=2&permiso=Periodo&nombre="+permisoPeriodo.getPedido().getNombre()+"&apellido="+permisoPeriodo.getPedido().getApellido()+"&dni="+permisoPeriodo.getPedido().getNroDocumento()+
    				"&fecha="+permisoPeriodo.getFecha()+"&desde="+lugar[0]+"&hasta="+lugar[1]+"&cantDias="+permisoPeriodo.getCantDias()+"&rodado="+permisoPeriodo.getRodado().getDominio();	
    	}
    	if(p instanceof PermisoDiario){
    		//TEXTO PERMISO DIARIO
    		PermisoDiario permisoDiario = (PermisoDiario) p;
        	System.out.println("PERMISO DIARIO: "+ permisoDiario.toString());

    		text = "tipo=1&permiso=Diario&nombre="+permisoDiario.getPedido().getNombre()+"&apellido="+permisoDiario.getPedido().getApellido()+"&dni="+permisoDiario.getPedido().getNroDocumento()+
    					"&fecha="+permisoDiario.getFecha()+"&desde="+lugar[0]+"&hasta="+lugar[1]+"&motivo="+permisoDiario.getMotivo();
    	}
		//Genera el código QR en formato de imagen. 
    	//Como parametros pasamos el texto +  ancho + altura + la ruta donde se guarda de forma local.
		QRCodeGenerator.generateQRCodeImage(text, 350, 350, QR_CODE_IMAGE_PATH);
		System.out.println("QR GENERADO");
		return "QR generado exitosamente";
	}

	//Generar QR en formato de matriz de bytes
    @GetMapping(value = "/generateQRCode/{codeText}")
   	public ResponseEntity<byte[]> generateQRCode(
   			@PathVariable("codeText") String codeText)
   		    throws Exception {
   			//Generar QR en formato de matriz de bytes
   			return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, 350, 350));
   		        
   }
   	//METODO PARA MOSTRAR EL QR EN LA PAGINA (CONTINUAR...)
   	@RequestMapping(value = "/qrcode/{id}", method= RequestMethod.GET)
   	public void qrcode(
   			@PathVariable("id") String id, 
   			HttpServletResponse response) throws Exception {
   		/*
   		ServletOutputStream stream;
   		
   		try {
   			stream = response.getOutputStream();
   			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "" + "static" + File.separator + "img" + File.separator;
   			QRCodeGenerator.getQRCodeImage(path, 200, 200);
   		}catch(IOException e) {
   			e.printStackTrace();
   		}*/   		
   		
   		response.setContentType("./src/main/resources/");
   		OutputStream outputStream = response.getOutputStream();
   		outputStream.write(QRCodeGenerator.getQRCodeImage(id, 200, 200));
   		outputStream.flush();
   		outputStream.close();
   	
   	}
}
