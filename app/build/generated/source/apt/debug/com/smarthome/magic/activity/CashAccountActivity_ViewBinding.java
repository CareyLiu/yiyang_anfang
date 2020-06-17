// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CashAccountActivity_ViewBinding implements Unbinder {
  private CashAccountActivity target;

  private View view7f090097;

  @UiThread
  public CashAccountActivity_ViewBinding(CashAccountActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CashAccountActivity_ViewBinding(final CashAccountActivity target, View source) {
    this.target = target;

    View view;
    target.etAccount = Utils.findRequiredViewAsType(source, R.id.et_account, "field 'etAccount'", EditText.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_save, "field 'btnSave' and method 'onViewClicked'");
    target.btnSave = Utils.castView(view, R.id.btn_save, "field 'btnSave'", Button.class);
    view7f090097 = view;
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
    CashAccountActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etAccount = null;
    target.etName = null;
    target.btnSave = null;

    view7f090097.setOnClickListener(null);
    view7f090097 = null;
  }
}
