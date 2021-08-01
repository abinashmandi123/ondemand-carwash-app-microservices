package com.project.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.project.customer.VO.ResponseTemplateVO;
import com.project.customer.model.Car;
import com.project.customer.model.Customer;
import com.project.customer.model.ScheduleLater;
import com.project.customer.repository.CustomerRepository;
import com.project.customer.repository.ScheduleLaterRepository;

@Service
public class CustomerService implements UserDetailsService {

	final Logger logger=LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ScheduleLaterRepository scheduleRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Customer saveCustomer(Customer customer) {
		logger.info("Inside saveCustomer method of CustomerService");
		return customerRepository.save(customer);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Inside loadUserByUsername method of CustomerService");
		Customer foundedUser=customerRepository.findByUsername(username);
		if(foundedUser==null) return null;
		
////		String name=foundedUser.getUsername();
////		String pwd=foundedUser.getPassword();	
//		return new User(name,pwd,new ArrayList<>());
		return UserDetailsImpl.build(foundedUser);
	}
	
	public void saveCar(Car car1) {
		logger.info("Inside saveCar method of CustomerService");
		customerRepository.save(car1);
	}
	
	
	public ResponseTemplateVO getCarDetails(String username) {
		logger.info("Inside getCarDetails method of CustomerService");
		ResponseTemplateVO vo=new ResponseTemplateVO();
		Customer customer=customerRepository.findByUsername(username);
		Car[] car= restTemplate.getForObject("http://localhost:8081/cars/getcars/"+customer.getUsername(),Car[].class);
		
		vo.setCustomer(customer);
		vo.setCar(car);
		
		return vo;
		
	}

	
	

	public List<Customer> getAllCustomers() {
		logger.info("Inside getAllCustomers method of CustomerService");
		return customerRepository.findAll();
	}

	public Optional<Customer> getCustomer(String id) {
		logger.info("Inside getCustomer method of CustomerService");
		return customerRepository.findById(id);
	}

	public void deleteCustomers() {
		logger.info("Inside deleteCustomers method of CustomerService");
		customerRepository.deleteAll();
	}

	

	public void deleteCustomerById(String id) {
		customerRepository.deleteById(id);
		
	}
	

}
