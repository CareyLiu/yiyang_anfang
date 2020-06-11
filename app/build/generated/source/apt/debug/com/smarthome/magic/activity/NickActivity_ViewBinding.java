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

public class NickActivity_ViewBinding implements Unbinder {
  private NickActivity target;

  private View view7f090352;

  private View view7f090091;

  @UiThread
  public NickActivity_ViewBinding(NickActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NickActivity_ViewBinding(final NickActivity target, View source) {
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
    target.etNickname = Utils.findRequiredViewAsType(source, R.id.et_nickname, "field 'etNickname'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_confirm, "field 'btnConfirm' and method 'onViewClicked'");
    target.btnConfirm = Utils.castView(view, R.id.btn_confirm, "field 'btnConfirm'", Button.class);
    view7f090091 = view;
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
    NickActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.etNickname = null;
    target.btnConfirm = null;

    view7f090352.setOnClickListener(null);
    view7f090352 = null;
    view7f090091.setOnClickListener(null);
    view7f090091 = null;
  }
}
