package io.ducky.javabrainsdemo.hello;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping
	public String say404() {
		return "That URL doesn't exist";
	}
	
	@RequestMapping("/")
	public String sayHome() {
		return "You're at home";
	}
	
	@RequestMapping("/hello")
	public String sayHi() {
		return "Hi";
	}
	
	@RequestMapping("/hello/{name}")
	public String sayHiPerson(@PathVariable String name) {
		return "Hi, " + name + "!";
	}
}
