package com.project.washer.payload.request;

import java.util.Set;

import com.project.washer.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

	private String name;
	private String username;
	private String email;
	private String password;
	private Set<String> roles;
}
