package com.hdjinzhaonet.multitype;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by han on 17-9-1.
 * 使用步骤
 * adapter = new MultiTypeAdapter(new TypeFactoryForHome(getContext()));
 * <p>
 * adapter.addData(datas)
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private TypeFactory typeFactory;
    private List<Visitable> models;

    private OnBundleChangeListener onBundleChangeListener;

    private Bundle bundle;//存储共享的参数


    public MultiTypeAdapter(TypeFactory typeFactory) {
        this.typeFactory = typeFactory;
        this.models = new ArrayList<>();
        bundle = new Bundle();
    }

    public void addData(List models) {
        if (models == null) {
            return;
        }
        if (this.models == null) {
            this.models = models;
        } else {
            this.models.addAll(models);
        }
        notifyDataSetChanged();
    }

    public void addData(Visitable model) {
        if (model == null) {
            return;
        }
        if (this.models == null) {
            this.models = new ArrayList<>();
        }
        models.add(model);
        notifyDataSetChanged();
    }

    public void setData(List models) {
        if (models == null) {
            return;
        }
        this.models = models;
        notifyDataSetChanged();
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    /**
     * 清除
     */
    public void remove() {
        models.clear();
        notifyDataSetChanged();
    }

    public Visitable getData(int positon) {
        return models.get(positon);
    }


    public void setOnBundleChangeListener(OnBundleChangeListener onBundleChangeListener) {
        this.onBundleChangeListener = onBundleChangeListener;
    }
    //
    private void bundleChange() {
        if (onBundleChangeListener != null) {
            onBundleChangeListener.onChangeListener();
        }
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return typeFactory.createViewHolder(viewType, LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setUpView(models.get(position), position, this);
    }

    @Override
    public int getItemCount() {
        if (null == models) {
            return 0;
        }
        return models.size();
    }


    @Override
    public int getItemViewType(int position) {
        return typeFactory.type(models.get(position).type());
    }

    public interface OnBundleChangeListener {
        public void onChangeListener();
    }

    public void setParam(String key, String v) {
        bundle.putString(key, v);
        bundleChange();
    }

    public void setParam(String key, int v) {
        bundle.putInt(key, v);
        bundleChange();
    }

    public void setParam(String key, boolean v) {
        bundle.putBoolean(key, v);
        bundleChange();
    }

    public String getStringParam(String key, String d) {
        return bundle.getString(key, d);
    }

    public boolean getBooleanParam(String key, boolean d) {
        return bundle.getBoolean(key, d);
    }

    public int getIntParam(String key, int d) {
        return bundle.getInt(key, d);
    }


}
