package com.fireant.oschat.adapter;

/**
 * Created by zhangdeyi on 15/7/25.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的ViewHolder
 *
 * Created by 火蚁 on 15/4/8.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    private int mLayoutId;
    private int mViewTypeCount;

    public CommonAdapter(Context context, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = new ArrayList<T>();
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh = ViewHolder.getViewHolder(this.mContext, convertView, parent, getLayoutId(position), position);
        convert(vh, getItem(position), position);
        return vh.getConvertView();
    }

    protected  int getLayoutId(int position) {
        return this.mLayoutId;
    }

    public List<T> getDatas() {
        return this.mDatas;
    }

    // 获取ViewHodler
    public ViewHolder getViewHodler(int position, View convertView, ViewGroup parent) {

        return ViewHolder.getViewHolder(this.mContext, convertView, parent, this.mLayoutId, position);
    }

    // 提供给外部填充实际的显示数据，以及可以一些其他的操作，如：隐藏＝＝
    public abstract void convert(ViewHolder vh, T item, int position);

    public void addItem(T item) {
        checkListNull();
        mDatas.add(item);
        notifyDataSetChanged();
    }

    public void addItem(int location, T item) {
        checkListNull();
        mDatas.add(location, item);
        notifyDataSetChanged();
    }

    public void addItem(List<T> items) {
        checkListNull();
        mDatas.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItem(int location) {
        if (mDatas == null && mDatas.isEmpty()) {
            return;
        }
        mDatas.remove(location);
        notifyDataSetChanged();
    }

    public void clear() {
        if (mDatas == null && mDatas.isEmpty()) {
            return;
        }
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void checkListNull() {
        if (mDatas == null) {
            mDatas = new ArrayList<T>();
        }
    }

    public int getCurrentPage() {
        return getCount() % 20;
    }
}
