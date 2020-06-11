// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddressActivity_ViewBinding implements Unbinder {
  private AddressActivity target;

  private View view7f090352;

  private View view7f090423;

  @UiThread
  public AddressActivity_ViewBinding(AddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddressActivity_ViewBinding(final AddressActivity target, View source) {
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
    target.list = Utils.findRequiredViewAsType(source, R.id.list, "field 'list'", LRecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tv_address, "field 'tvAddress' and method 'onViewClicked'");
    target.tvAddress = Utils.castView(view, R.id.tv_address, "field 'tvAddress'", TextView.class);
    view7f090423 = view;
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
    AddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.list = null;
    target.tvAddress = null;

    view7f090352.setOnClickListener(null);
    view7f090352 = null;
    view7f090423.setOnClickListener(null);
    view7f090423 = null;
  }
}
