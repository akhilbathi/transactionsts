package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.AccTransaction;
@Repository
public interface ITtxnDao extends JpaRepository<AccTransaction, Long> {

}
