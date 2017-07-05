package com.example.sky.rss_reader.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.sky.rss_reader.R;
import com.example.sky.rss_reader.activity.base.BaseActivity;
import com.example.sky.rss_reader.model.manager.AppManager;
import com.example.sky.rss_reader.model.news.NewsItem;
import com.example.sky.rss_reader.model.news.NewsList;
import com.example.sky.rss_reader.recyclerView.adapter.NewsListAdapter;
import com.example.sky.rss_reader.recyclerView.itemDecoration.NewsListItemDecoration;

public class StarListActivity extends BaseActivity {
	private static final int STAR_REQUEST_CODE = 1;
	public static final String STARS_LIST_CHANGED = "STARS_LIST_CHANGED";
	private TextView mTitle;
	private ImageButton mBackButton;
	private TextView mShowZero;
	private RecyclerView mRecyclerView;
	private NewsList mStarsList = AppManager.getInstance().getStarsList();

	public static Intent newIntent(Context context) {
		return new Intent(context, StarListActivity.class);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TransitionInflater transitionInflater = TransitionInflater.from(this);
		getWindow().setEnterTransition(transitionInflater.inflateTransition(R.transition.window_enter_transition));
		getWindow().setReturnTransition(transitionInflater.inflateTransition(R.transition.window_return_transition));

		setContentView(R.layout.activity_stars_list);

		initView();
		mTitle.setText("我的收藏");

		mBackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finishAfterTransition();
			}
		});

		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.addItemDecoration(new NewsListItemDecoration(this));
		NewsListAdapter adapter = new NewsListAdapter(this, mStarsList, false);
		adapter.setOnItemClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int position = mRecyclerView.getChildAdapterPosition(v);
				NewsItem newsItem = mStarsList.getNewsItem(position);
				startActivityForResult(NewsActivity.newIntent(StarListActivity.this, newsItem), STAR_REQUEST_CODE,
						ActivityOptions.makeSceneTransitionAnimation(StarListActivity.this).toBundle());
			}
		});
		mRecyclerView.setAdapter(adapter);
		if (adapter.getItemCount() == 0) {
			mShowZero.setVisibility(View.VISIBLE);
			mRecyclerView.setVisibility(View.INVISIBLE);
		} else {
			mShowZero.setVisibility(View.INVISIBLE);
			mRecyclerView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		if (requestCode == STAR_REQUEST_CODE) {
			if (data == null) {
				return;
			}
			if (data.getBooleanExtra(STARS_LIST_CHANGED, false)) {
				mRecyclerView.getAdapter().notifyDataSetChanged();
			}
		}
	}

	private void initView() {
		mTitle = (TextView) findViewById(R.id.activity_stars_list_title);
		mBackButton = (ImageButton) findViewById(R.id.activity_stars_list_back);
		mShowZero = (TextView) findViewById(R.id.activity_stars_list_show_zero);
		mRecyclerView = (RecyclerView) findViewById(R.id.activity_stars_list_recyclerview);
	}
}
