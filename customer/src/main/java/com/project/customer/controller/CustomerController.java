package com.project.customer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.customer.VO.ResponseTemplateVO;
import com.project.customer.model.Car;
import com.project.customer.model.Customer;
import com.project.customer.model.CustomerRegistration;
import com.project.customer.model.ERole;
import com.project.customer.model.JwtResponse;
import com.project.customer.model.LoginRequest;
import com.project.customer.model.MessageResponse;
import com.project.customer.model.Role;
import com.project.customer.model.ScheduleLater;
import com.project.customer.repository.CustomerRepository;
import com.project.customer.repository.RoleRepository;
import com.project.customer.service.CustomerService;
import com.project.customer.service.UserDetailsImpl;
import com.project.customer.utils.JwtUtils;




@CrossOrigin(origins="*")
@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	final Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	List<Car> list=new ArrayList<Car>();
	
	@PostMapping("/add")
	private ResponseEntity<MessageResponse> addCustomer(@RequestBody CustomerRegistration customer){
		logger.info("Inside addCustomer method of CustomerController");

		if (customerRepository.existsByUsername(customer.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (customerRepository.existsByEmail(customer.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		String name=customer.getName();
		String username=customer.getUsername();
		String email=customer.getEmail();
		String password=customer.getPassword();
		Customer customerModel=new Customer();
		customerModel.setName(name);
		customerModel.setUsername(username);
		customerModel.setEmail(email);
		customerModel.setPassword(password);
		Set<String> strRoles =customer.getRoles();
		Set<Role> roles = new HashSet<>();		
		if (strRoles == null) {
//			Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			Role userRole=new Role();
			userRole.setName(ERole.ROLE_CUSTOMER);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "washer":
					Role modRole = roleRepository.findByName(ERole.ROLE_WASHER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		customerModel.setRoles(roles);

		customerRepository.save(customerModel);
		
		
		

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
		
	
	
	@PostMapping("/login")
	private JwtResponse authenticateCustomer(@RequestBody LoginRequest loginRequest){
		logger.info("Inside authenticateCustomer method of CustomerController");
		
//		String username=customerRegistration.getUsername();
//		String password=customerRegistration.getPassword();
//		
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
//		} catch (Exception e) {
//			System.out.println(e);
//			
//		}
		
		 Authentication authentication=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
//		
//		UserDetails loadedUser=customerService.loadUserByUsername(username);
//		System.out.println(loadedUser);
		return new JwtResponse(jwt,
				 userDetails.getId(), 
				 userDetails.getName(),
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles
				);
	}

	@GetMapping("/")
	private List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/{id}")
	private Optional<Customer> getCustomer(@PathVariable String id) {
		return customerService.getCustomer(id);
		
	}

	@GetMapping("/cars/{username}")
	private ResponseTemplateVO getCustomerWithCarDetails(@PathVariable String username) {
		return customerService.getCarDetails(username);
	}
	
	@PutMapping(value="/update/{user}",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	private ResponseEntity<String> editProfile(@PathVariable String user,@RequestPart String username,@RequestPart String name,@RequestPart String email,@RequestPart String password,@RequestPart String contact,@RequestPart String address,@RequestPart MultipartFile profilePic) throws IOException{
//		String name=customer.getName();
//		String username=customer.getUsername();
//		String email=customer.getEmail();
//		String password=customer.getPassword();
//		String mobileNumber=customer.getMobileNumber();
//		String Address=customer.getAddress();
		Customer customerModel=customerRepository.findByUsername(user);
		customerModel.setName(name);
		customerModel.setUsername(username);
		customerModel.setEmail(email);
		customerModel.setPassword(password);
		customerModel.setContact(contact);
		customerModel.setAddress(address);
		customerModel.setProfilePic(new Binary(BsonBinarySubType.BINARY,profilePic.getBytes()));
		try {
			customerService.saveCustomer(customerModel);
		}catch(Exception e) {
			return ResponseEntity.ok("Error during updating customer "+username);
		}
		
		
		return ResponseEntity.ok("Succesful updation for customer "+username);
	}
	
	@DeleteMapping("/delete")
	public void deleteAllCustomers() {
		customerService.deleteCustomers();
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void deleteCustomer(@PathVariable String id) {
		customerService.deleteCustomerById(id);
	}
	
	//
	
}
