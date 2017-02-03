package com.fnfh.quanmingzhibo.api;


import com.fnfh.quanmingzhibo.ui.tv.model.FirstBean;
import com.fnfh.quanmingzhibo.ui.tv.model.TabBean;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;


/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2017/1/3.
 */

public interface TvApi {
    /**
     * tv所有分类
     */
    @GET("http://www.quanmin.tv/json/app/index/category/info-android.json?v=2.2.4&os=1&ver=4")
    Observable<List<TabBean>> getTabList();
//
    @GET("http://www.quanmin.tv/json/app/index/recommend/list-android.json?11241742&v=2.2.4&os=1&ver=4")
    Observable<FirstBean> getFirstList();
//
//    @GET("http://www.quanmin.tv/{url}?11211639&os=1&v=2.2.4&os=1&ver=4")
//    Observable<OtherBean> getOtherList(@Path("url") String url);
//
//    @GET("http://www.quanmin.tv/json/rooms/{uid}/info1.json?11241653&v=2.2.4&os=1&ver=4")
//    Observable<TvShowBean> onTvShow(@Path("uid") String uid);

    @GET("http://www.quanmin.tv/json/page/app-data/info.json?v=2.2.4&os=1&ver=4")
    Observable<Object> getBannerList();

}
