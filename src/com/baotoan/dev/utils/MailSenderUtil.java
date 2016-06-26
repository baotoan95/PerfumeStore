package com.baotoan.dev.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSenderUtil {
	public static boolean mailSender(String[] receivers, String subject, String content) {
		// sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        // creates a new session with an authenticator
//        Authenticator auth = new Authenticator() {
//            public PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("automailer95@gmail.com", "baotoan1142");
//            }
//        };
        Session session = Session.getInstance(properties, new GMailAuthenticator("automailer95@gmail.com", "baotoan1142"));
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        try {
			msg.setFrom(new InternetAddress("automailer95@gmail.com"));
			InternetAddress[] toAddresses = new InternetAddress[receivers.length];
			for(int i = 0; i < receivers.length; i++) {
				toAddresses[i] = new InternetAddress(receivers[i]);
			}
			msg.setHeader("Content-Type","text/html; charset=\"utf-8\"");
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        // set plain text message
	        msg.setContent(content, "text/html; charset=UTF-8");
	        // sends the e-mail
	        Transport.send(msg);
	        return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        return false;
	}
	
	public static void main(String[] args) {
		String html = "<h1> HTML MAIL </h1>";
		mailSender(new String[] {"baotoan1142@gmail.com", "13329032@st.hcmuaf.edu.vn"}, "Mail Tester", html + "<br/>Testing...");
	}
}

class GMailAuthenticator extends Authenticator {
	String user;
	String pw;

	public GMailAuthenticator(String username, String password) {
		super();
		this.user = username;
		this.pw = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, pw);
	}
}
