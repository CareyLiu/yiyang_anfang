// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.wode_page;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyQianBaoActivity_ViewBinding implements Unbinder {
  private MyQianBaoActivity target;

  @UiThread
  public MyQianBaoActivity_ViewBinding(MyQianBaoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyQianBaoActivity_ViewBinding(MyQianBaoActivity target, View source) {
    this.target = target;

    target.rlvList = Utils.findRequiredViewAsType(source, R.id.rlv_list, "field 'rlvList'", RecyclerView.class);
    target.ivBacground = Utils.findRequiredViewAsType(source, R.id.iv_bacground, "field 'ivBacground'", ImageView.class);
    target.tvKetixianjine = Utils.findRequiredViewAsType(source, R.id.tv_ketixianjine, "field 'tvKetixianjine'", TextView.class);
    target.tvKetixianPrice = Utils.findRequiredViewAsType(source, R.id.tv_ketixian_price, "field 'tvKetixianPrice'", TextView.class);
    target.tvKedikou = Utils.findRequiredViewAsType(source, R.id.tv_kedikou, "field 'tvKedikou'", TextView.class);
    target.tvKedikouPrice = Utils.findRequiredViewAsType(source, R.id.tv_kedikou_price, "field 'tvKedikouPrice'", TextView.class);
    target.tvDongjiejinePrice = Utils.findRequiredViewAsType(source, R.id.tv_dongjiejine_price, "field 'tvDongjiejinePrice'", TextView.class);
    target.tvDongjiejine = Utils.findRequiredViewAsType(source, R.id.tv_dongjiejine, "field 'tvDongjiejine'", TextView.class);
    target.llTixian = Utils.findRequiredViewAsType(source, R.id.ll_tixian, "field 'llTixian'", LinearLayout.class);
    target.clBack = Utils.findRequiredViewAsType(source, R.id.cl_back, "field 'clBack'", ConstraintLayout.class);
    target.tvMingxi = Utils.findRequiredViewAsType(source, R.id.tv_mingxi, "field 'tvMingxi'", TextView.class);
    target.tvZhichu = Utils.findRequiredViewAsType(source, R.id.tv_zhichu, "field 'tvZhichu'", TextView.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
  }

  @Override
  @CallSuper
  public void unbind() {
    MyQianBaoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlvList = null;
    target.ivBacground = null;
    target.tvKetixianjine = null;
    target.tvKetixianPrice = null;
    target.tvKedikou = null;
    target.tvKedikouPrice = null;
    target.tvDongjiejinePrice = null;
    target.tvDongjiejine = null;
    target.llTixian = null;
    target.clBack = null;
    target.tvMingxi = null;
    target.tvZhichu = null;
    target.viewLine = null;
  }
}
