package com.learning.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 5;
	static{
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Shyam", new Date()));
		users.add(new User(4, "Ram", new Date()));
		users.add(new User(5, "Kumar", new Date()));
	}
	
	public List<User> findall(){
		return users;
	}
	
	public User save(User user){
		users.add(user);
		user.setId(++usersCount);
		return findOne(usersCount);
	}
	
	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
