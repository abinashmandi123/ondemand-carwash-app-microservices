package com.project.bookwash.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.bookwash.VO.ReponseTemplateVO;
import com.project.bookwash.model.BookWash;

@Repository
public interface BookWashRepository extends MongoRepository<BookWash,String>{

	BookWash findBookingsByCarNumber(String carNumber);

	List<BookWash> findBookingByOwner(String owner);

	
}
