package com.learning.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.rest.webservices.restfulwebservices.exception.UserNotFoundException;

import ch.qos.logback.classic.pattern.MethodOfCallerConverter;

@RestController
public class UserResourceController {

	@Autowired
	private UserDaoService userDaoService;
	
	
	@GetMapping(path="users")
	public List<User> allUsers(){
		return userDaoService.findall();
		
	}
	
	@GetMapping(path="users/{id}")
	public Resource<User> getUser(@PathVariable int id) {

		User user = userDaoService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id :" + id);
		}
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).allUsers());
		resource.add(linkTo.withRel("all-users"));
		resource.add(linkTo.withRel("AllUsers"));
		resource.add(linkTo.withRel("Users"));
		
/*		{
		    "id": 1,
		    "name": "Adam",
		    "birthDate": "2018-10-21T04:30:08.967+0000",
		    "_links": {
		        "all-users": {
		            "href": "http://localhost:8080/users"
		        },
		        "AllUsers": {
		            "href": "http://localhost:8080/users"
		        },
		        "Users": {
		            "href": "http://localhost:8080/users"
		        }
		    }
		}
		*/
		return resource;
	}
	
	@PostMapping(path="users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="users/{id}")
	public void delete(@PathVariable int id){
		User deltedUser = userDaoService.deleteById(id);
		if(deltedUser==null) throw new UserNotFoundException("id :"+id);
	}
}
