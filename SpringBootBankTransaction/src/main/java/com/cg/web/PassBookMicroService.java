package com.cg.web;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.AccTransaction;
import com.cg.exceptions.AccountActiveException;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.TxnException;
import com.cg.service.IPassBookService;
import com.cg.util.CgConstants;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
public class PassBookMicroService {
	Logger logger = LoggerFactory.getLogger(PassBookMicroService.class);
	@Autowired
	private IPassBookService passBookService;
	
	@GetMapping("viewpassbook/{startdt}/{enddt}/{aid}")
	public List<AccTransaction> getPassBookService(@PathVariable("startdt") @DateTimeFormat(pattern="yyyy-M-d") LocalDate startDt,
			@PathVariable("enddt") @DateTimeFormat(pattern="yyyy-M-d")LocalDate endDt,@PathVariable("aid") String accountId) throws TxnException, AccountActiveException{
		
		
		return passBookService.getTransactions(startDt, endDt, accountId);
	}
	
	@GetMapping("viewpassbook/{aid}")
	public List<AccTransaction> getPassBookService(@PathVariable("aid") String accountId) throws TxnException, AccountActiveException {
		
		
		
		return passBookService.getTransactionById(accountId);
	}
	
	@GetMapping("viewall/{aid}")
	public List<AccTransaction> viewall(@RequestHeader(name = "tokenId" , required = false) String tokenId,@PathVariable("aid") String accountId) throws LoginException, TxnException, AccountActiveException{
        logger.info(CgConstants.TOKEN_ID+ tokenId);
		
		String role=passBookService.validateTokenInLoginRestController(tokenId);
		logger.info(CgConstants.ROLE+role);
		List<AccTransaction> elist = passBookService.getTransactionById(accountId);
		
		
		return elist;
		
		
	}
	
	

}