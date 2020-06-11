// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.wode_page;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TiXianActivity_ViewBinding implements Unbinder {
  private TiXianActivity target;

  @UiThread
  public TiXianActivity_ViewBinding(TiXianActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TiXianActivity_ViewBinding(TiXianActivity target, View source) {
    this.target = target;

    target.ll1 = Utils.findRequiredViewAsType(source, R.id.ll_1, "field 'll1'", LinearLayout.class);
    target.ivZhifubaoIcon = Utils.findRequiredViewAsType(source, R.id.iv_zhifubao_icon, "field 'ivZhifubaoIcon'", ImageView.class);
    target.ivRightBack = Utils.findRequiredViewAsType(source, R.id.iv_right_back, "field 'ivRightBack'", ImageView.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
    target.ll2 = Utils.findRequiredViewAsType(source, R.id.ll_2, "field 'll2'", ConstraintLayout.class);
    target.tvTixianhuashu = Utils.findRequiredViewAsType(source, R.id.tv_tixianhuashu, "field 'tvTixianhuashu'", TextView.class);
    target.tvRenminbi = Utils.findRequiredViewAsType(source, R.id.tv_renminbi, "field 'tvRenminbi'", TextView.class);
    target.cl3 = Utils.findRequiredViewAsType(source, R.id.cl_3, "field 'cl3'", ConstraintLayout.class);
    target.tvZuiditixian = Utils.findRequiredViewAsType(source, R.id.tv_zuiditixian, "field 'tvZuiditixian'", TextView.class);
    target.etText = Utils.findRequiredViewAsType(source, R.id.et_text, "field 'etText'", EditText.class);
    target.tvTixiankouchu = Utils.findRequiredViewAsType(source, R.id.tv_tixiankouchu, "field 'tvTixiankouchu'", TextView.class);
    target.tvTixian = Utils.findRequiredViewAsType(source, R.id.tv_tixian, "field 'tvTixian'", TextView.class);
    target.showShui = Utils.findRequiredViewAsType(source, R.id.show_shui, "field 'showShui'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TiXianActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ll1 = null;
    target.ivZhifubaoIcon = null;
    target.ivRightBack = null;
    target.viewLine = null;
    target.ll2 = null;
    target.tvTixianhuashu = null;
    target.tvRenminbi = null;
    target.cl3 = null;
    target.tvZuiditixian = null;
    target.etText = null;
    target.tvTixiankouchu = null;
    target.tvTixian = null;
    target.showShui = null;
  }
}
