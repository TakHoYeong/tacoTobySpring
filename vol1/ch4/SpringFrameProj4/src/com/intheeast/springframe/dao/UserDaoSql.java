package com.intheeast.springframe.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.intheeast.springframe.domain.User;

public class UserDaoSql{
	
//	public void setDataSource(DataSource dataSource) {
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}
//	
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> userMapper = 
			new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					return user;
				}
			};
			
	public void add(User user) throws SQLException{
		this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
				user.getId(), 
				user.getName(), 
				user.getPassword());
		
	}
	
	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");

	}

}
