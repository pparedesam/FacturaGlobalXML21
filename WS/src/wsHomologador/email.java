package wsHomologador;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class email {


	public static void main(String _correo_destino, String _file_xml) {
  	
		
		

	
		
		
		
	      String to = _correo_destino;
	      String from = "dummy21720@gmail.com";
 	      final String username = "dummy21720@gmail.com";
	      final String password = "pascola21720";
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.port", "587");
	      
	
	      
	      
		
	      
	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try {
	    	  
	    	  
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(to));

		         
		         
		         // Set Subject: header field
		         message.setSubject("ALERTA .. UNA LICENCIA DEL CONECTOR HA EXPIRADO....!!!:"+_file_xml);

		         // Create the message part
		         BodyPart messageBodyPart = new MimeBodyPart();

		         
			         
	         
	         
	         // Now set the actual message
	         messageBodyPart.setContent("mensaje de alerta","text/html; charset=utf-8" );
	         

	         
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment _file_xml
	         messageBodyPart = new MimeBodyPart();
	         String filename_xml = _file_xml;
	         DataSource source_xml = new FileDataSource(filename_xml);
	         messageBodyPart.setDataHandler(new DataHandler(source_xml));
	         messageBodyPart.setFileName(_file_xml);
	         multipart.addBodyPart(messageBodyPart);

            
	         
	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	       //  System.out.println("Correo Enviado Correctamente....");
	  
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	}






	}
