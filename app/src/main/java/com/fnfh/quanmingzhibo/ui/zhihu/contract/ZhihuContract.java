package com.fnfh.quanmingzhibo.ui.zhihu.contract;

        import com.fnfh.houyonglianglib.base.CoreBaseModel;
        import com.fnfh.houyonglianglib.base.CoreBasePresenter;
        import com.fnfh.houyonglianglib.base.CoreBaseView;
        import com.fnfh.quanmingzhibo.ui.zhihu.model.dailymodel.DailyListBean;
        import com.fnfh.quanmingzhibo.ui.zhihu.model.dailymodel.ZhihuDetailBean;
        import com.fnfh.quanmingzhibo.ui.zhihu.model.sectionmodel.SectionChildListBean;
        import com.fnfh.quanmingzhibo.ui.zhihu.model.sectionmodel.SectionListBean;
        import com.fnfh.quanmingzhibo.ui.zhihu.model.sectionmodel.SectionModel;
        import com.fnfh.quanmingzhibo.ui.zhihu.model.wechatmodel.WXItemBean;

        import java.util.List;

        import rx.Observable;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2017/1/6.
 */

public interface ZhihuContract {
    //主页接口
    abstract class ZhihuMainPresenter extends CoreBasePresenter<ZhihuMainModel, ZhihuMainView> {
        public abstract void getTabList();
    }

    interface ZhihuMainModel extends CoreBaseModel {
        String[] getTabs();
    }

    interface ZhihuMainView extends CoreBaseView {
        void showTabList(String[] mTabs);
    }

    //daily所有接口(model写在了一起,view presenter分开写)
    abstract class DailyPresenter extends CoreBasePresenter<DailyModel, DailyView> {
        public abstract void getDailyData();

        public abstract void startInterval();
    }

    interface DailyModel extends CoreBaseModel {
        Observable<DailyListBean> getDailyData();

        Observable<ZhihuDetailBean> getZhihuDetails(int anInt);
    }

    interface DailyView extends CoreBaseView {
        /*展示内容*/
        void showContent(DailyListBean info);
 /*做 间隔*/
        void doInterval(int i);
    }

    abstract class ZhihuDetailsPresenter extends CoreBasePresenter<DailyModel, ZhihuDetailsView> {
        public abstract void getZhihuDetails(int anInt);
    }

    interface ZhihuDetailsView extends CoreBaseView {
        void showContent(ZhihuDetailBean info);
    }

    interface SectionView extends CoreBaseView {
        void showContent(SectionListBean info);

    }
 interface SectionModel extends CoreBaseModel {

        Observable<SectionListBean> getSectionData();

        Observable<SectionChildListBean> getSectionListData(int id);
    }
     //section所有接口
   abstract class SectionPresenter extends CoreBasePresenter<SectionModel, SectionView> {

        public abstract void getSectionData();
    }





    abstract class SectionListPresenter extends CoreBasePresenter<SectionModel, SectionListView> {

        public abstract void getSectionListData(int id);
    }

    interface SectionListView extends CoreBaseView {
        void showContent(SectionChildListBean info);
    }

    abstract class WechatPresenter extends CoreBasePresenter<WechatModel, WechatView> {
        public abstract void getWechatData(int num, int page);
    }

    interface WechatModel extends CoreBaseModel {
        Observable<List<WXItemBean>> getWechatData(int num, int page);
    }

    interface WechatView extends CoreBaseView {
        void showContent(List<WXItemBean> mList);
    }
}
