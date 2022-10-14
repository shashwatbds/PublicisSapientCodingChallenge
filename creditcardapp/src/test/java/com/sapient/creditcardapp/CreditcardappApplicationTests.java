package com.sapient.creditcardapp;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpHeaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sapient.creditcardapp.controller.CreditCardAppController;
import com.sapient.creditcardapp.model.CreditCard;
import com.sapient.creditcardapp.util.CreditCardValidationUtil;

import junit.framework.Assert;

@SpringBootTest
class CreditcardappApplicationTests extends AbstractTest {

	@Autowired
	public CreditCardAppController controller;
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void testEmptyCustomerName() {
		Assert.assertFalse(CreditCardValidationUtil.isCustomerNameValid(""));
	}

	@Test
	public void testNullCustomerName() {
		Assert.assertFalse(CreditCardValidationUtil.isCustomerNameValid(null));
	}
	
	@Test
	public void testValidCustomerName() {
		Assert.assertTrue(CreditCardValidationUtil.isCustomerNameValid("Customer Name"));// For now Special Characters are allowed in name.
	}
	
	@Test
	public void testCardNumberInvalidLength() {
		Assert.assertFalse(CreditCardValidationUtil.isCardNumberValid(new BigInteger("12345678901234567890")));
	}
	
	@Test
	public void testCardNumberValidLengthButFailedLuhnValidation() {
		Assert.assertFalse(CreditCardValidationUtil.isCardNumberValid(new BigInteger("123456789012340")));
	}
	
	@Test
	public void testCardNumberValidLengthandVaildLuhnValidation() {
		Assert.assertTrue(CreditCardValidationUtil.isCardNumberValid(new BigInteger("1358954993914435")));
	}
	
	@Test
	public void testNegativeCardLimit() {
		Assert.assertFalse(CreditCardValidationUtil.isCardLimitValid(new BigDecimal("-123")));
	}
	
	@Test
	public void testValidCardLimit() {
		Assert.assertTrue(CreditCardValidationUtil.isCardLimitValid(new BigDecimal("123")));
	}
	
	@Test
	public void testUpperdCardLimit() {
		Assert.assertFalse(CreditCardValidationUtil.isCardLimitValid(new BigDecimal("123456")));
	}
	
	@Test
	void testFetchAllCards() throws Exception {
		String uri = "/creditcardsystem/creditcard/fetchAll";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	void testSaveCard() throws Exception {
		String uri = "/creditcardsystem/creditcard/save";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
	            .post(uri)
	            .accept(MediaType.APPLICATION_JSON)
	            .content("{\n"
	            		+ "    \"cardNumber\": 1358954993914435,\n"
	            		+ "    \"customerName\": \"Shashwat\",\n"
	            		+ "    \"cardLimit\": 1200\n"
	            		+ "}")
	            .contentType(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
}
