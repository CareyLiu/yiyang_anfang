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

public class PersonInfoAcctivity_ViewBinding implements Unbinder {
  private PersonInfoAcctivity target;

  private View view7f090516;

  private View view7f090703;

  private View view7f09071b;

  @UiThread
  public PersonInfoAcctivity_ViewBinding(PersonInfoAcctivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PersonInfoAcctivity_ViewBinding(final PersonInfoAcctivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090516 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_nick_name, "field 'tvNickName' and method 'onViewClicked'");
    target.tvNickName = Utils.castView(view, R.id.tv_nick_name, "field 'tvNickName'", TextView.class);
    view7f090703 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_phone, "field 'tvPhone' and method 'onViewClicked'");
    target.tvPhone = Utils.castView(view, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    view7f09071b = view;
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
    PersonInfoAcctivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.tvNickName = null;
    target.tvPhone = null;

    view7f090516.setOnClickListener(null);
    view7f090516 = null;
    view7f090703.setOnClickListener(null);
    view7f090703 = null;
    view7f09071b.setOnClickListener(null);
    view7f09071b = null;
  }
}
