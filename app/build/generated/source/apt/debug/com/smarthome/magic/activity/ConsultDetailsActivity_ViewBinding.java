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
import com.amap.api.maps.MapView;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConsultDetailsActivity_ViewBinding implements Unbinder {
  private ConsultDetailsActivity target;

  private View view7f090259;

  private View view7f09047b;

  @UiThread
  public ConsultDetailsActivity_ViewBinding(ConsultDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ConsultDetailsActivity_ViewBinding(final ConsultDetailsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.layout_back, "field 'layoutBack' and method 'onViewClicked'");
    target.layoutBack = Utils.castView(view, R.id.layout_back, "field 'layoutBack'", LinearLayout.class);
    view7f090259 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_finish, "field 'tvFinish' and method 'onViewClicked'");
    target.tvFinish = Utils.castView(view, R.id.tv_finish, "field 'tvFinish'", TextView.class);
    view7f09047b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.list = Utils.findRequiredViewAsType(source, R.id.list, "field 'list'", LRecyclerView.class);
    target.map = Utils.findRequiredViewAsType(source, R.id.map, "field 'map'", MapView.class);
    target.layoutCarInfo = Utils.findRequiredViewAsType(source, R.id.layout_car_info, "field 'layoutCarInfo'", LinearLayout.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.tvFault = Utils.findRequiredViewAsType(source, R.id.tv_fault, "field 'tvFault'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ConsultDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layoutBack = null;
    target.tvFinish = null;
    target.list = null;
    target.map = null;
    target.layoutCarInfo = null;
    target.tvName = null;
    target.tvNumber = null;
    target.tvFault = null;

    view7f090259.setOnClickListener(null);
    view7f090259 = null;
    view7f09047b.setOnClickListener(null);
    view7f09047b = null;
  }
}
