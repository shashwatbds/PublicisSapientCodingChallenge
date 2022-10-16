package com.sapient.creditcardapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sapient.creditcardapp.controller.CreditCardAppController;
import com.sapient.creditcardapp.util.CreditCardValidationUtil;

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
		StringBuilder errorMessage = new StringBuilder();
		assertFalse(CreditCardValidationUtil.isCustomerNameValid("", errorMessage));
		assertEquals(CreditCardValidationUtil.CUST_NAME_INVALID, errorMessage.toString());
	}

	@Test
	public void testNullCustomerName() {
		StringBuilder errorMessage = new StringBuilder();
		assertFalse(CreditCardValidationUtil.isCustomerNameValid(null, errorMessage));
		assertEquals(CreditCardValidationUtil.CUST_NAME_INVALID, errorMessage.toString());
	}

	@Test
	public void testValidCustomerName() {
		StringBuilder errorMessage = new StringBuilder();
		assertTrue(CreditCardValidationUtil.isCustomerNameValid("Customer Name", errorMessage));// For now Special Characters are allowed in name.
		assertTrue(errorMessage.toString().isEmpty());
	}

	@Test
	public void testCardNumberInvalidLength() {
		StringBuilder errorMessage = new StringBuilder();
		assertFalse(CreditCardValidationUtil.isCardNumberValid("12345678901234567890", errorMessage));
		assertEquals(errorMessage.toString(), CreditCardValidationUtil.CARD_NUM_LEN_INVALID);
	}

	@Test
	public void testCardNumberValidLengthButFailedLuhnValidation() {
		StringBuilder errorMessage = new StringBuilder();
		assertFalse(CreditCardValidationUtil.isCardNumberValid("123456789012340", errorMessage));
		assertEquals(CreditCardValidationUtil.CARD_NUM_FORMAT_INVALID, errorMessage.toString());
	}

	@Test
	public void testCardNumberValidLengthAndVaildLuhnValidation() {
		StringBuilder errorMessage = new StringBuilder();
		assertTrue(CreditCardValidationUtil.isCardNumberValid("1558954993914433", errorMessage));
		assertTrue(errorMessage.toString().isEmpty());
	}

	@Test
	public void testNegativeCardLimit() {
		StringBuilder errorMessage = new StringBuilder();
		assertFalse(CreditCardValidationUtil.isCardLimitValid(new BigDecimal("-123"), errorMessage));
		assertEquals(CreditCardValidationUtil.CARD_LIM_INVALID, errorMessage.toString());
	}

	@Test
	public void testValidCardLimit() {
		StringBuilder errorMessage = new StringBuilder();
		assertTrue(CreditCardValidationUtil.isCardLimitValid(new BigDecimal("123"), errorMessage));
		assertTrue(errorMessage.toString().isEmpty());
	}

	@Test
	public void testUpperdCardLimit() {
		StringBuilder errorMessage = new StringBuilder();
		assertFalse(CreditCardValidationUtil.isCardLimitValid(new BigDecimal("123456"), errorMessage));
		assertEquals(CreditCardValidationUtil.CARD_LIM_INVALID, errorMessage.toString());
	}

	@Test
	public void testDuplicateCreditCardNumber() {
		StringBuilder errorMessage = new StringBuilder();
		assertFalse(CreditCardValidationUtil.isCardNumberUnique("1358954993914435", errorMessage));
		assertEquals(CreditCardValidationUtil.DUP_CARD_NUM_INVALID, errorMessage.toString());
	}

	@Test
	public void testUniqueCreditCardNumber() {
		StringBuilder errorMessage = new StringBuilder();
		assertTrue(CreditCardValidationUtil.isCardNumberUnique("1358954993914534", errorMessage));
		assertTrue(errorMessage.toString().isEmpty());
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
						+ "    \"cardNumber\": 1853954993914435,\n"
						+ "    \"customerName\": \"Shashwat\",\n"
						+ "    \"cardLimit\": 1200\n"
						+ "}")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
}
