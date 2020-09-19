package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.AccTransaction;
import com.cg.entity.Account;

@Repository
public interface IAccDao  extends  JpaRepository<Account,String>{
	
	@Query("from AccTransaction tx  where tx.transDate >= :startDt and tx.transDate <= :endDt and tx.account.accountId=:accId")
	public List<AccTransaction> getTransactions(@Param("startDt")LocalDate startDt, @Param("endDt") LocalDate endDt, @Param("accId")String accountId);

	@Query("from AccTransaction tx where tx.account.accountId=:accId")
	public List<AccTransaction> getTransactionById(@Param("accId") String accountId);
}