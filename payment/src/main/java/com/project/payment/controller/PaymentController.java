package com.project.payment.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.paytm.pg.merchant.PaytmChecksum;

import com.project.payment.model.PaymentDetail;
import com.project.payment.model.PaytmDetail;
import com.project.payment.model.Request;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PaymentController {
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Autowired
//	private PaytmDetail paytmDetail;
//	
//	@Autowired
//	private Environment env;
//	
//	@GetMapping("/")
//	public String home() {
//		return "home";
//	}
	
//	@PostMapping(value="/order",consumes= {MediaType.APPLICATION_JSON_VALUE})
//	public Order createOrder(@RequestBody  Request request) throws RazorpayException {
//		RazorpayClient razorpay = new RazorpayClient("rzp_test_Bw7Y5q3mrb014T", "0kGSpXk39FYB1N7fHRlQPf0v");
//		JSONObject orderRequest = new JSONObject();
//		orderRequest.put("amount", request.getAmount()); // amount in the smallest currency unit
//		orderRequest.put("currency", request.getCurrency());
//		orderRequest.put("receipt", request.getReceipt());
//		Order order = razorpay.Orders.create(orderRequest);
//		return order;
//		
//	}

//	 @PostMapping(value = "/submitPaymentDetail")
//	    public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
//	                                    @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
//	                                    @RequestParam(name = "ORDER_ID") String orderId) throws Exception {
//
//	        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetail.getPaytmUrl());
////	        ModelAndView modelAndView = new ModelAndView();
//	        TreeMap<String, String> parameters = new TreeMap<>();
//	        paytmDetail.getDetails().forEach((k, v) -> parameters.put(k, v));
//	        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
//	        parameters.put("EMAIL", env.getProperty("paytm.email"));
//	        parameters.put("ORDER_ID", orderId);
//	        parameters.put("TXN_AMOUNT", transactionAmount);
//	        parameters.put("CUST_ID", customerId);
//	        String checkSum = getCheckSum(parameters);
//	        parameters.put("CHECKSUMHASH", checkSum);
//	        modelAndView.addAllObjects(parameters);
//	        return modelAndView;
//	    }
	
//	 @PostMapping("/submitPaymentDetail")
//	public ResponseEntity<?> getRedirect(@RequestBody PaymentDetail paymentDetail) throws Exception{
//		 TreeMap<String, String> parameters = new TreeMap<>();
//	        paytmDetail.getDetails().forEach((k, v) -> parameters.put(k, v));
//	        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
//	        parameters.put("EMAIL", env.getProperty("paytm.email"));
//	        parameters.put("ORDER_ID", paymentDetail.getOrderId());
//	        parameters.put("TXN_AMOUNT", paymentDetail.getTransactionAmount());
//	        parameters.put("CUST_ID", paymentDetail.getCustomerId());
//	        String checkSum = getCheckSum(parameters);
//	        parameters.put("CHECKSUMHASH", checkSum);
//		return null;
//		 
//	 }
	 
//	 @PostMapping(value = "/pgresponse")
//	    public String getResponseRedirect(HttpServletRequest request, Model model) {
//
//	        Map<String, String[]> mapData = request.getParameterMap();
//	        TreeMap<String, String> parameters = new TreeMap<String, String>();
//	        String paytmChecksum = "";
//	        for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {
//	            if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())){
//	                paytmChecksum = requestParamsEntry.getValue()[0];
//	            } else {
//	            	parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
//	            }
//	        }
//	        String result;
//
//	        boolean isValideChecksum = false;
//	        System.out.println("RESULT : "+parameters.toString());
//	        try {
//	            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
//	            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
//	                if (parameters.get("RESPCODE").equals("01")) {
//	                    result = "Payment Successful";
//	                } else {
//	                    result = "Payment Failed";
//	                }
//	            } else {
//	                result = "Checksum mismatched";
//	            }
//	        } catch (Exception e) {
//	            result = e.toString();
//	        }
//	        model.addAttribute("result",result);
//	        parameters.remove("CHECKSUMHASH");
//	        model.addAttribute("parameters",parameters);
//	        return "report";
//	    }
//
//	    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
//	        return PaytmChecksum.verifySignature(parameters,
//	                paytmDetail.getMerchantKey(), paytmChecksum);
//	    }
//	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
//		return PaytmChecksum.generateSignature(parameters, paytmDetail.getMerchantKey());
//	}
//	
}

