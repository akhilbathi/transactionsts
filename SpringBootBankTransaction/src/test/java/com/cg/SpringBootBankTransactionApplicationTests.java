package com.cg;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.entity.AccTransaction;
import com.cg.exceptions.AccountActiveException;
import com.cg.exceptions.TxnException;
import com.cg.service.IPassBookService;

@SpringBootTest
class SpringBootBankTransactionApplicationTests {
	@Autowired
	private IPassBookService passbookservice;

	@Test
	public void testtransactionbyIDfound() throws AccountActiveException, TxnException {
		List<AccTransaction> acc = passbookservice.getTransactionById("PBI1856427");
		assertNotNull(acc);
		
	}
	@Test
	public void testbytransactionbyIDNotfound() throws AccountActiveException, TxnException {
		assertThrows(AccountActiveException.class, ()->passbookservice.getTransactionById("PBI1856420"));
		
		
	}
	@Test
	public void testbytransactionbyDatefound()throws AccountActiveException, TxnException {
		List<AccTransaction> acc = passbookservice.getTransactions(LocalDate.of(1993, 01, 12), LocalDate.of(2020, 01, 12), "PBI1856427");
		assertNotNull(acc);
	}
	@Test
	public void testbytransactionbyDateNotFound() throws AccountActiveException, TxnException{
		assertThrows(TxnException.class, ()->passbookservice.getTransactions(LocalDate.of(2022, 02, 21), LocalDate.of(2023, 03, 22), "PBI1856427"));
		
		
	}
}
