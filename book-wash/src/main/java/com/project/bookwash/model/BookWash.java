package com.project.bookwash.model;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="bookings")
public class BookWash {


	
	@Id
	private String bookingId;
	private String carNumber;
	private String carModel;
	private String owner;
	private String washPackage;
	private String location;
	private String date;
	private String status;
}
