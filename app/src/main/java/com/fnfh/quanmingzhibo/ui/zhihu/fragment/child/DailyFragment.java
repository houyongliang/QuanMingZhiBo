package com.fnfh.quanmingzhibo.ui.zhihu.fragment.child;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.CBPageAdapter;
import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;


import com.fnfh.houyonglianglib.base.CoreBaseFragment;
import com.fnfh.quanmingzhibo.R;
import com.fnfh.quanmingzhibo.ui.zhihu.activity.ZhihuDetailsActivity;
import com.fnfh.quanmingzhibo.ui.zhihu.contract.ZhihuContract;
import com.fnfh.quanmingzhibo.ui.zhihu.model.dailymodel.DailyListBean;
import com.fnfh.quanmingzhibo.ui.zhihu.model.dailymodel.DailyModel;
import com.fnfh.quanmingzhibo.ui.zhihu.presenter.dailypresenter.DailyPresenter;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.BaseQuickAdapter;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.BaseViewHolder;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.CoreRecyclerView;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.listener.OnItemClickListener;
import com.fnfh.quanmingzhibo.wigdet.recyclerview.recyclerviewpager.LoopRecyclerViewPager;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

import static android.R.id.list;


/**
 * 构建 P 层对象和 M 层对象，实现 view  层的接口 ZhihuContract.DailyView
 *
 * doInterval vp 页面停留操作
 * showContent 展示内容操作
 *
 */
public class DailyFragment extends CoreBaseFragment<DailyPresenter, DailyModel> implements ZhihuContract.DailyView {
    CoreRecyclerView coreRecyclerView;
    LoopRecyclerViewPager vpTop;
//    ConvenientBanner vpTop;
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public View getLayoutView() {
        /* CoreRecyclerView 实现 刷新效果 的 reclclerview  ,初始化的时候 封装 了 adptger */

        /* <T, K extends BaseViewHolder> extends RecyclerView.Adapter<K> 其中T 为返回的数据类型  K 为 viewholder */
        coreRecyclerView = new CoreRecyclerView(mContext).init(new BaseQuickAdapter<DailyListBean.StoriesBean, BaseViewHolder>(R.layout.item_daily) {
            @Override
            protected void convert(BaseViewHolder helper, DailyListBean.StoriesBean item) {
                helper.setText(R.id.tv_daily_item_title, item.getTitle());
                Glide.with(mContext).load(item.getImages().get(0)).crossFade().placeholder(R.mipmap.def_head).into((ImageView) helper.getView(R.id.iv_daily_item_image));
            }
        })
               .addOnItemClickListener(new OnItemClickListener() {
                   @Override
                   public void SimpleOnItemClick(com.fnfh.quanmingzhibo.wigdet.recyclerview.BaseQuickAdapter adapter, View view, int position) {
                       showToast("点击了" + position+"条目");
                       ZhihuDetailsActivity.start(mActivity, view.findViewById(R.id.iv_daily_item_image), ((DailyListBean.StoriesBean) adapter.getData().get(position)).getId());

//                       ((SupportFragment) getParentFragment()).start(DailyDetailsFragment.newInstance(((DailyListBean.StoriesBean) adapter.getData().get(position)).getId()));
//               ZhihuDetailsActivity.start(mActivity, view.findViewById(R.id.iv_daily_item_image), ((DailyListBean.StoriesBean) adapter.getData().get(position)).getId());
//
                   }


        });
        return coreRecyclerView;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        View view1 = LayoutInflater.from(mContext).inflate(R.layout.daily_header, (ViewGroup) coreRecyclerView.getParent(), false);
        vpTop = (LoopRecyclerViewPager) view1.findViewById(R.id.vp_top);

        vpTop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        vpTop = (ConvenientBanner) view1.findViewById(R.id.vp_top);


        coreRecyclerView.addHeaderView(view1);
    }

    @Override
    public void initData() {
        mPresenter.getDailyData();
        mPresenter.startInterval();
    }

    @Override
    public void showContent(DailyListBean info) {
//        List<DailyListBean.TopStoriesBean> top_stories = info.getTop_stories();
//        initConvenientBanner(vpTop,top_stories);
//        vpTop.setOnItemClickListener(new com.bigkoo.convenientbanner.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Toast.makeText(mContext, "。。。。点击了", Toast.LENGTH_SHORT).show();
//                  ZhihuDetailsActivity.start(mActivity,vpTop.findViewById(R.id.iv_top_image) , top_stories.get(position).getId());
//            }
//        });
        vpTop.setAdapter(new BaseQuickAdapter<DailyListBean.TopStoriesBean, BaseViewHolder>(R.layout.item_daily_header, info.getTop_stories()) {
            @Override
            protected void convert(BaseViewHolder helper, DailyListBean.TopStoriesBean item) {

                helper.setText(R.id.tv_top_title, item.getTitle());
                Glide.with(mContext).load(item.getImage()).crossFade().placeholder(R.drawable.ic_default_cover).into((ImageView) helper.getView(R.id.iv_top_image));
                helper.setOnClickListener(R.id.iv_top_image, v -> {
                    Toast.makeText(mContext, "。。。。点击了", Toast.LENGTH_SHORT).show();
                  ZhihuDetailsActivity.start(mActivity, v.findViewById(R.id.iv_top_image), item.getId());
//                    ((SupportFragment) getParentFragment()).start(DailyDetailsFragment.newInstance(item.getId()));
                });
            }
        });



        coreRecyclerView.getAdapter().addData(info.getStories());
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void doInterval(int i) {
        /*平滑滚动到位置*/
        vpTop.smoothScrollToPosition(i);
    }

    private void initConvenientBanner(ConvenientBanner cb,List<DailyListBean.TopStoriesBean> list){
        cb.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new CBPageAdapter.Holder<DailyListBean.TopStoriesBean>() {
                    ImageView ivg;
                    TextView tv;
                    @Override
                    public View createView(Context context) {
                        View v=View.inflate(getActivity(),R.layout.item_daily_header,null);
                         ivg= (ImageView) v.findViewById(R.id.iv_top_image);
                         tv= (TextView) v.findViewById(R.id.tv_top_title);
                        return v;
                    }

                    @Override
                    public void UpdateUI(Context context, int position, DailyListBean.TopStoriesBean data) {
                        Glide.with(context).load(data.getImage()).crossFade().placeholder(R.drawable.ic_default_cover).into((ivg) );
                        tv.setText(data.getTitle());
                    }
                };
            }
        }, list) //mList是图片地址的集合
                .setPointViewVisible(true)    //设置指示器是否可见
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设

                //设置指示器位置（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(2000) ;    //设置自动切换（同时设置了切换时间间隔）
//                .setManualPageable(true);  //设置手动影响（设置了该项无法手动切换）

    }


}
