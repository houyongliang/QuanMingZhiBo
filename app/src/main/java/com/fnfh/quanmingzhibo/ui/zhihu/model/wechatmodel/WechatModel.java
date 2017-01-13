package com.fnfh.quanmingzhibo.ui.zhihu.model.wechatmodel;



import com.fnfh.houyonglianglib.data.net.RxService;
import com.fnfh.houyonglianglib.utils.helper.RxUtil;
import com.fnfh.quanmingzhibo.Constants;
import com.fnfh.quanmingzhibo.api.WechatApi;
import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;

import java.util.List;

import rx.Observable;

/**
 * Created by hpw on 16/11/6.
 */
public class WechatModel implements ZhihuContract.WechatModel {
    @Override
    public Observable<List<WXItemBean>> getWechatData(int num, int page) {
        return RxService.createApi(WechatApi.class, WechatApi.HOST)
                .getWXHot(Constants.WEIXIN_KEY, num, page)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult());
    }
}
