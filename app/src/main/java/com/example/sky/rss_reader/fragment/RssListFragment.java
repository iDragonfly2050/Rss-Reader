package com.example.sky.rssreader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sky.rssreader.R;
import com.example.sky.rssreader.fragment.base.BaseFragment;

public class RssListFragment extends BaseFragment {
	private View mRoot = null;
	public static RssListFragment newInstance() {
		return new RssListFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRoot = inflater.inflate(R.layout.fragment_rss_list, container, false);
		return mRoot;
	}
}
