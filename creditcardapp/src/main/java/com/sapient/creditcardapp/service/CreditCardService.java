package com.sapient.creditcardapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.creditcardapp.model.CreditCard;
import com.sapient.creditcardapp.repository.CreditCardRepository;

@Service
public class CreditCardService {
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

	public List<CreditCard> getAllCreditCards() {
		return creditCardRepository.findAll();
	}

	public CreditCard saveCreditCardDetails(CreditCard creditCard) {
		return this.creditCardRepository.save(creditCard);
	}
}
