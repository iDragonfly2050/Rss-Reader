package com.example.sky.rssreader.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class UnitsConvert {
	public static int dpToPx(Context context, float dp) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
	}

	public static int pxToDp(Context context, float px) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
	}
}
