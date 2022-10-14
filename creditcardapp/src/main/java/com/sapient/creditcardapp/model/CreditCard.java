package com.sapient.creditcardapp.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "credit_card")
public class CreditCard {

	@Id
    @GeneratedValue
    private Long id; // do not want in future credit card number to be used as foreign key, even customer name.
    private String cardNumber;
	private String customerName;
	private BigDecimal cardLimit;
	private BigDecimal cardBalance = BigDecimal.ZERO;;
	
	public CreditCard() {
		super();
	}

	public CreditCard(Long id, String cardNumber, String customerName, BigDecimal cardLimit,
			BigDecimal cardBalance) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.customerName = customerName;
		this.cardLimit = cardLimit;
		this.cardBalance = cardBalance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(BigDecimal cardLimit) {
		this.cardLimit = cardLimit;
	}

	public BigDecimal getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(BigDecimal cardBalance) {
		this.cardBalance = cardBalance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardBalance, cardLimit, cardNumber, customerName);
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
		return Objects.equals(cardBalance, other.cardBalance) && Objects.equals(cardLimit, other.cardLimit)
				&& Objects.equals(cardNumber, other.cardNumber) && Objects.equals(customerName, other.customerName);
	}

	@Override
	public String toString() {
		return "CreditCard [cardNumber=" + cardNumber + ", customerName=" + customerName + ", cardLimit=" + cardLimit
				+ ", cardBalance=" + cardBalance + "]";
	}
}
