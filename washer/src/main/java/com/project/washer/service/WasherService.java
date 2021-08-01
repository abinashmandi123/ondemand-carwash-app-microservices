package com.project.washer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.washer.model.Request;
import com.project.washer.model.Washer;
import com.project.washer.repository.RequestRepository;
import com.project.washer.repository.WasherRepository;

@Service
public class WasherService implements UserDetailsService{

	final Logger logger=LoggerFactory.getLogger(WasherService.class);
	
	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	public Washer saveWasher(Washer washer) {
		logger.info("Inside saveWasher method of WasherService");
		return washerRepository.save(washer);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Inside loadUserByUsername method of WasherService");
		Washer foundedUser=washerRepository.findByUsername(username);
		if(foundedUser==null) return null;
		
		
		return UserDetailsImpl.build(foundedUser);
	}

	public List<Washer> getAllWasher() {
		
		return washerRepository.findAll();
	}
	
	
	
	public void saveRequest(Request request) {
		logger.info("Inside saveRequest method of WasherService");
		requestRepository.save(request);
	}

	public List<Request> getAllRequest() {
		logger.info("Inside getAllRequest method of WasherService");
		return requestRepository.findAll();
	}

	public Optional<Washer> getWasherById(String id) {
		logger.info("Inside getWasherById method of WasherService");
		return washerRepository.findById(id);
	}

	public void deleteAllWasher() {
		logger.info("Inside getWasherById method of WasherService");
		 washerRepository.deleteAll();
	}

	public void deleteWasherById(String id) {
		logger.info("Inside getWasherById method of WasherService");
		washerRepository.deleteById(id);
		
	}

	
}
