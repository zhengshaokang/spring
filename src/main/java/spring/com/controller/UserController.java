package spring.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.com.domain.User;
import spring.com.jpa.dao.UserDao;
import spring.com.jpa.service.ipml.UserService;
import spring.com.redis.service.UserRedis;

@RestController
@EnableAutoConfiguration
@RequestMapping("user")
public class UserController {
	
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private UserRedis userRedis;
	 
		@RequestMapping("/{id}")
	    public User home(@PathVariable("id") Long id) {
			User u = new User();
			u.setAge(22);
			u.setName("张三");
			u.setId(id);
	        return u;
	    }
		
		@RequestMapping("/add/{id}")
		public User addUser(@PathVariable("id") Long id){
			User u = new User();
			u.setAge(22);
			u.setName("张三");
			u.setId(id);
			u = userService.addUser(u);
			return u;
		}
		
		@RequestMapping("/query/{id}")
		public User findById(@PathVariable("id") Long id){
			
			User u = userService.findById(id);
			return u;
		}
		
		@RequestMapping("/list")
		public List<User> list(){
			List<User> u = userService.findAll();
			return u;
		}
		@RequestMapping("/like/{name}")
		public List<User> like(@PathVariable("name") String name){
			List<User> u = userService.findLikeName("%"+name+"%");
			return u;
		}
		
		@RequestMapping("/page/{name}")
		public Page<User> page(@PathVariable("name") String name){
			User user = new User();
			user.setName(name);
			PageRequest pageRequest = new PageRequest(0, 3);
			Page<User> u = userService.findAll(user, pageRequest);
			return u;
		}
		
		@RequestMapping("/page1/{number}")
		public Page<User> page1(@PathVariable("number") Integer number){
			//User user = new User();
			//user.setName(name);
			PageRequest pageRequest = new PageRequest(number, 3);
			Page<User> u = userService.findAll(null, pageRequest);
			return u;
		}
		
		
		@RequestMapping("/redis/add/{name}")
		public User addUserRedis(@PathVariable("name") String name){
			User u = new User();
			u.setAge(22);
			u.setName(name);
			u.setId(1L);
			//service.put(u.getName(), u, -1);
			u.setLogName("admin");
			userRedis.addUser("tttt", u);
			return u;
		}

//	    public static void main(String[] args) throws Exception {
//	        SpringApplication.run(UserController.class, "--server.port=8080");
//	    }

}
