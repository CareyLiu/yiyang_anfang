// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatRadioButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HostActivity_ViewBinding implements Unbinder {
  private HostActivity target;

  private View view7f0901e0;

  private View view7f090086;

  private View view7f09008a;

  private View view7f090088;

  @UiThread
  public HostActivity_ViewBinding(HostActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HostActivity_ViewBinding(final HostActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'mIvBack' and method 'onClick'");
    target.mIvBack = Utils.castView(view, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    view7f0901e0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRbSingle = Utils.findRequiredViewAsType(source, R.id.rb_single, "field 'mRbSingle'", AppCompatRadioButton.class);
    target.mRbDouble = Utils.findRequiredViewAsType(source, R.id.rb_double, "field 'mRbDouble'", AppCompatRadioButton.class);
    target.mRgMagnet = Utils.findRequiredViewAsType(source, R.id.rg_magnet, "field 'mRgMagnet'", RadioGroup.class);
    target.mRbJc = Utils.findRequiredViewAsType(source, R.id.rb_jc, "field 'mRbJc'", AppCompatRadioButton.class);
    target.mRbLm = Utils.findRequiredViewAsType(source, R.id.rb_lm, "field 'mRbLm'", AppCompatRadioButton.class);
    target.mRgHeat = Utils.findRequiredViewAsType(source, R.id.rg_heat, "field 'mRgHeat'", RadioGroup.class);
    target.mRbTwo = Utils.findRequiredViewAsType(source, R.id.rb_two, "field 'mRbTwo'", AppCompatRadioButton.class);
    target.mRbFive = Utils.findRequiredViewAsType(source, R.id.rb_five, "field 'mRbFive'", AppCompatRadioButton.class);
    target.mRgPower = Utils.findRequiredViewAsType(source, R.id.rg_power, "field 'mRgPower'", RadioGroup.class);
    target.mRbDisable = Utils.findRequiredViewAsType(source, R.id.rb_disable, "field 'mRbDisable'", AppCompatRadioButton.class);
    target.mRbEnable = Utils.findRequiredViewAsType(source, R.id.rb_enable, "field 'mRbEnable'", AppCompatRadioButton.class);
    target.mRgFy = Utils.findRequiredViewAsType(source, R.id.rg_fy, "field 'mRgFy'", RadioGroup.class);
    target.mRbNtc = Utils.findRequiredViewAsType(source, R.id.rb_ntc, "field 'mRbNtc'", AppCompatRadioButton.class);
    target.mRbPtc = Utils.findRequiredViewAsType(source, R.id.rb_ptc, "field 'mRbPtc'", AppCompatRadioButton.class);
    target.mRbSensorAuto = Utils.findRequiredViewAsType(source, R.id.rb_sensor_auto, "field 'mRbSensorAuto'", AppCompatRadioButton.class);
    target.mRgSensor = Utils.findRequiredViewAsType(source, R.id.rg_sensor, "field 'mRgSensor'", RadioGroup.class);
    target.mRb12v = Utils.findRequiredViewAsType(source, R.id.rb_12v, "field 'mRb12v'", AppCompatRadioButton.class);
    target.mRb14v = Utils.findRequiredViewAsType(source, R.id.rb_14v, "field 'mRb14v'", AppCompatRadioButton.class);
    target.mRbVoltageAuto = Utils.findRequiredViewAsType(source, R.id.rb_voltage_auto, "field 'mRbVoltageAuto'", AppCompatRadioButton.class);
    target.mRgVoltage = Utils.findRequiredViewAsType(source, R.id.rg_voltage, "field 'mRgVoltage'", RadioGroup.class);
    view = Utils.findRequiredView(source, R.id.bt_cancel, "field 'mBtCancel' and method 'onClick'");
    target.mBtCancel = Utils.castView(view, R.id.bt_cancel, "field 'mBtCancel'", Button.class);
    view7f090086 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_sure, "field 'mBtSure' and method 'onClick'");
    target.mBtSure = Utils.castView(view, R.id.bt_sure, "field 'mBtSure'", Button.class);
    view7f09008a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_recovery, "field 'mBtRecovery' and method 'onClick'");
    target.mBtRecovery = Utils.castView(view, R.id.bt_recovery, "field 'mBtRecovery'", Button.class);
    view7f090088 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'rlTitle'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HostActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvBack = null;
    target.mRbSingle = null;
    target.mRbDouble = null;
    target.mRgMagnet = null;
    target.mRbJc = null;
    target.mRbLm = null;
    target.mRgHeat = null;
    target.mRbTwo = null;
    target.mRbFive = null;
    target.mRgPower = null;
    target.mRbDisable = null;
    target.mRbEnable = null;
    target.mRgFy = null;
    target.mRbNtc = null;
    target.mRbPtc = null;
    target.mRbSensorAuto = null;
    target.mRgSensor = null;
    target.mRb12v = null;
    target.mRb14v = null;
    target.mRbVoltageAuto = null;
    target.mRgVoltage = null;
    target.mBtCancel = null;
    target.mBtSure = null;
    target.mBtRecovery = null;
    target.rlTitle = null;

    view7f0901e0.setOnClickListener(null);
    view7f0901e0 = null;
    view7f090086.setOnClickListener(null);
    view7f090086 = null;
    view7f09008a.setOnClickListener(null);
    view7f09008a = null;
    view7f090088.setOnClickListener(null);
    view7f090088 = null;
  }
}
