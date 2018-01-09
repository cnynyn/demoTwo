package com.mybatis.pojo;

import java.util.Date;

@SuppressWarnings({ "serial", "unused" })
public class UserCustom extends User {

	// 这里添加扩展字段
	private Date birthday_start;// 起始日期
	private Date birthday_end;// 截止日期

}
