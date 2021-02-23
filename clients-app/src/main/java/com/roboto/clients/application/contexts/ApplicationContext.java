package com.roboto.clients.application.contexts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.roboto.clients.message.source.ReloadableApiErrorResourceBundle;

@Configuration
public class ApplicationContext {
	
	@Bean
	public ReloadableApiErrorResourceBundle apiErrorMessageSource() {
		ReloadableApiErrorResourceBundle apiErrorMessageSource = new ReloadableApiErrorResourceBundle();
		apiErrorMessageSource.setBasename("classpath:messages");
		apiErrorMessageSource.setCacheSeconds(3600);
		return apiErrorMessageSource;
	}

}
