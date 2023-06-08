package com.ibm.example.messages;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Incoming implements Serializable {

	@NotBlank(message = "Missing customer name")
	private String customerName;

	@NotBlank
	@Size(min = 8, max = 11, message = "SWIFT code is incorrect")
	private String swiftCode;

	@NotBlank
	@Size(min = 5, max = 34, message = "IBAN code is incorrect")
	private String IBAN;

	@NotBlank
	@Size(min = 3, max = 3, message = "Currency abbreviation is incorrect")
	private String currency;

	@Digits(integer = 6, fraction = 2, message = "Amount is incorrect")
	private BigDecimal amount;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
