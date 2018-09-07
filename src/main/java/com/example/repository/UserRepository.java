package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.UserDetails;

@Repository
public interface UserRepository extends MongoRepository<UserDetails, String> {
	
	public String findNameById(String userId);

}
