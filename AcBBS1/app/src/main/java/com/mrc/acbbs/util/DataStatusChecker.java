package com.mrc.acbbs.util;

import android.os.AsyncTask;
import android.util.Log;

import com.mrc.acbbs.Value;

import java.util.Objects;


public class DataStatusChecker {
	
	private Listener mListener;
	private Long waitTime = (long) 500;
	private int retryTime = 10;
	
	public DataStatusChecker(Listener Listener){
		mListener = Listener;
	}
	
	public DataStatusChecker(Long waitTime,int retryTime,Listener Listener){
		this.waitTime = waitTime;
		this.retryTime = retryTime;
		mListener = Listener;
	}
	
	public void checkForumList(){
		MyTask myTask = new MyTask();
		myTask.execute();
	}

	class MyTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String[] params) {
			for (int i=0;i<retryTime;i++){
				if (Value.getTitleSecondsArray()==null){
					try {
						Log.d("检查数据", "等待数据中，剩余尝试次数："+(retryTime-i));
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					Log.d("检查数据", "已经完成检查");
					return true;
				}
			}
			Log.w("检查数据", "数据检查超时");
			return false;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			if(result){
				mListener.ifExists();
				return;
			}
			mListener.ifNotExists();
		}

	}
	
	public interface Listener{
		void ifExists();
		void ifNotExists();
	}
	
	public void setListener(Listener listener){
		setmListener(listener);
	}

	public Listener getmListener() {
		return mListener;
	}

	public void setmListener(Listener mListener) {
		this.mListener = mListener;
	}

	public Long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(Long waitTime) {
		this.waitTime = waitTime;
	}

	public int getRetryTime() {
		return retryTime;
	}

	public void setRetryTime(int retryTime) {
		this.retryTime = retryTime;
	}

}
