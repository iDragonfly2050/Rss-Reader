package com.example.sky.rssreader.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.sky.rssreader.R;
import com.example.sky.rssreader.activity.base.BaseActivity;
import com.example.sky.rssreader.model.manager.AppManager;
import com.example.sky.rssreader.model.news.NewsItem;
import com.example.sky.rssreader.model.news.Style;

public class NewsActivity extends BaseActivity {
	private static String NEWS_ITEM = "NEWS_ITEM";
	private WebView mWebView;
	private TextView mTitle;
	private ImageButton mBackButton;
	private ImageButton mStarButton;

	public static Intent newIntent(Context context, NewsItem newsItem) {
		AppManager.getInstance().setCurrentNewsItem(newsItem);
		return new Intent(context, NewsActivity.class);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		TransitionInflater transitionInflater = TransitionInflater.from(this);
		getWindow().setEnterTransition(transitionInflater.inflateTransition(R.transition.window_enter_transition));
		getWindow().setReturnTransition(transitionInflater.inflateTransition(R.transition.window_return_transition));

		initView();
		loadNewItem();
		mStarButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mStarButton.setSelected(!mStarButton.isSelected());
			}
		});
		mBackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finishAfterTransition();
			}
		});

		mWebView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return (event.getAction() == MotionEvent.ACTION_MOVE);
			}
		});
	}

	private void loadNewItem() {
		NewsItem newsItem = AppManager.getInstance().getCurrentNewsItem();
		if (newsItem == null) {
			Log.e("ERROR", "newsItem haven't init");
		} else {
			mTitle.setText(newsItem.getRssSourceName());
			String html = newsItem.getHtmlBody();
			String mime = "text/html";
			String encoding = "utf-8";
			mWebView.loadDataWithBaseURL("", Style.generateStyle() + html, mime, encoding, null);
		}
	}

	private void initView() {
		mWebView = (WebView) findViewById(R.id.activity_news_webview);
		mTitle = (TextView) findViewById(R.id.activity_news_title);
		mStarButton = (ImageButton) findViewById(R.id.activity_news_star);
		mBackButton = (ImageButton) findViewById(R.id.activity_news_back);
	}
}
