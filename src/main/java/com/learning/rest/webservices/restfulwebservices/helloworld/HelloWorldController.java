package com.learning.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(method=RequestMethod.GET, path="helloWorld")
	@GetMapping(path="helloWorld")
	public String helloWorld(){
		return "Hello Developer World @RequestMapping vs @GetMapping";
	}
	
	@GetMapping(path="helloWorldBean")
	public HelloWorld helloWorldBean(){
		return new HelloWorld("HelloWorld from Bean ");
	}
	
	@GetMapping(path="helloWorldBean/pathVar/{name}")
	public HelloWorld helloWorldBeanPathVar(@PathVariable String name){
		return new HelloWorld(String.format("HelloWorld PathVar %s ", name));
	}
}
