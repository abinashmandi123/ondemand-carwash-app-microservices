package com.project.washer.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.washer.model.BookingStatus;
import com.project.washer.model.ERole;
import com.project.washer.model.Request;
import com.project.washer.model.Role;
import com.project.washer.model.User;
import com.project.washer.model.Washer;
import com.project.washer.payload.request.LoginRequest;
import com.project.washer.payload.request.SignupRequest;
import com.project.washer.payload.response.JwtResponse;
import com.project.washer.payload.response.MessageResponse;
import com.project.washer.repository.RoleRepository;
import com.project.washer.repository.WasherRepository;
import com.project.washer.service.UserDetailsImpl;
import com.project.washer.service.WasherService;
import com.project.washer.utils.JwtUtils;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/washers")
public class WasherController {
	
	final Logger logger=LoggerFactory.getLogger(WasherController.class);

	@Autowired
	private WasherService washerService;
	
	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	@GetMapping("/dashboard")
	private String test() {
		return "Welcome to the DashBoard "+SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	
	
@PostMapping("/register")
private ResponseEntity<MessageResponse> registerWasher(@RequestBody SignupRequest washer){
	logger.info("Inside registerWasher method of WasherController");
	if (washerRepository.existsByUsername(washer.getUsername())) {
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: Username is already taken!"));
	}

	if (washerRepository.existsByEmail(washer.getEmail())) {
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: Email is already in use!"));
	}
	
	String name=washer.getName();
	String username=washer.getUsername();
	String email=washer.getEmail();
	String password=washer.getPassword();
	Washer washerModel=new Washer();
	washerModel.setName(name);
	washerModel.setUsername(username);
	washerModel.setEmail(email);
	washerModel.setPassword(password);
	Set<String> strRoles = washer.getRoles();
	Set<Role> roles = new HashSet<>();

	if (strRoles == null) {
//		Role userRole = roleRepository.findByName(ERole.ROLE_WASHER)
//				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//		roles.add(userRole);
		Role userRole=new Role();
		userRole.setName(ERole.ROLE_WASHER);
		roles.add(userRole);
	} else {
		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(adminRole);

				break;
			case "customer":
				Role modRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(modRole);

				break;
			default:
				Role userRole = roleRepository.findByName(ERole.ROLE_WASHER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			}
		});
	}

	washerModel.setRoles(roles);
	washerService.saveWasher(washerModel);
	
	return ResponseEntity.ok(new MessageResponse("Washer registered successfully!"));
	
	}

@PostMapping("/login")
public JwtResponse authenticateUser( @RequestBody LoginRequest loginRequest) {


	
	 Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
	

	

	return new JwtResponse(jwt, 
											 userDetails.getId(), 
											 userDetails.getName(),
											 userDetails.getUsername(), 
											 userDetails.getEmail(), 
											 roles);
}

//	@PostMapping("/login")
//	private ResponseEntity<String> authenticateWasher(@RequestBody Washer washer){
//		logger.info("Inside authenticateWasher method of WasherController");
//		String username=washer.getUsername();
//		String password=washer.getPassword();
//		
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
//		} catch (Exception e) {
//			System.out.println(e);
//			return ResponseEntity.ok("Error during washer authentication "+username );
//		}
//		
//		UserDetails loadedUser=washerService.loadUserByUsername(username);
//		String generatedToken=jwtUtils.generateToken(loadedUser);
//		
//		return ResponseEntity.ok("Succesful Authentication for washer "+username+" "+generatedToken );
//	}
	
	@GetMapping("/getAll")
	private List<Washer> getAllWasher(){
		return washerService.getAllWasher();
	}
	
	
	@GetMapping("/get/{id}")
	public Optional<Washer> getWasherById(@PathVariable String id) {
		return washerService.getWasherById(id);
		
	}
	
	
//	public BookingStatus getNotification() {
//		User user=new User();
//		user.consumeMessageFromQueue(orderStatus);
//	}
	
	@DeleteMapping("/delete")
	public void deleteAllWasher() {
		washerService.deleteAllWasher();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteWasherById(@PathVariable String id) {
		washerService.deleteWasherById(id);
	}
	
	
	
	@PostMapping("/requests/save")
	private void saveRequest(@RequestBody Request request) {
		washerService.saveRequest(request);
	}
	
	@GetMapping("requests/get")
	private List<Request> getAllRequest(){
		return washerService.getAllRequest();
	}
	
}
