// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.smarthome.magic.R;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GoosDetailsActivity_ViewBinding implements Unbinder {
  private GoosDetailsActivity target;

  private View view7f090352;

  private View view7f0901e2;

  private View view7f090075;

  @UiThread
  public GoosDetailsActivity_ViewBinding(GoosDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GoosDetailsActivity_ViewBinding(final GoosDetailsActivity target, View source) {
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
    target.ablBar = Utils.findRequiredViewAsType(source, R.id.abl_bar, "field 'ablBar'", AppBarLayout.class);
    target.list = Utils.findRequiredViewAsType(source, R.id.list, "field 'list'", LRecyclerView.class);
    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinatorLayout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_cart, "field 'ivCart' and method 'onViewClicked'");
    target.ivCart = Utils.castView(view, R.id.iv_cart, "field 'ivCart'", ImageView.class);
    view7f0901e2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.banner, "field 'banner' and method 'onViewClicked'");
    target.banner = Utils.castView(view, R.id.banner, "field 'banner'", Banner.class);
    view7f090075 = view;
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
    GoosDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.ablBar = null;
    target.list = null;
    target.coordinatorLayout = null;
    target.ivCart = null;
    target.banner = null;

    view7f090352.setOnClickListener(null);
    view7f090352 = null;
    view7f0901e2.setOnClickListener(null);
    view7f0901e2 = null;
    view7f090075.setOnClickListener(null);
    view7f090075 = null;
  }
}
