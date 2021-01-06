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
		 *  can try a better alternative with a redirect in the TopicController class, later.
		 */
		try {
			// this syntax, god damn.
			return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		}
		catch(Exception e) {
			return new Topic("topic", "wasn't", "found");
		}
	}
	
	public void addTopic(Topic topic) {
		topics.add(topic);
	}

	public void updateTopic(Topic topic, String id) {
		/*
		 *  dude used vanilla for loop and used topics.set(index, topic), i said no.
		 *  although it's not the same result anymore, old topic gets yeeted
		 *  and everyhing below it moves up by 1 index and updated topic gets added at last.
		 */
		for(Topic tapic: topics) {
			if(tapic.getId().equals(id)) {
				topics.remove(tapic);
				topics.add(topic);
				return;
			}
		}
		
		// this shouldn't be a sysout but a spring logger.
		System.out.println("Topic " + id + " wasn't found.");
		
	}

	public void deleteTopic(String id) {
		
		for(Topic topic: topics) {
			if(topic.getId().equals(id)) {
				topics.remove(topic);
				return;
				
				/* 
				 * interesting: ConcurrentModificationException.
				 * i initally had thought of not keeping the return statement in order for
				 * the implementation to delete every occurrence but that throws an exception.
				 */
			}
		}
		
		/*
		 *  note: dude uses -
		 *  topics.removeIf(t -> t.getId().equals(id))
		 *  which was a suggestion on the Baeldung article.
		 *  appears as if Java 8 introduced this solely to tackle the exception usecase.
		 *  oh, and this also removes all occurences (and not just the first).
		 */
		
		// this again shouldn't be a sysout but a spring logger.
		System.out.println("Topic " + id + " wasn't found.");
	}
}
