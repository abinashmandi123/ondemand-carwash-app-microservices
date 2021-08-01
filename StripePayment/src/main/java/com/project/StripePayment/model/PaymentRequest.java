package com.project.StripePayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
	public enum Currency{
		INR,USD;
	}
	   private String description;
	    private int amount;
	    private Currency currency;
	    private String stripeEmail;
	    private Token token;
}
