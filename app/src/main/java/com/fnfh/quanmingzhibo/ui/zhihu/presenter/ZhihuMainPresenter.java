package com.fnfh.quanmingzhibo.ui.zhihu.presenter;

import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2017/1/6.
 */

public class ZhihuMainPresenter extends ZhihuContract.ZhihuMainPresenter {
    /*实现主页接口方法*/
    @Override
    public void getTabList() {
        /*mview fragment  mModel 模型层*/
        mView.showTabList(mModel.getTabs());

    }

    @Override
    public void onStart() {
        /*获取 tablist*/
        getTabList();
    }
}
