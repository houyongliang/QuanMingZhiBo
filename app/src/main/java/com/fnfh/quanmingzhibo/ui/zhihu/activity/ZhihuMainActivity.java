package com.fnfh.quanmingzhibo.ui.zhihu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.fnfh.houyonglianglib.base.CoreBaseActivity;
import com.fnfh.quanmingzhibo.R;
import com.fnfh.quanmingzhibo.ui.tv.activity.TvMainActivity;
import com.fnfh.quanmingzhibo.ui.zhihu.fragment.ZhihuMainFragment;


import butterknife.BindView;



public class ZhihuMainActivity extends CoreBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,  ZhihuMainFragment.OnFragmentOpenDrawerListener,ZhihuMainFragment.OnBackToFirstListener {


    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout_zhihu_main)
    DrawerLayout drawer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhihu_main;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        navView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {/*初始化布局*/
            loadRootFragment(R.id.fl_container, ZhihuMainFragment.newInstance(0));
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) { /*选择导航项目*/
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_publish) {
            // Handle the camera action
        } else if (id == R.id.menu_tv) {
            /*开启 视频活动*/
            startActivity(TvMainActivity.class);
        } else if (id == R.id.setting) {

        } else if (id == R.id.about) {

        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onOpenDrawer() {/* 实现 打开 drawer 里面内容*/
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*ActionBarDrawerToggle 这个类提供了一个方便的方式联系在一起的功能 DrawerLayout和框架ActionBar来实现导航抽屉推荐的设计。*/
       /*extends Object implements DrawerLayout.DrawerListener*/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, (Toolbar) findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);//设置一个监听器被通知抽屉的事件。注意，此方法已过时，并且你应该使用addDrawerListener(DrawerListener)添加一个监听器，并 removeDrawerListener(DrawerListener)删除已注册的监听器。
        toggle.syncState();//与同步链接DrawerLayout抽屉指示器/启示的状态。
    }


    @Override
    public void onBackPressedSupport() {
       /*如果 侧滑打开则关闭，否则不操作 调用 原有backpress*/
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressedSupport();
        }

    }

    @Override
    public void onBackToFirstFragment() {
        /*再次加重 frag*/
        loadRootFragment(R.id.fl_container, ZhihuMainFragment.newInstance(1));
    }
}
