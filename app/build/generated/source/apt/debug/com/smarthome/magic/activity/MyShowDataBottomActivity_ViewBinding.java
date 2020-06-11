// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.itheima.wheelpicker.WheelPicker;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyShowDataBottomActivity_ViewBinding implements Unbinder {
  private MyShowDataBottomActivity target;

  @UiThread
  public MyShowDataBottomActivity_ViewBinding(MyShowDataBottomActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyShowDataBottomActivity_ViewBinding(MyShowDataBottomActivity target, View source) {
    this.target = target;

    target.tvCannel = Utils.findRequiredViewAsType(source, R.id.tv_cannel, "field 'tvCannel'", TextView.class);
    target.tvEnter = Utils.findRequiredViewAsType(source, R.id.tv_enter, "field 'tvEnter'", TextView.class);
    target.wheelPicker1 = Utils.findRequiredViewAsType(source, R.id.wheel_picker1, "field 'wheelPicker1'", WheelPicker.class);
    target.wheelPicker2 = Utils.findRequiredViewAsType(source, R.id.wheel_picker2, "field 'wheelPicker2'", WheelPicker.class);
    target.wheelPicker3 = Utils.findRequiredViewAsType(source, R.id.wheel_picker3, "field 'wheelPicker3'", WheelPicker.class);
    target.view = Utils.findRequiredView(source, R.id.view, "field 'view'");
  }

  @Override
  @CallSuper
  public void unbind() {
    MyShowDataBottomActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvCannel = null;
    target.tvEnter = null;
    target.wheelPicker1 = null;
    target.wheelPicker2 = null;
    target.wheelPicker3 = null;
    target.view = null;
  }
}
