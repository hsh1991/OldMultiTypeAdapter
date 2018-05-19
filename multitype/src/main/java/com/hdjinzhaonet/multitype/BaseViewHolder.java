package com.hdjinzhaonet.multitype;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by han on 17-9-1.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private View mItemView;

    protected Context context;

    public BaseViewHolder(View itemView, Context context) {
        super(itemView);
        views = new SparseArray<>();
        this.mItemView = itemView;
        this.context = context;
        ButterKnife.bind(this,itemView);
    }

//    public View getView(int resID) {
//        View view = views.get(resID);
//
//        if (view == null) {
//            view = mItemView.findViewById(resID);
//            views.put(resID, view);
//        }
//
//        return view;
//    }

    public abstract void setUpView(T model, int position, MultiTypeAdapter adapter);
}
