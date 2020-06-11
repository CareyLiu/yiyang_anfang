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

public class ReviseLoginActivity_ViewBinding implements Unbinder {
  private ReviseLoginActivity target;

  private View view7f090352;

  private View view7f090095;

  @UiThread
  public ReviseLoginActivity_ViewBinding(ReviseLoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReviseLoginActivity_ViewBinding(final ReviseLoginActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090352 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_account, "field 'etPassword'", EditText.class);
    target.etRepeat = Utils.findRequiredViewAsType(source, R.id.et_repeat, "field 'etRepeat'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_save, "field 'btnSave' and method 'onViewClicked'");
    target.btnSave = Utils.castView(view, R.id.btn_save, "field 'btnSave'", Button.class);
    view7f090095 = view;
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
    ReviseLoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.etPassword = null;
    target.etRepeat = null;
    target.btnSave = null;

    view7f090352.setOnClickListener(null);
    view7f090352 = null;
    view7f090095.setOnClickListener(null);
    view7f090095 = null;
  }
}
