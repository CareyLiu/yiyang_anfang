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

public class AtmosActivity_ViewBinding implements Unbinder {
  private AtmosActivity target;

  private View view7f090352;

  private View view7f09008a;

  @UiThread
  public AtmosActivity_ViewBinding(AtmosActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AtmosActivity_ViewBinding(final AtmosActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onClick'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
    view7f090352 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mEtAtmos = Utils.findRequiredViewAsType(source, R.id.et_atmos, "field 'mEtAtmos'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_sure, "field 'mBtSure' and method 'onClick'");
    target.mBtSure = Utils.castView(view, R.id.bt_sure, "field 'mBtSure'", Button.class);
    view7f09008a = view;
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
    AtmosActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRlBack = null;
    target.mEtAtmos = null;
    target.mBtSure = null;

    view7f090352.setOnClickListener(null);
    view7f090352 = null;
    view7f09008a.setOnClickListener(null);
    view7f09008a = null;
  }
}
