package com.project.customer.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.customer.model.Customer;


public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String name;
	
	private String username;

	private String email;

	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl(String id, String name,String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name=name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<Role> roles=user.getRoles();
//		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
//		
//		for(Role role:roles) {
//			authorities.add(new SimpleGrantedAuthority(role.getName().name()));
//		}
//		return authorities;
//	}

	public static UserDetailsImpl build(Customer user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
		
	

		return new UserDetailsImpl(
				user.getId(), 
				user.getName(),
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public String getId() {

		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {

		return email;
	}
	
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
