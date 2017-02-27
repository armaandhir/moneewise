package com.armaandhir.moneewise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.armaandhir.moneewise.model.ExpenseDesc;
import com.armaandhir.moneewise.repository.ExpenseDescRepository;
import com.armaandhir.moneewise.service.ExpenseDescService;

@Service
@Transactional(
		propagation = Propagation.SUPPORTS,
		readOnly = true)
public class ExpenseDescServiceImpl implements ExpenseDescService {
	
	@Autowired
	private ExpenseDescRepository expenseDescRepository;
	
	@Override
	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			readOnly = false)
	public ExpenseDesc addDesc(ExpenseDesc inExpenseDesc) {
		if (inExpenseDesc != null) {
			return expenseDescRepository.save(inExpenseDesc);
		}
		return null;
	}
	
	@Override
	@Transactional
	public ExpenseDesc getDesc(Long expenseId) {
		if (expenseId != null) {
			return expenseDescRepository.findByExpenseId(expenseId);
		}
		return null;
	}


}
