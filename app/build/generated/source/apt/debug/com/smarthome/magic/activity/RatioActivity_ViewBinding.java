// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RatioActivity_ViewBinding implements Unbinder {
  private RatioActivity target;

  private View view7f090081;

  private View view7f090758;

  private View view7f0901ed;

  private View view7f0901fe;

  private View view7f0901fc;

  private View view7f0901e6;

  private View view7f0901e5;

  private View view7f0901fa;

  private View view7f0907b8;

  private View view7f09079a;

  private View view7f09069b;

  private View view7f090734;

  @UiThread
  public RatioActivity_ViewBinding(RatioActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RatioActivity_ViewBinding(final RatioActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'mTvSave' and method 'onClick'");
    target.mTvSave = Utils.castView(view, R.id.tv_save, "field 'mTvSave'", TextView.class);
    view7f090758 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRlOuterOne = Utils.findRequiredViewAsType(source, R.id.rl_outer_one, "field 'mRlOuterOne'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.item_one, "field 'mItemOne' and method 'onClick'");
    target.mItemOne = Utils.castView(view, R.id.item_one, "field 'mItemOne'", LinearLayout.class);
    view7f0901ed = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRlOuterTwo = Utils.findRequiredViewAsType(source, R.id.rl_outer_two, "field 'mRlOuterTwo'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.item_two, "field 'mItemTwo' and method 'onClick'");
    target.mItemTwo = Utils.castView(view, R.id.item_two, "field 'mItemTwo'", LinearLayout.class);
    view7f0901fe = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRlOuterThree = Utils.findRequiredViewAsType(source, R.id.rl_outer_three, "field 'mRlOuterThree'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.item_three, "field 'mItemThree' and method 'onClick'");
    target.mItemThree = Utils.castView(view, R.id.item_three, "field 'mItemThree'", LinearLayout.class);
    view7f0901fc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRlOuterFour = Utils.findRequiredViewAsType(source, R.id.rl_outer_four, "field 'mRlOuterFour'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.item_four, "field 'mItemFour' and method 'onClick'");
    target.mItemFour = Utils.castView(view, R.id.item_four, "field 'mItemFour'", LinearLayout.class);
    view7f0901e6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRlOuterFive = Utils.findRequiredViewAsType(source, R.id.rl_outer_five, "field 'mRlOuterFive'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.item_five, "field 'mItemFive' and method 'onClick'");
    target.mItemFive = Utils.castView(view, R.id.item_five, "field 'mItemFive'", LinearLayout.class);
    view7f0901e5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRlOuterStop = Utils.findRequiredViewAsType(source, R.id.rl_outer_stop, "field 'mRlOuterStop'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.item_stop, "field 'mItemStop' and method 'onClick'");
    target.mItemStop = Utils.castView(view, R.id.item_stop, "field 'mItemStop'", LinearLayout.class);
    view7f0901fa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_two, "field 'mTvTwo' and method 'onClick'");
    target.mTvTwo = Utils.castView(view, R.id.tv_two, "field 'mTvTwo'", TextView.class);
    view7f0907b8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_three, "field 'mTvThree' and method 'onClick'");
    target.mTvThree = Utils.castView(view, R.id.tv_three, "field 'mTvThree'", TextView.class);
    view7f09079a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_five, "field 'mTvFive' and method 'onClick'");
    target.mTvFive = Utils.castView(view, R.id.tv_five, "field 'mTvFive'", TextView.class);
    view7f09069b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_recovery, "field 'mTvRecovery' and method 'onClick'");
    target.mTvRecovery = Utils.castView(view, R.id.tv_recovery, "field 'mTvRecovery'", TextView.class);
    view7f090734 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mEtSpeedOne = Utils.findRequiredViewAsType(source, R.id.et_speed_one, "field 'mEtSpeedOne'", EditText.class);
    target.mEtFrequencyOne = Utils.findRequiredViewAsType(source, R.id.et_frequency_one, "field 'mEtFrequencyOne'", EditText.class);
    target.mEtSpeedTwo = Utils.findRequiredViewAsType(source, R.id.et_speed_two, "field 'mEtSpeedTwo'", EditText.class);
    target.mEtFrequencyTwo = Utils.findRequiredViewAsType(source, R.id.et_frequency_two, "field 'mEtFrequencyTwo'", EditText.class);
    target.mEtSpeedThree = Utils.findRequiredViewAsType(source, R.id.et_speed_three, "field 'mEtSpeedThree'", EditText.class);
    target.mEtFrequencyThree = Utils.findRequiredViewAsType(source, R.id.et_frequency_three, "field 'mEtFrequencyThree'", EditText.class);
    target.mEtSpeedFour = Utils.findRequiredViewAsType(source, R.id.et_speed_four, "field 'mEtSpeedFour'", EditText.class);
    target.mEtFrequencyFour = Utils.findRequiredViewAsType(source, R.id.et_frequency_four, "field 'mEtFrequencyFour'", EditText.class);
    target.mEtSpeedFive = Utils.findRequiredViewAsType(source, R.id.et_speed_five, "field 'mEtSpeedFive'", EditText.class);
    target.mEtFrequencyFive = Utils.findRequiredViewAsType(source, R.id.et_frequency_five, "field 'mEtFrequencyFive'", EditText.class);
    target.mEtHeat = Utils.findRequiredViewAsType(source, R.id.et_heat, "field 'mEtHeat'", EditText.class);
    target.rlTitle1 = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'rlTitle1'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RatioActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBack = null;
    target.mTvSave = null;
    target.mRlOuterOne = null;
    target.mItemOne = null;
    target.mRlOuterTwo = null;
    target.mItemTwo = null;
    target.mRlOuterThree = null;
    target.mItemThree = null;
    target.mRlOuterFour = null;
    target.mItemFour = null;
    target.mRlOuterFive = null;
    target.mItemFive = null;
    target.mRlOuterStop = null;
    target.mItemStop = null;
    target.mTvTwo = null;
    target.mTvThree = null;
    target.mTvFive = null;
    target.mTvRecovery = null;
    target.mEtSpeedOne = null;
    target.mEtFrequencyOne = null;
    target.mEtSpeedTwo = null;
    target.mEtFrequencyTwo = null;
    target.mEtSpeedThree = null;
    target.mEtFrequencyThree = null;
    target.mEtSpeedFour = null;
    target.mEtFrequencyFour = null;
    target.mEtSpeedFive = null;
    target.mEtFrequencyFive = null;
    target.mEtHeat = null;
    target.rlTitle1 = null;

    view7f090081.setOnClickListener(null);
    view7f090081 = null;
    view7f090758.setOnClickListener(null);
    view7f090758 = null;
    view7f0901ed.setOnClickListener(null);
    view7f0901ed = null;
    view7f0901fe.setOnClickListener(null);
    view7f0901fe = null;
    view7f0901fc.setOnClickListener(null);
    view7f0901fc = null;
    view7f0901e6.setOnClickListener(null);
    view7f0901e6 = null;
    view7f0901e5.setOnClickListener(null);
    view7f0901e5 = null;
    view7f0901fa.setOnClickListener(null);
    view7f0901fa = null;
    view7f0907b8.setOnClickListener(null);
    view7f0907b8 = null;
    view7f09079a.setOnClickListener(null);
    view7f09079a = null;
    view7f09069b.setOnClickListener(null);
    view7f09069b = null;
    view7f090734.setOnClickListener(null);
    view7f090734 = null;
  }
}
