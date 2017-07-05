package com.example.sky.rssreader.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sky.rssreader.R;
import com.example.sky.rssreader.fragment.NewsListFragment;
import com.example.sky.rssreader.fragment.base.BaseFragment;
import com.example.sky.rssreader.model.manager.AppManager;
import com.example.sky.rssreader.model.rss.BaseRssSource;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {
	private View mRoot = null;
	private TabLayout mTabLayout = null;
	private ViewPager mViewPager = null;
	private ArrayList<BaseRssSource> mRssSources = AppManager.getInstance().getRssFollowList();

	public static HomeFragment newInstance() {
		return new HomeFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRoot = inflater.inflate(R.layout.fragment_home, container, false);
		initView();

		mViewPager.setOffscreenPageLimit(2);
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
			@Override
			public Fragment getItem(int position) {
				return NewsListFragment.newInstance(position);
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return mRssSources.get(position).getName();
			}

			@Override
			public int getCount() {
				return mRssSources.size();
			}
		});

		mTabLayout.setupWithViewPager(mViewPager);

		return mRoot;
	}

	private void initView() {
		mTabLayout = (TabLayout) mRoot.findViewById(R.id.fragment_home_tablayout);
		mViewPager = (ViewPager) mRoot.findViewById(R.id.fragment_home_viewpager);
	}
}

