// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.gouwuche;

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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GouWuCheActivity_ViewBinding implements Unbinder {
  private GouWuCheActivity target;

  @UiThread
  public GouWuCheActivity_ViewBinding(GouWuCheActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GouWuCheActivity_ViewBinding(GouWuCheActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.srLSmart = Utils.findRequiredViewAsType(source, R.id.srL_smart, "field 'srLSmart'", SmartRefreshLayout.class);
    target.ivChooseAll = Utils.findRequiredViewAsType(source, R.id.iv_choose_all, "field 'ivChooseAll'", ImageView.class);
    target.llJiesuan = Utils.findRequiredViewAsType(source, R.id.ll_jiesuan, "field 'llJiesuan'", LinearLayout.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.llShanchu = Utils.findRequiredViewAsType(source, R.id.ll_shanchu, "field 'llShanchu'", LinearLayout.class);
    target.clJiesuan = Utils.findRequiredViewAsType(source, R.id.cl_jiesuan, "field 'clJiesuan'", ConstraintLayout.class);
    target.tvJiesuanJine = Utils.findRequiredViewAsType(source, R.id.tv_jiesuan_jine, "field 'tvJiesuanJine'", TextView.class);
    target.tvQuanxuan = Utils.findRequiredViewAsType(source, R.id.tv_quanxuan, "field 'tvQuanxuan'", TextView.class);
    target.ivEmpty = Utils.findRequiredViewAsType(source, R.id.iv_empty, "field 'ivEmpty'", ImageView.class);
    target.emptyView = Utils.findRequiredViewAsType(source, R.id.empty_view, "field 'emptyView'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GouWuCheActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.srLSmart = null;
    target.ivChooseAll = null;
    target.llJiesuan = null;
    target.tvPrice = null;
    target.llShanchu = null;
    target.clJiesuan = null;
    target.tvJiesuanJine = null;
    target.tvQuanxuan = null;
    target.ivEmpty = null;
    target.emptyView = null;
  }
}
