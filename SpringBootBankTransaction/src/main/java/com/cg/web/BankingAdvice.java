package com.cg.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.dto.Error;
import com.cg.dto.ErrorInfo;
import com.cg.exceptions.AccountActiveException;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.TxnException;
@RestControllerAdvice

public class BankingAdvice {
	
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	public Error handleAccountException(AccountActiveException ex) {
		return new Error(HttpStatus.FORBIDDEN.toString(),ex.getMessage(), LocalDateTime.now().toString());
		
	}
	
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public Error handleTxnException(TxnException ex) {
		return new Error(HttpStatus.NOT_FOUND.toString(),ex.getMessage(), LocalDateTime.now().toString());
		
	}
	
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	public ErrorInfo handleLoginException(LoginException ex) {
		return new ErrorInfo(HttpStatus.FORBIDDEN.toString(),ex.getMessage().toString());
		
	}


}
