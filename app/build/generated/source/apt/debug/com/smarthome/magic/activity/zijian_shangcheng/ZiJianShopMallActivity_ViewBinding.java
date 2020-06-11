// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.zijian_shangcheng;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ZiJianShopMallActivity_ViewBinding implements Unbinder {
  private ZiJianShopMallActivity target;

  @UiThread
  public ZiJianShopMallActivity_ViewBinding(ZiJianShopMallActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ZiJianShopMallActivity_ViewBinding(ZiJianShopMallActivity target, View source) {
    this.target = target;

    target.swipeTarget = Utils.findRequiredViewAsType(source, R.id.swipe_target, "field 'swipeTarget'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ZiJianShopMallActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.swipeTarget = null;
    target.refreshLayout = null;
  }
}
