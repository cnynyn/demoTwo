package com.mybatis.dao;

import java.util.List;

import com.mybatis.pojo.OrdersCustom;

public interface OrdersCustomDao {

	public List<OrdersCustom> findOrdersAndUser();
}
