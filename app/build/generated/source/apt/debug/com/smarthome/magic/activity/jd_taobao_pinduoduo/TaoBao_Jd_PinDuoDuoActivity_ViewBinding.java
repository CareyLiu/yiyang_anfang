// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.jd_taobao_pinduoduo;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.view.magicindicator.MagicIndicator;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaoBao_Jd_PinDuoDuoActivity_ViewBinding implements Unbinder {
  private TaoBao_Jd_PinDuoDuoActivity target;

  @UiThread
  public TaoBao_Jd_PinDuoDuoActivity_ViewBinding(TaoBao_Jd_PinDuoDuoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaoBao_Jd_PinDuoDuoActivity_ViewBinding(TaoBao_Jd_PinDuoDuoActivity target, View source) {
    this.target = target;

    target.ivJd = Utils.findRequiredViewAsType(source, R.id.iv_jd, "field 'ivJd'", ImageView.class);
    target.ivPdd = Utils.findRequiredViewAsType(source, R.id.iv_pdd, "field 'ivPdd'", ImageView.class);
    target.ivTaobao = Utils.findRequiredViewAsType(source, R.id.iv_taobao, "field 'ivTaobao'", ImageView.class);
    target.magicIndicator4 = Utils.findRequiredViewAsType(source, R.id.magic_indicator4, "field 'magicIndicator4'", MagicIndicator.class);
    target.banner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'banner'", Banner.class);
    target.llTag = Utils.findRequiredViewAsType(source, R.id.ll_tag, "field 'llTag'", LinearLayout.class);
    target.collapsingToolbar = Utils.findRequiredViewAsType(source, R.id.collapsing_toolbar, "field 'collapsingToolbar'", CollapsingToolbarLayout.class);
    target.appbar = Utils.findRequiredViewAsType(source, R.id.appbar, "field 'appbar'", AppBarLayout.class);
    target.llItit = Utils.findRequiredViewAsType(source, R.id.ll_itit, "field 'llItit'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaoBao_Jd_PinDuoDuoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivJd = null;
    target.ivPdd = null;
    target.ivTaobao = null;
    target.magicIndicator4 = null;
    target.banner = null;
    target.llTag = null;
    target.collapsingToolbar = null;
    target.appbar = null;
    target.llItit = null;
  }
}
