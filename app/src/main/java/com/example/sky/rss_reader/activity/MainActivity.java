package com.example.sky.rssreader.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.sky.rssreader.R;
import com.example.sky.rssreader.activity.base.BaseActivity;
import com.example.sky.rssreader.fragment.main.HomeFragment;
import com.example.sky.rssreader.fragment.main.ProfileFragment;
import com.example.sky.rssreader.fragment.main.RssFragment;

public class MainActivity extends BaseActivity {
	private TextView mTextView;
	private BottomNavigationView mBottomNavigationView;
	private int mCurrentPosition = 0;
	private static final String TAG_HOME_FRAGMENT = "TAG_HOME_FRAGMENT";
	private static final String TAG_RSS_FRAGMENT = "TAG_RSS_FRAGMENT";
	private static final String TAG_PROFILE_FRAGMENT = "TAG_PROFILE_FRAGMENT";
	HomeFragment mHomeFragment;
	RssFragment mRssFragment;
	ProfileFragment mProfileFragment;
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
			case R.id.navigation_home:
				switchToPositon(0);
				return true;
			case R.id.navigation_rss:
				switchToPositon(1);
				return true;
			case R.id.navigation_profile:
				switchToPositon(2);
				return true;
			}
			return false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TransitionInflater transitionInflater = TransitionInflater.from(this);
		getWindow().setExitTransition(transitionInflater.inflateTransition(R.transition.window_exit_transition));
		getWindow().setReenterTransition(transitionInflater.inflateTransition(R.transition.window_reenter_transition));

		initView();

		mHomeFragment = HomeFragment.newInstance();
		mRssFragment = RssFragment.newInstance();
		mProfileFragment = ProfileFragment.newInstance();

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.activity_main_framelayout, mHomeFragment, TAG_HOME_FRAGMENT);
		fragmentTransaction.add(R.id.activity_main_framelayout, mRssFragment, TAG_RSS_FRAGMENT);
		fragmentTransaction.add(R.id.activity_main_framelayout, mProfileFragment, TAG_PROFILE_FRAGMENT);
		fragmentTransaction.commit();
		switchToPositon(0);
		mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
	}

	private void switchToPositon(int index) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		switch (index) {
		case 0:
			mTextView.setText(getResources().getString(R.string.title_home));
			fragmentTransaction.hide(mRssFragment);
			fragmentTransaction.hide(mProfileFragment);
			fragmentTransaction.show(mHomeFragment);
			fragmentTransaction.commit();
			break;
		case 1:
			mTextView.setText(getResources().getString(R.string.title_rss));
			fragmentTransaction.hide(mHomeFragment);
			fragmentTransaction.hide(mProfileFragment);
			fragmentTransaction.show(mRssFragment);
			fragmentTransaction.commit();
			break;
		case 2:
			mTextView.setText(getResources().getString(R.string.title_profile));
			fragmentTransaction.hide(mHomeFragment);
			fragmentTransaction.show(mProfileFragment);
			fragmentTransaction.hide(mRssFragment);
			fragmentTransaction.commit();
			break;
		default:
			break;
		}
	}

	private void initView() {
		mTextView = (TextView) findViewById(R.id.activity_main_textview);
		mBottomNavigationView = (BottomNavigationView) findViewById(R.id.activity_main_bottomnavigationview);
	}
}
