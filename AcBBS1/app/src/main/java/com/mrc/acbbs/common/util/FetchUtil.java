package com.mrc.acbbs.common.util;

import android.os.Handler;

import com.mrc.acbbs.entity.JsonEntity;

import java.util.Map;


public class FetchUtil {
	static Thread mThread;

	public static void getJson(final String Url,final Map<String, String> mapParams,final Handler mHandler,final JsonEntity mJsonEntity){
		mThread = new Thread(){
			public void run(){
		try {
			mJsonEntity.setJsonString(HttpClintUtil.getUrlCon(Url, mapParams));
			mHandler.sendEmptyMessage(0x123);
		} catch (Exception e) {
			e.printStackTrace();
		}
			}
		};
		mThread.start();
	}
	



	public void stop(){
		mThread.interrupt();
	}
}
