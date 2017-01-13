package com.fnfh.quanmingzhibo.ui.zhihu.presenter.sectionpresenter;


import android.util.Log;

import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;



/**
 * Created by hpw on 16/11/5.
 */
public class SectionPresenter extends ZhihuContract.SectionPresenter {
    @Override
    public void onStart() {
        getSectionData();
    }

    @Override
    public void getSectionData() {
        Log.e("SectionPresenter","----------"+"成功");
        mRxManager.add(mModel.getSectionData()
                .subscribe(
                        data -> mView.showContent(data),
                        e -> mView.showError("数据加载失败ヽ(≧Д≦)ノ")
                ));
    }
}
