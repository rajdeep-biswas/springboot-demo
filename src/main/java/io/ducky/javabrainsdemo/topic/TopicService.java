package io.ducky.javabrainsdemo.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {

	// this happens to be a static initialization block
	private List<Topic> topics = new ArrayList<>(
		Arrays.asList(
			new Topic("spring", "spring framework", "yada yada 1"),
			new Topic("java", "core java", "yada yada 2"),
			new Topic("js", "shitcake", "yada yada 3")
		)
	);
	
	public List<Topic> getAllTopics() {
		return topics;
	}
	
	public Topic getTopic(String id) {
		/*
		 *  added the try-catch block so it doesn't return
		 *  a Whitelabel error page when invalid topic ID is entered.
		 *  can try a better alternative with a redirect in the TopicController class, later
		 */
		try {
			// this syntax, god damn
			return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		}
		catch(Exception e) {
			return new Topic("topic", "wasn't", "found");
		}
	}
	
	public void addTopic(Topic topic) {
		topics.add(topic);
	}
}
