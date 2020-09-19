package com.cg.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.dao.IAccDao;
import com.cg.entity.AccTransaction;
import com.cg.entity.Account;
import com.cg.exceptions.AccountActiveException;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.TxnException;
import com.cg.util.CgConstants;
import com.cg.web.PassBookMicroService;

@Service
public class PassBookServiceImpl  implements IPassBookService {
	Logger logger = LoggerFactory.getLogger(PassBookMicroService.class);
	@Autowired
	private IAccDao accDao;
	@Autowired
	private RestTemplate rt;
	
	@Override
	public List<AccTransaction> getTransactions(LocalDate startDt, LocalDate endDt, String accountId) throws TxnException, AccountActiveException {
	
		if(validateDate(startDt, endDt)) {
			Optional<Account> optaccount = accDao.findById(accountId);
			if(!optaccount.isPresent())
				throw new AccountActiveException(CgConstants.IN_ACTIVE_ACCOUNT);
				
			List<AccTransaction>  txlist = accDao.getTransactions(startDt, endDt,accountId);
			if(txlist.isEmpty())
				throw new TxnException(CgConstants.TXN_NOT_AVAILABLE);
			txlist.sort((txn1, txn2)->txn1.getTransDate().compareTo(txn2.getTransDate()));
			
			return txlist;
		}
		return null;
	}
	
	public boolean validateDate(LocalDate startDt, LocalDate endDt) throws TxnException {
		
		if(startDt.isAfter(LocalDate.now()))
			throw new TxnException(CgConstants.START_AFTER_DATE);
		if(endDt.isBefore(startDt))
			throw new TxnException(CgConstants.BEFORE_START_DATE);
		return true;
	}

	@Override
	public List<AccTransaction> getTransactionById(String accountId) throws TxnException, AccountActiveException {
	
		if(validateDate(accountId)) {
			Optional<Account> optaccount = accDao.findById(accountId);
			if(!optaccount.isPresent())
				throw new AccountActiveException(CgConstants.NO_ACCOUNT_FOUND);
		
			List<AccTransaction>  txlist = accDao.getTransactionById(accountId);
			if(txlist.isEmpty())
				throw new TxnException(CgConstants.TXN_NOT_AVAILABLE);
			txlist.sort((txn1, txn2)->txn1.getTransDate().compareTo(txn2.getTransDate()));
			
			return txlist;
		}
		return null;
	}
	


	private boolean validateDate(String accountId) throws TxnException {
		if(accountId.isEmpty())
			throw new TxnException(CgConstants.START_AFTER_DATE);
		
		return true;
	}

	

	@Override
	public String validateTokenInLoginRestController(String tokenId) throws LoginException {
		if(tokenId==null||tokenId.length()==0)
			throw new LoginException(CgConstants.USER_NOT_AUTHORIZED);
		String url="http://localhost:8034/cgbank/verifylogin";
		String role=rt.postForObject(url, tokenId, String.class);
		logger.info(CgConstants.ROLE + role);
		if(role==null)
			throw new LoginException(CgConstants.USER_NOT_AUTHENTICATED);
		return role;

		
		
	}

	
}
