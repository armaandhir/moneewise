package com.armaandhir.moneewise.service;

import java.util.List;

import com.armaandhir.moneewise.model.Expense;

public interface ExpenseService {
	
	/**
	 * @param expense
	 * @return
	 */
	public Expense addExpense (Expense expense);
	
	public List<Expense> getExpensesByMonth (String stormpath_email, int month, int year);  
}
