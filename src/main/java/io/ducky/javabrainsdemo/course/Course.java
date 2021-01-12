package io.ducky.javabrainsdemo.course;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.ducky.javabrainsdemo.topic.Topic;

@Entity
public class Course {
	
	@Id
	private String id;
	private String name;
	private String descr;
	
	@ManyToOne
	private Topic topic;

	public Course() {}
	
	public Course(String id, String name, String descr, String topicId) {
		// super();
		this.id = id;
		this.name = name;
		this.descr = descr;
		this.topic = new Topic(topicId, "", ""); // no clue what the guy is doing
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
