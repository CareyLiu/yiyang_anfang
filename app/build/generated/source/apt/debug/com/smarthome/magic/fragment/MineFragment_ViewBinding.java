// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundRelativeLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarthome.magic.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineFragment_ViewBinding implements Unbinder {
  private MineFragment target;

  private View view7f09035f;

  private View view7f0904ec;

  private View view7f090248;

  private View view7f09020f;

  private View view7f090544;

  private View view7f090543;

  private View view7f0904ad;

  private View view7f0904ae;

  private View view7f0905ab;

  private View view7f0905ac;

  private View view7f0904cd;

  private View view7f09022f;

  private View view7f090585;

  private View view7f090390;

  private View view7f090486;

  private View view7f09043e;

  private View view7f0901ea;

  private View view7f0901fe;

  private View view7f0901fd;

  private View view7f09046c;

  private View view7f090200;

  private View view7f090239;

  private View view7f090501;

  private View view7f090202;

  private View view7f090387;

  private View view7f090492;

  private View view7f0901f3;

  private View view7f0901f4;

  private View view7f09045d;

  private View view7f09022c;

  private View view7f0904e1;

  private View view7f09025e;

  private View view7f090545;

  private View view7f09023c;

  private View view7f090388;

  private View view7f0905aa;

  private View view7f090261;

  private View view7f090225;

  private View view7f0904cf;

  private View view7f0901f6;

  private View view7f09045f;

  private View view7f09024d;

  private View view7f09058a;

  private View view7f09025f;

  private View view7f090392;

  private View view7f090256;

  private View view7f090571;

  private View view7f0901ff;

  private View view7f0901e4;

  private View view7f0905c2;

  private View view7f090385;

  private View view7f0902ba;

  private View view7f0902a4;

  private View view7f0902af;

  private View view7f09046f;

  private View view7f090470;

  private View view7f090475;

  private View view7f090471;

  @UiThread
  public MineFragment_ViewBinding(final MineFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.riv_image, "field 'rivImage' and method 'onViewClicked'");
    target.rivImage = Utils.castView(view, R.id.riv_image, "field 'rivImage'", CircleImageView.class);
    view7f09035f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_name, "field 'tvName' and method 'onViewClicked'");
    target.tvName = Utils.castView(view, R.id.tv_name, "field 'tvName'", TextView.class);
    view7f0904ec = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_shezhi, "field 'ivShezhi' and method 'onViewClicked'");
    target.ivShezhi = Utils.castView(view, R.id.iv_shezhi, "field 'ivShezhi'", ImageView.class);
    view7f090248 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_gouwuche, "field 'ivGouwuche' and method 'onViewClicked'");
    target.ivGouwuche = Utils.castView(view, R.id.iv_gouwuche, "field 'ivGouwuche'", ImageView.class);
    view7f09020f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shoucangjia_number, "field 'tvShoucangjiaNumber' and method 'onViewClicked'");
    target.tvShoucangjiaNumber = Utils.castView(view, R.id.tv_shoucangjia_number, "field 'tvShoucangjiaNumber'", TextView.class);
    view7f090544 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shoucangjia, "field 'tvShoucangjia' and method 'onViewClicked'");
    target.tvShoucangjia = Utils.castView(view, R.id.tv_shoucangjia, "field 'tvShoucangjia'", TextView.class);
    view7f090543 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_guanzhu_dianpu, "field 'tvGuanzhuDianpu' and method 'onViewClicked'");
    target.tvGuanzhuDianpu = Utils.castView(view, R.id.tv_guanzhu_dianpu, "field 'tvGuanzhuDianpu'", TextView.class);
    view7f0904ad = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_guanzhu_dianpu_num, "field 'tvGuanzhuDianpuNum' and method 'onViewClicked'");
    target.tvGuanzhuDianpuNum = Utils.castView(view, R.id.tv_guanzhu_dianpu_num, "field 'tvGuanzhuDianpuNum'", TextView.class);
    view7f0904ae = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_jifen, "field 'tvZhanghuJifen' and method 'onViewClicked'");
    target.tvZhanghuJifen = Utils.castView(view, R.id.tv_zhanghu_jifen, "field 'tvZhanghuJifen'", TextView.class);
    view7f0905ab = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_jifen_number, "field 'tvZhanghuJifenNumber' and method 'onViewClicked'");
    target.tvZhanghuJifenNumber = Utils.castView(view, R.id.tv_zhanghu_jifen_number, "field 'tvZhanghuJifenNumber'", TextView.class);
    view7f0905ac = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_kajuan, "field 'tvKajuan' and method 'onViewClicked'");
    target.tvKajuan = Utils.castView(view, R.id.tv_kajuan, "field 'tvKajuan'", TextView.class);
    view7f0904cd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_mine_icon_qianbao, "field 'ivMineIconQianbao' and method 'onViewClicked'");
    target.ivMineIconQianbao = Utils.castView(view, R.id.iv_mine_icon_qianbao, "field 'ivMineIconQianbao'", ImageView.class);
    view7f09022f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_wodeqianbao, "field 'tvWodeqianbao' and method 'onViewClicked'");
    target.tvWodeqianbao = Utils.castView(view, R.id.tv_wodeqianbao, "field 'tvWodeqianbao'", TextView.class);
    view7f090585 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_qianbao, "field 'rlvQianbao' and method 'onViewClicked'");
    target.rlvQianbao = Utils.castView(view, R.id.rlv_qianbao, "field 'rlvQianbao'", RoundRelativeLayout.class);
    view7f090390 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_dingdan, "field 'tvDingdan' and method 'onViewClicked'");
    target.tvDingdan = Utils.castView(view, R.id.tv_dingdan, "field 'tvDingdan'", TextView.class);
    view7f090486 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_all, "field 'tvAll' and method 'onViewClicked'");
    target.tvAll = Utils.castView(view, R.id.tv_all, "field 'tvAll'", TextView.class);
    view7f09043e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view7f0901ea = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daifukuan, "field 'ivDaifukuan' and method 'onViewClicked'");
    target.ivDaifukuan = Utils.castView(view, R.id.iv_daifukuan, "field 'ivDaifukuan'", ImageView.class);
    view7f0901fe = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daifahuo, "field 'ivDaifahuo' and method 'onViewClicked'");
    target.ivDaifahuo = Utils.castView(view, R.id.iv_daifahuo, "field 'ivDaifahuo'", ImageView.class);
    view7f0901fd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daifahuo, "field 'tvDaifahuo' and method 'onViewClicked'");
    target.tvDaifahuo = Utils.castView(view, R.id.tv_daifahuo, "field 'tvDaifahuo'", TextView.class);
    view7f09046c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daishouhuo, "field 'ivDaishouhuo' and method 'onViewClicked'");
    target.ivDaishouhuo = Utils.castView(view, R.id.iv_daishouhuo, "field 'ivDaishouhuo'", ImageView.class);
    view7f090200 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_pingjia, "field 'ivPingjia' and method 'onViewClicked'");
    target.ivPingjia = Utils.castView(view, R.id.iv_pingjia, "field 'ivPingjia'", ImageView.class);
    view7f090239 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_pingjia, "field 'tvPingjia' and method 'onViewClicked'");
    target.tvPingjia = Utils.castView(view, R.id.tv_pingjia, "field 'tvPingjia'", TextView.class);
    view7f090501 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daodian, "field 'ivDaodian' and method 'onViewClicked'");
    target.ivDaodian = Utils.castView(view, R.id.iv_daodian, "field 'ivDaodian'", ImageView.class);
    view7f090202 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_dingdan, "field 'rlvDingdan' and method 'onViewClicked'");
    target.rlvDingdan = Utils.castView(view, R.id.rlv_dingdan, "field 'rlvDingdan'", RoundRelativeLayout.class);
    view7f090387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_ershou_che, "field 'tvErshouChe' and method 'onViewClicked'");
    target.tvErshouChe = Utils.castView(view, R.id.tv_ershou_che, "field 'tvErshouChe'", TextView.class);
    view7f090492 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_che_yuyue, "field 'ivCheYuyue' and method 'onViewClicked'");
    target.ivCheYuyue = Utils.castView(view, R.id.iv_che_yuyue, "field 'ivCheYuyue'", ImageView.class);
    view7f0901f3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_chejindu, "field 'ivChejindu' and method 'onViewClicked'");
    target.ivChejindu = Utils.castView(view, R.id.iv_chejindu, "field 'ivChejindu'", ImageView.class);
    view7f0901f4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_chejindu, "field 'tvChejindu' and method 'onViewClicked'");
    target.tvChejindu = Utils.castView(view, R.id.tv_chejindu, "field 'tvChejindu'", TextView.class);
    view7f09045d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_maiche_jindu, "field 'ivMaicheJindu' and method 'onViewClicked'");
    target.ivMaicheJindu = Utils.castView(view, R.id.iv_maiche_jindu, "field 'ivMaicheJindu'", ImageView.class);
    view7f09022c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_maiche_jindu, "field 'tvMaicheJindu' and method 'onViewClicked'");
    target.tvMaicheJindu = Utils.castView(view, R.id.tv_maiche_jindu, "field 'tvMaicheJindu'", TextView.class);
    view7f0904e1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_xiaofeijilu, "field 'ivXiaofeijilu' and method 'onViewClicked'");
    target.ivXiaofeijilu = Utils.castView(view, R.id.iv_xiaofeijilu, "field 'ivXiaofeijilu'", ImageView.class);
    view7f09025e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shouhou, "field 'tvShouhou' and method 'onViewClicked'");
    target.tvShouhou = Utils.castView(view, R.id.tv_shouhou, "field 'tvShouhou'", TextView.class);
    view7f090545 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_quanbu_dingdan, "field 'ivQuanbuDingdan' and method 'onViewClicked'");
    target.ivQuanbuDingdan = Utils.castView(view, R.id.iv_quanbu_dingdan, "field 'ivQuanbuDingdan'", ImageView.class);
    view7f09023c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_ershouche, "field 'rlvErshouche' and method 'onViewClicked'");
    target.rlvErshouche = Utils.castView(view, R.id.rlv_ershouche, "field 'rlvErshouche'", RoundRelativeLayout.class);
    view7f090388 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_chongzhi, "field 'tvZhanghuChongzhi' and method 'onViewClicked'");
    target.tvZhanghuChongzhi = Utils.castView(view, R.id.tv_zhanghu_chongzhi, "field 'tvZhanghuChongzhi'", TextView.class);
    view7f0905aa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_zhanghu_chongzhi, "field 'ivZhanghuChongzhi' and method 'onViewClicked'");
    target.ivZhanghuChongzhi = Utils.castView(view, R.id.iv_zhanghu_chongzhi, "field 'ivZhanghuChongzhi'", ImageView.class);
    view7f090261 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_kapianchongzhi, "field 'ivKapianchongzhi' and method 'onViewClicked'");
    target.ivKapianchongzhi = Utils.castView(view, R.id.iv_kapianchongzhi, "field 'ivKapianchongzhi'", ImageView.class);
    view7f090225 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_kapianchongzhi, "field 'tvKapianchongzhi' and method 'onViewClicked'");
    target.tvKapianchongzhi = Utils.castView(view, R.id.tv_kapianchongzhi, "field 'tvKapianchongzhi'", TextView.class);
    view7f0904cf = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_chongzhi_jilu, "field 'ivChongzhiJilu' and method 'onViewClicked'");
    target.ivChongzhiJilu = Utils.castView(view, R.id.iv_chongzhi_jilu, "field 'ivChongzhiJilu'", ImageView.class);
    view7f0901f6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_chongzhi_jilu, "field 'tvChongzhiJilu' and method 'onViewClicked'");
    target.tvChongzhiJilu = Utils.castView(view, R.id.tv_chongzhi_jilu, "field 'tvChongzhiJilu'", TextView.class);
    view7f09045f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_shouhou_fuwu, "field 'ivShouhouFuwu' and method 'onViewClicked'");
    target.ivShouhouFuwu = Utils.castView(view, R.id.iv_shouhou_fuwu, "field 'ivShouhouFuwu'", ImageView.class);
    view7f09024d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_xiaofeijilu, "field 'tvXiaofeijilu' and method 'onViewClicked'");
    target.tvXiaofeijilu = Utils.castView(view, R.id.tv_xiaofeijilu, "field 'tvXiaofeijilu'", TextView.class);
    view7f09058a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_xiche_erwei, "field 'ivXicheErwei' and method 'onViewClicked'");
    target.ivXicheErwei = Utils.castView(view, R.id.iv_xiche_erwei, "field 'ivXicheErwei'", ImageView.class);
    view7f09025f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_xiche, "field 'rlvXiche' and method 'onViewClicked'");
    target.rlvXiche = Utils.castView(view, R.id.rlv_xiche, "field 'rlvXiche'", RoundRelativeLayout.class);
    view7f090392 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_tuiguangma, "field 'ivTuiguangma' and method 'onViewClicked'");
    target.ivTuiguangma = Utils.castView(view, R.id.iv_tuiguangma, "field 'ivTuiguangma'", ImageView.class);
    view7f090256 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_tuiguangma, "field 'tvTuiguangma' and method 'onViewClicked'");
    target.tvTuiguangma = Utils.castView(view, R.id.tv_tuiguangma, "field 'tvTuiguangma'", TextView.class);
    view7f090571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_dailishang, "field 'ivDailishang' and method 'onViewClicked'");
    target.ivDailishang = Utils.castView(view, R.id.iv_dailishang, "field 'ivDailishang'", ImageView.class);
    view7f0901ff = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_about_us, "field 'ivAboutUs' and method 'onViewClicked'");
    target.ivAboutUs = Utils.castView(view, R.id.iv_about_us, "field 'ivAboutUs'", ImageView.class);
    view7f0901e4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.view, "field 'view' and method 'onViewClicked'");
    target.view = view;
    view7f0905c2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_about_us, "field 'rlvAboutUs' and method 'onViewClicked'");
    target.rlvAboutUs = Utils.castView(view, R.id.rlv_about_us, "field 'rlvAboutUs'", RoundRelativeLayout.class);
    view7f090385 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.ivQianbaoEnter = Utils.findRequiredViewAsType(source, R.id.iv_qianbao_enter, "field 'ivQianbaoEnter'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_shoucangjia, "field 'llShoucangjia' and method 'onViewClicked'");
    target.llShoucangjia = Utils.castView(view, R.id.ll_shoucangjia, "field 'llShoucangjia'", LinearLayout.class);
    view7f0902ba = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_guanzhudianpu, "field 'llGuanzhudianpu' and method 'onViewClicked'");
    target.llGuanzhudianpu = Utils.castView(view, R.id.ll_guanzhudianpu, "field 'llGuanzhudianpu'", LinearLayout.class);
    view7f0902a4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llZhanghujifen = Utils.findRequiredViewAsType(source, R.id.ll_zhanghujifen, "field 'llZhanghujifen'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ll_kaquan, "field 'llKaquan' and method 'onViewClicked'");
    target.llKaquan = Utils.castView(view, R.id.ll_kaquan, "field 'llKaquan'", LinearLayout.class);
    view7f0902af = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvKajuanNumber = Utils.findRequiredViewAsType(source, R.id.tv_kajuan_number, "field 'tvKajuanNumber'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_daifukuan, "field 'tvDaifukuan' and method 'onViewClicked'");
    target.tvDaifukuan = Utils.castView(view, R.id.tv_daifukuan, "field 'tvDaifukuan'", TextView.class);
    view7f09046f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daishouhuo, "field 'tvDaishouhuo' and method 'onViewClicked'");
    target.tvDaishouhuo = Utils.castView(view, R.id.tv_daishouhuo, "field 'tvDaishouhuo'", TextView.class);
    view7f090470 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daodian, "field 'tvDaodian' and method 'onViewClicked'");
    target.tvDaodian = Utils.castView(view, R.id.tv_daodian, "field 'tvDaodian'", TextView.class);
    view7f090475 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.constrain1 = Utils.findRequiredViewAsType(source, R.id.constrain1, "field 'constrain1'", ConstraintLayout.class);
    target.constrain2 = Utils.findRequiredViewAsType(source, R.id.constrain2, "field 'constrain2'", ConstraintLayout.class);
    target.constrain3 = Utils.findRequiredViewAsType(source, R.id.constrain3, "field 'constrain3'", ConstraintLayout.class);
    target.srLSmart = Utils.findRequiredViewAsType(source, R.id.srL_smart, "field 'srLSmart'", SmartRefreshLayout.class);
    target.rlMain = Utils.findRequiredViewAsType(source, R.id.rl_main, "field 'rlMain'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_dalishang, "field 'tvDalishang' and method 'onViewClicked'");
    target.tvDalishang = Utils.castView(view, R.id.tv_dalishang, "field 'tvDalishang'", TextView.class);
    view7f090471 = view;
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
    MineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rivImage = null;
    target.tvName = null;
    target.ivShezhi = null;
    target.ivGouwuche = null;
    target.tvShoucangjiaNumber = null;
    target.tvShoucangjia = null;
    target.tvGuanzhuDianpu = null;
    target.tvGuanzhuDianpuNum = null;
    target.tvZhanghuJifen = null;
    target.tvZhanghuJifenNumber = null;
    target.tvKajuan = null;
    target.ivMineIconQianbao = null;
    target.tvWodeqianbao = null;
    target.rlvQianbao = null;
    target.tvDingdan = null;
    target.tvAll = null;
    target.ivBack = null;
    target.ivDaifukuan = null;
    target.ivDaifahuo = null;
    target.tvDaifahuo = null;
    target.ivDaishouhuo = null;
    target.ivPingjia = null;
    target.tvPingjia = null;
    target.ivDaodian = null;
    target.rlvDingdan = null;
    target.tvErshouChe = null;
    target.ivCheYuyue = null;
    target.ivChejindu = null;
    target.tvChejindu = null;
    target.ivMaicheJindu = null;
    target.tvMaicheJindu = null;
    target.ivXiaofeijilu = null;
    target.tvShouhou = null;
    target.ivQuanbuDingdan = null;
    target.rlvErshouche = null;
    target.tvZhanghuChongzhi = null;
    target.ivZhanghuChongzhi = null;
    target.ivKapianchongzhi = null;
    target.tvKapianchongzhi = null;
    target.ivChongzhiJilu = null;
    target.tvChongzhiJilu = null;
    target.ivShouhouFuwu = null;
    target.tvXiaofeijilu = null;
    target.ivXicheErwei = null;
    target.rlvXiche = null;
    target.ivTuiguangma = null;
    target.tvTuiguangma = null;
    target.ivDailishang = null;
    target.ivAboutUs = null;
    target.view = null;
    target.rlvAboutUs = null;
    target.tvPhone = null;
    target.ivQianbaoEnter = null;
    target.llShoucangjia = null;
    target.llGuanzhudianpu = null;
    target.llZhanghujifen = null;
    target.llKaquan = null;
    target.tvKajuanNumber = null;
    target.tvDaifukuan = null;
    target.tvDaishouhuo = null;
    target.tvDaodian = null;
    target.constrain1 = null;
    target.constrain2 = null;
    target.constrain3 = null;
    target.srLSmart = null;
    target.rlMain = null;
    target.tvDalishang = null;

    view7f09035f.setOnClickListener(null);
    view7f09035f = null;
    view7f0904ec.setOnClickListener(null);
    view7f0904ec = null;
    view7f090248.setOnClickListener(null);
    view7f090248 = null;
    view7f09020f.setOnClickListener(null);
    view7f09020f = null;
    view7f090544.setOnClickListener(null);
    view7f090544 = null;
    view7f090543.setOnClickListener(null);
    view7f090543 = null;
    view7f0904ad.setOnClickListener(null);
    view7f0904ad = null;
    view7f0904ae.setOnClickListener(null);
    view7f0904ae = null;
    view7f0905ab.setOnClickListener(null);
    view7f0905ab = null;
    view7f0905ac.setOnClickListener(null);
    view7f0905ac = null;
    view7f0904cd.setOnClickListener(null);
    view7f0904cd = null;
    view7f09022f.setOnClickListener(null);
    view7f09022f = null;
    view7f090585.setOnClickListener(null);
    view7f090585 = null;
    view7f090390.setOnClickListener(null);
    view7f090390 = null;
    view7f090486.setOnClickListener(null);
    view7f090486 = null;
    view7f09043e.setOnClickListener(null);
    view7f09043e = null;
    view7f0901ea.setOnClickListener(null);
    view7f0901ea = null;
    view7f0901fe.setOnClickListener(null);
    view7f0901fe = null;
    view7f0901fd.setOnClickListener(null);
    view7f0901fd = null;
    view7f09046c.setOnClickListener(null);
    view7f09046c = null;
    view7f090200.setOnClickListener(null);
    view7f090200 = null;
    view7f090239.setOnClickListener(null);
    view7f090239 = null;
    view7f090501.setOnClickListener(null);
    view7f090501 = null;
    view7f090202.setOnClickListener(null);
    view7f090202 = null;
    view7f090387.setOnClickListener(null);
    view7f090387 = null;
    view7f090492.setOnClickListener(null);
    view7f090492 = null;
    view7f0901f3.setOnClickListener(null);
    view7f0901f3 = null;
    view7f0901f4.setOnClickListener(null);
    view7f0901f4 = null;
    view7f09045d.setOnClickListener(null);
    view7f09045d = null;
    view7f09022c.setOnClickListener(null);
    view7f09022c = null;
    view7f0904e1.setOnClickListener(null);
    view7f0904e1 = null;
    view7f09025e.setOnClickListener(null);
    view7f09025e = null;
    view7f090545.setOnClickListener(null);
    view7f090545 = null;
    view7f09023c.setOnClickListener(null);
    view7f09023c = null;
    view7f090388.setOnClickListener(null);
    view7f090388 = null;
    view7f0905aa.setOnClickListener(null);
    view7f0905aa = null;
    view7f090261.setOnClickListener(null);
    view7f090261 = null;
    view7f090225.setOnClickListener(null);
    view7f090225 = null;
    view7f0904cf.setOnClickListener(null);
    view7f0904cf = null;
    view7f0901f6.setOnClickListener(null);
    view7f0901f6 = null;
    view7f09045f.setOnClickListener(null);
    view7f09045f = null;
    view7f09024d.setOnClickListener(null);
    view7f09024d = null;
    view7f09058a.setOnClickListener(null);
    view7f09058a = null;
    view7f09025f.setOnClickListener(null);
    view7f09025f = null;
    view7f090392.setOnClickListener(null);
    view7f090392 = null;
    view7f090256.setOnClickListener(null);
    view7f090256 = null;
    view7f090571.setOnClickListener(null);
    view7f090571 = null;
    view7f0901ff.setOnClickListener(null);
    view7f0901ff = null;
    view7f0901e4.setOnClickListener(null);
    view7f0901e4 = null;
    view7f0905c2.setOnClickListener(null);
    view7f0905c2 = null;
    view7f090385.setOnClickListener(null);
    view7f090385 = null;
    view7f0902ba.setOnClickListener(null);
    view7f0902ba = null;
    view7f0902a4.setOnClickListener(null);
    view7f0902a4 = null;
    view7f0902af.setOnClickListener(null);
    view7f0902af = null;
    view7f09046f.setOnClickListener(null);
    view7f09046f = null;
    view7f090470.setOnClickListener(null);
    view7f090470 = null;
    view7f090475.setOnClickListener(null);
    view7f090475 = null;
    view7f090471.setOnClickListener(null);
    view7f090471 = null;
  }
}
