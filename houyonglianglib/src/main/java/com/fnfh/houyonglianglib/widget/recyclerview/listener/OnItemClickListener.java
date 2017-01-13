package com.fnfh.houyonglianglib.widget.recyclerview.listener;

import android.view.View;

import com.fnfh.houyonglianglib.widget.recyclerview.BaseQuickAdapter;


/**
 * A convenience class to extend when you only want to OnItemClickListener for a subset
 * of all the SimpleClickListener. This implements all methods in the
 * {@link SimpleClickListener}
 */
public abstract   class OnItemClickListener extends SimpleClickListener {



    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SimpleOnItemClick(adapter,view,position);
    }


    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }


    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }


    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
    public abstract void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position);
}
