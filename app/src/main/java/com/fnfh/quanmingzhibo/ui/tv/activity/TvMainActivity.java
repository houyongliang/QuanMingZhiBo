package com.fnfh.quanmingzhibo.ui.tv.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;


import com.fnfh.houyonglianglib.base.CoreBaseActivity;
import com.fnfh.houyonglianglib.base.CoreBaseFragment;
import com.fnfh.houyonglianglib.utils.StatusBarUtil;
import com.fnfh.houyonglianglib.utils.helper.FragmentAdapter;
import com.fnfh.quanmingzhibo.Constants;
import com.fnfh.quanmingzhibo.R;

import com.fnfh.quanmingzhibo.ui.tv.contract.TvContract;
import com.fnfh.quanmingzhibo.ui.tv.fragment.FirstFragment;
import com.fnfh.quanmingzhibo.ui.tv.model.TabBean;
import com.fnfh.quanmingzhibo.ui.tv.model.TvMainModel;
import com.fnfh.quanmingzhibo.ui.tv.presenter.TvMainPresenter;
import com.fnfh.quanmingzhibo.ui.zhihu.activity.ZhihuMainActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



public class TvMainActivity extends CoreBaseActivity<TvMainPresenter,TvMainModel> implements TvContract.TvMainView,CoreBaseFragment.OnBackToFirstListener{
    /*获取 fragments*/
    List<Fragment> fragments = new ArrayList<Fragment>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tv_main;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
//        StatusBarUtil.setTransparent(this);/*设置状态栏透明？？*/
        toolbar.setTitle("全民TV");

    }


    @Override
    public void showTabList(List<TabBean> mTabs) {
        for (int i = 0; i < mTabs.size(); i++) {
            /*设置tab*/
            tabs.addTab(tabs.newTab().setText(mTabs.get(i).getName()));
            switch (i){
                case 0:
                    fragments.add(new FirstFragment());
                    break;
                default:
                    fragments.add(new FirstFragment());
                    break;
            }
        }
    /*vp 设配器设置*/
        int position = 0;
        for (int i = 0; i < mTabs.size(); i++) {
            if (mTabs.get(i).getSlug().equals(getIntent().getStringExtra(Constants.ARG_POSITION_TV)))
                position = i;
        }
        viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
        viewpager.setCurrentItem(position);//要设置到viewpager.setAdapter后才起作用
        tabs.setupWithViewPager(viewpager);
        tabs.setVerticalScrollbarPosition(position);
        for (int i = 0; i < mTabs.size(); i++) {
            tabs.getTabAt(i).setText(mTabs.get(i).getName());
        }

    }   

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String msg) {

    }
    /*跳转*/
    public static void startActivity(Context context, String position) {
        Intent starter = new Intent(context, TvMainActivity.class);
        starter.putExtra(Constants.ARG_POSITION_TV, position);
        context.startActivity(starter);
    }

    @Override
    public void onBackToFirstFragment() {
        startActivity(ZhihuMainActivity.class);
        finish();
    }
}
