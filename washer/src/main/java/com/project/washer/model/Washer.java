package com.project.washer.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="washers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Washer {

	@Id
	private String id;
	private String name;
	private String username;
	private int age;
	private String email;
	private String password;
	private String Contact;
	private String Address;
	
	@DBRef
	private Set<Role> roles=new HashSet<>();
	
	public boolean isEnabled;
}
