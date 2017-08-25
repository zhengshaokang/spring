package spring.com.jpa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import spring.com.domain.User;

public interface JpaUserService {
	public User addUser(User user);
	public User findById(Long id);
	public List<User> findLikeName(String name);
	public Page<User> findAll(User user, PageRequest pageRequest);
	public List<User> findAll();
}
