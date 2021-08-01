package com.project.customer.VO;

import java.util.List;

import com.project.customer.model.Car;
import com.project.customer.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
	private Customer customer;
	private Car[] car;
}
