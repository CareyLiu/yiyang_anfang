// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ServiceMineFragment_ViewBinding implements Unbinder {
  private ServiceMineFragment target;

  private View view7f09024b;

  private View view7f09075d;

  private View view7f0902c2;

  private View view7f0902d4;

  private View view7f0902d7;

  private View view7f0902df;

  private View view7f0902c1;

  private View view7f0902b9;

  private View view7f0902c0;

  private View view7f0902d1;

  @UiThread
  public ServiceMineFragment_ViewBinding(final ServiceMineFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_header, "field 'ivHeader' and method 'onViewClicked'");
    target.ivHeader = Utils.castView(view, R.id.iv_header, "field 'ivHeader'", RoundedImageView.class);
    view7f09024b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.serviceName = Utils.findRequiredViewAsType(source, R.id.service_name, "field 'serviceName'", TextView.class);
    target.serviceAge = Utils.findRequiredViewAsType(source, R.id.service_age, "field 'serviceAge'", TextView.class);
    target.servicePhone = Utils.findRequiredViewAsType(source, R.id.service_phone, "field 'servicePhone'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_setting, "field 'tvSetting' and method 'onViewClicked'");
    target.tvSetting = Utils.castView(view, R.id.tv_setting, "field 'tvSetting'", TextView.class);
    view7f09075d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_consult, "field 'layoutConsult' and method 'onViewClicked'");
    target.layoutConsult = Utils.castView(view, R.id.layout_consult, "field 'layoutConsult'", LinearLayout.class);
    view7f0902c2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_pending, "field 'layoutPending' and method 'onViewClicked'");
    target.layoutPending = Utils.castView(view, R.id.layout_pending, "field 'layoutPending'", LinearLayout.class);
    view7f0902d4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_processed, "field 'layoutProcessed' and method 'onViewClicked'");
    target.layoutProcessed = Utils.castView(view, R.id.layout_processed, "field 'layoutProcessed'", LinearLayout.class);
    view7f0902d7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_tobe_evaluated, "field 'layoutTobeEvaluated' and method 'onViewClicked'");
    target.layoutTobeEvaluated = Utils.castView(view, R.id.layout_tobe_evaluated, "field 'layoutTobeEvaluated'", LinearLayout.class);
    view7f0902df = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_closed, "field 'layoutClosed' and method 'onViewClicked'");
    target.layoutClosed = Utils.castView(view, R.id.layout_closed, "field 'layoutClosed'", LinearLayout.class);
    view7f0902c1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_about_us, "field 'layoutAboutUs' and method 'onViewClicked'");
    target.layoutAboutUs = Utils.castView(view, R.id.layout_about_us, "field 'layoutAboutUs'", LinearLayout.class);
    view7f0902b9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_clear_cache, "field 'layoutClearCache' and method 'onViewClicked'");
    target.layoutClearCache = Utils.castView(view, R.id.layout_clear_cache, "field 'layoutClearCache'", LinearLayout.class);
    view7f0902c0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_out, "field 'layoutOut' and method 'onViewClicked'");
    target.layoutOut = Utils.castView(view, R.id.layout_out, "field 'layoutOut'", LinearLayout.class);
    view7f0902d1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCache = Utils.findRequiredViewAsType(source, R.id.tv_cache, "field 'tvCache'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ServiceMineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivHeader = null;
    target.serviceName = null;
    target.serviceAge = null;
    target.servicePhone = null;
    target.tvSetting = null;
    target.layoutConsult = null;
    target.layoutPending = null;
    target.layoutProcessed = null;
    target.layoutTobeEvaluated = null;
    target.layoutClosed = null;
    target.layoutAboutUs = null;
    target.layoutClearCache = null;
    target.layoutOut = null;
    target.tvCache = null;

    view7f09024b.setOnClickListener(null);
    view7f09024b = null;
    view7f09075d.setOnClickListener(null);
    view7f09075d = null;
    view7f0902c2.setOnClickListener(null);
    view7f0902c2 = null;
    view7f0902d4.setOnClickListener(null);
    view7f0902d4 = null;
    view7f0902d7.setOnClickListener(null);
    view7f0902d7 = null;
    view7f0902df.setOnClickListener(null);
    view7f0902df = null;
    view7f0902c1.setOnClickListener(null);
    view7f0902c1 = null;
    view7f0902b9.setOnClickListener(null);
    view7f0902b9 = null;
    view7f0902c0.setOnClickListener(null);
    view7f0902c0 = null;
    view7f0902d1.setOnClickListener(null);
    view7f0902d1 = null;
  }
}
