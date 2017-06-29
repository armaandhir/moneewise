package com.armaandhir.moneewise.api;

import java.math.BigDecimal;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.armaandhir.moneewise.model.Expense;
import com.armaandhir.moneewise.model.ExpenseDesc;
import com.armaandhir.moneewise.service.ExpenseDescService;
import com.armaandhir.moneewise.service.ExpenseService;

@RestController
public class MoneeWiseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private ExpenseDescService expenseDescService;
	
	@RequestMapping("/")
    public String index() {
        return "Moneewise app";
    }
	
	
	
	/**
	 * @param stormpathEmail
	 * @param place
	 * @param amount
	 * @param currency
	 * @param categoryId
	 * @param description (optional)
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/moneewise/api/expense",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Expense> insertExpense (@RequestParam String stormpathEmail, @RequestParam String place,
    		@RequestParam BigDecimal amount, @RequestParam String currency, @RequestParam int categoryId,
    		@RequestParam(required=false) String description)
	{
        Money money = Money.of(CurrencyUnit.of(currency), amount);
		Expense expense = new Expense(stormpathEmail, place, money, categoryId);
		// TODO might want to persist data - decide to do here or another persistence layer
		Expense insertedExpense = expenseService.addExpense(expense);
		if (insertedExpense != null)
		{
			if (description != null)
			{
				ExpenseDesc inExpenseDesc = new ExpenseDesc();
				// Check for data before inserting
				inExpenseDesc.setExpenseId(insertedExpense.getId());
				inExpenseDesc.setDescription(description);
				ExpenseDesc outExpenseDesc = expenseDescService.addDesc(inExpenseDesc);
				if (outExpenseDesc != null)
				{
					return new ResponseEntity<Expense>(insertedExpense, HttpStatus.OK);
				}
				else 
				{
					return new ResponseEntity<Expense>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			return new ResponseEntity<Expense>(insertedExpense, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Expense>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	
	/**
	 * GET - All expenses for the month
	 * @param stormpath_email
	 * @param month
	 * @param year
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/moneewise/api/expense/month",
			method=RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Expense>> getExpenses (@RequestParam String stormpath_email, @RequestParam int month,
														@RequestParam int year)
	{
		List<Expense> expenses = expenseService.getExpensesByMonth(stormpath_email, month, year);
		return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
	}
	
	/**
	 * @param expense_id
	 * @param desc
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/moneewise/api/expense/description",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExpenseDesc> insertDesc (@RequestParam Long expense_id, @RequestParam String description)
	{
		if(expense_id != null && description != ""){
			ExpenseDesc inExpenseDesc = new ExpenseDesc();
			// Check for data before inserting
			inExpenseDesc.setExpenseId(expense_id);
			inExpenseDesc.setDescription(description);
			ExpenseDesc outExpenseDesc = expenseDescService.addDesc(inExpenseDesc);
			if (outExpenseDesc != null) {
				return new ResponseEntity<ExpenseDesc> (outExpenseDesc, HttpStatus.OK);
			}
			return new ResponseEntity<ExpenseDesc> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else{
			throw new IllegalArgumentException("invalid data");
		}
		
	}
	
	/**
	 * GET - Expense Description 
	 * @param expenseId
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/moneewise/api/expense/desc/{expense_id}",
			method=RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExpenseDesc> getExpenseDesc (@PathVariable("expense_id") Long expenseId)
	{
		if(expenseId != null){
			ExpenseDesc expenseDesc = expenseDescService.getDesc(expenseId);
			if (expenseDesc.getExpenseId() != null) {
				return new ResponseEntity<ExpenseDesc>(expenseDesc, HttpStatus.OK);
			}
			return new ResponseEntity<ExpenseDesc>(HttpStatus.NOT_FOUND);
		}
		else{
			throw new IllegalArgumentException("invalid data");
		}
	}
	
// end of class	
}
