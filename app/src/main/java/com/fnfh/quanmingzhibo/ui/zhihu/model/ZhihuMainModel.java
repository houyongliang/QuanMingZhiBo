package com.fnfh.quanmingzhibo.ui.zhihu.model;

import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2017/1/6.
 */

public class ZhihuMainModel implements ZhihuContract.ZhihuMainModel {
    /*实现接口方法*/
    @Override
    public String[] getTabs() {
        String[] mTabs = new String[]{"日报", "专栏", "微信"};
        return mTabs;
    }
}
