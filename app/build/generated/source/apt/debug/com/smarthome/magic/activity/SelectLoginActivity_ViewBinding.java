// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectLoginActivity_ViewBinding implements Unbinder {
  private SelectLoginActivity target;

  private View view7f09051a;

  @UiThread
  public SelectLoginActivity_ViewBinding(SelectLoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectLoginActivity_ViewBinding(final SelectLoginActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_close, "field 'rlClose' and method 'onViewClicked'");
    target.rlClose = Utils.castView(view, R.id.rl_close, "field 'rlClose'", RelativeLayout.class);
    view7f09051a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.list = Utils.findRequiredViewAsType(source, R.id.list, "field 'list'", LRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectLoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlClose = null;
    target.list = null;

    view7f09051a.setOnClickListener(null);
    view7f09051a = null;
  }
}
