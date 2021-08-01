package com.project.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetail {
	
	private String customerId;
	private String orderId;
	private String carNumber;
	private String carModel;
	private String transactionAmount;
	
}
