package com.sample.libs.utils;

import android.os.Handler;

public abstract class GroundBackTask<T2, T3> implements Runnable {

	Handler mhandler;
	boolean mCancel;

	T2[] mParmas;
	T3[] mResultParmas;

	public GroundBackTask(T2[] parmas2, T3[] parmas3) {
		mhandler = new Handler();
		mParmas = parmas2;
		mResultParmas = parmas3;
	}

	@Override
	public void run() {
		mhandler.post(new Runnable() {

			@Override
			public void run() {
				onPreExecute();
			}
		});

		onLoadBackGround(mParmas);

		mhandler.post(new Runnable() {

			@Override
			public void run() {
				onPostExecute(mResultParmas);

			}
		});
	}

	public abstract void onLoadBackGround(T2... parmas);

	public abstract void onPreExecute();

	public abstract void onPostExecute(T3... parmas);
}
