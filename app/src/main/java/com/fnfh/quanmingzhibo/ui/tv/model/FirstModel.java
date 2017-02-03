package com.fnfh.quanmingzhibo.ui.tv.model;



import com.fnfh.houyonglianglib.data.net.RxService;
import com.fnfh.houyonglianglib.utils.helper.RxUtil;
import com.fnfh.quanmingzhibo.api.TvApi;
import com.fnfh.quanmingzhibo.ui.tv.contract.TvContract;

import rx.Observable;

/**
 * Created by hpw on 16/12/4.
 */

public class FirstModel implements TvContract.FirstModel {
    @Override
    public Observable<FirstBean> getFirstData() {
        return RxService.createApi(TvApi.class).getFirstList().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<Object> getBannerData() {
        return RxService.createApi(TvApi.class).getBannerList().compose(RxUtil.rxSchedulerHelper());
    }
}
