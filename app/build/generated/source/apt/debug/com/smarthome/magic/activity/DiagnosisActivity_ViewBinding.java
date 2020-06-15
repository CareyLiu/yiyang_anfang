// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DiagnosisActivity_ViewBinding implements Unbinder {
  private DiagnosisActivity target;

  private View view7f090090;

  private View view7f090359;

  private View view7f09035e;

  @UiThread
  public DiagnosisActivity_ViewBinding(DiagnosisActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DiagnosisActivity_ViewBinding(final DiagnosisActivity target, View source) {
    this.target = target;

    View view;
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mTvMessage = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'mTvMessage'", TextView.class);
    target.mTvFactory = Utils.findRequiredViewAsType(source, R.id.tv_factory, "field 'mTvFactory'", TextView.class);
    target.mTvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'mTvPhone'", TextView.class);
    target.mTvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'mTvType'", TextView.class);
    target.mTvVoltage = Utils.findRequiredViewAsType(source, R.id.tv_voltage, "field 'mTvVoltage'", TextView.class);
    target.mTvDate = Utils.findRequiredViewAsType(source, R.id.tv_date, "field 'mTvDate'", TextView.class);
    target.mTvAddr = Utils.findRequiredViewAsType(source, R.id.tv_addr, "field 'mTvAddr'", TextView.class);
    target.mTvRate = Utils.findRequiredViewAsType(source, R.id.tv_rate, "field 'mTvRate'", TextView.class);
    target.mIvIcon = Utils.findRequiredViewAsType(source, R.id.iv_icon, "field 'mIvIcon'", ImageView.class);
    target.layoutMessage = Utils.findRequiredViewAsType(source, R.id.layout_message, "field 'layoutMessage'", LinearLayout.class);
    target.layoutInfo = Utils.findRequiredViewAsType(source, R.id.layout_info, "field 'layoutInfo'", ConstraintLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_clean, "field 'btnClean' and method 'onViewClicked'");
    target.btnClean = Utils.castView(view, R.id.btn_clean, "field 'btnClean'", Button.class);
    view7f090090 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090359 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_consult, "field 'rlConsult' and method 'onViewClicked'");
    target.rlConsult = Utils.castView(view, R.id.rl_consult, "field 'rlConsult'", RelativeLayout.class);
    view7f09035e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rl_title = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'rl_title'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DiagnosisActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvTitle = null;
    target.mTvMessage = null;
    target.mTvFactory = null;
    target.mTvPhone = null;
    target.mTvType = null;
    target.mTvVoltage = null;
    target.mTvDate = null;
    target.mTvAddr = null;
    target.mTvRate = null;
    target.mIvIcon = null;
    target.layoutMessage = null;
    target.layoutInfo = null;
    target.btnClean = null;
    target.rlBack = null;
    target.rlConsult = null;
    target.rl_title = null;

    view7f090090.setOnClickListener(null);
    view7f090090 = null;
    view7f090359.setOnClickListener(null);
    view7f090359 = null;
    view7f09035e.setOnClickListener(null);
    view7f09035e = null;
  }
}
