package com.project.washer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

import com.project.washer.model.ERole;
import com.project.washer.model.Role;
import com.project.washer.model.Washer;
import com.project.washer.repository.WasherRepository;
import com.project.washer.service.WasherService;

public class WasherServiceTest {

	@InjectMocks
	private WasherService washerService;
	
	@Mock
	private WasherRepository washerRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveWasherTest() {
		Washer washer=new Washer();
		
		when(washerRepository.save(washer)).thenAnswer(invocation -> invocation.getArgument(0));
		
		Washer savedWasher=washerService.saveWasher(washer);
		
		Assertions.assertNotNull(savedWasher);
		
		verify(washerRepository).save(any(Washer.class));
	}
	
	@Test
	public void getAllWasherTest() {
		List<Washer> washers=new ArrayList<Washer>();
		Set<Role> roles=new HashSet<>();
		Role userRole=new Role();
		userRole.setName(ERole.ROLE_WASHER);
		roles.add(userRole);
		washers.add(new Washer("101","Abinash Mandi","abinash",23,"abinash@gmail.com","12121","8765432456","Hooghly,West Bengal",roles,true));
		washers.add(new Washer("102","Abinash Mandi","abinash",23,"abinash@gmail.com","12121","8765432456","Hooghly,West Bengal",roles,true));
		
		when(washerRepository.findAll()).thenReturn(washers);
		
		List<Washer> expected=washerService.getAllWasher();
		
		Assertions.assertEquals(expected,washers);
	}
	
	@Test
	public void getWasherByIdTest() {
		Set<Role> roles=new HashSet<>();
		Role userRole=new Role();
		userRole.setName(ERole.ROLE_WASHER);
		roles.add(userRole);
		Washer washer=new Washer("101","Abinash Mandi","abinash",23,"abinash@gmail.com","12121","8765432456","Hooghly,West Bengal",roles,true);
		
		when(washerRepository.findById("101")).thenReturn(Optional.ofNullable(washer));
		
		Optional<Washer> result=washerService.getWasherById("101");
		
		Assertions.assertEquals(washer.getName(), result.get().getName());
	}
	
	@Test
	public void deleteWasherByIdTest() {
		String id="101";
		
		washerService.deleteWasherById(id);
		washerService.deleteWasherById(id);
	
		verify(washerRepository,times(2)).deleteById(id);
	}
	
}
