package com.fnfh.quanmingzhibo.ui.tv.presenter;

import com.fnfh.quanmingzhibo.ui.tv.contract.TvContract;

import static android.R.attr.data;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2017/1/16.
 */

public class TvMainPresenter extends TvContract.TvMainPresenter {
    /*TvMainPresenter 将 view 与 model 对象传入*/
    @Override
    public void getTabList() {
        mRxManager.add( mModel.getTabList().subscribe(
                data -> mView.showTabList(data),
                e -> mView.showError("数据加载失败ヽ(≧Д≦)ノ")
        ));

    }

    @Override
    public void onStart() {
        getTabList();
    }
}
