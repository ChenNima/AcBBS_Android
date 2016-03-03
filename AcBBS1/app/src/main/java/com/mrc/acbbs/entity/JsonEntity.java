package com.mrc.acbbs.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

public class JsonEntity {
	
	private String JsonString;
	private JSONArray MyJSONArray;
	public String getJsonString() {
		return JsonString;
	}
	public void setJsonString(String jsonString) {
		JsonString = jsonString;
		try {
			MyJSONArray = new JSONArray(JsonString);
		} catch (JSONException e) {
			Log.i("JSON", "JSON解析失败");
			e.printStackTrace();
		}
	}
	public JSONArray getMyJSONArray() {
		return MyJSONArray;
	}
	public void setMyJSONArray(JSONArray myJSONArray) {
		MyJSONArray = myJSONArray;
	}

}
