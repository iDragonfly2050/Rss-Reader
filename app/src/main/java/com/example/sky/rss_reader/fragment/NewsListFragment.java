package com.example.sky.rssreader.fragment;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sky.rssreader.R;
import com.example.sky.rssreader.activity.NewsActivity;
import com.example.sky.rssreader.fragment.base.BaseFragment;
import com.example.sky.rssreader.model.manager.AppManager;
import com.example.sky.rssreader.model.news.NewsItem;
import com.example.sky.rssreader.model.news.NewsList;
import com.example.sky.rssreader.model.rss.BaseRssSource;
import com.example.sky.rssreader.model.rss.RssSource1;
import com.example.sky.rssreader.recyclerView.adapter.NewsListAdapter;
import com.example.sky.rssreader.recyclerView.diffutils.NewsListDiffCallback;
import com.example.sky.rssreader.recyclerView.itemDecoration.NewsListItemDecoration;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

public class NewsListFragment extends BaseFragment {
	private static final String KEY_INDEX = "INDEX";
	private static final int PUSH_FIRST = 0;
	private static final int PUSH_BACK = 1;
	private View mRoot = null;
	private RecyclerView mNewsListRecyclerView = null;
	private SwipeRefreshLayout mSwipeRefreshLayout = null;
	private CoordinatorLayout mCoordinatorLayout = null;
	private NewsList mNewsList = new NewsList();
	private BaseRssSource mRssSource = new RssSource1();
	private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
		@Override
		public void onRefresh() {
			Observable
					.create(new ObservableOnSubscribe<Integer>() {
						@Override
						public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
							mRssSource.pickRaw();
							emitter.onNext(mRssSource.getSize());
							emitter.onComplete();
						}
					})
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Consumer<Integer>() {
						@Override
						public void accept(@NonNull Integer integer) throws Exception {
							ArrayList<NewsItem> oldList = mNewsList.getList();
							int count = mNewsList.pushFirst(mRssSource.getItemsList());
							ArrayList<NewsItem> newList = mNewsList.getList();
							DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new NewsListDiffCallback(oldList, newList));
							diffResult.dispatchUpdatesTo(mNewsListRecyclerView.getAdapter());
							mNewsListRecyclerView.getAdapter().notifyDataSetChanged();
							mSwipeRefreshLayout.setRefreshing(false);
							Snackbar snackbar;
							if (count == 0) {
								snackbar = Snackbar.make(mCoordinatorLayout, "没有更多信息", Snackbar.LENGTH_SHORT);
							} else {
								snackbar = Snackbar.make(mCoordinatorLayout, "新增 " + String.valueOf(count) + " 条信息", Snackbar.LENGTH_SHORT);
							}
							snackbar.show();
						}
					});
		}
	};

	public static NewsListFragment newInstance(int index) {
		NewsListFragment newsListFragment = new NewsListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(KEY_INDEX, index);
		newsListFragment.setArguments(bundle);
		return newsListFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int index = getArguments().getInt(KEY_INDEX, -1);
		mRssSource = AppManager.getInstance().getRssFollowList().get(index);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRoot = inflater.inflate(R.layout.fragment_news_list, container, false);
		initView();

		mNewsListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mNewsListRecyclerView.addItemDecoration(new NewsListItemDecoration(getActivity()));
		NewsListAdapter adapter = new NewsListAdapter(getActivity(), mNewsList);
		adapter.setOnItemClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int position = mNewsListRecyclerView.getChildAdapterPosition(v);
				NewsItem newsItem = mNewsList.getNewsItem(position);
				getActivity().startActivity(NewsActivity.newIntent(getActivity(), newsItem),
						ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
			}
		});
		mNewsListRecyclerView.setAdapter(adapter);
		mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
		mSwipeRefreshLayout.setColorSchemeResources(R.color.color_blue_grey_8);
		mOnRefreshListener.onRefresh();
		return mRoot;
	}

	private void refresh(final int firstOrBack) {
		if (firstOrBack != PUSH_FIRST && firstOrBack != PUSH_BACK) {
			return;
		}
		Observable
				.create(new ObservableOnSubscribe<Integer>() {
					@Override
					public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
						mRssSource.pickRaw();
						emitter.onNext(mRssSource.getSize());
						emitter.onComplete();
					}
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						ArrayList<NewsItem> oldList = mNewsList.getList();
						if (firstOrBack == PUSH_FIRST) {
							mNewsList.pushFirst(mRssSource.getItemsList());
						} else {
							mNewsList.pushBack(mRssSource.getItemsList());
						}
						ArrayList<NewsItem> newList = mNewsList.getList();
						DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new NewsListDiffCallback(oldList, newList));
						diffResult.dispatchUpdatesTo(mNewsListRecyclerView.getAdapter());
						mNewsListRecyclerView.getAdapter().notifyDataSetChanged();
					}
				});
	}

	private void initView() {
		mNewsListRecyclerView = (RecyclerView) mRoot.findViewById(R.id.fragment_news_list_recyclerview);
		mSwipeRefreshLayout = (SwipeRefreshLayout) mRoot.findViewById(R.id.fragment_news_list_swiperefreshlayout);
		mCoordinatorLayout = (CoordinatorLayout) mRoot.findViewById(R.id.fragment_news_list_coordinatorlayout);
	}
}
