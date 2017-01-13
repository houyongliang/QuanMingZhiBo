package com.fnfh.quanmingzhibo.ui.zhihu.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.Toolbar;


import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fnfh.houyonglianglib.base.CoreBaseActivity;
import com.fnfh.houyonglianglib.utils.HtmlUtil;
import com.fnfh.houyonglianglib.utils.NetUtils;
import com.fnfh.houyonglianglib.utils.SnackbarUtil;
import com.fnfh.houyonglianglib.utils.SpUtil;


import com.fnfh.quanmingzhibo.Constants;
import com.fnfh.quanmingzhibo.R;

import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;
import com.fnfh.quanmingzhibo.ui.zhihu.model.dailymodel.DailyModel;
import com.fnfh.quanmingzhibo.ui.zhihu.model.dailymodel.ZhihuDetailBean;
import com.fnfh.quanmingzhibo.ui.zhihu.presenter.dailypresenter.ZhihuDetailsPresenter;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;



public class ZhihuDetailsActivity extends CoreBaseActivity <ZhihuDetailsPresenter,DailyModel>implements ZhihuContract.ZhihuDetailsView{
    private static final String TRANSLATE_VIEW = "translate_view";
    private Typeface type;
    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.wv_detail_content)
    WebView wvDetailContent;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    boolean isTransitionEnd = false;
    boolean isImageShow = false;
    String imgUrl;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_daily_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        type= Typeface.createFromAsset(this.getAssets(),"font/ssssss.ttf");
        setToolBar(toolbar,"");
        /*---------------------web 处理*/
        WebSettings settings=wvDetailContent.getSettings();
        if (SpUtil.getNoImageState()) {
            settings.setBlockNetworkImage(true);
        }
        if (SpUtil.getAutoCacheState()) {
            settings.setAppCacheEnabled(true);/*缓存模式 --无模式选择，通过setAppCacheEnabled(boolean flag)设置是否打开。默认关闭，即，H5的缓存无法使用。*/
            settings.setDomStorageEnabled(true);/*key/value对即可解决的数据*/
            settings.setDatabaseEnabled(true);/*设置数据库已启用*/
            if (NetUtils.isConnected(mContext)) {
                settings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            }

        }
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);/*设置布局算法*/
        settings.setSupportZoom(true);

        wvDetailContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
/*--------------------其他控件处理*/
        nsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });
        /*----------------------------mvp 处理*/
        mPresenter.getZhihuDetails(getIntent().getIntExtra(Constants.ARG_DAILY_ID, -1));
        (getWindow().getSharedElementEnterTransition()).addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
               /* 测试发现部分手机(如红米note2)上加载图片会变形,没有达到centerCrop效果
                        * 查阅资料发现Glide配合SharedElementTransition是有坑的,需要在Transition动画结束后再加载图片
                        * https://github.com/TWiStErRob/glide-support/blob/master/src/glide3/java/com/bumptech/glide/supportapp/github/_847_shared_transition/DetailFragment.java
                */
                isTransitionEnd = true;
                if (imgUrl != null) {
                    isImageShow = true;
                    Glide.with(mContext).load(imgUrl).crossFade().into(detailBarImage);
                }
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhihuDetailsActivity.this.finish();
            }
        });
    }




    @Override
    public void showContent(ZhihuDetailBean info) {
        imgUrl = info.getImage();
        if (!isImageShow && isTransitionEnd) {
            Glide.with(mContext).load(info.getImage()).crossFade().into(detailBarImage);
        }
        collapsingToolbar.setTitle(info.getTitle());
        detailBarCopyright.setTypeface( type);
        detailBarCopyright.setText(info.getImage_source());
        String htmlData = HtmlUtil.createHtmlData(info.getBody(), info.getCss(), info.getJs());
        wvDetailContent.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String msg) {
        SnackbarUtil.showShort(getWindow().getDecorView(), msg);
    }

    public static void start(Context context, View view, int id) {
        Intent starter = new Intent(context, ZhihuDetailsActivity.class);
        starter.putExtra(Constants.ARG_DAILY_ID, id);
        ActivityCompat.startActivity((Activity) context, starter, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, view, TRANSLATE_VIEW).toBundle());
    }

    public static void start(Context context, int id) {
        Intent starter = new Intent(context, ZhihuDetailsActivity.class);
        starter.putExtra(Constants.ARG_DAILY_ID, id);
        ActivityCompat.startActivity((Activity) context, starter,null);
    }


}
