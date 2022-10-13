package com.sapient.creditcardapp.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import com.sapient.creditcardapp.model.CreditCard;

public class CreditCardValidationUtil {

	public static boolean isValidInput(CreditCard creditCard) {
		return isCardNumberValid(creditCard.getCardNumber()) && isCardLimitValid(creditCard.getCardLimit()) && isCustomerNameValid(creditCard.getCustomerName());
	}
	
	public static boolean isCustomerNameValid(String customerName) {
		return null != customerName && !customerName.isEmpty();
	}

	public static boolean isCardLimitValid(BigDecimal cardLimit) {
		return cardLimit.compareTo(BigDecimal.ZERO) > 0 && cardLimit.toString().length()<6;
	}

	public static boolean isCardNumberValid(BigInteger creditCardNumber) {
		
		String cardNumber = creditCardNumber.toString();
		if(creditCardNumber.toString().length()<=19) {
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
	        if(sum%10==0) 
	            return true;
	        
	        return false;
		}
		
		return false ;
	}
	
	public static int sumDigits(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}
