package com.roboto.clients.application.contexts;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.roboto.clients.message.source.ReloadableApiErrorResourceBundle;

@Configuration
public class ApplicationContext {
	
	/*
	 * @Bean public ReloadableApiErrorResourceBundle apiErrorMessageSource() {
	 * ReloadableApiErrorResourceBundle apiErrorMessageSource = new
	 * ReloadableApiErrorResourceBundle();
	 * apiErrorMessageSource.setBasename("classpath:messages");
	 * apiErrorMessageSource.setCacheSeconds(3600); return apiErrorMessageSource; }
	 */
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(3600);
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

}
