package io.ducky.javabrainsdemo.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		
		return Arrays.asList(
				new Topic("spring", "spring framework", "yada yada 1"),
				new Topic("java", "core java", "yada yada 2"),
				new Topic("js", "shitcake", "yada yada 3")
		);
	}
}
