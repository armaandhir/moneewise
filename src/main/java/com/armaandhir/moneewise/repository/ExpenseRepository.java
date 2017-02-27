package com.armaandhir.moneewise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.armaandhir.moneewise.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	/*
	@query
	public void expensesByMonth (){
	
	}
	*/
	
	
	@Query(value = "SELECT * FROM EXPENSE WHERE STORMPATH_EMAIL = ?1 AND MONTH(CREATED_AT) = ?2 AND YEAR(CREATED_AT) = ?3 AND IS_DELETED = 0",
			nativeQuery = true)
	List<Expense> findByMonth(String stormpath_email, int month, int year);
}
