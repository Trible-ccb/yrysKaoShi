package com.ccc.test.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = UserInfo.USERINFO_TABLE_NAME)
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String USERINFO_TABLE_NAME = "tb_userinfo";
	public static final String USERINFO_COLUMN_ID = "uid";
	public static final String USERINFO_COLUMN_USER_NAME = "username";
	public static final String USERINFO_COLUMN_PASSWORD = "password";
	public static final String USERINFO_COLUMN_TYPE = "usertype";
	
	
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	@Column(name=USERINFO_COLUMN_ID)
	private int id;
	
	@Column(name=USERINFO_COLUMN_USER_NAME)
	private String username;
	
	@Column(name=USERINFO_COLUMN_PASSWORD)
	private String password;
	
	@Column(name=USERINFO_COLUMN_TYPE)
	private String type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
