package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.AccTransaction;
import com.cg.exceptions.AccountActiveException;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.TxnException;

public interface IPassBookService {
	
	public List<AccTransaction> getTransactions(LocalDate startDt, LocalDate endDt, String accountId) throws TxnException, AccountActiveException;
	public List<AccTransaction> getTransactionById(String accountId) throws TxnException, AccountActiveException;
	public String validateTokenInLoginRestController(String tokenId) throws LoginException;

}
