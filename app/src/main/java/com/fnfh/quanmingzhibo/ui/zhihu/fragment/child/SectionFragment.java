package com.fnfh.quanmingzhibo.ui.zhihu.fragment.child;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fnfh.houyonglianglib.base.CoreBaseFragment;
import com.fnfh.houyonglianglib.utils.DisplayUtils;
import com.fnfh.quanmingzhibo.App;
import com.fnfh.quanmingzhibo.R;
import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;
import com.fnfh.quanmingzhibo.ui.zhihu.fragment.SectionListFragment;
import com.fnfh.quanmingzhibo.ui.zhihu.model.sectionmodel.SectionListBean;
import com.fnfh.quanmingzhibo.ui.zhihu.model.sectionmodel.SectionModel;
import com.fnfh.quanmingzhibo.ui.zhihu.presenter.sectionpresenter.SectionPresenter;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.BaseQuickAdapter;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.BaseViewHolder;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.CoreRecyclerView;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by hpw on 16/11/5.
 */

public class SectionFragment extends CoreBaseFragment <SectionPresenter, SectionModel> implements ZhihuContract.SectionView {

    CoreRecyclerView coreRecyclerView;
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public View getLayoutView() {
        coreRecyclerView = new CoreRecyclerView(mContext).init(new GridLayoutManager(mContext, 2),
                new BaseQuickAdapter<SectionListBean.DataBean, BaseViewHolder>(R.layout.item_section) {
                    @Override
                    protected void convert(BaseViewHolder helper, SectionListBean.DataBean item) {
                        //Glide在加载GridView等时,由于ImageView和Bitmap实际大小不符合,第一次时加载可能会变形(我这里出现了放大),必须在加载前再次固定ImageView大小
                        ViewGroup.LayoutParams lp = helper.getView(R.id.section_bg).getLayoutParams();
                        lp.width = (App.SCREEN_WIDTH - DisplayUtils.dp2px(mContext, 12)) / 2;
                        lp.height = DisplayUtils.dp2px(mContext, 120);

                        Glide.with(mContext).load(item.getThumbnail()).crossFade().placeholder(R.drawable.ic_default_cover).into((ImageView) helper.getView(R.id.section_bg));
                        helper.setText(R.id.section_kind, item.getName())
                                .setText(R.id.section_des, item.getDescription())
                                 .setOnClickListener(R.id.ll_click, v -> {
                                    ((SupportFragment) getParentFragment()).start(SectionListFragment.newInstance(item.getId(), item.getName()));
                                });
                    }
                });
        return coreRecyclerView;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }


    @Override
    public void showContent(SectionListBean info) {
        coreRecyclerView.getAdapter().addData(info.getData());
    }

    @Override
    public void showError(String msg) {
        Log.e(TAG, "showError: "+"加载失败。。。");
    }
}
