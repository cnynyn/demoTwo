package com.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.dao.OrdersCustomDao;
import com.mybatis.dao.OrdersDao;
import com.mybatis.dao.UserMapper;
import com.mybatis.pojo.Orders;
import com.mybatis.pojo.OrdersCustom;
import com.mybatis.pojo.User;
import com.mybatis.util.ToolUtils;

public class TestJunit {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void mainTest() throws IOException {
		// 加载全局配置文件
		String resource = "sqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void findOrdersAndUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersCustomDao ordersCustomDao = sqlSession.getMapper(OrdersCustomDao.class);
		List<OrdersCustom> list = ordersCustomDao.findOrdersAndUser();
		System.out.println(list);
	}
	
	@Test
	public void findOrdersAndUserByMap(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersDao ordersDao = sqlSession.getMapper(OrdersDao.class);
		List<Orders> list = ordersDao.findOrdersAndUserByMap();
		System.out.println(list);
	}
	
	@Test
	public void findAllByOrdersMap(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersDao ordersDao = sqlSession.getMapper(OrdersDao.class);
		List<Orders> list = ordersDao.findAllByOrdersMap();
		System.out.println(list);
	}
	
	// 多对多查询
	@Test
	public void findUserAndOrdersAndOrderdetail(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> userList = userMapper.findUserAndOrdersAndOrderdetail();
		System.out.println(userList);
	}
	
	// 级联保存
	@Test
	public void addUserAndOrders(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("李四");
		user.setSex("2");
		user.setBirthday(new Date());
		user.setAddress("北京海淀");
		userMapper.addUser(user);
		
		System.out.println(new Date()+"\t"+user.getId());
		OrdersDao ordersDao = sqlSession.getMapper(OrdersDao.class);
		Orders orders = new Orders();
		orders.setUserId(user.getId());
		orders.setNumber(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		orders.setCreatetime(new Date());
		orders.setNote(ToolUtils.getRandomNumber(32));
		ordersDao.addOrders(orders);
		sqlSession.commit();
		sqlSession.close();
	}
	
	
	@Test
	public void findOrdersDelayUser(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersDao ordersDao = sqlSession.getMapper(OrdersDao.class);
		List<Orders> orderslist = ordersDao.findOrdersDelayUser();
		System.out.println(Arrays.asList(orderslist));
	}
	
	@Test
	public void test(){
		System.out.println(new Date()+"\t"+UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		System.out.println(new Random().nextBoolean() );
	}
}
