package com.armaandhir.moneewise.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.armaandhir.moneewise.model.ExpenseDesc;

@Repository
public interface ExpenseDescRepository extends CrudRepository<ExpenseDesc, Long> {

	@Query(value="SELECT * FROM EXPENSE_DESC WHERE EXPENSE_ID = ?1", nativeQuery = true)
	ExpenseDesc findByExpenseId(Long expenseId);
}
