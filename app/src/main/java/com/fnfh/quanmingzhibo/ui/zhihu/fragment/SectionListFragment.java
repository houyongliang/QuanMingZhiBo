package com.fnfh.quanmingzhibo.ui.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.fnfh.houyonglianglib.base.CoreBaseFragment;
import com.fnfh.quanmingzhibo.Constants;
import com.fnfh.quanmingzhibo.R;
import com.fnfh.quanmingzhibo.ui.zhihu.activity.ZhihuDetailsActivity;
import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;
import com.fnfh.quanmingzhibo.ui.zhihu.model.sectionmodel.SectionChildListBean;

import com.fnfh.quanmingzhibo.ui.zhihu.model.sectionmodel.SectionModel;
import com.fnfh.quanmingzhibo.ui.zhihu.presenter.sectionpresenter.SectionListPresenter;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.BaseQuickAdapter;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.BaseViewHolder;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.CoreRecyclerView;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.listener.OnItemClickListener;

import butterknife.BindView;

/**
 * Created by hpw on 16/11/5.
 */

public class SectionListFragment extends CoreBaseFragment<SectionListPresenter, SectionModel> implements ZhihuContract.SectionListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    CoreRecyclerView coreRecyclerView;

    public static SectionListFragment newInstance(int id, String title) {
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_LIST_ID, id);
        args.putString(Constants.ARG_SECTION_LIST_TITLE, title);
        SectionListFragment fragment = new SectionListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_section_list;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        coreRecyclerView.init(new BaseQuickAdapter<SectionChildListBean.StoriesBean, BaseViewHolder>(R.layout.item_daily) {
            @Override
            protected void convert(BaseViewHolder helper, SectionChildListBean.StoriesBean item) {
                helper.setText(R.id.tv_daily_item_title, item.getTitle());
                Glide.with(mContext).load(item.getImages().get(0)).crossFade().placeholder(R.mipmap.def_head).into((ImageView) helper.getView(R.id.iv_daily_item_image));
            }
        }).addOnItemClickListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZhihuDetailsActivity.start(mActivity, view.findViewById(R.id.iv_daily_item_image), ((SectionChildListBean.StoriesBean) adapter.getData().get(position)).getId());
            }
        });
    }

    @Override
    public void getBundle(Bundle bundle) {
      mPresenter.getSectionListData(bundle.getInt(Constants.ARG_SECTION_LIST_ID, -1));
        setToolBar(toolbar, bundle.getString(Constants.ARG_SECTION_LIST_TITLE));
    }

    @Override
    public void showContent(SectionChildListBean info) {
        coreRecyclerView.getAdapter().addData(info.getStories());
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onBackPressedSupport() {
        _mBackToFirstListener.onBackToFirstFragment();
        return true;
    }
}
