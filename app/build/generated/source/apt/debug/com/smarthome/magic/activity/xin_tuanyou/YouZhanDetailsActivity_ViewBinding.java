// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.xin_tuanyou;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.smarthome.magic.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class YouZhanDetailsActivity_ViewBinding implements Unbinder {
  private YouZhanDetailsActivity target;

  @UiThread
  public YouZhanDetailsActivity_ViewBinding(YouZhanDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public YouZhanDetailsActivity_ViewBinding(YouZhanDetailsActivity target, View source) {
    this.target = target;

    target.main = Utils.findRequiredViewAsType(source, R.id.main, "field 'main'", LinearLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.collapsingToolbar = Utils.findRequiredViewAsType(source, R.id.collapsing_toolbar, "field 'collapsingToolbar'", CollapsingToolbarLayout.class);
    target.appbar = Utils.findRequiredViewAsType(source, R.id.appbar, "field 'appbar'", AppBarLayout.class);
    target.tvYouzhanname = Utils.findRequiredViewAsType(source, R.id.tv_youzhanname, "field 'tvYouzhanname'", TextView.class);
    target.civYouzhanImg = Utils.findRequiredViewAsType(source, R.id.civ_youzhan_img, "field 'civYouzhanImg'", CircleImageView.class);
    target.tvShopName = Utils.findRequiredViewAsType(source, R.id.tv_shop_name, "field 'tvShopName'", TextView.class);
    target.ivAddress = Utils.findRequiredViewAsType(source, R.id.iv_address, "field 'ivAddress'", ImageView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.llJiang = Utils.findRequiredViewAsType(source, R.id.ll_jiang, "field 'llJiang'", RelativeLayout.class);
    target.ivDaohang = Utils.findRequiredViewAsType(source, R.id.iv_daohang, "field 'ivDaohang'", ImageView.class);
    target.constrain = Utils.findRequiredViewAsType(source, R.id.constrain, "field 'constrain'", ConstraintLayout.class);
    target.allProduct = Utils.findRequiredViewAsType(source, R.id.all_product, "field 'allProduct'", TextView.class);
    target.rlvAllPro = Utils.findRequiredViewAsType(source, R.id.rlv_all_pro, "field 'rlvAllPro'", RecyclerView.class);
    target.viewWhite = Utils.findRequiredView(source, R.id.view_white, "field 'viewWhite'");
    target.tvChooseYouhao = Utils.findRequiredViewAsType(source, R.id.tv_choose_youhao, "field 'tvChooseYouhao'", TextView.class);
    target.rlvYouhaoList = Utils.findRequiredViewAsType(source, R.id.rlv_youhao_list, "field 'rlvYouhaoList'", RecyclerView.class);
    target.viewWhite1 = Utils.findRequiredView(source, R.id.view_white_1, "field 'viewWhite1'");
    target.tvQianghao = Utils.findRequiredViewAsType(source, R.id.tv_qianghao, "field 'tvQianghao'", TextView.class);
    target.rlvQianghaoList = Utils.findRequiredViewAsType(source, R.id.rlv_qianghao_list, "field 'rlvQianghaoList'", RecyclerView.class);
    target.viewWhite2 = Utils.findRequiredView(source, R.id.view_white_2, "field 'viewWhite2'");
    target.tvJiayouJineHuashu = Utils.findRequiredViewAsType(source, R.id.tv_jiayou_jine_huashu, "field 'tvJiayouJineHuashu'", TextView.class);
    target.etText = Utils.findRequiredViewAsType(source, R.id.et_text, "field 'etText'", EditText.class);
    target.rlvJiayouJine = Utils.findRequiredViewAsType(source, R.id.rlv_jiayou_jine, "field 'rlvJiayouJine'", RecyclerView.class);
    target.rlvNext = Utils.findRequiredViewAsType(source, R.id.rlv_next, "field 'rlvNext'", RoundRelativeLayout.class);
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.rlvYouzhanInfo = Utils.findRequiredViewAsType(source, R.id.rlv_youzhan_info, "field 'rlvYouzhanInfo'", CardView.class);
    target.tvBiguobiao = Utils.findRequiredViewAsType(source, R.id.tv_biguobiao, "field 'tvBiguobiao'", TextView.class);
    target.tvBiyouzhan = Utils.findRequiredViewAsType(source, R.id.tv_biyouzhan, "field 'tvBiyouzhan'", TextView.class);
    target.tvKm = Utils.findRequiredViewAsType(source, R.id.tv_km, "field 'tvKm'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    YouZhanDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.main = null;
    target.toolbar = null;
    target.collapsingToolbar = null;
    target.appbar = null;
    target.tvYouzhanname = null;
    target.civYouzhanImg = null;
    target.tvShopName = null;
    target.ivAddress = null;
    target.tvAddress = null;
    target.tvPrice = null;
    target.llJiang = null;
    target.ivDaohang = null;
    target.constrain = null;
    target.allProduct = null;
    target.rlvAllPro = null;
    target.viewWhite = null;
    target.tvChooseYouhao = null;
    target.rlvYouhaoList = null;
    target.viewWhite1 = null;
    target.tvQianghao = null;
    target.rlvQianghaoList = null;
    target.viewWhite2 = null;
    target.tvJiayouJineHuashu = null;
    target.etText = null;
    target.rlvJiayouJine = null;
    target.rlvNext = null;
    target.ivBack = null;
    target.rlvYouzhanInfo = null;
    target.tvBiguobiao = null;
    target.tvBiyouzhan = null;
    target.tvKm = null;
  }
}
