// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.LinearLayout;
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

public class ShopCartActivity_ViewBinding implements Unbinder {
  private ShopCartActivity target;

  private View view7f090259;

  @UiThread
  public ShopCartActivity_ViewBinding(ShopCartActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShopCartActivity_ViewBinding(final ShopCartActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.layout_back, "field 'layoutBack' and method 'onViewClicked'");
    target.layoutBack = Utils.castView(view, R.id.layout_back, "field 'layoutBack'", LinearLayout.class);
    view7f090259 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.list = Utils.findRequiredViewAsType(source, R.id.list, "field 'list'", LRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShopCartActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layoutBack = null;
    target.tvTitle = null;
    target.list = null;

    view7f090259.setOnClickListener(null);
    view7f090259 = null;
  }
}
