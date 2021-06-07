package G20.OO2.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

		public static void generateQRCodeImage(String text, int width, int height, String filePath)
	            throws WriterException, IOException {
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

	        Path path = FileSystems.getDefault().getPath(filePath);
	        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	    }
		
		
		public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
		    QRCodeWriter qrCodeWriter = new QRCodeWriter();
		    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		    
		    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		    byte[] pngData = pngOutputStream.toByteArray(); 
		    return pngData;
		}
		
		
		public static void generarQRCodeImage(String text, int width, int height, String filePath) throws Exception {
	        String content = "http://localhost:8080/permiso/"+text;
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
	        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", Paths.get(filePath));
	    }

}