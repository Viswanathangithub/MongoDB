package com.example.dao;

import java.util.List;

import com.example.model.UserDetails;

public interface UserDao {
	
	public List<UserDetails> getUserByAddress(String address);

}
