package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.dao.UserDao;
import com.example.model.UserDetails;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<UserDetails> getUserByAddress(String address) {
		Query query = new Query();
		query.addCriteria(Criteria.where("address").is(address));
		List<UserDetails> userList = mongoTemplate.find(query, UserDetails.class);
		return userList;
	}
}
