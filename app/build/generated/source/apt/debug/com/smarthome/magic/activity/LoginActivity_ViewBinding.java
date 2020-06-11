// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f09052d;

  private View view7f090087;

  private View view7f090484;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.mEtPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'mEtPhone'", EditText.class);
    target.mEtPwdCode = Utils.findRequiredViewAsType(source, R.id.et_pwd_code, "field 'mEtPwdCode'", EditText.class);
    target.mGetCode = Utils.findRequiredViewAsType(source, R.id.get_code, "field 'mGetCode'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_switch, "field 'mTvSwitch' and method 'onClick'");
    target.mTvSwitch = Utils.castView(view, R.id.tv_switch, "field 'mTvSwitch'", TextView.class);
    view7f09052d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_login, "field 'mBtLogin' and method 'onClick'");
    target.mBtLogin = Utils.castView(view, R.id.bt_login, "field 'mBtLogin'", Button.class);
    view7f090087 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'mTvGetCode' and method 'onClick'");
    target.mTvGetCode = Utils.castView(view, R.id.tv_get_code, "field 'mTvGetCode'", TextView.class);
    view7f090484 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.imageView3 = Utils.findRequiredViewAsType(source, R.id.imageView3, "field 'imageView3'", ImageView.class);
    target.imageView4 = Utils.findRequiredViewAsType(source, R.id.imageView4, "field 'imageView4'", ImageView.class);
    target.view = Utils.findRequiredViewAsType(source, R.id.view, "field 'view'", ImageView.class);
    target.imageView7 = Utils.findRequiredViewAsType(source, R.id.imageView7, "field 'imageView7'", ImageView.class);
    target.imageView10 = Utils.findRequiredViewAsType(source, R.id.imageView10, "field 'imageView10'", ImageView.class);
    target.tvYinsi = Utils.findRequiredViewAsType(source, R.id.tv_yinsi, "field 'tvYinsi'", TextView.class);
    target.tvYonghushiyong = Utils.findRequiredViewAsType(source, R.id.tv_yonghushiyong, "field 'tvYonghushiyong'", TextView.class);
    target.imageView9 = Utils.findRequiredViewAsType(source, R.id.imageView9, "field 'imageView9'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEtPhone = null;
    target.mEtPwdCode = null;
    target.mGetCode = null;
    target.mTvSwitch = null;
    target.mBtLogin = null;
    target.mTvGetCode = null;
    target.imageView3 = null;
    target.imageView4 = null;
    target.view = null;
    target.imageView7 = null;
    target.imageView10 = null;
    target.tvYinsi = null;
    target.tvYonghushiyong = null;
    target.imageView9 = null;

    view7f09052d.setOnClickListener(null);
    view7f09052d = null;
    view7f090087.setOnClickListener(null);
    view7f090087 = null;
    view7f090484.setOnClickListener(null);
    view7f090484 = null;
  }
}
