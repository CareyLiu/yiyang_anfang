// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.zijian_shangcheng;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FenLeiHomeActivity_ViewBinding implements Unbinder {
  private FenLeiHomeActivity target;

  @UiThread
  public FenLeiHomeActivity_ViewBinding(FenLeiHomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FenLeiHomeActivity_ViewBinding(FenLeiHomeActivity target, View source) {
    this.target = target;

    target.rlvList = Utils.findRequiredViewAsType(source, R.id.rlv_list, "field 'rlvList'", RecyclerView.class);
    target.flContainer = Utils.findRequiredViewAsType(source, R.id.fl_container, "field 'flContainer'", FrameLayout.class);
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.rlMain = Utils.findRequiredViewAsType(source, R.id.rl_main, "field 'rlMain'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FenLeiHomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlvList = null;
    target.flContainer = null;
    target.ivBack = null;
    target.rlMain = null;
  }
}
