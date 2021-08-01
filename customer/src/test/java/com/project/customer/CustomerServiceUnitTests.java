//package com.project.customer;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.times;
//
//import com.project.customer.model.Customer;
//import com.project.customer.model.ERole;
//import com.project.customer.model.Role;
//import com.project.customer.repository.CustomerRepository;
//import com.project.customer.service.CustomerService;
//import com.project.customer.utils.JwtUtils;
//
//public class CustomerServiceUnitTests {
//
//	@InjectMocks
//	private CustomerService customerService;
//	
//	@Mock
//	private CustomerRepository customerRepository;
//	
//	@Mock
//	private JwtUtils jwtUtils;
//	
//	@BeforeEach
//	public void setUp() throws Exception{
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	@Test
//	public void saveCustomerTest() {
//		Set<Role> roles=new HashSet<>();
//		Role userRole=new Role();
//		userRole.setName(ERole.ROLE_CUSTOMER);
//		roles.add(userRole);
//		Customer customer=new Customer("101","Abinash Mandi","abinash","abinash@gmail.com","password","9876543210","Hooghly,West Bengal",roles,true);
//		
//		Customer savedCustomer=customerService.saveCustomer(customer);
//		
//		Assertions.assertNotNull(savedCustomer);
//		
//		verify(customerRepository).save(any(Customer.class));
//	}
//	
//	@Test
//	public void getAllCustomersTest() {
//		List<Customer> customers=new ArrayList<Customer>();
//		Set<Role> roles=new HashSet<>();
//		Role userRole=new Role();
//		userRole.setName(ERole.ROLE_CUSTOMER);
//		roles.add(userRole);
//		customers.add(new Customer("101","Abinash Mandi","abinash","abinash@gmail.com","password","9876543210","Hooghly,West Bengal",roles,true));
//		customers.add(new Customer("102","Rahul Singh","rahul123","rahul@gmail.com","password","9876547810","Hooghly,West Bengal",roles,true));
//		
//		when(customerRepository.findAll()).thenReturn(customers);
//		
//		List<Customer> expected=customerService.getAllCustomers();
//		
//		Assertions.assertEquals(expected,customers);
//	}
//	
//	@Test
//	public void deleteCustomerById() {
//		String customerId="101";
//		
//		customerService.deleteCustomerById(customerId);
//		customerService.deleteCustomerById(customerId);
//		
//		verify(customerRepository,times(2)).deleteById(customerId);
//	}
//	
//	
//}
