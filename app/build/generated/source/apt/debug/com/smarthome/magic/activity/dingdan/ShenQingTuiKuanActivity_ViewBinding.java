// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.dingdan;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundRelativeLayout;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShenQingTuiKuanActivity_ViewBinding implements Unbinder {
  private ShenQingTuiKuanActivity target;

  @UiThread
  public ShenQingTuiKuanActivity_ViewBinding(ShenQingTuiKuanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShenQingTuiKuanActivity_ViewBinding(ShenQingTuiKuanActivity target, View source) {
    this.target = target;

    target.ivImage = Utils.findRequiredViewAsType(source, R.id.iv_image, "field 'ivImage'", ImageView.class);
    target.tvTuihuoTuikuan = Utils.findRequiredViewAsType(source, R.id.tv_tuihuo_tuikuan, "field 'tvTuihuoTuikuan'", TextView.class);
    target.rrl1 = Utils.findRequiredViewAsType(source, R.id.rrl_1, "field 'rrl1'", RoundRelativeLayout.class);
    target.ivImage1 = Utils.findRequiredViewAsType(source, R.id.iv_image1, "field 'ivImage1'", ImageView.class);
    target.tvTuihuoTuikuan1 = Utils.findRequiredViewAsType(source, R.id.tv_tuihuo_tuikuan1, "field 'tvTuihuoTuikuan1'", TextView.class);
    target.rrl2 = Utils.findRequiredViewAsType(source, R.id.rrl_2, "field 'rrl2'", RoundRelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShenQingTuiKuanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivImage = null;
    target.tvTuihuoTuikuan = null;
    target.rrl1 = null;
    target.ivImage1 = null;
    target.tvTuihuoTuikuan1 = null;
    target.rrl2 = null;
  }
}
