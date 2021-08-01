package com.project.customer.model;





import java.util.HashSet;
import java.util.Set;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	
	@Id
	private String id;
	private String username;
	private String name;
	private String email;
	private String password;
	private String contact;
	private String Address;
	private Binary profilePic;

	private Set<Role> roles=new HashSet<>();
	
	public boolean isEnabled;
}
