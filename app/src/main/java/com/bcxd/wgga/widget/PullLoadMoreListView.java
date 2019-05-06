package com.bcxd.wgga.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.utils.DensityUtils;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;


/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class PullLoadMoreListView extends LinearLayout {
    private Context mContext;
    private ListView mListView;
    private PtrFrameLayout mPtrFrameLayout;
    private LoadMoreListViewContainer mLoadMoreContainer;
    private PullLoadMoreListener mPullLoadMoreListener;
    private boolean pullRefreshEnable = true;
    private boolean pushRefreshEnable = true;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;


    public PullLoadMoreListView(Context context) {
        super(context);
        initView(context);
    }

    public void setAdapter(BaseAdapter adapter) {
        if (adapter != null) {
            mListView.setAdapter(adapter);
        }
    }

    public void addHeaderView(View view) {
        mListView.addHeaderView(view);
    }


    public PullLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullLoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.listview_pull_loadmore_layout, null);
        mListView = (ListView) view.findViewById(R.id.list);
        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.pfl);
        mLoadMoreContainer = (LoadMoreListViewContainer) view.findViewById(R.id.load_more_container);
        setPtrHeader();
        initLoadMore();
        this.addView(view);
    }

    public void refreshComplete() {
        mPtrFrameLayout.refreshComplete();
        isRefresh = false;
    }

    public void loadMoreFinish(boolean emptyResult, boolean hasMore) {
        mLoadMoreContainer.loadMoreFinish(emptyResult, hasMore);
        isLoadMore = false;
    }

    private void initLoadMore() {
        mLoadMoreContainer.useDefaultFooter();
        mLoadMoreContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
//                Logger.d("PullLoadMoreListView", "onLoadMore");
                if (mPullLoadMoreListener != null && !isRefresh && !isLoadMore && pushRefreshEnable) {
                    mPullLoadMoreListener.onLoadMore();
                    isLoadMore = true;
                }
            }
        });
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListView.setOnItemClickListener(listener);
    }

    public void setOnPullLoadMoreListener(PullLoadMoreListener listener) {
        mPullLoadMoreListener = listener;
    }

    public interface PullLoadMoreListener {
        void onRefresh();

        void onLoadMore();
    }

    public void setPullRefreshEnable(boolean enable) {
        pullRefreshEnable = enable;
    }


    public boolean getPullRefreshEnable() {
        return pullRefreshEnable;
    }

    public boolean getPushRefreshEnable() {
        return pushRefreshEnable;
    }


    public void setPushRefreshEnable(boolean pushRefreshEnable) {
        this.pushRefreshEnable = pushRefreshEnable;
    }

    private boolean canPull() {
        boolean result = false;
        if (mListView.getFirstVisiblePosition() == 0) {
            final View topChildView = mListView.getChildAt(0);
            if (topChildView == null) {
                result = true;
            } else {
                result = topChildView.getTop() == 0;
            }
        }
        return result;
    }

    public void autoRefresh() {
        mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.autoRefresh(false);
            }
        }, 100);

    }

    private void setPtrHeader() {
        // header
        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getContext().getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(getContext(), 8f), 0, DensityUtils.dp2px(getContext(), 8f));
        header.setPtrFrameLayout(mPtrFrameLayout);
        mPtrFrameLayout.setKeepHeaderWhenRefresh(true);
        mPtrFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrameLayout.setResistance(1.5f);
        mPtrFrameLayout.setPullToRefresh(true);
        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return pullRefreshEnable && !isLoadMore && canPull();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                if (mPullLoadMoreListener != null) {
//                    Logger.d("PullLoadMoreListView", "onRefreshBegin");
                    mPullLoadMoreListener.onRefresh();
                    isRefresh = true;
                }
            }
        });
    }

}
