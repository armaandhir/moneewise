package com.armaandhir.moneewise.model;

import java.io.Serializable;
import java.math.BigDecimal;
//import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Expense implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="stormpath_email")
	private String stormpathEmail;
	
	@Column(name="created_at")
	//private LocalDateTime createdAt;
	private Date createdAt;
	
	//@Column(name="updated_at")
	//private LocalDateTime updatedAt;
	
	@Column(name="place")
	private String place;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="is_deleted")
	private short isDeleted;
	
	//
	@JsonIgnore
	private transient Money money;
	
	public Expense(){}
	
	public Expense(String stormpathEmail, String place, Money money, int categoryId){
		this.stormpathEmail = stormpathEmail;
		this.place = place;
		this.amount = money.getAmount();
		this.currency = money.getCurrencyUnit().getCurrencyCode();
		this.categoryId = categoryId;
		this.isDeleted = 0;
		this.createdAt = null;
		//this.updatedAt = null;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStormpathEmail() {
		return stormpathEmail;
	}

	public void setStormpathEmail(String stormpathEmail) {
		this.stormpathEmail = stormpathEmail;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/*
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	*/

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	// look into this
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}
	
	// look into this
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public short getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(short isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Money getMoney() {
		if (money == null) {
            money = Money.of(CurrencyUnit.of(currency), amount);
        }
        return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

}
