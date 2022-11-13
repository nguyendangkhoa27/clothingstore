package com.clothingstore.config;

import java.util.Properties;

import javax.mail.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost("smtp.gmail.com");
		mailSenderImpl.setPort(587);
		mailSenderImpl.setUsername("vutrunrd@gmail.com");
		mailSenderImpl.setPassword("hoxlrclmayccdtfd");
		mailSenderImpl.setJavaMailProperties(getJavaMailProperties());
		return mailSenderImpl;
	}
	
	private Properties getJavaMailProperties() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
		return properties;
	}
	

}
