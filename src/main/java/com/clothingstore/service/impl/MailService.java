package com.clothingstore.service.impl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.clothingstore.entity.PasswordResetToken;
import com.clothingstore.service.IMailService;

import Message.message;

@Service
public class MailService implements IMailService {
	
	private final String content_type_html = "text/html;charset=\"UTF-8\"";
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ThymeleafService thymeService;
	
	@Override
	public void sendMailResetPassword(PasswordResetToken passwordResetToken) {
		String fullName = passwordResetToken.getEntityUser().getLastName()+" "+passwordResetToken.getEntityUser().getFirstName();
		MimeMessage mailMessage = sendMail("vutrunrd@gmail.com",passwordResetToken.getEntityUser().getEmail(),"reset password CNSHOP!!!",fullName, passwordResetToken.getToken());
		mailSender.send(mailMessage);
	}
	
	@SuppressWarnings("finally")
	private MimeMessage sendMail(String from, String to, String subject, String fullname,String token) {
		MimeMessage mailMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
		boolean check =false;
		try {
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			mailMessage.setContent(thymeService.getContent(fullname, token),content_type_html);
		} catch (MessagingException e) {
			check = true;
			e.printStackTrace();
		}finally {
			if(check ==true) {
				return null;
			}else {
				return mailMessage;
			}
		}
		
	}
	
	
}
