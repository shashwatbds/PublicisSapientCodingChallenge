package com.sapient.creditcardapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sapient.creditcardapp.util.CreditCardDataUtil;

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
		this.repository.saveAll(CreditCardDataUtil.getCreditCardList());
	}
}
