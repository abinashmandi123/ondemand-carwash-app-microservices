package com.project.washer.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	private String token;
//	private String type = "Bearer";
	private String id;
	private String name;
	private String username;
	private String email;
	private List<String> roles;
}
