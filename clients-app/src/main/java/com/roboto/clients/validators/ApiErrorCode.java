package com.roboto.clients.validators;

public enum ApiErrorCode {
	
	ALREADY_EXISTS("AlreadyExists");

    private String code;

    ApiErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
