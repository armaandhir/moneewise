package com.armaandhir.moneewise.service;

import com.armaandhir.moneewise.model.ExpenseDesc;

public interface ExpenseDescService {
	
	/**
	 * Inserts the description for the given Expense
	 * @param inExpenseDesc
	 * @return
	 */
	public ExpenseDesc addDesc (ExpenseDesc inExpenseDesc);
	
	/**
	 * Gets the description of the given Expense
	 * @param expenseId
	 * @return
	 */
	public ExpenseDesc getDesc (Long expenseId);
	
}
