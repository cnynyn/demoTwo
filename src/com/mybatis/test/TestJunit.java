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
	public void findUserById(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.findUserById(28);
		System.out.println(user);
		sqlSession.commit();// 可测试一级缓存、事务
		User user2 = mapper.findUserById(28);
		System.out.println(user2);
	}
	
	/**
	 * 测试二级缓存
	 */
	@Test
	public void findUserByIdTwoCache(){
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
		User user1 = mapper1.findUserById(28);
		System.out.println(user1+"\n关闭sqlSession当前时间:\t"+new Date());
		sqlSession1.commit();// 可测试一级缓存、事务
		sqlSession1.close();
		
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = mapper2.findUserById(28);
		System.out.println(user2);
	}
	
	@Test
	public void testTime(){
		System.out.println(new Date()+"\t"+UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		System.out.println(new Random().nextBoolean() );
	}
}
