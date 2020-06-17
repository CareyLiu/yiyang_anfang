// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class HistoryLocusActivity_ViewBinding implements Unbinder {
  private HistoryLocusActivity target;

  private View view7f090366;

  private View view7f090374;

  private View view7f090445;

  private View view7f09048f;

  private View view7f090286;

  @UiThread
  public HistoryLocusActivity_ViewBinding(HistoryLocusActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HistoryLocusActivity_ViewBinding(final HistoryLocusActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090366 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_map, "field 'rlMap' and method 'onViewClicked'");
    target.rlMap = Utils.castView(view, R.id.rl_map, "field 'rlMap'", RelativeLayout.class);
    view7f090374 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_begin_date, "field 'tvBeginDate' and method 'onViewClicked'");
    target.tvBeginDate = Utils.castView(view, R.id.tv_begin_date, "field 'tvBeginDate'", TextView.class);
    view7f090445 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_end_date, "field 'tvEndDate' and method 'onViewClicked'");
    target.tvEndDate = Utils.castView(view, R.id.tv_end_date, "field 'tvEndDate'", TextView.class);
    view7f09048f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_screen, "field 'layoutScreen' and method 'onViewClicked'");
    target.layoutScreen = Utils.castView(view, R.id.layout_screen, "field 'layoutScreen'", LinearLayout.class);
    view7f090286 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvBrandPic = Utils.findRequiredViewAsType(source, R.id.tv_brand_pic, "field 'tvBrandPic'", ImageView.class);
    target.tvCarType = Utils.findRequiredViewAsType(source, R.id.tv_car_type, "field 'tvCarType'", TextView.class);
    target.tvCarNumber = Utils.findRequiredViewAsType(source, R.id.tv_car_number, "field 'tvCarNumber'", TextView.class);
    target.list = Utils.findRequiredViewAsType(source, R.id.list, "field 'list'", LRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HistoryLocusActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.rlMap = null;
    target.tvBeginDate = null;
    target.tvEndDate = null;
    target.layoutScreen = null;
    target.tvBrandPic = null;
    target.tvCarType = null;
    target.tvCarNumber = null;
    target.list = null;

    view7f090366.setOnClickListener(null);
    view7f090366 = null;
    view7f090374.setOnClickListener(null);
    view7f090374 = null;
    view7f090445.setOnClickListener(null);
    view7f090445 = null;
    view7f09048f.setOnClickListener(null);
    view7f09048f = null;
    view7f090286.setOnClickListener(null);
    view7f090286 = null;
  }
}
