package com.sample.libs.utils;

import android.os.Handler;

public final class TimeTask {

	Runnable mRunnable;
	Handler mHandler;
	
	int mInterval;
	boolean mStop = false;

	public TimeTask(Runnable runnable, int interval) {
		this.mRunnable = runnable;
		this.mInterval = interval;
	}

	public void start() throws TimeILegalException{
		if(mRunnable != null){
	         if(mInterval >0){
	        	 mHandler.postDelayed(mRunnable, mInterval);
	         }else{
	        	 throw new TimeILegalException();
	         }
		}
	}
	
	public static class TimeILegalException extends Exception{
		public TimeILegalException(){
			
		}
		
		@Override
		public String toString() {
			return new String("Time Ilegal exception");
		}
	}
}
