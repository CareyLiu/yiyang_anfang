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
import com.smarthome.magic.view.Keyboard;
import com.smarthome.magic.view.PayEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RevisePayActivity_ViewBinding implements Unbinder {
  private RevisePayActivity target;

  private View view7f090359;

  @UiThread
  public RevisePayActivity_ViewBinding(RevisePayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RevisePayActivity_ViewBinding(final RevisePayActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090359 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.PayEditTextPay = Utils.findRequiredViewAsType(source, R.id.PayEditText_pay, "field 'PayEditTextPay'", PayEditText.class);
    target.KeyboardViewPay = Utils.findRequiredViewAsType(source, R.id.KeyboardView_pay, "field 'KeyboardViewPay'", Keyboard.class);
    target.tvTips = Utils.findRequiredViewAsType(source, R.id.tv_tips, "field 'tvTips'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RevisePayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.PayEditTextPay = null;
    target.KeyboardViewPay = null;
    target.tvTips = null;

    view7f090359.setOnClickListener(null);
    view7f090359 = null;
  }
}
