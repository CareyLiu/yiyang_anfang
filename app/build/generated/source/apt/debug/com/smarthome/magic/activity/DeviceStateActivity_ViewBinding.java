// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DeviceStateActivity_ViewBinding implements Unbinder {
  private DeviceStateActivity target;

  private View view7f090366;

  @UiThread
  public DeviceStateActivity_ViewBinding(DeviceStateActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DeviceStateActivity_ViewBinding(final DeviceStateActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090366 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.tvDeviceState = Utils.findRequiredViewAsType(source, R.id.tv_device_state, "field 'tvDeviceState'", TextView.class);
    target.tvDeviceVoltage = Utils.findRequiredViewAsType(source, R.id.tv_device_voltage, "field 'tvDeviceVoltage'", TextView.class);
    target.tvSpeed = Utils.findRequiredViewAsType(source, R.id.tv_speed, "field 'tvSpeed'", TextView.class);
    target.tvRate = Utils.findRequiredViewAsType(source, R.id.tv_rate, "field 'tvRate'", TextView.class);
    target.tvInTemperature = Utils.findRequiredViewAsType(source, R.id.tv_in_temperature, "field 'tvInTemperature'", TextView.class);
    target.tvOutTemperature = Utils.findRequiredViewAsType(source, R.id.tv_out_temperature, "field 'tvOutTemperature'", TextView.class);
    target.tvMode = Utils.findRequiredViewAsType(source, R.id.tv_mode, "field 'tvMode'", TextView.class);
    target.tvPower = Utils.findRequiredViewAsType(source, R.id.tv_power, "field 'tvPower'", TextView.class);
    target.tvDuration = Utils.findRequiredViewAsType(source, R.id.tv_duration, "field 'tvDuration'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DeviceStateActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.tvDeviceState = null;
    target.tvDeviceVoltage = null;
    target.tvSpeed = null;
    target.tvRate = null;
    target.tvInTemperature = null;
    target.tvOutTemperature = null;
    target.tvMode = null;
    target.tvPower = null;
    target.tvDuration = null;

    view7f090366.setOnClickListener(null);
    view7f090366 = null;
  }
}
