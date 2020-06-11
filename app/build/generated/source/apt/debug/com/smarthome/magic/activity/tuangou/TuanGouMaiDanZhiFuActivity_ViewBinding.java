// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.tuangou;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundTextView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TuanGouMaiDanZhiFuActivity_ViewBinding implements Unbinder {
  private TuanGouMaiDanZhiFuActivity target;

  @UiThread
  public TuanGouMaiDanZhiFuActivity_ViewBinding(TuanGouMaiDanZhiFuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TuanGouMaiDanZhiFuActivity_ViewBinding(TuanGouMaiDanZhiFuActivity target, View source) {
    this.target = target;

    target.viewWeixin = Utils.findRequiredView(source, R.id.view_weixin, "field 'viewWeixin'");
    target.viewZhifubao = Utils.findRequiredView(source, R.id.view_zhifubao, "field 'viewZhifubao'");
    target.tvChooseZhifufangshi = Utils.findRequiredViewAsType(source, R.id.tv_choose_zhifufangshi, "field 'tvChooseZhifufangshi'", TextView.class);
    target.ivIcon1 = Utils.findRequiredViewAsType(source, R.id.iv_icon_1, "field 'ivIcon1'", ImageView.class);
    target.ivWeixinChoose = Utils.findRequiredViewAsType(source, R.id.iv_weixin_choose, "field 'ivWeixinChoose'", ImageView.class);
    target.view = Utils.findRequiredView(source, R.id.view, "field 'view'");
    target.ivIcon2 = Utils.findRequiredViewAsType(source, R.id.iv_icon_2, "field 'ivIcon2'", ImageView.class);
    target.ivZhifubaoChoose = Utils.findRequiredViewAsType(source, R.id.iv_zhifubao_choose, "field 'ivZhifubaoChoose'", ImageView.class);
    target.frtvPay = Utils.findRequiredViewAsType(source, R.id.frtv_pay, "field 'frtvPay'", RoundTextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TuanGouMaiDanZhiFuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewWeixin = null;
    target.viewZhifubao = null;
    target.tvChooseZhifufangshi = null;
    target.ivIcon1 = null;
    target.ivWeixinChoose = null;
    target.view = null;
    target.ivIcon2 = null;
    target.ivZhifubaoChoose = null;
    target.frtvPay = null;
  }
}
