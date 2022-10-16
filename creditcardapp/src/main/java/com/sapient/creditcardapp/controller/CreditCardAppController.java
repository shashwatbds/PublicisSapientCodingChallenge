package com.sapient.creditcardapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.creditcardapp.model.CreditCard;
import com.sapient.creditcardapp.service.CreditCardService;
import com.sapient.creditcardapp.util.CreditCardValidationUtil;

/**
 * 1) Test Coverage
 * 2) Validation - send appropriate response code and message
 * 3) Rest API authentication.
 * */
@RestController
@RequestMapping("/creditcardsystem/creditcard")
@CrossOrigin(origins="http://localhost:3000")
public class CreditCardAppController {

	@Autowired
	private CreditCardService creditCardService;

	public CreditCardAppController(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	@GetMapping("/fetchAll")
	public List<CreditCard> getClients() {
		return creditCardService.getAllCreditCards();
	}

	@PostMapping("/save")
	public ResponseEntity createClient(@RequestBody CreditCard creditCard) throws URISyntaxException {
		StringBuilder errorMessage = new StringBuilder("");
		if(CreditCardValidationUtil.isValidInput(creditCard,errorMessage)) {
			CreditCard savedCreditCard = creditCardService.saveCreditCardDetails(creditCard);
			return ResponseEntity.created(new URI("/creditcardsystem/creditcard/save" + savedCreditCard.getId())).body(savedCreditCard);
		} else 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());//Return correct response code
	}
}
