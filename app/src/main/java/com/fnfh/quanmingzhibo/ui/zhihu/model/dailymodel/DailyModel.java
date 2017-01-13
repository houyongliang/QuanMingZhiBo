package com.fnfh.quanmingzhibo.ui.zhihu.model.dailymodel;



import com.fnfh.houyonglianglib.data.net.RxService;
import com.fnfh.houyonglianglib.utils.helper.RxUtil;
import com.fnfh.quanmingzhibo.api.ZhiHuApi;
import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;

import rx.Observable;

/**
 * Created by hpw on 16/11/2.
 */
public class DailyModel implements ZhihuContract.DailyModel {

    @Override
    public Observable<DailyListBean> getDailyData() {
        return RxService.createApi(ZhiHuApi.class).getDailyList().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<ZhihuDetailBean> getZhihuDetails(int anInt) {
        return RxService.createApi(ZhiHuApi.class).getDetailInfo(anInt).compose(RxUtil.rxSchedulerHelper());
    }
}
