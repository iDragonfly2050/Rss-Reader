package com.example.sky.rssreader.model.manager;

import com.example.sky.rssreader.model.news.NewsItem;
import com.example.sky.rssreader.model.rss.BaseRssSource;
import com.example.sky.rssreader.model.rss.RssSource1;
import com.example.sky.rssreader.model.rss.RssSource2;
import com.example.sky.rssreader.model.rss.RssSource3;

import java.util.ArrayList;

// 用于管理所有App数据
public class AppManager {
	private static AppManager instance = null;
	private ArrayList<BaseRssSource> mRssFollowList = new ArrayList<>();
	private SettingsManager mSettingsManager;
	private NewsItem mCurrentNewsItem;

	public static synchronized AppManager getInstance() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	private AppManager() {
		mSettingsManager = new SettingsManager();
		mRssFollowList.add(new RssSource1());
		mRssFollowList.add(new RssSource2());
		mRssFollowList.add(new RssSource3());
	}

	public NewsItem getCurrentNewsItem() {
		return mCurrentNewsItem;
	}

	public void setCurrentNewsItem(NewsItem currentNewsItem) {
		mCurrentNewsItem = currentNewsItem;
	}

	public ArrayList<BaseRssSource> getRssFollowList() {
		return mRssFollowList;
	}

	public void setRssFollowList(ArrayList<BaseRssSource> rssFollowList) {
		mRssFollowList = rssFollowList;
	}

	public SettingsManager getSettingsManager() {
		return mSettingsManager;
	}

	public void setSettingsManager(SettingsManager settingsManager) {
		mSettingsManager = settingsManager;
	}
}

