package com.sapient.creditcardapp.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCard {

	@Id
    @GeneratedValue
    private Long id;
    private Integer creditCardNumber;
	private String customerName;
	private Integer cardLimit;
	
	public CreditCard() {
		super();
	}

	public CreditCard(Integer creditCardNumber, String customerName, Integer cardLimit) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.customerName = customerName;
		this.cardLimit = cardLimit;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cardLimit, creditCardNumber, customerName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		return Objects.equals(cardLimit, other.cardLimit) && Objects.equals(creditCardNumber, other.creditCardNumber)
				&& Objects.equals(customerName, other.customerName);
	}
	
	@Override
	public String toString() {
		return "CreditCard [creditCardNumber=" + creditCardNumber + ", customerName=" + customerName + ", cardLimit="
				+ cardLimit + "]";
	}
	
	public Integer getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(Integer creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getCardLimit() {
		return cardLimit;
	}
	public void setCardLimit(Integer cardLimit) {
		this.cardLimit = cardLimit;
	}
}
