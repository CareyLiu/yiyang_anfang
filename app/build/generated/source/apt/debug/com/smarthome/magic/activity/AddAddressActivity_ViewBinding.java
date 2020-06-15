// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddAddressActivity_ViewBinding implements Unbinder {
  private AddAddressActivity target;

  private View view7f090359;

  private View view7f09051a;

  private View view7f090505;

  @UiThread
  public AddAddressActivity_ViewBinding(AddAddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddAddressActivity_ViewBinding(final AddAddressActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090359 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'tvSave' and method 'onViewClicked'");
    target.tvSave = Utils.castView(view, R.id.tv_save, "field 'tvSave'", TextView.class);
    view7f09051a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", EditText.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_region, "field 'tvRegion' and method 'onViewClicked'");
    target.tvRegion = Utils.castView(view, R.id.tv_region, "field 'tvRegion'", TextView.class);
    view7f090505 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etAddress = Utils.findRequiredViewAsType(source, R.id.et_address, "field 'etAddress'", EditText.class);
    target.swDefault = Utils.findRequiredViewAsType(source, R.id.sw_default, "field 'swDefault'", Switch.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.rl1 = Utils.findRequiredViewAsType(source, R.id.rl_1, "field 'rl1'", RelativeLayout.class);
    target.tvDelete = Utils.findRequiredViewAsType(source, R.id.tv_delete, "field 'tvDelete'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddAddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.tvSave = null;
    target.etName = null;
    target.etPhone = null;
    target.tvRegion = null;
    target.etAddress = null;
    target.swDefault = null;
    target.tvTitle = null;
    target.rl1 = null;
    target.tvDelete = null;

    view7f090359.setOnClickListener(null);
    view7f090359 = null;
    view7f09051a.setOnClickListener(null);
    view7f09051a = null;
    view7f090505.setOnClickListener(null);
    view7f090505 = null;
  }
}
