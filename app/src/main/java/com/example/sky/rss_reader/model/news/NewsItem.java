package com.example.sky.rss_reader.model.news;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsItem implements Serializable {
	private boolean mReaded = false;
	private String mRssSourceName = null;
	private String mGuid = null;
	private String mTitle = null;
	private String mPublishTime = null;
	private String mAuthor = null;
	private String mSummary = null;
	private String mHtmlBody = null;
	private ArrayList<String> mImageUrls = null;

	public boolean isReaded() {
		return mReaded;
	}

	public void setReaded(boolean readed) {
		mReaded = readed;
	}

	public String getRssSourceName() {
		return mRssSourceName;
	}

	public void setRssSourceName(String rssSourceName) {
		mRssSourceName = rssSourceName;
	}

	public String getGuid() {
		return mGuid;
	}

	public void setGuid(String guid) {
		mGuid = guid;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getPublishTime() {
		return mPublishTime;
	}

	public void setPublishTime(String publishTime) {
		mPublishTime = publishTime;
	}

	public String getAuthor() {
		return mAuthor;
	}

	public void setAuthor(String author) {
		mAuthor = author;
	}

	public String getSummary() {
		return mSummary;
	}

	public void setSummary(String summary) {
		mSummary = summary;
	}

	public String getHtmlBody() {
		return mHtmlBody;
	}

	public void setHtmlBody(String htmlBody) {
		mHtmlBody = htmlBody;
	}

	public ArrayList<String> getImageUrls() {
		return mImageUrls;
	}

	public void setImageUrls(ArrayList<String> imageUrls) {
		mImageUrls = imageUrls;
	}

	@Override
	public boolean equals(Object obj) {
		NewsItem newsItem = (NewsItem) obj;
		return mGuid.equals(newsItem.getGuid());
	}
}


