package com.intheeast.springframe.service;

import com.intheeast.springframe.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
	void add(User user);
	void deleteAll();
	void update(User user);
	void upgradeLevels();

	@Transactional
	User get(String id);

	@Transactional
	List<User> getAll();
}

