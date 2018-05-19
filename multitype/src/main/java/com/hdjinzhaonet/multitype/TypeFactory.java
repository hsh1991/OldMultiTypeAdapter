package com.hdjinzhaonet.multitype;

import android.content.Context;
import android.view.View;

/**
 * Created by han on 17-9-1.
 */

public abstract class TypeFactory {
    protected Context context;
    public TypeFactory(Context context){
        this.context = context;
    }

    /**
     * 如果需要给model 返回不同的布局文件，复写此方法
     * @param type
     * @return
     */
    public int type(int type) {
        return type;
    }

    public abstract BaseViewHolder createViewHolder(int type, View itemView);
}
