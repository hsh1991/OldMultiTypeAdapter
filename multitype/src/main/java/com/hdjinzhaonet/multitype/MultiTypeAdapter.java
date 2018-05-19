package com.hdjinzhaonet.multitype;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by han on 17-9-1.
 * 使用步骤
 * adapter = new MultiTypeAdapter(new TypeFactoryForHome(getContext()));
 *
 * adapter.addData(datas)
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private TypeFactory typeFactory;
    private List<Visitable> models;


    public MultiTypeAdapter(TypeFactory typeFactory) {
        this.typeFactory = typeFactory;
        this.models = new ArrayList<>();
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

    /**
     * 清除
     */
    public void remove() {
        models.clear();
        notifyDataSetChanged();
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
//        return typeFactory.type(models.get(position));
//        return models.get(position).type(typeFactory);
    }


}
