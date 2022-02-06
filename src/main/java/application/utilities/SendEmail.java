package application.utilities;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import application.model.Utente;

public class SendEmail {
	
	private static SendEmail instance = null;
	final String fromEmail = "sito.di.ricette@gmail.com";
	final String password = "Melanzana82!";
	
	
	private Session session = null;
	
	private SendEmail() {
		Properties pr = new Properties();
		pr.put("mail.smtp.host", "smtp.gmail.com");
		pr.put("mail.smtp.port", "465");
		pr.put("mail.smtp.auth", "true");
		pr.put("mail.smtp.ssl.enable", "true");
		pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	
		if(session == null) {

			session = Session.getInstance(pr, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});	
		}
		
	}
	
	public static SendEmail getInstance() {
		if(instance == null)
			instance = new SendEmail();
		return instance;
	}
	
	public String getRandom() {
		Random r = new Random();
		int number = r.nextInt(999999);
		
		return String.format("%06d", number);
	}
	
	public boolean sendEmailVerification(Utente u) {
		boolean res = false;
		
		String toEmail = u.getMail();
		
		try {
			
			Message message = new MimeMessage(session);
			
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setFrom(new InternetAddress(fromEmail));
			
			message.setSubject("Verifca Email Utente");
			message.setText("Ci sei quasi! Per essere un membro effettivo di Viola Melanzana devi verificare la tua mail!\n"
					+ "Inserisci questo codice per confermarla:" + u.getVerificationCode());
			
			Transport.send(message);
			
			res = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean sendEmailChangePassword(Utente u, String resetPasswordLink) {
		boolean res = false;
		
		String toEmail = u.getMail();
		
		try {
			
			Message message = new MimeMessage(session);
			
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setFrom(new InternetAddress(fromEmail));
			
			message.setSubject("Cambio password");
			message.setText("Ciao!\n"
							+ "Hai richiesto di cambiare la tua password,\n"
							+ "Clicca il link qui sotto per cambiare la tua password.\n\n"
							+ resetPasswordLink + "\n\n"
							+ "Ignora questa mail se non hai richiesto il reset");
			
			Transport.send(message);
			
			res = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
