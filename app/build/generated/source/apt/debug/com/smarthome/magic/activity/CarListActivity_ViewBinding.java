// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CarListActivity_ViewBinding implements Unbinder {
  private CarListActivity target;

  private View view7f090352;

  @UiThread
  public CarListActivity_ViewBinding(CarListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CarListActivity_ViewBinding(final CarListActivity target, View source) {
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
    target.mList = Utils.findRequiredViewAsType(source, R.id.list, "field 'mList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CarListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRlBack = null;
    target.mList = null;

    view7f090352.setOnClickListener(null);
    view7f090352 = null;
  }
}
