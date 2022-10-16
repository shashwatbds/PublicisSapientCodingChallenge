package com.sapient.creditcardapp.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sapient.creditcardapp.model.CreditCard;

public final class CreditCardDataUtil {
	
	private CreditCardDataUtil() {}
	
	private static final List<CreditCard> currentActiveCards;
	
	static {
		currentActiveCards = new ArrayList<>();
		currentActiveCards.add(new CreditCard(1L, "1358954993914435", "Customer One", new BigDecimal(1000), new BigDecimal(-1200)));
		currentActiveCards.add(new CreditCard(2L, "9499391443513585", "Customer Two", new BigDecimal(1500), new BigDecimal(1000)));
	}
	
	public static List<CreditCard> getCreditCardList() {
		return currentActiveCards;
	}
}
