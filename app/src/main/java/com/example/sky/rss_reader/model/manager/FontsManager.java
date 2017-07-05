package com.example.sky.rss_reader.model.manager;

import android.content.res.AssetManager;
import com.example.sky.rss_reader.application.MyApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FontsManager {
	private ArrayList<String> mFonts;
	private static FontsManager instance = null;

	public static synchronized FontsManager getInstance() {
		if (instance == null) {
			instance = new FontsManager();
		}
		return instance;
	}

	private FontsManager() {
		AssetManager assetManager = MyApplication.getContext().getAssets();
		try {
			String[] names = assetManager.list("fonts");
			mFonts = new ArrayList<>(Arrays.asList(names));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getFonts() {
		return mFonts;
	}
}
