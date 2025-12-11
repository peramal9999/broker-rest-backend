package com.radianbroker.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.HashMap;

import java.util.Map;

import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.radianbroker.entity.User;
import com.radianbroker.exceptions.ResourceNotFoundException;
import com.radianbroker.repository.UserRepository;
import com.radianbroker.service.MailService;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class MailServiceImpl implements MailService {

	Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	DateFormat timeFormat = new SimpleDateFormat("HH:mm");
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");

	@Value("${app.client.name}")
	private String appName;

	@Value("${app.support.email}")
	private String SUPPORT_EMAIL;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration freemarkerConfig;

	@Autowired
	UserRepository userRepository;

	@Async
	@Override
	public void send2faOtpEmail(Long userId, String email, String otp) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with email: " + email);
		}
		System.out.println("SUPPORT_EMAIL: " + SUPPORT_EMAIL);
		String subject = "OTP Verification";

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("fullName", user.getFirstName().concat(" ").concat(user.getLastName()));
		model.put("appName", appName);
		model.put("Otp", otp);

		try {
			Template t = freemarkerConfig.getTemplate("2fa-otp.html");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			sendHtmlOtpMail(SUPPORT_EMAIL, user.getEmail(), subject, html);
		} catch (MessagingException e) {
			logger.error("Failed to send mail", e);
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	public void sendHtmlOtpMail(String from, String to, String subject, String message) throws MessagingException {
		MimeMessage mail = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
		helper.setFrom(from);
		if (to.contains(",")) {
			helper.setTo(to.split(","));
		} else {
			helper.setTo(to);
		}
		helper.setSubject(subject);
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		mail.setContent(multipart);

		mailSender.send(mail);
		logger.info("{} mail sent to: {}", subject, to);
	}

}