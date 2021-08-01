package com.project.customer.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistration {
	
	private String name;
	private String username;
	private String email;
	private String password;
	public Set<String> roles;

}
