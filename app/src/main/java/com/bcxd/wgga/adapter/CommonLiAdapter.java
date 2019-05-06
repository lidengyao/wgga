package com.bcxd.wgga.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 *
 * 通用Adapter
 *
 * Created by jinxh on 15/12/4.
 */
public abstract class CommonLiAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mData;
    private View _itemView;
//    protected final int mItemLayoutId;

    public CommonLiAdapter(Context context, List<T> data, View itemView) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = data;
        _itemView=itemView;
//        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData == null ? null :mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewLiHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(position,viewHolder, getItem(position));
        return viewHolder.getConvertView();

    }

    public abstract void convert(int position,ViewLiHolder helper, T item);

    private ViewLiHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewLiHolder.get(mContext, convertView, parent, _itemView,
                position);
    }

    public void refresh(List<T> data){
        this.mData = data;
        notifyDataSetChanged();
    }
}