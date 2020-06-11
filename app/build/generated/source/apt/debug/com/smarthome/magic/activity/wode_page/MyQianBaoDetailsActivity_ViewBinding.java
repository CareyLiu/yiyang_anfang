// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.wode_page;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyQianBaoDetailsActivity_ViewBinding implements Unbinder {
  private MyQianBaoDetailsActivity target;

  @UiThread
  public MyQianBaoDetailsActivity_ViewBinding(MyQianBaoDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyQianBaoDetailsActivity_ViewBinding(MyQianBaoDetailsActivity target, View source) {
    this.target = target;

    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.tvTixian = Utils.findRequiredViewAsType(source, R.id.tv_tixian, "field 'tvTixian'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvTixianDangqianzhuangtai = Utils.findRequiredViewAsType(source, R.id.tv_tixian_dangqianzhuangtai, "field 'tvTixianDangqianzhuangtai'", TextView.class);
    target.tvTixianTixianjine = Utils.findRequiredViewAsType(source, R.id.tv_tixian_tixianjine, "field 'tvTixianTixianjine'", TextView.class);
    target.tvTixianFuwufei = Utils.findRequiredViewAsType(source, R.id.tv_tixian_fuwufei, "field 'tvTixianFuwufei'", TextView.class);
    target.tvTixianShenqingshijian = Utils.findRequiredViewAsType(source, R.id.tv_tixian_shenqingshijian, "field 'tvTixianShenqingshijian'", TextView.class);
    target.tvTixianDaozhangshijian = Utils.findRequiredViewAsType(source, R.id.tv_tixian_daozhangshijian, "field 'tvTixianDaozhangshijian'", TextView.class);
    target.tvTixianTixianfangshi = Utils.findRequiredViewAsType(source, R.id.tv_tixian_tixianfangshi, "field 'tvTixianTixianfangshi'", TextView.class);
    target.tvTixianTixiandanhao = Utils.findRequiredViewAsType(source, R.id.tv_tixian_tixiandanhao, "field 'tvTixianTixiandanhao'", TextView.class);
    target.llTixian = Utils.findRequiredViewAsType(source, R.id.ll_tixian, "field 'llTixian'", LinearLayout.class);
    target.tvZaixianDangqianzhuangtai = Utils.findRequiredViewAsType(source, R.id.tv_zaixian_dangqianzhuangtai, "field 'tvZaixianDangqianzhuangtai'", TextView.class);
    target.tvZaixianYouhuiquan = Utils.findRequiredViewAsType(source, R.id.tv_zaixian_youhuiquan, "field 'tvZaixianYouhuiquan'", TextView.class);
    target.tvZaixianShangpin = Utils.findRequiredViewAsType(source, R.id.tv_zaixian_shangpin, "field 'tvZaixianShangpin'", TextView.class);
    target.tvZaixianShanghuquancheng = Utils.findRequiredViewAsType(source, R.id.tv_zaixian_shanghuquancheng, "field 'tvZaixianShanghuquancheng'", TextView.class);
    target.tvZaixianZhifu = Utils.findRequiredViewAsType(source, R.id.tv_zaixian_zhifu, "field 'tvZaixianZhifu'", TextView.class);
    target.tvZaixianJiaoyidanhao = Utils.findRequiredViewAsType(source, R.id.tv_zaixian_jiaoyidanhao, "field 'tvZaixianJiaoyidanhao'", TextView.class);
    target.llZaixianZhifu = Utils.findRequiredViewAsType(source, R.id.ll_zaixian_zhifu, "field 'llZaixianZhifu'", LinearLayout.class);
    target.tvFenrunDangqianzhuangtai = Utils.findRequiredViewAsType(source, R.id.tv_fenrun_dangqianzhuangtai, "field 'tvFenrunDangqianzhuangtai'", TextView.class);
    target.tvFenRunShouKuanFangShi = Utils.findRequiredViewAsType(source, R.id.tv_fenrun_shoukuanfangshi, "field 'tvFenRunShouKuanFangShi'", TextView.class);
    target.tvZaixianShoukuanshijian = Utils.findRequiredViewAsType(source, R.id.tv_zaixian_shoukuanshijian, "field 'tvZaixianShoukuanshijian'", TextView.class);
    target.llFenrun = Utils.findRequiredViewAsType(source, R.id.ll_fenrun, "field 'llFenrun'", LinearLayout.class);
    target.tvTuikuanDangqianzhuangtai = Utils.findRequiredViewAsType(source, R.id.tv_tuikuan_dangqianzhuangtai, "field 'tvTuikuanDangqianzhuangtai'", TextView.class);
    target.tvTuikuanShangpin = Utils.findRequiredViewAsType(source, R.id.tv_tuikuan_shangpin, "field 'tvTuikuanShangpin'", TextView.class);
    target.tvTuikuanZhifushijian = Utils.findRequiredViewAsType(source, R.id.tv_tuikuan_zhifushijian, "field 'tvTuikuanZhifushijian'", TextView.class);
    target.tvTuikuanZhifufangshi = Utils.findRequiredViewAsType(source, R.id.tv_tuikuan_zhifufangshi, "field 'tvTuikuanZhifufangshi'", TextView.class);
    target.tvTuikuanTuikuandanhao = Utils.findRequiredViewAsType(source, R.id.tv_tuikuan_tuikuandanhao, "field 'tvTuikuanTuikuandanhao'", TextView.class);
    target.llTuikuan = Utils.findRequiredViewAsType(source, R.id.ll_tuikuan, "field 'llTuikuan'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyQianBaoDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivImg = null;
    target.tvTixian = null;
    target.tvPrice = null;
    target.tvTixianDangqianzhuangtai = null;
    target.tvTixianTixianjine = null;
    target.tvTixianFuwufei = null;
    target.tvTixianShenqingshijian = null;
    target.tvTixianDaozhangshijian = null;
    target.tvTixianTixianfangshi = null;
    target.tvTixianTixiandanhao = null;
    target.llTixian = null;
    target.tvZaixianDangqianzhuangtai = null;
    target.tvZaixianYouhuiquan = null;
    target.tvZaixianShangpin = null;
    target.tvZaixianShanghuquancheng = null;
    target.tvZaixianZhifu = null;
    target.tvZaixianJiaoyidanhao = null;
    target.llZaixianZhifu = null;
    target.tvFenrunDangqianzhuangtai = null;
    target.tvFenRunShouKuanFangShi = null;
    target.tvZaixianShoukuanshijian = null;
    target.llFenrun = null;
    target.tvTuikuanDangqianzhuangtai = null;
    target.tvTuikuanShangpin = null;
    target.tvTuikuanZhifushijian = null;
    target.tvTuikuanZhifufangshi = null;
    target.tvTuikuanTuikuandanhao = null;
    target.llTuikuan = null;
  }
}
