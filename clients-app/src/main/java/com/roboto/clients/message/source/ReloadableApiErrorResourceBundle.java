package com.roboto.clients.message.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ReloadableApiErrorResourceBundle extends ReloadableResourceBundleMessageSource {
	
	public ReloadableApiErrorResourceBundle() {
		super();
	}
	
	public String[] getMessage(FieldError fldError, Object[] args, Locale locale, boolean msgAsCode) {
		List<String> codes = new ArrayList<String>(Arrays.asList(fldError.getCodes()));
		if(msgAsCode) {
			codes.add(fldError.getDefaultMessage());
			}
		String[] codeMsg = resolveMessage(codes, args, locale);
		return codeMsg;
	}
	
	public String[] getMessage(ObjectError objError, Object[] args, Locale locale, boolean msgAsCode) {
		List<String> codes = new ArrayList<String>(Arrays.asList(objError.getCodes()));
		if(msgAsCode) {
			codes.add(objError.getDefaultMessage());
			}
		String[] codeMsg = resolveMessage(codes, args, locale);
		return codeMsg;
	}
	
	private String[] resolveMessage(List<String> codes, Object[] args, Locale locale) {
		for (String code: codes) {
			String msg = this.getMessageInternal(code,  args,  locale);
			if(msg != null) {
				return new String[] {code, msg};
			}
		}
		String strCodes = codes.stream().collect(Collectors.joining(", "));
		throw new NoSuchMessageException(strCodes,locale);
	}

}
