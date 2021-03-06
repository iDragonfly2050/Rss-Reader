package com.example.sky.rss_reader.application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	private static MyApplication instance;

	public static Context getContext() {
		return instance.getApplicationContext();
	}

	public static MyApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
}
