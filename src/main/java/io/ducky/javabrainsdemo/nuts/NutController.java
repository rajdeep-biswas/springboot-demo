package io.ducky.javabrainsdemo.nuts;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ducky.javabrainsdemo.topic.Topic;

@RestController
public class NutController {
	
	@RequestMapping("/nuts")
	public List<Nut> getNuts() {
		Nut nut = new Nut();
		nut.setTopic(new Topic("js", "shitcake", "yada yada 3"));
		return Arrays.asList(
			nut
		);
	}
	
}
