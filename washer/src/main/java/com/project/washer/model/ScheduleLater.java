package com.project.washer.model;

import java.time.LocalDate;
import java.time.LocalTime;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleLater {

	@Id
	private String id;
	private String carNumber;
	private String carModel;
	private String owner;
	private String washPackage;
	private String location;
	private LocalDate date;
	private LocalTime time;
}
