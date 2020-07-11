// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.xin_tuanyou;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TuanYouList_ViewBinding implements Unbinder {
  private TuanYouList target;

  private View view7f0906da;

  private View view7f0907e0;

  private View view7f0907fc;

  private View view7f090614;

  private View view7f0900da;

  private View view7f0900e8;

  private View view7f0900ea;

  private View view7f0900d2;

  private View view7f0900e3;

  @UiThread
  public TuanYouList_ViewBinding(TuanYouList target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TuanYouList_ViewBinding(final TuanYouList target, View source) {
    this.target = target;

    View view;
    target.rlvList = Utils.findRequiredViewAsType(source, R.id.rlv_list, "field 'rlvList'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tv_km, "field 'tvKm' and method 'onViewClicked'");
    target.tvKm = Utils.castView(view, R.id.tv_km, "field 'tvKm'", TextView.class);
    view7f0906da = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_youhao, "field 'tvYouhao' and method 'onViewClicked'");
    target.tvYouhao = Utils.castView(view, R.id.tv_youhao, "field 'tvYouhao'", TextView.class);
    view7f0907e0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhinengpaixu, "field 'tvZhinengpaixu' and method 'onViewClicked'");
    target.tvZhinengpaixu = Utils.castView(view, R.id.tv_zhinengpaixu, "field 'tvZhinengpaixu'", TextView.class);
    view7f0907fc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_all_pinpai, "field 'tvAllPinpai' and method 'onViewClicked'");
    target.tvAllPinpai = Utils.castView(view, R.id.tv_all_pinpai, "field 'tvAllPinpai'", TextView.class);
    view7f090614 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rl1 = Utils.findRequiredViewAsType(source, R.id.rl_1, "field 'rl1'", RelativeLayout.class);
    target.ivKmDown = Utils.findRequiredViewAsType(source, R.id.iv_km_down, "field 'ivKmDown'", ImageView.class);
    target.ivYouhaoDown = Utils.findRequiredViewAsType(source, R.id.iv_youhao_down, "field 'ivYouhaoDown'", ImageView.class);
    target.ivPaixuDown = Utils.findRequiredViewAsType(source, R.id.iv_paixu_down, "field 'ivPaixuDown'", ImageView.class);
    target.ivAllPinpaiDown = Utils.findRequiredViewAsType(source, R.id.iv_all_pinpai_down, "field 'ivAllPinpaiDown'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.cl_km, "field 'clKm' and method 'onViewClicked'");
    target.clKm = Utils.castView(view, R.id.cl_km, "field 'clKm'", ConstraintLayout.class);
    view7f0900da = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cl_youhao, "field 'clYouhao' and method 'onViewClicked'");
    target.clYouhao = Utils.castView(view, R.id.cl_youhao, "field 'clYouhao'", ConstraintLayout.class);
    view7f0900e8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cl_zhinengpaixu, "field 'clZhinengpaixu' and method 'onViewClicked'");
    target.clZhinengpaixu = Utils.castView(view, R.id.cl_zhinengpaixu, "field 'clZhinengpaixu'", ConstraintLayout.class);
    view7f0900ea = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cl_all_pinpai, "field 'clAllPinpai' and method 'onViewClicked'");
    target.clAllPinpai = Utils.castView(view, R.id.cl_all_pinpai, "field 'clAllPinpai'", ConstraintLayout.class);
    view7f0900d2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cl_shaming, "field 'clShaming' and method 'onViewClicked'");
    target.clShaming = Utils.castView(view, R.id.cl_shaming, "field 'clShaming'", ConstraintLayout.class);
    view7f0900e3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rlvKmList = Utils.findRequiredViewAsType(source, R.id.rlv_km_list, "field 'rlvKmList'", RecyclerView.class);
    target.rlvYouhaoList = Utils.findRequiredViewAsType(source, R.id.rlv_youhao_list, "field 'rlvYouhaoList'", RecyclerView.class);
    target.rlvPaixuList = Utils.findRequiredViewAsType(source, R.id.rlv_paixu_list, "field 'rlvPaixuList'", RecyclerView.class);
    target.rlvPinpaiList = Utils.findRequiredViewAsType(source, R.id.rlv_pinpai_list, "field 'rlvPinpaiList'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.viewClPinpai = Utils.findRequiredView(source, R.id.view_cl_pinpai, "field 'viewClPinpai'");
    target.ivSelectImg = Utils.findRequiredViewAsType(source, R.id.iv_select_img, "field 'ivSelectImg'", ImageView.class);
    target.llQueren = Utils.findRequiredViewAsType(source, R.id.ll_queren, "field 'llQueren'", LinearLayout.class);
    target.clPinpaiErji = Utils.findRequiredViewAsType(source, R.id.cl_pinpai_erji, "field 'clPinpaiErji'", ConstraintLayout.class);
    target.ivOrder = Utils.findRequiredViewAsType(source, R.id.iv_order, "field 'ivOrder'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TuanYouList target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlvList = null;
    target.tvKm = null;
    target.tvYouhao = null;
    target.tvZhinengpaixu = null;
    target.tvAllPinpai = null;
    target.rl1 = null;
    target.ivKmDown = null;
    target.ivYouhaoDown = null;
    target.ivPaixuDown = null;
    target.ivAllPinpaiDown = null;
    target.clKm = null;
    target.clYouhao = null;
    target.clZhinengpaixu = null;
    target.clAllPinpai = null;
    target.clShaming = null;
    target.rlvKmList = null;
    target.rlvYouhaoList = null;
    target.rlvPaixuList = null;
    target.rlvPinpaiList = null;
    target.refreshLayout = null;
    target.viewClPinpai = null;
    target.ivSelectImg = null;
    target.llQueren = null;
    target.clPinpaiErji = null;
    target.ivOrder = null;

    view7f0906da.setOnClickListener(null);
    view7f0906da = null;
    view7f0907e0.setOnClickListener(null);
    view7f0907e0 = null;
    view7f0907fc.setOnClickListener(null);
    view7f0907fc = null;
    view7f090614.setOnClickListener(null);
    view7f090614 = null;
    view7f0900da.setOnClickListener(null);
    view7f0900da = null;
    view7f0900e8.setOnClickListener(null);
    view7f0900e8 = null;
    view7f0900ea.setOnClickListener(null);
    view7f0900ea = null;
    view7f0900d2.setOnClickListener(null);
    view7f0900d2 = null;
    view7f0900e3.setOnClickListener(null);
    view7f0900e3 = null;
  }
}
