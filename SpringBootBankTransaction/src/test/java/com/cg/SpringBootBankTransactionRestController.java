package com.cg;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cg.entity.AccTransaction;
import com.cg.exceptions.AccountActiveException;
import com.cg.exceptions.TxnException;

class SpringBootBankTransactionRestController {
	
	private RestTemplate rt = new RestTemplate();

	@Test
	public void testViewTransactionbyID() throws AccountActiveException,TxnException{
		String url ="http://localhost:8034/cgbank/viewpassbook/PBI1856427";
		ResponseEntity<List<AccTransaction>> resp = rt.exchange(url,HttpMethod.GET, null, new ParameterizedTypeReference<List<AccTransaction>>(){});
		assertFalse(resp.getBody().isEmpty());
	}
	@Test
	public void testViewTransactionbyIdNotFound() throws AccountActiveException,TxnException {
		String url = "http://localhost:8034/cgbank/viewpassbook/PBI1856420";
		assertThrows(HttpClientErrorException.class, ()-> rt.exchange(url,HttpMethod.GET,null, new ParameterizedTypeReference<List<AccTransaction>>(){}));
		
	}
	
	@Test
	public void testViewTransactionbetweenDates() {
		String url ="http://localhost:8034/cgbank/viewpassbook/1994-03-12/2020-03-02/PBI8391037";
		ResponseEntity<List<AccTransaction>> resp = rt.exchange(url,HttpMethod.GET, null, new ParameterizedTypeReference<List<AccTransaction>>(){});
		assertFalse(resp.getBody().isEmpty());
	}
	
	@Test
	public void testViewTransactionbetweenDatesNotFound() throws AccountActiveException,TxnException {
		String url = "http://localhost:8034/cgbank/viewpassbook/2021-03-12/2022-03-02/PBI8391037";
		assertThrows(HttpClientErrorException.class, ()-> rt.exchange(url,HttpMethod.GET,null, new ParameterizedTypeReference<List<AccTransaction>>(){}));
		
	}

}
