package G20.OO2.services.implementations;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.server.RSocketServer.Transport;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import G20.OO2.services.IMailService;

@Service("mailService")
public class MailService implements IMailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("oo2.grupo20@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		
		mailSender.send(message);
	}
	
	public void mail(String to) {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port","587");
		props.setProperty("mail.smtp.user", "oo2.grupo20@gmail.com");
		props.setProperty("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
		
		BodyPart texto = new MimeBodyPart();
		try {
			texto.setText("Escanee el código QR para obtener la información del permiso.");
			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\camac\\OneDrive\\Escritorio\\Facultad\\OO2\\Proyecto\\Grupo-20-OO2-2021\\src\\main\\resources\\QRCode.png")));
			adjunto.setFileName("QRCode.png");
			
			MimeMultipart multiParte = new MimeMultipart();

			multiParte.addBodyPart(texto);
			multiParte.addBodyPart(adjunto);
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("oo2.grupo20@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Creación de permiso");
			message.setContent(multiParte);
			
			javax.mail.Transport t = session.getTransport("smtp");
			t.connect("oo2.grupo20@gmail.com","2021objetos2");
			t.sendMessage(message,message.getAllRecipients());
			t.close();
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
