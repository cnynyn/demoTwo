package com.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.dao.OrdersCustomDao;
import com.mybatis.pojo.OrdersCustom;

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
}
