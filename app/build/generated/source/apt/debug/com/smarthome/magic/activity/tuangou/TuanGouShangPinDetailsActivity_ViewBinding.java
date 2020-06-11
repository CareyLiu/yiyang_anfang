// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.tuangou;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatRatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundTextView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TuanGouShangPinDetailsActivity_ViewBinding implements Unbinder {
  private TuanGouShangPinDetailsActivity target;

  @UiThread
  public TuanGouShangPinDetailsActivity_ViewBinding(TuanGouShangPinDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TuanGouShangPinDetailsActivity_ViewBinding(TuanGouShangPinDetailsActivity target,
      View source) {
    this.target = target;

    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvMeishiming = Utils.findRequiredViewAsType(source, R.id.tv_meishiming, "field 'tvMeishiming'", TextView.class);
    target.tvJieshao = Utils.findRequiredViewAsType(source, R.id.tv_jieshao, "field 'tvJieshao'", TextView.class);
    target.tvXiaoliang = Utils.findRequiredViewAsType(source, R.id.tv_xiaoliang, "field 'tvXiaoliang'", TextView.class);
    target.ivImage = Utils.findRequiredViewAsType(source, R.id.iv_image, "field 'ivImage'", ImageView.class);
    target.tvMeishiming1 = Utils.findRequiredViewAsType(source, R.id.tv_meishiming1, "field 'tvMeishiming1'", TextView.class);
    target.llTaocanDetails = Utils.findRequiredViewAsType(source, R.id.ll_taocan_details, "field 'llTaocanDetails'", LinearLayout.class);
    target.tvWenxinTishi = Utils.findRequiredViewAsType(source, R.id.tv_wenxin_tishi, "field 'tvWenxinTishi'", TextView.class);
    target.llWenxin = Utils.findRequiredViewAsType(source, R.id.ll_wenxin, "field 'llWenxin'", LinearLayout.class);
    target.tvDianjiaJiashao = Utils.findRequiredViewAsType(source, R.id.tv_dianjia_jiashao, "field 'tvDianjiaJiashao'", TextView.class);
    target.ivShopImage = Utils.findRequiredViewAsType(source, R.id.iv_shop_image, "field 'ivShopImage'", ImageView.class);
    target.tvDianjia = Utils.findRequiredViewAsType(source, R.id.tv_dianjia, "field 'tvDianjia'", TextView.class);
    target.star = Utils.findRequiredViewAsType(source, R.id.star, "field 'star'", AppCompatRatingBar.class);
    target.tvShopAddr = Utils.findRequiredViewAsType(source, R.id.tv_shop_addr, "field 'tvShopAddr'", TextView.class);
    target.tvYonghuPingjia = Utils.findRequiredViewAsType(source, R.id.tv_yonghu_pingjia, "field 'tvYonghuPingjia'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.tvClickMore = Utils.findRequiredViewAsType(source, R.id.tv_click_more, "field 'tvClickMore'", TextView.class);
    target.tvZuigaoMoney = Utils.findRequiredViewAsType(source, R.id.tv_zuigao_money, "field 'tvZuigaoMoney'", TextView.class);
    target.rtvLijiQianggou = Utils.findRequiredViewAsType(source, R.id.rtv_liji_qianggou, "field 'rtvLijiQianggou'", RoundTextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TuanGouShangPinDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvName = null;
    target.tvMeishiming = null;
    target.tvJieshao = null;
    target.tvXiaoliang = null;
    target.ivImage = null;
    target.tvMeishiming1 = null;
    target.llTaocanDetails = null;
    target.tvWenxinTishi = null;
    target.llWenxin = null;
    target.tvDianjiaJiashao = null;
    target.ivShopImage = null;
    target.tvDianjia = null;
    target.star = null;
    target.tvShopAddr = null;
    target.tvYonghuPingjia = null;
    target.tvMoney = null;
    target.tvClickMore = null;
    target.tvZuigaoMoney = null;
    target.rtvLijiQianggou = null;
  }
}
