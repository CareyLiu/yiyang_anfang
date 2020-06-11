// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.dingdan;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderTuiKuanDetailsActivity_ViewBinding implements Unbinder {
  private OrderTuiKuanDetailsActivity target;

  @UiThread
  public OrderTuiKuanDetailsActivity_ViewBinding(OrderTuiKuanDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderTuiKuanDetailsActivity_ViewBinding(OrderTuiKuanDetailsActivity target, View source) {
    this.target = target;

    target.tvTuihuodanhao = Utils.findRequiredViewAsType(source, R.id.tv_tuihuodanhao, "field 'tvTuihuodanhao'", TextView.class);
    target.tvDingdanhao = Utils.findRequiredViewAsType(source, R.id.tv_dingdanhao, "field 'tvDingdanhao'", TextView.class);
    target.ll1 = Utils.findRequiredViewAsType(source, R.id.ll_1, "field 'll1'", LinearLayout.class);
    target.view1 = Utils.findRequiredView(source, R.id.view_1, "field 'view1'");
    target.llJindutiao = Utils.findRequiredViewAsType(source, R.id.ll_jindutiao, "field 'llJindutiao'", LinearLayout.class);
    target.rl1 = Utils.findRequiredViewAsType(source, R.id.rl_1, "field 'rl1'", RelativeLayout.class);
    target.view2 = Utils.findRequiredView(source, R.id.view_2, "field 'view2'");
    target.tvShouhuoren = Utils.findRequiredViewAsType(source, R.id.tv_shouhuoren, "field 'tvShouhuoren'", TextView.class);
    target.tvShouhuodizhi = Utils.findRequiredViewAsType(source, R.id.tv_shouhuodizhi, "field 'tvShouhuodizhi'", TextView.class);
    target.cl2 = Utils.findRequiredViewAsType(source, R.id.cl_2, "field 'cl2'", ConstraintLayout.class);
    target.view3 = Utils.findRequiredView(source, R.id.view_3, "field 'view3'");
    target.tvWuliudanhao = Utils.findRequiredViewAsType(source, R.id.tv_wuliudanhao, "field 'tvWuliudanhao'", TextView.class);
    target.etText = Utils.findRequiredViewAsType(source, R.id.et_text, "field 'etText'", EditText.class);
    target.tvTijioao = Utils.findRequiredViewAsType(source, R.id.tv_tijioao, "field 'tvTijioao'", TextView.class);
    target.cl3 = Utils.findRequiredViewAsType(source, R.id.cl_3, "field 'cl3'", ConstraintLayout.class);
    target.ivProduct = Utils.findRequiredViewAsType(source, R.id.iv_product, "field 'ivProduct'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvXinghao = Utils.findRequiredViewAsType(source, R.id.tv_xinghao, "field 'tvXinghao'", TextView.class);
    target.tvDanjia = Utils.findRequiredViewAsType(source, R.id.tv_danjia, "field 'tvDanjia'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.tvPay = Utils.findRequiredViewAsType(source, R.id.tv_pay, "field 'tvPay'", TextView.class);
    target.tvHaisheng = Utils.findRequiredViewAsType(source, R.id.tv_haisheng, "field 'tvHaisheng'", TextView.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
    target.tvWuliudanhaoMaijia = Utils.findRequiredViewAsType(source, R.id.tv_wuliudanhao_maijia, "field 'tvWuliudanhaoMaijia'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderTuiKuanDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTuihuodanhao = null;
    target.tvDingdanhao = null;
    target.ll1 = null;
    target.view1 = null;
    target.llJindutiao = null;
    target.rl1 = null;
    target.view2 = null;
    target.tvShouhuoren = null;
    target.tvShouhuodizhi = null;
    target.cl2 = null;
    target.view3 = null;
    target.tvWuliudanhao = null;
    target.etText = null;
    target.tvTijioao = null;
    target.cl3 = null;
    target.ivProduct = null;
    target.tvTitle = null;
    target.tvXinghao = null;
    target.tvDanjia = null;
    target.tvNumber = null;
    target.tvPay = null;
    target.tvHaisheng = null;
    target.viewLine = null;
    target.tvWuliudanhaoMaijia = null;
  }
}
