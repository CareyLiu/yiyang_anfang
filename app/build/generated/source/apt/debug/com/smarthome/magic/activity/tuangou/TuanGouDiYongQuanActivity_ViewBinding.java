// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.tuangou;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TuanGouDiYongQuanActivity_ViewBinding implements Unbinder {
  private TuanGouDiYongQuanActivity target;

  @UiThread
  public TuanGouDiYongQuanActivity_ViewBinding(TuanGouDiYongQuanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TuanGouDiYongQuanActivity_ViewBinding(TuanGouDiYongQuanActivity target, View source) {
    this.target = target;

    target.rlvList = Utils.findRequiredViewAsType(source, R.id.rlv_list, "field 'rlvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TuanGouDiYongQuanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlvList = null;
  }
}
