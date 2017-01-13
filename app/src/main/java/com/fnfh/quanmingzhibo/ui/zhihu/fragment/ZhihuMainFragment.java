package com.fnfh.quanmingzhibo.ui.zhihu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fnfh.houyonglianglib.base.CoreBaseActivity;
import com.fnfh.houyonglianglib.base.CoreBaseFragment;
import com.fnfh.houyonglianglib.utils.SpUtil;
import com.fnfh.houyonglianglib.utils.helper.FragmentAdapter;
import com.fnfh.quanmingzhibo.Constants;
import com.fnfh.quanmingzhibo.R;
import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;
import com.fnfh.quanmingzhibo.ui.zhihu.fragment.child.DailyFragment;
import com.fnfh.quanmingzhibo.ui.zhihu.fragment.child.SectionFragment;
import com.fnfh.quanmingzhibo.ui.zhihu.fragment.child.WechatFragment;
import com.fnfh.quanmingzhibo.ui.zhihu.model.ZhihuMainModel;
import com.fnfh.quanmingzhibo.ui.zhihu.presenter.ZhihuMainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



public class ZhihuMainFragment extends CoreBaseFragment<ZhihuMainPresenter, ZhihuMainModel> implements Toolbar.OnMenuItemClickListener, ZhihuContract.ZhihuMainView {

    private OnFragmentOpenDrawerListener mOpenDraweListener;
    /*创建集合 来处理 fragment*/
    List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.fab)
    FloatingActionButton fab;




    @Override
    public int getLayoutId() {/*获取布局内容*/
        return R.layout.fragment_zhihu_main;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {/*初始化UI*/
        /*toolbar 的设置*/
        toolbar.setTitle("首页");
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(v -> {/* 设置 图标点击事件*/
            if (mOpenDraweListener != null) {   /*调用 frag 打开抽屉视图 接口*/
                mOpenDraweListener.onOpenDrawer();
            }
        });
        toolbar.inflateMenu(R.menu.fragment_main_menu);/*toolbar 设置 菜单，并设置菜单条目的点击事件*/
        toolbar.setOnMenuItemClickListener(this);
        /* 悬浮框的点击事件  采用 Snackbar 吐司*/
//        fab.setOnClickListener(v -> Snackbar.make(v, "Snackbar 吐司", Snackbar.LENGTH_SHORT).setAction("action", vi -> {
//            showToast("ZhihuMainFragment");
        fab.setOnClickListener(v -> Snackbar.make(v, "Snackbar 吐司", Snackbar.LENGTH_SHORT).show() );

    }


    public static ZhihuMainFragment newInstance(int position) {/* 对外提供本身的实例，可用于传值到本frag*/
        ZhihuMainFragment fragment = new ZhihuMainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ARG_POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    /*toolbar 条目点击事件 设置 主题的点击事件*/
    /*主题改变 有问题？？*/
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.day:/*白天*/
                SpUtil.setNightModel(mContext, false);
                ((CoreBaseActivity) getActivity()).reload();
                return true;

            case R.id.night:/*晚上*/

                SpUtil.setNightModel(mContext, true);
                ((CoreBaseActivity) getActivity()).reload();
                return true;

        }

        return false;
    }



    public interface OnFragmentOpenDrawerListener {/*定义接口，对外回调*/

        void onOpenDrawer();
    }

    @Override
    public void onAttach(Context context) {/* activty 向上转型 为 接口，，传值过去，接口赋值*/
        super.onAttach(context);
        if (context instanceof OnFragmentOpenDrawerListener) {
            mOpenDraweListener = (OnFragmentOpenDrawerListener) context;
        }
    }

    @Override
    public void onDetach() { /*关闭的时候 ，置空*/
        super.onDetach();
        mOpenDraweListener = null;
    }

    /*实现 tablayout 的方法*/
    @Override
    public void showTabList(String[] mTabs) {
        for (int i = 0; i < mTabs.length; i++) {
            tabs.addTab(tabs.newTab().setText(mTabs[i]));
            switch (i) {
                case 0:
                    fragments.add(new DailyFragment());
                    break;
                case 1:
                    fragments.add(new SectionFragment());
//                    fragments.add(new SectionFragment());
                    break;
                case 2:
                    fragments.add(new WechatFragment());
//                    fragments.add(new WechatFragment());
                    break;
                default:
                    fragments.add(new DailyFragment());
//                    fragments.add(new QuickFragment());
                    break;
            }
        }

        int position = getArguments().getInt(Constants.ARG_POSITION, 1);
        /*设置适配器*/
        viewpager.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragments));
        viewpager.setCurrentItem(position);//要设置到viewpager.setAdapter后才起作用
        tabs.setupWithViewPager(viewpager);/*和ViewPager的联动*/
      tabs.setVerticalScrollbarPosition(position);/*设置垂直滚动条位置*/
        for (int i = 0; i < mTabs.length; i++) {
            tabs.getTabAt(i).setText(mTabs[i]);/*默认选中某项*/
        }

    }

    @Override
    public void showError(String msg) {

    }

}
