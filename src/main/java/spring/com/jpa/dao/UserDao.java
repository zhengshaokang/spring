package spring.com.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.com.domain.User;


public interface UserDao extends JpaRepository<User, Long>{
	public List<User> findByNameLike(String name);
	public User findById(Long id);
}
