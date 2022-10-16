package com.sapient.creditcardapp.util;

import java.math.BigDecimal;
import java.util.Arrays;
import com.sapient.creditcardapp.model.CreditCard;

public abstract class CreditCardValidationUtil {

	public static final String CUST_NAME_INVALID = "Customer Name is Invalid";
	public static final String CARD_LIM_INVALID = "Credit Card Limit Invalid";
	public static final String CARD_NUM_FORMAT_INVALID = "Invalid Credit Card Number format";
	public static final String DUP_CARD_NUM_INVALID = "Credit Card Number already exists";
	public static final String CARD_NUM_LEN_INVALID = "Credit Card number length must be between 0 and 19";
	
	private CreditCardValidationUtil() {};

	public static boolean isValidInput(CreditCard creditCard, StringBuilder errorMessage) {
		return isCardNumberValid(creditCard.getCardNumber(), errorMessage) && isCardLimitValid(creditCard.getCardLimit(), errorMessage) && isCustomerNameValid(creditCard.getCustomerName(), errorMessage);
	}

	public static boolean isCustomerNameValid(String customerName, StringBuilder errorMessage) {
		boolean isValid = null != customerName && !customerName.isEmpty();
		if(!isValid)
			errorMessage.append(CUST_NAME_INVALID);
		return isValid;
	}

	public static boolean isCardLimitValid(BigDecimal cardLimit, StringBuilder errorMessage) {
		boolean isValid = cardLimit.compareTo(BigDecimal.ZERO) > 0 && cardLimit.toString().length()<6;
		if(!isValid)
			errorMessage.append(CARD_LIM_INVALID);
		return isValid;
	}

	public static boolean isCardNumberValid(String creditCardNumber, StringBuilder errorMessage) {

		String cardNumber = creditCardNumber.toString();
		if(isLengthValid(creditCardNumber, errorMessage) && isCardNumberUnique(creditCardNumber, errorMessage)) {
			int[] cardNumArr = new int[creditCardNumber.toString().length()];
			for(int i=0;i<cardNumber.length();i++) {
				char c= cardNumber.charAt(i);
				cardNumArr[i]=  Integer.parseInt(""+c);
			}

			for(int i=cardNumArr.length-2;i>=0;i=i-2) {
				int num = cardNumArr[i];
				num = num * 2; 
				if(num>9)
					num = num%10 + num/10;  

				cardNumArr[i]=num;
			}

			int sum = sumDigits(cardNumArr);
			if(sum%10==0) {
				return true;
			}
			errorMessage.append(CARD_NUM_FORMAT_INVALID);
			return false;
		}

		return false ;
	}

	public static boolean isCardNumberUnique(String creditCardNumber, StringBuilder errorMessage) {
		boolean isValid = CreditCardDataUtil.getCreditCardList().stream().anyMatch(c -> c.getCardNumber().equalsIgnoreCase(creditCardNumber));
		if(isValid)
			errorMessage.append(DUP_CARD_NUM_INVALID);
		return !isValid;
	}

	private static boolean isLengthValid(String creditCardNumber, StringBuilder errorMessage) {
		boolean isValid = creditCardNumber.toString().length()<=19;
		if(!isValid)
			errorMessage.append(CARD_NUM_LEN_INVALID);
		return isValid;
	}

	public static int sumDigits(int[] arr) {
		return Arrays.stream(arr).sum();
	}
}
