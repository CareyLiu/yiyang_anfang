// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.tuangou;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TuanGouShangJiaListActivity_ViewBinding implements Unbinder {
  private TuanGouShangJiaListActivity target;

  @UiThread
  public TuanGouShangJiaListActivity_ViewBinding(TuanGouShangJiaListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TuanGouShangJiaListActivity_ViewBinding(TuanGouShangJiaListActivity target, View source) {
    this.target = target;

    target.swipeTarget = Utils.findRequiredViewAsType(source, R.id.swipe_target, "field 'swipeTarget'", RecyclerView.class);
    target.constrainXx = Utils.findRequiredViewAsType(source, R.id.constrain_xx, "field 'constrainXx'", LinearLayout.class);
    target.constrain = Utils.findRequiredViewAsType(source, R.id.constrain, "field 'constrain'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TuanGouShangJiaListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.swipeTarget = null;
    target.constrainXx = null;
    target.constrain = null;
  }
}
