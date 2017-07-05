package com.example.sky.rssreader.recyclerView.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.sky.rssreader.R;

public class NewsListItemDecoration extends RecyclerView.ItemDecoration {
	private Drawable mDivider;

	public NewsListItemDecoration(Context context) {
		mDivider = context.getResources().getDrawable(R.drawable.shape_newslist_itemdecoration, null);
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();
		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);
			RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			if (i == 0) {
				int bottom = child.getTop();
				int top = bottom - mDivider.getIntrinsicHeight();
				mDivider.setBounds(left, top, right, bottom);
				mDivider.draw(c);
			}
			int top = child.getBottom() + params.bottomMargin;
			int bottom = top + mDivider.getIntrinsicHeight();
			// 设置mDivider的绘画区域和宽高，相当于给定左上角和右下角坐标确定一个矩形
			mDivider.setBounds(left, top, right, bottom);
			// 在画布（Canvas）上画
			mDivider.draw(c);
		}
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDrawOver(c, parent, state);
	}

	// Item偏移
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		if (parent.getChildAdapterPosition(view) == 0) {
			outRect.set(0, mDivider.getIntrinsicHeight(), 0, mDivider.getIntrinsicHeight());
		} else {
			outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
		}
	}
}
