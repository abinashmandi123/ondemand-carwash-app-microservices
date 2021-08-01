package com.project.bookwash.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	
	private String carNumber;
	private String carType;
	private String carModel;
	private String carImage;
}
