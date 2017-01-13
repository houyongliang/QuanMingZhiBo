package com.fnfh.quanmingzhibo.ui.main;


import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.fnfh.houyonglianglib.base.CoreBaseActivity;
import com.fnfh.houyonglianglib.utils.RxCountDown;
import com.fnfh.houyonglianglib.utils.helper.RxUtil;
import com.fnfh.quanmingzhibo.R;
import com.fnfh.quanmingzhibo.ui.zhihu.activity.ZhihuMainActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;

import rx.Observable;
import rx.Subscriber;


public class FlashActivity extends CoreBaseActivity {


    private final int countTime = 3;
    @BindView(R.id.tv_activity_flash)
    TextView tvActivityFlash;

    /*获取布局参数*/
    @Override
    public int getLayoutId() {
        return R.layout.activity_flash;
    }


    /* 确认 使用 getlayoutid 布局*/
    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /*初始的时候判断权限*/

            Observable.just("权限申请")
                    .compose(RxPermissions.getInstance(this).ensureEach(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE))
                    .compose(RxUtil.rxSchedulerHelper())
                    .subscribe(permission -> {
                        if (permission.granted) {
                            timeDown(countTime);
                        }
                    });

        } else {

            timeDown(countTime);

        }


    }
    public void timeDown(int time){
        RxCountDown.countdown(time)

                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onCompleted() {
                        startActivity(ZhihuMainActivity.class);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("失败了 ");
                    }

                    @Override
                    public void onNext(Integer ainteger) {
                        tvActivityFlash.setText(ainteger + " S");
                    }
                });
    }

}
