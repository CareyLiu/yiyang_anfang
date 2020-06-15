// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import com.smarthome.magic.view.ArcProgressBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WindHeaterActivity_ViewBinding implements Unbinder {
  private WindHeaterActivity target;

  private View view7f09032d;

  private View view7f09032e;

  private View view7f09032f;

  private View view7f090330;

  private View view7f090331;

  @UiThread
  public WindHeaterActivity_ViewBinding(WindHeaterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WindHeaterActivity_ViewBinding(final WindHeaterActivity target, View source) {
    this.target = target;

    View view;
    target.ivHeaterHost = Utils.findRequiredViewAsType(source, R.id.iv_heater_host, "field 'ivHeaterHost'", RelativeLayout.class);
    target.ivHeaterImpeller = Utils.findRequiredViewAsType(source, R.id.iv_heater_impeller, "field 'ivHeaterImpeller'", ImageView.class);
    target.ivHeaterFire = Utils.findRequiredViewAsType(source, R.id.iv_heater_fire, "field 'ivHeaterFire'", ImageView.class);
    target.arcProgressBar = Utils.findRequiredViewAsType(source, R.id.arcProgressBar, "field 'arcProgressBar'", ArcProgressBar.class);
    target.btnHeaterClose = Utils.findRequiredViewAsType(source, R.id.btn_heater_close, "field 'btnHeaterClose'", Button.class);
    target.mToolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'mToolbarTitle'", TextView.class);
    target.mTvWd = Utils.findRequiredViewAsType(source, R.id.tv_wd, "field 'mTvWd'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rb_heater_air_mode, "field 'rbHeaterAirMode' and method 'onViewClicked'");
    target.rbHeaterAirMode = Utils.castView(view, R.id.rb_heater_air_mode, "field 'rbHeaterAirMode'", RadioButton.class);
    view7f09032d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rb_heater_gear_mode, "field 'rbHeaterGearMode' and method 'onViewClicked'");
    target.rbHeaterGearMode = Utils.castView(view, R.id.rb_heater_gear_mode, "field 'rbHeaterGearMode'", RadioButton.class);
    view7f09032e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rb_heater_pump_mode, "field 'rbHeaterPumpMode' and method 'onViewClicked'");
    target.rbHeaterPumpMode = Utils.castView(view, R.id.rb_heater_pump_mode, "field 'rbHeaterPumpMode'", RadioButton.class);
    view7f09032f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rb_heater_yby_mode, "field 'rbHeaterYbyMode' and method 'onViewClicked'");
    target.rbHeaterYbyMode = Utils.castView(view, R.id.rb_heater_yby_mode, "field 'rbHeaterYbyMode'", RadioButton.class);
    view7f090330 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rb_heater_ytf_mode, "field 'rbHeaterYtfMode' and method 'onViewClicked'");
    target.rbHeaterYtfMode = Utils.castView(view, R.id.rb_heater_ytf_mode, "field 'rbHeaterYtfMode'", RadioButton.class);
    view7f090331 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rgMagnet = Utils.findRequiredViewAsType(source, R.id.rg_magnet, "field 'rgMagnet'", RadioGroup.class);
    target.tvYuShe_WenDu = Utils.findRequiredViewAsType(source, R.id.tv_yushe_wendu, "field 'tvYuShe_WenDu'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WindHeaterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivHeaterHost = null;
    target.ivHeaterImpeller = null;
    target.ivHeaterFire = null;
    target.arcProgressBar = null;
    target.btnHeaterClose = null;
    target.mToolbarTitle = null;
    target.mTvWd = null;
    target.rbHeaterAirMode = null;
    target.rbHeaterGearMode = null;
    target.rbHeaterPumpMode = null;
    target.rbHeaterYbyMode = null;
    target.rbHeaterYtfMode = null;
    target.rgMagnet = null;
    target.tvYuShe_WenDu = null;

    view7f09032d.setOnClickListener(null);
    view7f09032d = null;
    view7f09032e.setOnClickListener(null);
    view7f09032e = null;
    view7f09032f.setOnClickListener(null);
    view7f09032f = null;
    view7f090330.setOnClickListener(null);
    view7f090330 = null;
    view7f090331.setOnClickListener(null);
    view7f090331 = null;
  }
}
