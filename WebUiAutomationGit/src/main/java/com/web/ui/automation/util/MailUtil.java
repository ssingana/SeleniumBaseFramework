package com.web.ui.automation.util;

import org.testng.log4testng.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static com.web.ui.automation.configurations.MailConfiguration.emailProperties;

import java.util.Properties;

public class MailUtil {

	private static final Logger LOGGER = Logger.getLogger(MailUtil.class);

	private static Properties mailProperties() {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", emailProperties.getProperty("mail.smtp.host"));

		if (emailProperties.containsKey("mail.smtp.port")) {
			props.setProperty("mail.smtp.port", emailProperties.getProperty("mail.smtp.port"));
		}

		if (emailProperties.containsKey("mail.smtp.starttls.enable")) {
			props.setProperty("mail.smtp.starttls.enable", emailProperties.getProperty("mail.smtp.starttls.enable"));
		}

		if (emailProperties.containsKey("mail.smtp.auth")) {
			props.setProperty("mail.smtp.auth", emailProperties.getProperty("mail.smtp.auth"));
			if (emailProperties.getProperty("mail.smtp.auth").equals("true")) {
				props.setProperty("mail.smtp.user", emailProperties.getProperty("mail.smtp.user"));
				props.setProperty("mail.smtp.password", emailProperties.getProperty("mail.smtp.password"));
			}
		} else {
			props.setProperty("mail.smtp.auth", "false");
		}

		props.setProperty("mail.smtp.from", emailProperties.getProperty("mail.smtp.from"));
		return props;
	}

	public void sendMail(String[] to, String subject, String body) {
		try {
			Properties props = mailProperties();
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.smtp.from")));

			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject(subject);

			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(body.toString(), "text/html");
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(textPart);
			message.setContent(mp);
			Transport transport = session.getTransport("smtp");
			if (props.getProperty("mail.smtp.auth").equals("false")) {
				transport.connect();
			} else {
				transport.connect(props.getProperty("mail.smtp.host"), props.getProperty("mail.smtp.user"),
						props.getProperty("mail.smtp.password"));
			}
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			LOGGER.info("Mail sent succesfully !!!");
		} catch (Exception e) {
			LOGGER.error("Could not send email" + e.getMessage());
		}

	}

	public void sendMail(String[] to, String subject, String body, String attachment) {
		try {
			Properties props = mailProperties();
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.smtp.from")));

			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject(subject);

			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(body, "text/html");
			MimeBodyPart attachFilePart = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(attachment);
			attachFilePart.setDataHandler(new DataHandler(fds));
			attachFilePart.setFileName(fds.getName());
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(textPart);
			mp.addBodyPart(attachFilePart);
			message.setContent(mp);
			Transport transport = session.getTransport("smtp");
			if (props.getProperty("mail.smtp.auth").equals("false")) {
				transport.connect();
			} else {
				transport.connect(props.getProperty("mail.smtp.host"), props.getProperty("mail.smtp.user"),
						props.getProperty("mail.smtp.password"));
			}
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			LOGGER.info("Mail sent succesfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Could not send email 2 : " + e.getMessage());
		}

	}

	public void sendMail(String[] to, String subject, StringBuffer body, String[] cc, String attachment) {
		try {
			Properties props = mailProperties();
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.smtp.from")));

			for (int i = 0; i < to.length; i++)
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));

			for (int i = 0; i < cc.length; i++)
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));

			message.setSubject(subject);

			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(body.toString(), "text/html");

			MimeBodyPart attachFilePart = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(attachment);
			attachFilePart.setDataHandler(new DataHandler(fds));
			attachFilePart.setFileName(fds.getName());

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(textPart);
			message.setContent(mp);
			mp.addBodyPart(attachFilePart);

			Transport transport = session.getTransport("smtp");
			if (props.getProperty("mail.smtp.auth").equals("false")) {
				transport.connect();
			} else {
				transport.connect(props.getProperty("mail.smtp.host"), props.getProperty("mail.smtp.user"),
						props.getProperty("mail.smtp.password"));
			}
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			LOGGER.info("Mail sent succesfully !!!");
		} catch (Exception e) {
			LOGGER.error("Could not send email 4 : " + e.getMessage());
		}

	}
}
