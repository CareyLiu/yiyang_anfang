// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.amap.api.maps.MapView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LocusActivity_ViewBinding implements Unbinder {
  private LocusActivity target;

  private View view7f090359;

  private View view7f0901e3;

  private View view7f090558;

  private View view7f090227;

  private View view7f0900a4;

  @UiThread
  public LocusActivity_ViewBinding(LocusActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LocusActivity_ViewBinding(final LocusActivity target, View source) {
    this.target = target;

    View view;
    target.map = Utils.findRequiredViewAsType(source, R.id.map, "field 'map'", MapView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090359 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_before, "field 'ivBefore' and method 'onViewClicked'");
    target.ivBefore = Utils.castView(view, R.id.iv_before, "field 'ivBefore'", ImageView.class);
    view7f0901e3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_today, "field 'tvToday' and method 'onViewClicked'");
    target.tvToday = Utils.castView(view, R.id.tv_today, "field 'tvToday'", TextView.class);
    view7f090558 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_next, "field 'ivNext' and method 'onViewClicked'");
    target.ivNext = Utils.castView(view, R.id.iv_next, "field 'ivNext'", ImageView.class);
    view7f090227 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.frameLayout = Utils.findRequiredViewAsType(source, R.id.frame_layout, "field 'frameLayout'", FrameLayout.class);
    target.sbSpeed = Utils.findRequiredViewAsType(source, R.id.sb_speed, "field 'sbSpeed'", SeekBar.class);
    target.sbTime = Utils.findRequiredViewAsType(source, R.id.sb_time, "field 'sbTime'", SeekBar.class);
    view = Utils.findRequiredView(source, R.id.cb_switch, "field 'cbSwitch' and method 'onViewClicked'");
    target.cbSwitch = Utils.castView(view, R.id.cb_switch, "field 'cbSwitch'", CheckBox.class);
    view7f0900a4 = view;
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
    LocusActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.map = null;
    target.rlBack = null;
    target.ivBefore = null;
    target.tvToday = null;
    target.ivNext = null;
    target.frameLayout = null;
    target.sbSpeed = null;
    target.sbTime = null;
    target.cbSwitch = null;

    view7f090359.setOnClickListener(null);
    view7f090359 = null;
    view7f0901e3.setOnClickListener(null);
    view7f0901e3 = null;
    view7f090558.setOnClickListener(null);
    view7f090558 = null;
    view7f090227.setOnClickListener(null);
    view7f090227 = null;
    view7f0900a4.setOnClickListener(null);
    view7f0900a4 = null;
  }
}
