// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.tuangou;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundTextView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TuanGouMaiDanActivity_ViewBinding implements Unbinder {
  private TuanGouMaiDanActivity target;

  @UiThread
  public TuanGouMaiDanActivity_ViewBinding(TuanGouMaiDanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TuanGouMaiDanActivity_ViewBinding(TuanGouMaiDanActivity target, View source) {
    this.target = target;

    target.etJine = Utils.findRequiredViewAsType(source, R.id.et_jine, "field 'etJine'", EditText.class);
    target.rtvPay = Utils.findRequiredViewAsType(source, R.id.rtv_pay, "field 'rtvPay'", RoundTextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TuanGouMaiDanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etJine = null;
    target.rtvPay = null;
  }
}
