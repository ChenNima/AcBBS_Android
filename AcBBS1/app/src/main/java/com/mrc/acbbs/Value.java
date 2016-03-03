package com.mrc.acbbs;

import com.mrc.acbbs.entity.Forum;
import com.mrc.acbbs.entity.JsonEntity;
import com.mrc.acbbs.entity.TitleSecond;

import java.util.ArrayList;

public  class Value {
	private static String forumListJson;
	private static JsonEntity forumListJsonEntity;
	private static ArrayList<Forum> forumArray;
	private static ArrayList<TitleSecond> titleSecondsArray;

	public static String getForumListJson() {
		return forumListJson;
	}

	public static void setForumListJson(String forumListJson) {
		Value.forumListJson = forumListJson;
	}

	public static JsonEntity getForumListJsonEntity() {
		return forumListJsonEntity;
	}

	public static void setForumListJsonEntity(JsonEntity forumListJsonEntity) {
		Value.forumListJsonEntity = forumListJsonEntity;
	}

	public static ArrayList<Forum> getForumArray() {
		return forumArray;
	}

	public static void setForumArray(ArrayList<Forum> forumArray) {
		Value.forumArray = forumArray;
	}

	public static ArrayList<TitleSecond> getTitleSecondsArray() {
		return titleSecondsArray;
	}

	public static void setTitleSecondsArray(ArrayList<TitleSecond> titleSecondsArray) {
		Value.titleSecondsArray = titleSecondsArray;
	}
}
