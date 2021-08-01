package com.project.washer.model;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingStatus {

	private BookWash booking;
	private String status;
	private String message;
}
