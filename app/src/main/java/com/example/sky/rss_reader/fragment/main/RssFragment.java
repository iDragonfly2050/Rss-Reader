package com.example.sky.rssreader.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sky.rssreader.R;
import com.example.sky.rssreader.fragment.RssListFragment;
import com.example.sky.rssreader.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class RssFragment extends BaseFragment {
	private View mRoot;
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private ArrayList<String> mArrayList = new ArrayList<>(Arrays.asList("可订阅", "已订阅"));

	public static RssFragment newInstance() {
		return new RssFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRoot = inflater.inflate(R.layout.fragment_rss, container, false);
		initView();

		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
			@Override
			public RssListFragment getItem(int position) {
				return RssListFragment.newInstance();
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return mArrayList.get(position);
			}

			@Override
			public int getCount() {
				return 2;
			}
		});
		mTabLayout.setupWithViewPager(mViewPager);
		return mRoot;
	}

	public void initView() {
		mTabLayout = (TabLayout) mRoot.findViewById(R.id.fragment_rss_tablayout);
		mViewPager = (ViewPager) mRoot.findViewById(R.id.fragment_rss_viewpager);
	}
}
