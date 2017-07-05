package com.example.sky.rss_reader.model.news;

import java.util.ArrayList;

public class NewsList {
	private ArrayList<NewsItem> mList = new ArrayList<>();

	public int pushFirst(ArrayList<NewsItem> newsItems) {
		int count = 0;
		for (NewsItem newsItem : newsItems) {
			if (!mList.contains(newsItem)) {
				mList.add(0, newsItem);
				count++;
			}
		}
		return count;
	}

	public int pushBack(ArrayList<NewsItem> newsItems) {
		int count = 0;
		for (NewsItem newsItem : newsItems) {
			if (!mList.contains(newsItem)) {
				mList.add(newsItem);
				count++;
			}
		}
		return count;
	}

	public ArrayList<NewsItem> getList() {
		return mList;
	}

	public NewsItem getNewsItem(int index) {
		return mList.get(index);
	}

	public int getSize() {
		return mList.size();
	}
}

