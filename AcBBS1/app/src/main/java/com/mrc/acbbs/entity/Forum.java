package com.mrc.acbbs.entity;

public class Forum {
	private String id;
	private String title;
	private JsonEntity forumJson;
	public Forum (String id,String title){
		setId(id);
		setTitle(title);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public JsonEntity getForumJson() {
		return forumJson;
	}
	public void setForumJson(JsonEntity forumJson) {
		this.forumJson = forumJson;
	}
}
