// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WifiSettingActivity_ViewBinding implements Unbinder {
  private WifiSettingActivity target;

  @UiThread
  public WifiSettingActivity_ViewBinding(WifiSettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WifiSettingActivity_ViewBinding(WifiSettingActivity target, View source) {
    this.target = target;

    target.tvKaiqi = Utils.findRequiredViewAsType(source, R.id.tv_kaiqi, "field 'tvKaiqi'", TextView.class);
    target.tvClose = Utils.findRequiredViewAsType(source, R.id.tv_close, "field 'tvClose'", TextView.class);
    target.tvWifiState = Utils.findRequiredViewAsType(source, R.id.tv_wifi_state, "field 'tvWifiState'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WifiSettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvKaiqi = null;
    target.tvClose = null;
    target.tvWifiState = null;
  }
}
