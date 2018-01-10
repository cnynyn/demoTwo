package com.mybatis.dao;

import java.util.List;

import com.mybatis.pojo.Orders;

public interface OrdersDao {

	public List<Orders> findOrdersAndUserByMap();

	public List<Orders> findAllByOrdersMap();

	public void addOrders(Orders orders);

	public List<Orders> findOrdersDelayUser();
}
