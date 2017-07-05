package com.example.sky.rssreader.model.manager;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import com.example.sky.rssreader.R;
import com.example.sky.rssreader.application.MyApplication;

public class SettingsManager {
	private String mTitleFontName;
	private float mTitleFontSize;
	private String mFontName;
	private float mFontSize;
	private float mLineHeight;
	private SharedPreferences mSharedPreferences = null;

	public SettingsManager() {
		Resources resources = MyApplication.getContext().getResources();
		String defaultTitleFontName = resources.getString(R.string.settings_default_title_font_name);
		String defaultFontName = resources.getString(R.string.settings_default_font_name);
		TypedValue typedValue = new TypedValue();
		resources.getValue(R.dimen.settings_default_title_font_size, typedValue, true);
		Float defaultTitleFontSize = typedValue.getFloat();
		resources.getValue(R.dimen.settings_default_font_size, typedValue, true);
		Float defaultFontSize = typedValue.getFloat();
		resources.getValue(R.dimen.settings_default_line_height, typedValue, true);
		Float defaultLineHeight = typedValue.getFloat();

		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
		mTitleFontName = mSharedPreferences.getString("title_font_name", defaultTitleFontName);
		mTitleFontSize = mSharedPreferences.getFloat("title_font_size", defaultTitleFontSize);
		mFontName = mSharedPreferences.getString("font_name", defaultFontName);
		mFontSize = mSharedPreferences.getFloat("font_size", defaultFontSize);
		mLineHeight = mSharedPreferences.getFloat("line_height", defaultLineHeight);

		mTitleFontName = defaultTitleFontName;
		mTitleFontSize = defaultTitleFontSize;
		mFontName = defaultFontName;
		mFontSize = defaultFontSize;
		mLineHeight = defaultLineHeight;
	}

	public float getTitleFontSize() {
		return mTitleFontSize;
	}

	public void setTitleFontSize(float titleFontSize) {
		mTitleFontSize = titleFontSize;
		mSharedPreferences.edit().putFloat("title_font_size", mTitleFontSize).apply();
	}

	public String getTitleFontName() {
		return mTitleFontName;
	}

	public void setTitleFontName(String titleFontName) {
		mTitleFontName = titleFontName;
		mSharedPreferences.edit().putString("title_font_name", mTitleFontName).apply();
	}

	public String getFontName() {
		return mFontName;
	}

	public void setFontName(String fontName) {
		mFontName = fontName;
		mSharedPreferences.edit().putString("font_name", mFontName).apply();
	}

	public float getFontSize() {
		return mFontSize;
	}

	public void setFontSize(float fontSize) {
		mFontSize = fontSize;
		mSharedPreferences.edit().putFloat("font_size", mFontSize).apply();
	}

	public float getLineHeight() {
		return mLineHeight;
	}

	public void setLineHeight(float lineHeight) {
		mLineHeight = lineHeight;
		mSharedPreferences.edit().putFloat("line_height", mLineHeight).apply();
	}
}
