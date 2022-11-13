package com.clothingstore.service.impl;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Service
public class ThymeleafService {
	private static final String MAIL_TEMPALTE_BASE_NAME = "mail/MailMessages";
	private static final String MAIL_TEMPALTE_PREFIX = "/templates/";//thư mục template chứa file html
	private static final String MAIL_TEMPALTE_SUFFIX = ".html";// đuôi của file template html
	private static final String UTF_8 = "utf-8";
	private static final String TEMPLATE_FILE_NAME = "email-forgot-password";//tên file
	
	private static TemplateEngine templateEngine;
	
	static {
		templateEngine = emailTemplateEngine();
	}

	private static TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(htmlTemplateResolver());
		engine.setTemplateEngineMessageSource(emailMessageSource());
		return engine;
	}

	private static ResourceBundleMessageSource emailMessageSource() {
		final ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
		bundleMessageSource.setBasename(MAIL_TEMPALTE_BASE_NAME);
		return bundleMessageSource;
	}

	private static ITemplateResolver htmlTemplateResolver() {
		final ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix(MAIL_TEMPALTE_PREFIX);
		resolver.setSuffix(MAIL_TEMPALTE_SUFFIX);
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCharacterEncoding(UTF_8);
		resolver.setCacheable(false);
		return resolver;
	}
	
	public String getContent(String fullName,String token) {
		final Context context = new Context();
		context.setVariable("user", fullName);
		context.setVariable("token", token);
		return templateEngine.process(TEMPLATE_FILE_NAME, context);
	}
	
}
