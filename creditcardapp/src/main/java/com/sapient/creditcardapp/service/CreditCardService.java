package com.sapient.creditcardapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.creditcardapp.model.CreditCard;
import com.sapient.creditcardapp.repository.CreditCardRepository;
import com.sapient.creditcardapp.util.CreditCardDataUtil;

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
		CreditCard savedCreditCard = this.creditCardRepository.save(creditCard);
		CreditCardDataUtil.getCreditCardList().add(savedCreditCard);
		return savedCreditCard;
	}
}
