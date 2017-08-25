package spring.com.jpa.service.ipml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import spring.com.domain.User;
import spring.com.jpa.dao.UserDao;
import spring.com.jpa.service.JpaUserService;

@Service
public class UserService implements JpaUserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public List<User> findLikeName(String name) {
		List<User> list = userDao.findByNameLike(name);
		return list;
	}

	@Override
	public Page<User> findAll(User user, PageRequest pageRequest) {
//		if(pageRequest.getSort() == null ) {
//			pageRequest = new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),new Sort(Direction.DESC,"id"));
//		}
		if(user == null){
			return userDao.findAll(pageRequest);
		}
		//创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withMatcher("name", GenericPropertyMatchers.startsWith()) //姓名采用“开始匹配”的方式查询
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
      
        //创建实例
        Example<User> ex = Example.of(user, matcher); 
       // Example<User> ex = Example.of(user); 
        System.out.println(user.toString());
		return userDao.findAll(ex, pageRequest);
	}

	@Override
	public User addUser(User user) {
		User u = userDao.save(user);
		return u;
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
