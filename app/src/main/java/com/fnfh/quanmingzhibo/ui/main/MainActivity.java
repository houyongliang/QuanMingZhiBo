package com.fnfh.quanmingzhibo.ui.main;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import com.fnfh.houyonglianglib.base.CoreBaseActivity;
import com.fnfh.houyonglianglib.utils.SpUtil;
import com.fnfh.quanmingzhibo.R;

public class MainActivity extends CoreBaseActivity {
Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }
};

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        findViewById(R.id.btn_1).setOnClickListener(click -> {
            SpUtil.setNightModel(mContext, !SpUtil.getNightModel(mContext));
            reload();
        });
    }
}
