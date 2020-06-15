// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AppointmentActivity_ViewBinding implements Unbinder {
  private AppointmentActivity target;

  private View view7f090359;

  private View view7f090096;

  @UiThread
  public AppointmentActivity_ViewBinding(AppointmentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AppointmentActivity_ViewBinding(final AppointmentActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090359 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.flDate = Utils.findRequiredViewAsType(source, R.id.fl_date, "field 'flDate'", FrameLayout.class);
    target.cbSunday = Utils.findRequiredViewAsType(source, R.id.cb_sunday, "field 'cbSunday'", CheckBox.class);
    target.cbMonday = Utils.findRequiredViewAsType(source, R.id.cb_monday, "field 'cbMonday'", CheckBox.class);
    target.cbTuesday = Utils.findRequiredViewAsType(source, R.id.cb_tuesday, "field 'cbTuesday'", CheckBox.class);
    target.cbWednesday = Utils.findRequiredViewAsType(source, R.id.cb_wednesday, "field 'cbWednesday'", CheckBox.class);
    target.cbThursday = Utils.findRequiredViewAsType(source, R.id.cb_thursday, "field 'cbThursday'", CheckBox.class);
    target.cbFriday = Utils.findRequiredViewAsType(source, R.id.cb_friday, "field 'cbFriday'", CheckBox.class);
    target.cbSaturday = Utils.findRequiredViewAsType(source, R.id.cb_saturday, "field 'cbSaturday'", CheckBox.class);
    target.flTime = Utils.findRequiredViewAsType(source, R.id.fl_time, "field 'flTime'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_setting, "method 'onViewClicked'");
    view7f090096 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AppointmentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.flDate = null;
    target.cbSunday = null;
    target.cbMonday = null;
    target.cbTuesday = null;
    target.cbWednesday = null;
    target.cbThursday = null;
    target.cbFriday = null;
    target.cbSaturday = null;
    target.flTime = null;

    view7f090359.setOnClickListener(null);
    view7f090359 = null;
    view7f090096.setOnClickListener(null);
    view7f090096 = null;
  }
}
