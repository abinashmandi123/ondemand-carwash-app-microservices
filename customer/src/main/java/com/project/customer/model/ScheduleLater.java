package com.project.customer.model;

import java.util.Date;

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
	private Date date;
	private Date time;
	private String location;
	private String washPackage;
}
