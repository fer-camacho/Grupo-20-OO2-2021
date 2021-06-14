package G20.OO2.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

		//GENERA EL QR EN FORMATO PNG
		public static void generateQRCodeImage(String text, int width, int height, String filePath)
	            throws WriterException, IOException {
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        
	        //AGREGO
	        //Link repositorio pagina publica: https://github.com/rociobustos/OO2-TPCuatrimestral-PaginaPublica
	        String contenido = "https://rociobustos.github.io/OO2-TPCuatrimestral-PaginaPublica/?"+text;
	        BitMatrix bitMatrix = qrCodeWriter.encode(contenido, BarcodeFormat.QR_CODE, width, height);

	        Path path = FileSystems.getDefault().getPath(filePath);
	        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	       
	    }
		
		//GENERA EL QR EN FORMA DE UNA MATRIZ DE BYTES
		public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
		    QRCodeWriter qrCodeWriter = new QRCodeWriter();
		    
	        String contenido = "https://rociobustos.github.io/OO2-TPCuatrimestral-PaginaPublica/?"+text;
		    //BitMatrix bitMatrix = qrCodeWriter.encode(contenido, BarcodeFormat.QR_CODE, width, height);
		    
		    //AGREGO
		    BitMatrix bitMatrix = new MultiFormatWriter().encode(contenido, BarcodeFormat.QR_CODE, width, height);
		    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		  
		    //MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		    //byte[] pngData = pngOutputStream.toByteArray(); 
		    //return pngData;
		    return pngOutputStream.toByteArray();
		}		
}