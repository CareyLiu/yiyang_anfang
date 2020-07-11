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
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  private View view7f0902ca;

  private View view7f0902d0;

  private View view7f0902c6;

  private View view7f0902bd;

  private View view7f0902ba;

  private View view7f0902bf;

  private View view7f0902cd;

  private View view7f0902d3;

  private View view7f0902c0;

  private View view7f090685;

  private View view7f090516;

  private View view7f09032a;

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
    view7f0902ca = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvNickname = Utils.findRequiredViewAsType(source, R.id.tv_nickname, "field 'tvNickname'", TextView.class);
    view = Utils.findRequiredView(source, R.id.layout_nickname, "field 'layoutNickname' and method 'onViewClicked'");
    target.layoutNickname = Utils.castView(view, R.id.layout_nickname, "field 'layoutNickname'", LinearLayout.class);
    view7f0902d0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvGender = Utils.findRequiredViewAsType(source, R.id.tv_gender, "field 'tvGender'", TextView.class);
    view = Utils.findRequiredView(source, R.id.layout_gender, "field 'layoutGender' and method 'onViewClicked'");
    target.layoutGender = Utils.castView(view, R.id.layout_gender, "field 'layoutGender'", LinearLayout.class);
    view7f0902c6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvBirthday = Utils.findRequiredViewAsType(source, R.id.tv_birthday, "field 'tvBirthday'", TextView.class);
    view = Utils.findRequiredView(source, R.id.layout_birthday, "field 'layoutBirthday' and method 'onViewClicked'");
    target.layoutBirthday = Utils.castView(view, R.id.layout_birthday, "field 'layoutBirthday'", LinearLayout.class);
    view7f0902bd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_address, "field 'layoutAddress' and method 'onViewClicked'");
    target.layoutAddress = Utils.castView(view, R.id.layout_address, "field 'layoutAddress'", LinearLayout.class);
    view7f0902ba = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_cash_account, "field 'layoutCashAccount' and method 'onViewClicked'");
    target.layoutCashAccount = Utils.castView(view, R.id.layout_cash_account, "field 'layoutCashAccount'", LinearLayout.class);
    view7f0902bf = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_login_password, "field 'layoutLoginPassword' and method 'onViewClicked'");
    target.layoutLoginPassword = Utils.castView(view, R.id.layout_login_password, "field 'layoutLoginPassword'", LinearLayout.class);
    view7f0902cd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_pay_password, "field 'layoutPayPassword' and method 'onViewClicked'");
    target.layoutPayPassword = Utils.castView(view, R.id.layout_pay_password, "field 'layoutPayPassword'", LinearLayout.class);
    view7f0902d3 = view;
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
    view = Utils.findRequiredView(source, R.id.tv_exit, "field 'tvExit' and method 'onViewClicked'");
    target.tvExit = Utils.castView(view, R.id.tv_exit, "field 'tvExit'", TextView.class);
    view7f090685 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCache = Utils.findRequiredViewAsType(source, R.id.tv_cache, "field 'tvCache'", TextView.class);
    target.shebeiPeiwang = Utils.findRequiredViewAsType(source, R.id.shebei_peiwang, "field 'shebeiPeiwang'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090516 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvZhifubaoMing = Utils.findRequiredViewAsType(source, R.id.tv_zhifubao_ming, "field 'tvZhifubaoMing'", TextView.class);
    target.tvWeixinMing = Utils.findRequiredViewAsType(source, R.id.tv_weixin_ming, "field 'tvWeixinMing'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_shezhi_weixin, "field 'llShezhiWeixin' and method 'onViewClicked'");
    target.llShezhiWeixin = Utils.castView(view, R.id.ll_shezhi_weixin, "field 'llShezhiWeixin'", LinearLayout.class);
    view7f09032a = view;
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
    target.shebeiPeiwang = null;
    target.rlBack = null;
    target.tvZhifubaoMing = null;
    target.tvWeixinMing = null;
    target.llShezhiWeixin = null;

    view7f0902ca.setOnClickListener(null);
    view7f0902ca = null;
    view7f0902d0.setOnClickListener(null);
    view7f0902d0 = null;
    view7f0902c6.setOnClickListener(null);
    view7f0902c6 = null;
    view7f0902bd.setOnClickListener(null);
    view7f0902bd = null;
    view7f0902ba.setOnClickListener(null);
    view7f0902ba = null;
    view7f0902bf.setOnClickListener(null);
    view7f0902bf = null;
    view7f0902cd.setOnClickListener(null);
    view7f0902cd = null;
    view7f0902d3.setOnClickListener(null);
    view7f0902d3 = null;
    view7f0902c0.setOnClickListener(null);
    view7f0902c0 = null;
    view7f090685.setOnClickListener(null);
    view7f090685 = null;
    view7f090516.setOnClickListener(null);
    view7f090516 = null;
    view7f09032a.setOnClickListener(null);
    view7f09032a = null;
  }
}
