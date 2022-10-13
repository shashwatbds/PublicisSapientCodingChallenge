package com.sapient.creditcardapp.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sapient.creditcardapp.model.CreditCard;

@Component
public class BootstrapDataLoader implements CommandLineRunner {

	public CreditCardRepository repository;

	@Autowired
	public BootstrapDataLoader(CreditCardRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		this.repository.saveAll(getDemoList());
	}

	private List<CreditCard> getDemoList() {
		List<CreditCard> defaultCards = new ArrayList<>();
		defaultCards.add(new CreditCard(1L, new BigInteger("1358954993914435"), "Customer One", new BigDecimal(1000), new BigDecimal(-1200)));
		defaultCards.add(new CreditCard(2L, new BigInteger("9499391443513585"), "Customer Two", new BigDecimal(1500), new BigDecimal(1000)));
		return defaultCards;
	}
}
