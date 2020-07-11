// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HeaterSettingActivity_ViewBinding implements Unbinder {
  private HeaterSettingActivity target;

  private View view7f090081;

  private View view7f0901ee;

  private View view7f0901ef;

  private View view7f0901f5;

  private View view7f0901e8;

  private View view7f0901de;

  @UiThread
  public HeaterSettingActivity_ViewBinding(HeaterSettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HeaterSettingActivity_ViewBinding(final HeaterSettingActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.back, "field 'mBack' and method 'onClick'");
    target.mBack = Utils.castView(view, R.id.back, "field 'mBack'", LinearLayout.class);
    view7f090081 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_ovner, "field 'mItemOvner' and method 'onClick'");
    target.mItemOvner = Utils.castView(view, R.id.item_ovner, "field 'mItemOvner'", LinearLayout.class);
    view7f0901ee = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_parts, "field 'mItemParts' and method 'onClick'");
    target.mItemParts = Utils.castView(view, R.id.item_parts, "field 'mItemParts'", LinearLayout.class);
    view7f0901ef = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_ratio, "field 'mItemRatio' and method 'onClick'");
    target.mItemRatio = Utils.castView(view, R.id.item_ratio, "field 'mItemRatio'", LinearLayout.class);
    view7f0901f5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_host, "field 'mItemHost' and method 'onClick'");
    target.mItemHost = Utils.castView(view, R.id.item_host, "field 'mItemHost'", LinearLayout.class);
    view7f0901e8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_atmos, "field 'mItemAtmos' and method 'onClick'");
    target.mItemAtmos = Utils.castView(view, R.id.item_atmos, "field 'mItemAtmos'", LinearLayout.class);
    view7f0901de = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HeaterSettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBack = null;
    target.mItemOvner = null;
    target.mItemParts = null;
    target.mItemRatio = null;
    target.mItemHost = null;
    target.mItemAtmos = null;

    view7f090081.setOnClickListener(null);
    view7f090081 = null;
    view7f0901ee.setOnClickListener(null);
    view7f0901ee = null;
    view7f0901ef.setOnClickListener(null);
    view7f0901ef = null;
    view7f0901f5.setOnClickListener(null);
    view7f0901f5 = null;
    view7f0901e8.setOnClickListener(null);
    view7f0901e8 = null;
    view7f0901de.setOnClickListener(null);
    view7f0901de = null;
  }
}
