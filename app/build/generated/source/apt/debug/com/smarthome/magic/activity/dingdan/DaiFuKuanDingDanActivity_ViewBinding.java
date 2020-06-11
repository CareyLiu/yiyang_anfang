// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.dingdan;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DaiFuKuanDingDanActivity_ViewBinding implements Unbinder {
  private DaiFuKuanDingDanActivity target;

  @UiThread
  public DaiFuKuanDingDanActivity_ViewBinding(DaiFuKuanDingDanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DaiFuKuanDingDanActivity_ViewBinding(DaiFuKuanDingDanActivity target, View source) {
    this.target = target;

    target.tvDingdanZhuangtai = Utils.findRequiredViewAsType(source, R.id.tv_dingdan_zhuangtai, "field 'tvDingdanZhuangtai'", TextView.class);
    target.conlayout1 = Utils.findRequiredViewAsType(source, R.id.conlayout_1, "field 'conlayout1'", ConstraintLayout.class);
    target.ivAddress = Utils.findRequiredViewAsType(source, R.id.iv_address, "field 'ivAddress'", ImageView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.constrain2 = Utils.findRequiredViewAsType(source, R.id.constrain2, "field 'constrain2'", ConstraintLayout.class);
    target.view1 = Utils.findRequiredView(source, R.id.view_1, "field 'view1'");
    target.ivImage = Utils.findRequiredViewAsType(source, R.id.iv_image, "field 'ivImage'", ImageView.class);
    target.tvShop = Utils.findRequiredViewAsType(source, R.id.tv_shop, "field 'tvShop'", TextView.class);
    target.constrain3 = Utils.findRequiredViewAsType(source, R.id.constrain3, "field 'constrain3'", ConstraintLayout.class);
    target.ivProduct = Utils.findRequiredViewAsType(source, R.id.iv_product, "field 'ivProduct'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvKuanshi = Utils.findRequiredViewAsType(source, R.id.tv_kuanshi, "field 'tvKuanshi'", TextView.class);
    target.tvDanjia = Utils.findRequiredViewAsType(source, R.id.tv_danjia, "field 'tvDanjia'", TextView.class);
    target.tvPaycount = Utils.findRequiredViewAsType(source, R.id.tv_paycount, "field 'tvPaycount'", TextView.class);
    target.constrain4 = Utils.findRequiredViewAsType(source, R.id.constrain4, "field 'constrain4'", ConstraintLayout.class);
    target.tvShifujine = Utils.findRequiredViewAsType(source, R.id.tv_shifujine, "field 'tvShifujine'", TextView.class);
    target.constrain5 = Utils.findRequiredViewAsType(source, R.id.constrain5, "field 'constrain5'", ConstraintLayout.class);
    target.view2 = Utils.findRequiredView(source, R.id.view_2, "field 'view2'");
    target.llInfo = Utils.findRequiredViewAsType(source, R.id.ll_info, "field 'llInfo'", LinearLayout.class);
    target.tvGoPay = Utils.findRequiredViewAsType(source, R.id.tv_go_pay, "field 'tvGoPay'", TextView.class);
    target.tvQuxiaodingdan = Utils.findRequiredViewAsType(source, R.id.tv_quxiaodingdan, "field 'tvQuxiaodingdan'", TextView.class);
    target.clDaifukuan = Utils.findRequiredViewAsType(source, R.id.cl_daifukuan, "field 'clDaifukuan'", ConstraintLayout.class);
    target.tvDaifahuoShenqingtuikuan = Utils.findRequiredViewAsType(source, R.id.tv_daifahuo_shenqingtuikuan, "field 'tvDaifahuoShenqingtuikuan'", TextView.class);
    target.tvDaifahuoCuifahuo = Utils.findRequiredViewAsType(source, R.id.tv_daifahuo_cuifahuo, "field 'tvDaifahuoCuifahuo'", TextView.class);
    target.clDaifahuo = Utils.findRequiredViewAsType(source, R.id.cl_daifahuo, "field 'clDaifahuo'", ConstraintLayout.class);
    target.tvdaipingjia = Utils.findRequiredViewAsType(source, R.id.tvdaipingjia, "field 'tvdaipingjia'", TextView.class);
    target.tvShangchudingdan = Utils.findRequiredViewAsType(source, R.id.tv_shangchudingdan, "field 'tvShangchudingdan'", TextView.class);
    target.clDaiingjia = Utils.findRequiredViewAsType(source, R.id.cl_daiingjia, "field 'clDaiingjia'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DaiFuKuanDingDanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvDingdanZhuangtai = null;
    target.conlayout1 = null;
    target.ivAddress = null;
    target.tvAddress = null;
    target.constrain2 = null;
    target.view1 = null;
    target.ivImage = null;
    target.tvShop = null;
    target.constrain3 = null;
    target.ivProduct = null;
    target.tvTitle = null;
    target.tvKuanshi = null;
    target.tvDanjia = null;
    target.tvPaycount = null;
    target.constrain4 = null;
    target.tvShifujine = null;
    target.constrain5 = null;
    target.view2 = null;
    target.llInfo = null;
    target.tvGoPay = null;
    target.tvQuxiaodingdan = null;
    target.clDaifukuan = null;
    target.tvDaifahuoShenqingtuikuan = null;
    target.tvDaifahuoCuifahuo = null;
    target.clDaifahuo = null;
    target.tvdaipingjia = null;
    target.tvShangchudingdan = null;
    target.clDaiingjia = null;
  }
}
