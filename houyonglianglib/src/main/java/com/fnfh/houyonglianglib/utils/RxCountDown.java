package com.fnfh.houyonglianglib.utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2017/1/4.
 */

public class RxCountDown {
/*利用interval()定时发送Observable，通过map()将0、1、2、3...的计数变为...3、2、1、0倒计时。通过take()取>=0的Observable。*/
    public static Observable<Integer> countdown(int time) {
        if (time < 0) time = 0;

        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long increaseTime) {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take(countTime + 1);

    }
}
