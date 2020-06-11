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

public class TuanGouShangJiaDetailsActivity_ViewBinding implements Unbinder {
  private TuanGouShangJiaDetailsActivity target;

  @UiThread
  public TuanGouShangJiaDetailsActivity_ViewBinding(TuanGouShangJiaDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TuanGouShangJiaDetailsActivity_ViewBinding(TuanGouShangJiaDetailsActivity target,
      View source) {
    this.target = target;

    target.rlvList = Utils.findRequiredViewAsType(source, R.id.rlv_list, "field 'rlvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TuanGouShangJiaDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlvList = null;
  }
}
