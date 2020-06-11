// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  private View view7f090267;

  private View view7f09026d;

  private View view7f090264;

  private View view7f09025b;

  private View view7f090258;

  private View view7f09025d;

  private View view7f09026a;

  private View view7f090270;

  private View view7f09025e;

  private View view7f090473;

  private View view7f090352;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(final SettingActivity target, View source) {
    this.target = target;

    View view;
    target.ivHeader = Utils.findRequiredViewAsType(source, R.id.iv_header, "field 'ivHeader'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.layout_header, "field 'layoutHeader' and method 'onViewClicked'");
    target.layoutHeader = Utils.castView(view, R.id.layout_header, "field 'layoutHeader'", LinearLayout.class);
    view7f090267 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvNickname = Utils.findRequiredViewAsType(source, R.id.tv_nickname, "field 'tvNickname'", TextView.class);
    view = Utils.findRequiredView(source, R.id.layout_nickname, "field 'layoutNickname' and method 'onViewClicked'");
    target.layoutNickname = Utils.castView(view, R.id.layout_nickname, "field 'layoutNickname'", LinearLayout.class);
    view7f09026d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvGender = Utils.findRequiredViewAsType(source, R.id.tv_gender, "field 'tvGender'", TextView.class);
    view = Utils.findRequiredView(source, R.id.layout_gender, "field 'layoutGender' and method 'onViewClicked'");
    target.layoutGender = Utils.castView(view, R.id.layout_gender, "field 'layoutGender'", LinearLayout.class);
    view7f090264 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvBirthday = Utils.findRequiredViewAsType(source, R.id.tv_birthday, "field 'tvBirthday'", TextView.class);
    view = Utils.findRequiredView(source, R.id.layout_birthday, "field 'layoutBirthday' and method 'onViewClicked'");
    target.layoutBirthday = Utils.castView(view, R.id.layout_birthday, "field 'layoutBirthday'", LinearLayout.class);
    view7f09025b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_address, "field 'layoutAddress' and method 'onViewClicked'");
    target.layoutAddress = Utils.castView(view, R.id.layout_address, "field 'layoutAddress'", LinearLayout.class);
    view7f090258 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_cash_account, "field 'layoutCashAccount' and method 'onViewClicked'");
    target.layoutCashAccount = Utils.castView(view, R.id.layout_cash_account, "field 'layoutCashAccount'", LinearLayout.class);
    view7f09025d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_login_password, "field 'layoutLoginPassword' and method 'onViewClicked'");
    target.layoutLoginPassword = Utils.castView(view, R.id.layout_login_password, "field 'layoutLoginPassword'", LinearLayout.class);
    view7f09026a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_pay_password, "field 'layoutPayPassword' and method 'onViewClicked'");
    target.layoutPayPassword = Utils.castView(view, R.id.layout_pay_password, "field 'layoutPayPassword'", LinearLayout.class);
    view7f090270 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_clear_cache, "field 'layoutClearCache' and method 'onViewClicked'");
    target.layoutClearCache = Utils.castView(view, R.id.layout_clear_cache, "field 'layoutClearCache'", LinearLayout.class);
    view7f09025e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_exit, "field 'tvExit' and method 'onViewClicked'");
    target.tvExit = Utils.castView(view, R.id.tv_exit, "field 'tvExit'", TextView.class);
    view7f090473 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCache = Utils.findRequiredViewAsType(source, R.id.tv_cache, "field 'tvCache'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'onViewClicked'");
    view7f090352 = view;
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
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivHeader = null;
    target.layoutHeader = null;
    target.tvNickname = null;
    target.layoutNickname = null;
    target.tvGender = null;
    target.layoutGender = null;
    target.tvBirthday = null;
    target.layoutBirthday = null;
    target.layoutAddress = null;
    target.layoutCashAccount = null;
    target.layoutLoginPassword = null;
    target.layoutPayPassword = null;
    target.layoutClearCache = null;
    target.tvExit = null;
    target.tvCache = null;

    view7f090267.setOnClickListener(null);
    view7f090267 = null;
    view7f09026d.setOnClickListener(null);
    view7f09026d = null;
    view7f090264.setOnClickListener(null);
    view7f090264 = null;
    view7f09025b.setOnClickListener(null);
    view7f09025b = null;
    view7f090258.setOnClickListener(null);
    view7f090258 = null;
    view7f09025d.setOnClickListener(null);
    view7f09025d = null;
    view7f09026a.setOnClickListener(null);
    view7f09026a = null;
    view7f090270.setOnClickListener(null);
    view7f090270 = null;
    view7f09025e.setOnClickListener(null);
    view7f09025e = null;
    view7f090473.setOnClickListener(null);
    view7f090473 = null;
    view7f090352.setOnClickListener(null);
    view7f090352 = null;
  }
}
