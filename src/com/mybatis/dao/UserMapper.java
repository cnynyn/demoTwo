package com.mybatis.dao;

import java.util.List;

import com.mybatis.pojo.User;

public interface UserMapper {

	//
	public List<User> findUserAndOrdersAndOrderdetail();

	public void addUser(User user);

	public User findUserById(int id);
}
