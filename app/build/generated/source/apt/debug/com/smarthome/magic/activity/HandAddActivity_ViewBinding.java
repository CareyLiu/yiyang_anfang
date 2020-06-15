// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HandAddActivity_ViewBinding implements Unbinder {
  private HandAddActivity target;

  private View view7f090089;

  private View view7f090359;

  @UiThread
  public HandAddActivity_ViewBinding(HandAddActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HandAddActivity_ViewBinding(final HandAddActivity target, View source) {
    this.target = target;

    View view;
    target.mEtNumber = Utils.findRequiredViewAsType(source, R.id.et_number, "field 'mEtNumber'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_submit, "field 'mBtSubmit' and method 'onClick'");
    target.mBtSubmit = Utils.castView(view, R.id.bt_submit, "field 'mBtSubmit'", Button.class);
    view7f090089 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onClick'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
    view7f090359 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HandAddActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEtNumber = null;
    target.mBtSubmit = null;
    target.mRlBack = null;

    view7f090089.setOnClickListener(null);
    view7f090089 = null;
    view7f090359.setOnClickListener(null);
    view7f090359 = null;
  }
}
