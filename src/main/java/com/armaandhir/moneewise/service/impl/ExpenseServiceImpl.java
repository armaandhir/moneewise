package com.armaandhir.moneewise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.armaandhir.moneewise.model.Expense;
import com.armaandhir.moneewise.repository.ExpenseRepository;
import com.armaandhir.moneewise.service.ExpenseService;

@Service
@Transactional(
		propagation = Propagation.SUPPORTS,
		readOnly = true)
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Override
	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			readOnly = false)
	public Expense addExpense(Expense expense) {
		if (expense == null) {
			return null;
		}
		else {
			Expense addedExpense = expenseRepository.save(expense);
			return addedExpense;
		}
	}

	@Override
	@Transactional
	public List<Expense> getExpensesByMonth(String stormpath_email, int month, int year) {
		System.out.println(stormpath_email);
		List<Expense> expenseCollection = expenseRepository.findByMonth(stormpath_email, month, year);
		return expenseCollection;
	}

}
