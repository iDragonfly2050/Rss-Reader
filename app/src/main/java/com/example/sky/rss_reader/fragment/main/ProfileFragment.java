package com.example.sky.rssreader.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sky.rssreader.R;
import com.example.sky.rssreader.fragment.base.BaseFragment;

public class ProfileFragment extends BaseFragment {
	private View mRoot;

	public static ProfileFragment newInstance() {
		return new ProfileFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRoot = inflater.inflate(R.layout.fragment_profile, container, false);
		return mRoot;
	}
}
