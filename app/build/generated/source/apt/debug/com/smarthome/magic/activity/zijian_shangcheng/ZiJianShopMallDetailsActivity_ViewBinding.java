// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.zijian_shangcheng;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ZiJianShopMallDetailsActivity_ViewBinding implements Unbinder {
  private ZiJianShopMallDetailsActivity target;

  @UiThread
  public ZiJianShopMallDetailsActivity_ViewBinding(ZiJianShopMallDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ZiJianShopMallDetailsActivity_ViewBinding(ZiJianShopMallDetailsActivity target,
      View source) {
    this.target = target;

    target.rlvList = Utils.findRequiredViewAsType(source, R.id.rlv_list, "field 'rlvList'", RecyclerView.class);
    target.ivDianpuIamge = Utils.findRequiredViewAsType(source, R.id.iv_dianpu_iamge, "field 'ivDianpuIamge'", ImageView.class);
    target.tvDianpu = Utils.findRequiredViewAsType(source, R.id.tv_dianpu, "field 'tvDianpu'", TextView.class);
    target.ivKefuImage = Utils.findRequiredViewAsType(source, R.id.iv_kefu_image, "field 'ivKefuImage'", ImageView.class);
    target.ivShoucangIamge = Utils.findRequiredViewAsType(source, R.id.iv_shoucang_iamge, "field 'ivShoucangIamge'", ImageView.class);
    target.llJiaruGouwuche = Utils.findRequiredViewAsType(source, R.id.ll_jiaru_gouwuche, "field 'llJiaruGouwuche'", LinearLayout.class);
    target.llLijiGoumai = Utils.findRequiredViewAsType(source, R.id.ll_liji_goumai, "field 'llLijiGoumai'", LinearLayout.class);
    target.tvShoucang = Utils.findRequiredViewAsType(source, R.id.tv_shoucang, "field 'tvShoucang'", TextView.class);
    target.tvKefu = Utils.findRequiredViewAsType(source, R.id.tv_kefu, "field 'tvKefu'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ZiJianShopMallDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlvList = null;
    target.ivDianpuIamge = null;
    target.tvDianpu = null;
    target.ivKefuImage = null;
    target.ivShoucangIamge = null;
    target.llJiaruGouwuche = null;
    target.llLijiGoumai = null;
    target.tvShoucang = null;
    target.tvKefu = null;
  }
}
