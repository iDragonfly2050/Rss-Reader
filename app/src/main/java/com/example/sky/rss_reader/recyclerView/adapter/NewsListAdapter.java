package com.example.sky.rssreader.recyclerView.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.sky.rssreader.R;
import com.example.sky.rssreader.model.news.NewsItem;
import com.example.sky.rssreader.model.news.NewsList;

public class NewsListAdapter extends RecyclerView.Adapter<Holder> {
	private Context mContext;
	private NewsList mNewsList;
	private View.OnClickListener mOnItemClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) { }
	};

	public NewsListAdapter(Context context, NewsList newsList) {
		mContext = context;
		mNewsList = newsList;
	}

	@Override
	public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		View view = layoutInflater.inflate(R.layout.widget_news_list_item, parent, false);
		return new Holder(mContext, view);
	}

	@Override
	public void onBindViewHolder(Holder holder, int position) {
		NewsItem newsItem = mNewsList.getNewsItem(holder.getAdapterPosition());
		holder.bindModel(newsItem);
		holder.setOnClickListener(mOnItemClickListener);
	}

	public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
		mOnItemClickListener = onItemClickListener;
	}

	public void setItems(NewsList newsList) {
		mNewsList = newsList;
	}

	@Override
	public int getItemViewType(int position) {
		if (position == 0) {
			return 0;
		}
		return 1;
	}

	@Override
	public int getItemCount() {
		return mNewsList.getSize();
	}
}

@SuppressWarnings({"FieldCanBeLocal", "unused"})
class Holder extends RecyclerView.ViewHolder {
	private Context mContext;
	private TextView mTitle;
	private TextView mSummary;
	private TextView mAuthor;
	private ImageView mPerson;
	private ImageView[] mImageViews = new ImageView[3];
	private int mbackgroundColor;

	Holder(Context context, View itemView) {
		super(itemView);
		initView(itemView);
		mContext = context;
	}

	void bindModel(final NewsItem newsItem) {
		mTitle.setText(newsItem.getTitle());
		mSummary.setText(newsItem.getSummary());
		mAuthor.setText(newsItem.getAuthor());
		for (int i = 0; i < 3; i++) {
			if (i < newsItem.getImageUrls().size()) {
				mImageViews[i].setVisibility(View.VISIBLE);
				Glide.with(mContext)
						.load(newsItem.getImageUrls().get(i))
						.into(mImageViews[i]);
			}
			else {
				mImageViews[i].setVisibility(View.GONE);
			}
		}


		setReaded(newsItem.isReaded());

		itemView.setOnTouchListener(new View.OnTouchListener() {
			@SuppressWarnings("deprecation")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					itemView.setBackgroundColor(mContext.getResources().getColor(R.color.color_grey_3));
					break;
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_UP:
					itemView.setBackgroundColor(mContext.getResources().getColor(R.color.color_grey_1));
					newsItem.setReaded(true);
					setReaded(true);
					break;
				case MotionEvent.ACTION_CANCEL:
					itemView.setBackgroundColor(mContext.getResources().getColor(R.color.color_grey_1));
					break;
				case MotionEvent.ACTION_OUTSIDE:
					break;
				}
				return false;
			}
		});
	}

	void setOnClickListener(View.OnClickListener onClickListener) {
		itemView.setOnClickListener(onClickListener);
	}

	@SuppressWarnings("deprecation")
	private void setReaded(boolean readed) {
		if (readed) {
			mTitle.setTextColor(mContext.getResources().getColor(R.color.color_grey_6));
			mSummary.setTextColor(mContext.getResources().getColor(R.color.color_grey_6));
			mAuthor.setTextColor(mContext.getResources().getColor(R.color.color_grey_6));
			mPerson.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_person_grey_600_24dp));
		} else {
			mTitle.setTextColor(mContext.getResources().getColor(R.color.black));
			mSummary.setTextColor(mContext.getResources().getColor(R.color.color_grey_7));
			mAuthor.setTextColor(mContext.getResources().getColor(R.color.color_grey_7));
			mPerson.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_person_blue_grey_800_24dp));
		}
	}

	private void initView(View itemView) {
		mTitle = (TextView) itemView.findViewById(R.id.widget_news_list_item_title);
		mSummary = (TextView) itemView.findViewById(R.id.widget_news_list_item_summary);
		mAuthor = (TextView) itemView.findViewById(R.id.widget_news_list_item_author);
		mPerson = (ImageView) itemView.findViewById(R.id.widget_news_list_item_person);
		mImageViews[0] = (ImageView) itemView.findViewById(R.id.widget_news_list_item_img0);
		mImageViews[1] = (ImageView) itemView.findViewById(R.id.widget_news_list_item_img1);
		mImageViews[2] = (ImageView) itemView.findViewById(R.id.widget_news_list_item_img2);
	}
}

