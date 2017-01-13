package com.fnfh.quanmingzhibo.ui.zhihu.model.sectionmodel;



import com.fnfh.houyonglianglib.data.net.RxService;
import com.fnfh.houyonglianglib.utils.helper.RxUtil;
import com.fnfh.quanmingzhibo.api.ZhiHuApi;
import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;

import rx.Observable;

/**
 * Created by hpw on 16/11/5.
 */

public class SectionModel implements ZhihuContract.SectionModel {
    @Override
    public Observable<SectionListBean> getSectionData() {
        return RxService.createApi(ZhiHuApi.class).getSectionList().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<SectionChildListBean> getSectionListData(int id) {
        return RxService.createApi(ZhiHuApi.class).getSectionChildList(id).compose(RxUtil.rxSchedulerHelper());
    }
}
