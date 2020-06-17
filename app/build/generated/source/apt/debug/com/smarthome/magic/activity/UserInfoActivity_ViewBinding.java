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

public class UserInfoActivity_ViewBinding implements Unbinder {
  private UserInfoActivity target;

  private View view7f090076;

  private View view7f0901bd;

  private View view7f0901ac;

  private View view7f0901c3;

  private View view7f0901b4;

  private View view7f0901b9;

  private View view7f0901cc;

  private View view7f0901b8;

  private View view7f0901ae;

  private View view7f0901b6;

  private View view7f0901b0;

  private View view7f0901c6;

  @UiThread
  public UserInfoActivity_ViewBinding(UserInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserInfoActivity_ViewBinding(final UserInfoActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.back, "field 'mBack' and method 'onClick'");
    target.mBack = Utils.castView(view, R.id.back, "field 'mBack'", LinearLayout.class);
    view7f090076 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_pic, "field 'mItemPic' and method 'onClick'");
    target.mItemPic = Utils.castView(view, R.id.item_pic, "field 'mItemPic'", LinearLayout.class);
    view7f0901bd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_brand, "field 'mItemBrand' and method 'onClick'");
    target.mItemBrand = Utils.castView(view, R.id.item_brand, "field 'mItemBrand'", LinearLayout.class);
    view7f0901ac = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_region, "field 'mItemRegion' and method 'onClick'");
    target.mItemRegion = Utils.castView(view, R.id.item_region, "field 'mItemRegion'", LinearLayout.class);
    view7f0901c3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_fuel, "field 'mItemFuel' and method 'onClick'");
    target.mItemFuel = Utils.castView(view, R.id.item_fuel, "field 'mItemFuel'", LinearLayout.class);
    view7f0901b4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_number, "field 'mItemNumber' and method 'onClick'");
    target.mItemNumber = Utils.castView(view, R.id.item_number, "field 'mItemNumber'", LinearLayout.class);
    view7f0901b9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_type, "field 'mItemType' and method 'onClick'");
    target.mItemType = Utils.castView(view, R.id.item_type, "field 'mItemType'", LinearLayout.class);
    view7f0901cc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_name, "field 'mItemName' and method 'onClick'");
    target.mItemName = Utils.castView(view, R.id.item_name, "field 'mItemName'", LinearLayout.class);
    view7f0901b8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_contact, "field 'mItemContact' and method 'onClick'");
    target.mItemContact = Utils.castView(view, R.id.item_contact, "field 'mItemContact'", LinearLayout.class);
    view7f0901ae = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_insurance, "field 'mItemInsurance' and method 'onClick'");
    target.mItemInsurance = Utils.castView(view, R.id.item_insurance, "field 'mItemInsurance'", LinearLayout.class);
    view7f0901b6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_date, "field 'mItemDate' and method 'onClick'");
    target.mItemDate = Utils.castView(view, R.id.item_date, "field 'mItemDate'", LinearLayout.class);
    view7f0901b0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_service, "field 'mItemService' and method 'onClick'");
    target.mItemService = Utils.castView(view, R.id.item_service, "field 'mItemService'", LinearLayout.class);
    view7f0901c6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mIvPic = Utils.findRequiredViewAsType(source, R.id.iv_pic, "field 'mIvPic'", ImageView.class);
    target.mTvBrand = Utils.findRequiredViewAsType(source, R.id.tv_brand, "field 'mTvBrand'", TextView.class);
    target.mTvRegion = Utils.findRequiredViewAsType(source, R.id.tv_region, "field 'mTvRegion'", TextView.class);
    target.mTvFuleType = Utils.findRequiredViewAsType(source, R.id.tv_fule_type, "field 'mTvFuleType'", TextView.class);
    target.mTvNumber = Utils.findRequiredViewAsType(source, R.id.tv_model, "field 'mTvNumber'", TextView.class);
    target.mTvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'mTvType'", TextView.class);
    target.mTvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'mTvName'", TextView.class);
    target.mTvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'mTvPhone'", TextView.class);
    target.mTvSafe = Utils.findRequiredViewAsType(source, R.id.tv_safe, "field 'mTvSafe'", TextView.class);
    target.mTvDate = Utils.findRequiredViewAsType(source, R.id.tv_date, "field 'mTvDate'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBack = null;
    target.mItemPic = null;
    target.mItemBrand = null;
    target.mItemRegion = null;
    target.mItemFuel = null;
    target.mItemNumber = null;
    target.mItemType = null;
    target.mItemName = null;
    target.mItemContact = null;
    target.mItemInsurance = null;
    target.mItemDate = null;
    target.mItemService = null;
    target.mIvPic = null;
    target.mTvBrand = null;
    target.mTvRegion = null;
    target.mTvFuleType = null;
    target.mTvNumber = null;
    target.mTvType = null;
    target.mTvName = null;
    target.mTvPhone = null;
    target.mTvSafe = null;
    target.mTvDate = null;

    view7f090076.setOnClickListener(null);
    view7f090076 = null;
    view7f0901bd.setOnClickListener(null);
    view7f0901bd = null;
    view7f0901ac.setOnClickListener(null);
    view7f0901ac = null;
    view7f0901c3.setOnClickListener(null);
    view7f0901c3 = null;
    view7f0901b4.setOnClickListener(null);
    view7f0901b4 = null;
    view7f0901b9.setOnClickListener(null);
    view7f0901b9 = null;
    view7f0901cc.setOnClickListener(null);
    view7f0901cc = null;
    view7f0901b8.setOnClickListener(null);
    view7f0901b8 = null;
    view7f0901ae.setOnClickListener(null);
    view7f0901ae = null;
    view7f0901b6.setOnClickListener(null);
    view7f0901b6 = null;
    view7f0901b0.setOnClickListener(null);
    view7f0901b0 = null;
    view7f0901c6.setOnClickListener(null);
    view7f0901c6 = null;
  }
}
