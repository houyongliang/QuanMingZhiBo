package com.fnfh.quanmingzhibo.ui.tv.model;

import com.fnfh.houyonglianglib.data.net.RxService;
import com.fnfh.houyonglianglib.utils.helper.RxUtil;
import com.fnfh.quanmingzhibo.api.TvApi;
import com.fnfh.quanmingzhibo.ui.tv.contract.TvContract;

import java.util.List;

import rx.Observable;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2017/1/16.
 */

public class TvMainModel  implements  TvContract.TvMainModel {
    @Override
    public Observable<List<TabBean>> getTabList() {
        return RxService.createApi(TvApi.class)/*创建 retrofit对象，并对 OKhttp 进行相应处理*/
                .getTabList()
                .compose(RxUtil.rxSchedulerHelper());/*简化线程 子线程处理数据后返回主线程*/
    }
}
