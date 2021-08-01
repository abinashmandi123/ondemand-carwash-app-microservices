package com.project.bookwash.VO;

import com.project.bookwash.model.BookWash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReponseTemplateVO {
	private BookWash bookWash;
	private Car car;
}
