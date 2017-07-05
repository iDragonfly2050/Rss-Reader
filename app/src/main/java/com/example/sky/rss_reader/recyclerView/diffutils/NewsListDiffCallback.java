package com.example.sky.rssreader.recyclerView.diffutils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import com.example.sky.rssreader.model.news.NewsItem;

import java.util.ArrayList;

public class NewsListDiffCallback extends DiffUtil.Callback {
	private ArrayList<NewsItem> mOldList;
	private ArrayList<NewsItem> mNewList;

	public NewsListDiffCallback(ArrayList<NewsItem> oldList, ArrayList<NewsItem> newList) {
		this.mOldList = oldList;
		this.mNewList = newList;
	}

	@Override
	public int getOldListSize() {
		return mOldList != null ? mOldList.size() : 0;
	}

	@Override
	public int getNewListSize() {
		return mNewList != null ? mNewList.size() : 0;
	}

	@Override
	public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
		return mNewList.get(newItemPosition).equals(mOldList.get(oldItemPosition));
	}

	@Override
	public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
		return mNewList.get(newItemPosition).equals(mOldList.get(oldItemPosition));
	}

	@Nullable
	@Override
	public Object getChangePayload(int oldItemPosition, int newItemPosition) {
		// 同一个Item，但是内容发生改变时
		return null;
	}
}
