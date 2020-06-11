// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.dingdan;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QuXiaoDingDanTanCengActivity_ViewBinding implements Unbinder {
  private QuXiaoDingDanTanCengActivity target;

  @UiThread
  public QuXiaoDingDanTanCengActivity_ViewBinding(QuXiaoDingDanTanCengActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public QuXiaoDingDanTanCengActivity_ViewBinding(QuXiaoDingDanTanCengActivity target,
      View source) {
    this.target = target;

    target.constrain = Utils.findRequiredViewAsType(source, R.id.constrain, "field 'constrain'", ConstraintLayout.class);
    target.ivBuxiangmaile = Utils.findRequiredViewAsType(source, R.id.iv_buxiangmaile, "field 'ivBuxiangmaile'", ImageView.class);
    target.rlBuxiangmaile = Utils.findRequiredViewAsType(source, R.id.rl_buxiangmaile, "field 'rlBuxiangmaile'", RelativeLayout.class);
    target.ivChongxinpai = Utils.findRequiredViewAsType(source, R.id.iv_chongxinpai, "field 'ivChongxinpai'", ImageView.class);
    target.rlChongxinpai = Utils.findRequiredViewAsType(source, R.id.rl_chongxinpai, "field 'rlChongxinpai'", RelativeLayout.class);
    target.ivMaijiaquehuo = Utils.findRequiredViewAsType(source, R.id.iv_maijiaquehuo, "field 'ivMaijiaquehuo'", ImageView.class);
    target.rlQuehuo = Utils.findRequiredViewAsType(source, R.id.rl_quehuo, "field 'rlQuehuo'", RelativeLayout.class);
    target.ivMianjiaoyi = Utils.findRequiredViewAsType(source, R.id.iv_mianjiaoyi, "field 'ivMianjiaoyi'", ImageView.class);
    target.rlMianjiaoyi = Utils.findRequiredViewAsType(source, R.id.rl_mianjiaoyi, "field 'rlMianjiaoyi'", RelativeLayout.class);
    target.tvQuedingquxiao = Utils.findRequiredViewAsType(source, R.id.tv_quedingquxiao, "field 'tvQuedingquxiao'", TextView.class);
    target.tvZanbuquxiao = Utils.findRequiredViewAsType(source, R.id.tv_zanbuquxiao, "field 'tvZanbuquxiao'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    QuXiaoDingDanTanCengActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.constrain = null;
    target.ivBuxiangmaile = null;
    target.rlBuxiangmaile = null;
    target.ivChongxinpai = null;
    target.rlChongxinpai = null;
    target.ivMaijiaquehuo = null;
    target.rlQuehuo = null;
    target.ivMianjiaoyi = null;
    target.rlMianjiaoyi = null;
    target.tvQuedingquxiao = null;
    target.tvZanbuquxiao = null;
  }
}
