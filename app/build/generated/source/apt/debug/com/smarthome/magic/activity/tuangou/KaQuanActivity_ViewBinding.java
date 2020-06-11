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

public class KaQuanActivity_ViewBinding implements Unbinder {
  private KaQuanActivity target;

  @UiThread
  public KaQuanActivity_ViewBinding(KaQuanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public KaQuanActivity_ViewBinding(KaQuanActivity target, View source) {
    this.target = target;

    target.rlvList = Utils.findRequiredViewAsType(source, R.id.rlv_list, "field 'rlvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    KaQuanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlvList = null;
  }
}
