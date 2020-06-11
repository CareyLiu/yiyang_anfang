// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.gouwuche;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GouWuCheQueRenDingDanActivity_ViewBinding implements Unbinder {
  private GouWuCheQueRenDingDanActivity target;

  @UiThread
  public GouWuCheQueRenDingDanActivity_ViewBinding(GouWuCheQueRenDingDanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GouWuCheQueRenDingDanActivity_ViewBinding(GouWuCheQueRenDingDanActivity target,
      View source) {
    this.target = target;

    target.rlvList = Utils.findRequiredViewAsType(source, R.id.rlv_list, "field 'rlvList'", RecyclerView.class);
    target.llNowPay = Utils.findRequiredViewAsType(source, R.id.ll_now_pay, "field 'llNowPay'", LinearLayout.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.constrain = Utils.findRequiredViewAsType(source, R.id.constrain, "field 'constrain'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GouWuCheQueRenDingDanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlvList = null;
    target.llNowPay = null;
    target.tvPrice = null;
    target.constrain = null;
  }
}
