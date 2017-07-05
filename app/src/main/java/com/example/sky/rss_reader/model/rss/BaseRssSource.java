package com.example.sky.rss_reader.model.rss;

import com.example.sky.rss_reader.model.news.NewsItem;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public abstract class BaseRssSource {
	protected String mUrl = null;
	protected String mName = null;
	protected String mRaw = null;
	protected ArrayList<String> mItemRaws = new ArrayList<>();
	protected ArrayList<NewsItem> mItemsList = new ArrayList<>();

	abstract protected void pickItemRaws();

	abstract protected void generateItems();

	@SuppressWarnings("ConstantConditions")
	public void pickRaw() {
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
				.readTimeout(10, TimeUnit.SECONDS)
				.connectTimeout(9, TimeUnit.SECONDS)
				.build();

		final Request request = new Request.Builder().url(mUrl).build();

		Call call = okHttpClient.newCall(request);

		try {
			Response response = call.execute();
			mRaw = response.body().string();
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}

		pickItemRaws();
		generateItems();
	}

	public String getName() {
		return mName;
	}

	public ArrayList<NewsItem> getItemsList() {
		return mItemsList;
	}

	public int getSize() {
		return mItemsList.size();
	}
}
