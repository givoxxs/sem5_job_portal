package utils;

import java.net.InterfaceAddress;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils{
	//Create instance
	private static EmailUtils instance;
	
	//Constructor
	private EmailUtils() {
	}
	public static EmailUtils getInstance() {
		if (instance == null) {
			instance = new EmailUtils();
		}
		return instance;
	}
	//Email: npthanh60@gmail.com
	//Pass: dswa wcjm verg bsbs
	static final String from = "npthanh60@gmail.com";
	static final String pasword = "dswa wcjm verg bsbs";
	
	public Session auth() {
		
		//Khai bao
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // TSL protocol
        props.put("mail.smtp.auth", "true"); // login
        props.put("mail.smtp.starttls.enable", "true");
        
        //create authenticator
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pasword);
			}
		};
		
		return Session.getInstance(props, authenticator);
	}
	
	public void sendEmail(String to, String subject, String body) {
		
		//Send email
		Session session = auth();
		MimeMessage message = new MimeMessage(session);
		try {
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.setFrom(from);
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
			message.setSubject(subject);

			// Định dạng nội dung email với HTML
	        String formattedBody = body.replace("\n", "<br>");
	        message.setContent(formattedBody, "text/html; charset=UTF-8");
	        
			Transport.send(message);
		}catch	(Exception e) {
			e.printStackTrace();
		}
    }
	
}
