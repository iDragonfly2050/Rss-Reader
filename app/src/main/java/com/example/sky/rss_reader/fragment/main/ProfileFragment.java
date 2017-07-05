package com.example.sky.rss_reader.fragment.main;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.example.sky.rss_reader.R;
import com.example.sky.rss_reader.activity.StarListActivity;
import com.example.sky.rss_reader.fragment.base.BaseFragment;

public class ProfileFragment extends BaseFragment {
	private ViewGroup mRoot;
	private ViewGroup mLogin;
	private ViewGroup mStarsList;
	private ViewGroup mHistory;
	private ViewGroup mCleanHistory;
	private ViewGroup mCoordinatorLayout;
	private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
		@SuppressWarnings("deprecation")
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(getResources().getColor(R.color.color_blue_grey_1));
				break;
			case MotionEvent.ACTION_UP:
				v.setBackgroundColor(getResources().getColor(R.color.white));
				switch (v.getId()) {
				case R.id.fragment_profile_login:
					Snackbar.make(mCoordinatorLayout, "未完成", Snackbar.LENGTH_SHORT).show();
					break;
				case R.id.fragment_profile_stars_list:
					startActivity(StarListActivity.newIntent(getActivity()),
							ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
					break;
				case R.id.fragment_profile_history:
					Snackbar.make(mCoordinatorLayout, "未完成", Snackbar.LENGTH_SHORT).show();
					break;
				case R.id.fragment_profile_clean_history:
					Snackbar.make(mCoordinatorLayout, "未完成", Snackbar.LENGTH_SHORT).show();
					break;
				}
				break;
			case MotionEvent.ACTION_CANCEL:
				v.setBackgroundColor(getResources().getColor(R.color.white));
				break;
			}
			return true;
		}
	};

	public static ProfileFragment newInstance() {
		return new ProfileFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRoot = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
		initView();

		mLogin.setOnTouchListener(mOnTouchListener);
		mStarsList.setOnTouchListener(mOnTouchListener);
		mHistory.setOnTouchListener(mOnTouchListener);
		mCleanHistory.setOnTouchListener(mOnTouchListener);
		return mRoot;
	}

	public void initView() {
		mLogin = (ViewGroup) mRoot.findViewById(R.id.fragment_profile_login);
		mStarsList = (ViewGroup) mRoot.findViewById(R.id.fragment_profile_stars_list);
		mHistory = (ViewGroup) mRoot.findViewById(R.id.fragment_profile_history);
		mCleanHistory = (ViewGroup) mRoot.findViewById(R.id.fragment_profile_clean_history);
		mCoordinatorLayout = (ViewGroup) mRoot.findViewById(R.id.fragment_profile_coordinatorlayout);
	}
}
