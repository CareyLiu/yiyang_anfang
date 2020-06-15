// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundRelativeLayout;
import com.smarthome.magic.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineFragment_ViewBinding implements Unbinder {
  private MineFragment target;

  private View view7f090352;

  private View view7f0904da;

  private View view7f09023d;

  private View view7f090205;

  private View view7f09052f;

  private View view7f09052e;

  private View view7f09049b;

  private View view7f09049c;

  private View view7f090596;

  private View view7f090597;

  private View view7f0904bb;

  private View view7f090224;

  private View view7f090570;

  private View view7f090383;

  private View view7f090475;

  private View view7f09042f;

  private View view7f0901e0;

  private View view7f0901f4;

  private View view7f0901f3;

  private View view7f09045d;

  private View view7f0901f6;

  private View view7f09022e;

  private View view7f0904ef;

  private View view7f0901f8;

  private View view7f09037a;

  private View view7f090481;

  private View view7f0901e9;

  private View view7f0901ea;

  private View view7f09044e;

  private View view7f090221;

  private View view7f0904cf;

  private View view7f090253;

  private View view7f090530;

  private View view7f090231;

  private View view7f09037b;

  private View view7f090595;

  private View view7f090256;

  private View view7f09021a;

  private View view7f0904bd;

  private View view7f0901ec;

  private View view7f090450;

  private View view7f090242;

  private View view7f090575;

  private View view7f090254;

  private View view7f090385;

  private View view7f09024b;

  private View view7f09055c;

  private View view7f0901f5;

  private View view7f0901da;

  private View view7f0905ad;

  private View view7f090378;

  private View view7f0902a3;

  private View view7f090460;

  private View view7f090461;

  private View view7f090465;

  @UiThread
  public MineFragment_ViewBinding(final MineFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.riv_image, "field 'rivImage' and method 'onViewClicked'");
    target.rivImage = Utils.castView(view, R.id.riv_image, "field 'rivImage'", CircleImageView.class);
    view7f090352 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_name, "field 'tvName' and method 'onViewClicked'");
    target.tvName = Utils.castView(view, R.id.tv_name, "field 'tvName'", TextView.class);
    view7f0904da = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_shezhi, "field 'ivShezhi' and method 'onViewClicked'");
    target.ivShezhi = Utils.castView(view, R.id.iv_shezhi, "field 'ivShezhi'", ImageView.class);
    view7f09023d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_gouwuche, "field 'ivGouwuche' and method 'onViewClicked'");
    target.ivGouwuche = Utils.castView(view, R.id.iv_gouwuche, "field 'ivGouwuche'", ImageView.class);
    view7f090205 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shoucangjia_number, "field 'tvShoucangjiaNumber' and method 'onViewClicked'");
    target.tvShoucangjiaNumber = Utils.castView(view, R.id.tv_shoucangjia_number, "field 'tvShoucangjiaNumber'", TextView.class);
    view7f09052f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shoucangjia, "field 'tvShoucangjia' and method 'onViewClicked'");
    target.tvShoucangjia = Utils.castView(view, R.id.tv_shoucangjia, "field 'tvShoucangjia'", TextView.class);
    view7f09052e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_guanzhu_dianpu, "field 'tvGuanzhuDianpu' and method 'onViewClicked'");
    target.tvGuanzhuDianpu = Utils.castView(view, R.id.tv_guanzhu_dianpu, "field 'tvGuanzhuDianpu'", TextView.class);
    view7f09049b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_guanzhu_dianpu_num, "field 'tvGuanzhuDianpuNum' and method 'onViewClicked'");
    target.tvGuanzhuDianpuNum = Utils.castView(view, R.id.tv_guanzhu_dianpu_num, "field 'tvGuanzhuDianpuNum'", TextView.class);
    view7f09049c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_jifen, "field 'tvZhanghuJifen' and method 'onViewClicked'");
    target.tvZhanghuJifen = Utils.castView(view, R.id.tv_zhanghu_jifen, "field 'tvZhanghuJifen'", TextView.class);
    view7f090596 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_jifen_number, "field 'tvZhanghuJifenNumber' and method 'onViewClicked'");
    target.tvZhanghuJifenNumber = Utils.castView(view, R.id.tv_zhanghu_jifen_number, "field 'tvZhanghuJifenNumber'", TextView.class);
    view7f090597 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_kajuan, "field 'tvKajuan' and method 'onViewClicked'");
    target.tvKajuan = Utils.castView(view, R.id.tv_kajuan, "field 'tvKajuan'", TextView.class);
    view7f0904bb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_mine_icon_qianbao, "field 'ivMineIconQianbao' and method 'onViewClicked'");
    target.ivMineIconQianbao = Utils.castView(view, R.id.iv_mine_icon_qianbao, "field 'ivMineIconQianbao'", ImageView.class);
    view7f090224 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_wodeqianbao, "field 'tvWodeqianbao' and method 'onViewClicked'");
    target.tvWodeqianbao = Utils.castView(view, R.id.tv_wodeqianbao, "field 'tvWodeqianbao'", TextView.class);
    view7f090570 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_qianbao, "field 'rlvQianbao' and method 'onViewClicked'");
    target.rlvQianbao = Utils.castView(view, R.id.rlv_qianbao, "field 'rlvQianbao'", RoundRelativeLayout.class);
    view7f090383 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_dingdan, "field 'tvDingdan' and method 'onViewClicked'");
    target.tvDingdan = Utils.castView(view, R.id.tv_dingdan, "field 'tvDingdan'", TextView.class);
    view7f090475 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_all, "field 'tvAll' and method 'onViewClicked'");
    target.tvAll = Utils.castView(view, R.id.tv_all, "field 'tvAll'", TextView.class);
    view7f09042f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view7f0901e0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daifukuan, "field 'ivDaifukuan' and method 'onViewClicked'");
    target.ivDaifukuan = Utils.castView(view, R.id.iv_daifukuan, "field 'ivDaifukuan'", ImageView.class);
    view7f0901f4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daifahuo, "field 'ivDaifahuo' and method 'onViewClicked'");
    target.ivDaifahuo = Utils.castView(view, R.id.iv_daifahuo, "field 'ivDaifahuo'", ImageView.class);
    view7f0901f3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daifahuo, "field 'tvDaifahuo' and method 'onViewClicked'");
    target.tvDaifahuo = Utils.castView(view, R.id.tv_daifahuo, "field 'tvDaifahuo'", TextView.class);
    view7f09045d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daishouhuo, "field 'ivDaishouhuo' and method 'onViewClicked'");
    target.ivDaishouhuo = Utils.castView(view, R.id.iv_daishouhuo, "field 'ivDaishouhuo'", ImageView.class);
    view7f0901f6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_pingjia, "field 'ivPingjia' and method 'onViewClicked'");
    target.ivPingjia = Utils.castView(view, R.id.iv_pingjia, "field 'ivPingjia'", ImageView.class);
    view7f09022e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_pingjia, "field 'tvPingjia' and method 'onViewClicked'");
    target.tvPingjia = Utils.castView(view, R.id.tv_pingjia, "field 'tvPingjia'", TextView.class);
    view7f0904ef = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daodian, "field 'ivDaodian' and method 'onViewClicked'");
    target.ivDaodian = Utils.castView(view, R.id.iv_daodian, "field 'ivDaodian'", ImageView.class);
    view7f0901f8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_dingdan, "field 'rlvDingdan' and method 'onViewClicked'");
    target.rlvDingdan = Utils.castView(view, R.id.rlv_dingdan, "field 'rlvDingdan'", RoundRelativeLayout.class);
    view7f09037a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_ershou_che, "field 'tvErshouChe' and method 'onViewClicked'");
    target.tvErshouChe = Utils.castView(view, R.id.tv_ershou_che, "field 'tvErshouChe'", TextView.class);
    view7f090481 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_che_yuyue, "field 'ivCheYuyue' and method 'onViewClicked'");
    target.ivCheYuyue = Utils.castView(view, R.id.iv_che_yuyue, "field 'ivCheYuyue'", ImageView.class);
    view7f0901e9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_chejindu, "field 'ivChejindu' and method 'onViewClicked'");
    target.ivChejindu = Utils.castView(view, R.id.iv_chejindu, "field 'ivChejindu'", ImageView.class);
    view7f0901ea = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_chejindu, "field 'tvChejindu' and method 'onViewClicked'");
    target.tvChejindu = Utils.castView(view, R.id.tv_chejindu, "field 'tvChejindu'", TextView.class);
    view7f09044e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_maiche_jindu, "field 'ivMaicheJindu' and method 'onViewClicked'");
    target.ivMaicheJindu = Utils.castView(view, R.id.iv_maiche_jindu, "field 'ivMaicheJindu'", ImageView.class);
    view7f090221 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_maiche_jindu, "field 'tvMaicheJindu' and method 'onViewClicked'");
    target.tvMaicheJindu = Utils.castView(view, R.id.tv_maiche_jindu, "field 'tvMaicheJindu'", TextView.class);
    view7f0904cf = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_xiaofeijilu, "field 'ivXiaofeijilu' and method 'onViewClicked'");
    target.ivXiaofeijilu = Utils.castView(view, R.id.iv_xiaofeijilu, "field 'ivXiaofeijilu'", ImageView.class);
    view7f090253 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shouhou, "field 'tvShouhou' and method 'onViewClicked'");
    target.tvShouhou = Utils.castView(view, R.id.tv_shouhou, "field 'tvShouhou'", TextView.class);
    view7f090530 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_quanbu_dingdan, "field 'ivQuanbuDingdan' and method 'onViewClicked'");
    target.ivQuanbuDingdan = Utils.castView(view, R.id.iv_quanbu_dingdan, "field 'ivQuanbuDingdan'", ImageView.class);
    view7f090231 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_ershouche, "field 'rlvErshouche' and method 'onViewClicked'");
    target.rlvErshouche = Utils.castView(view, R.id.rlv_ershouche, "field 'rlvErshouche'", RoundRelativeLayout.class);
    view7f09037b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_chongzhi, "field 'tvZhanghuChongzhi' and method 'onViewClicked'");
    target.tvZhanghuChongzhi = Utils.castView(view, R.id.tv_zhanghu_chongzhi, "field 'tvZhanghuChongzhi'", TextView.class);
    view7f090595 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_zhanghu_chongzhi, "field 'ivZhanghuChongzhi' and method 'onViewClicked'");
    target.ivZhanghuChongzhi = Utils.castView(view, R.id.iv_zhanghu_chongzhi, "field 'ivZhanghuChongzhi'", ImageView.class);
    view7f090256 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_kapianchongzhi, "field 'ivKapianchongzhi' and method 'onViewClicked'");
    target.ivKapianchongzhi = Utils.castView(view, R.id.iv_kapianchongzhi, "field 'ivKapianchongzhi'", ImageView.class);
    view7f09021a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_kapianchongzhi, "field 'tvKapianchongzhi' and method 'onViewClicked'");
    target.tvKapianchongzhi = Utils.castView(view, R.id.tv_kapianchongzhi, "field 'tvKapianchongzhi'", TextView.class);
    view7f0904bd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_chongzhi_jilu, "field 'ivChongzhiJilu' and method 'onViewClicked'");
    target.ivChongzhiJilu = Utils.castView(view, R.id.iv_chongzhi_jilu, "field 'ivChongzhiJilu'", ImageView.class);
    view7f0901ec = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_chongzhi_jilu, "field 'tvChongzhiJilu' and method 'onViewClicked'");
    target.tvChongzhiJilu = Utils.castView(view, R.id.tv_chongzhi_jilu, "field 'tvChongzhiJilu'", TextView.class);
    view7f090450 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_shouhou_fuwu, "field 'ivShouhouFuwu' and method 'onViewClicked'");
    target.ivShouhouFuwu = Utils.castView(view, R.id.iv_shouhou_fuwu, "field 'ivShouhouFuwu'", ImageView.class);
    view7f090242 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_xiaofeijilu, "field 'tvXiaofeijilu' and method 'onViewClicked'");
    target.tvXiaofeijilu = Utils.castView(view, R.id.tv_xiaofeijilu, "field 'tvXiaofeijilu'", TextView.class);
    view7f090575 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_xiche_erwei, "field 'ivXicheErwei' and method 'onViewClicked'");
    target.ivXicheErwei = Utils.castView(view, R.id.iv_xiche_erwei, "field 'ivXicheErwei'", ImageView.class);
    view7f090254 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_xiche, "field 'rlvXiche' and method 'onViewClicked'");
    target.rlvXiche = Utils.castView(view, R.id.rlv_xiche, "field 'rlvXiche'", RoundRelativeLayout.class);
    view7f090385 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_tuiguangma, "field 'ivTuiguangma' and method 'onViewClicked'");
    target.ivTuiguangma = Utils.castView(view, R.id.iv_tuiguangma, "field 'ivTuiguangma'", ImageView.class);
    view7f09024b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_tuiguangma, "field 'tvTuiguangma' and method 'onViewClicked'");
    target.tvTuiguangma = Utils.castView(view, R.id.tv_tuiguangma, "field 'tvTuiguangma'", TextView.class);
    view7f09055c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_dailishang, "field 'ivDailishang' and method 'onViewClicked'");
    target.ivDailishang = Utils.castView(view, R.id.iv_dailishang, "field 'ivDailishang'", ImageView.class);
    view7f0901f5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_about_us, "field 'ivAboutUs' and method 'onViewClicked'");
    target.ivAboutUs = Utils.castView(view, R.id.iv_about_us, "field 'ivAboutUs'", ImageView.class);
    view7f0901da = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.view, "field 'view' and method 'onViewClicked'");
    target.view = view;
    view7f0905ad = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_about_us, "field 'rlvAboutUs' and method 'onViewClicked'");
    target.rlvAboutUs = Utils.castView(view, R.id.rlv_about_us, "field 'rlvAboutUs'", RoundRelativeLayout.class);
    view7f090378 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.ivQianbaoEnter = Utils.findRequiredViewAsType(source, R.id.iv_qianbao_enter, "field 'ivQianbaoEnter'", ImageView.class);
    target.llShoucangjia = Utils.findRequiredViewAsType(source, R.id.ll_shoucangjia, "field 'llShoucangjia'", LinearLayout.class);
    target.llGuanzhudianpu = Utils.findRequiredViewAsType(source, R.id.ll_guanzhudianpu, "field 'llGuanzhudianpu'", LinearLayout.class);
    target.llZhanghujifen = Utils.findRequiredViewAsType(source, R.id.ll_zhanghujifen, "field 'llZhanghujifen'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ll_kaquan, "field 'llKaquan' and method 'onViewClicked'");
    target.llKaquan = Utils.castView(view, R.id.ll_kaquan, "field 'llKaquan'", LinearLayout.class);
    view7f0902a3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvKajuanNumber = Utils.findRequiredViewAsType(source, R.id.tv_kajuan_number, "field 'tvKajuanNumber'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_daifukuan, "field 'tvDaifukuan' and method 'onViewClicked'");
    target.tvDaifukuan = Utils.castView(view, R.id.tv_daifukuan, "field 'tvDaifukuan'", TextView.class);
    view7f090460 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daishouhuo, "field 'tvDaishouhuo' and method 'onViewClicked'");
    target.tvDaishouhuo = Utils.castView(view, R.id.tv_daishouhuo, "field 'tvDaishouhuo'", TextView.class);
    view7f090461 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daodian, "field 'tvDaodian' and method 'onViewClicked'");
    target.tvDaodian = Utils.castView(view, R.id.tv_daodian, "field 'tvDaodian'", TextView.class);
    view7f090465 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.constrain1 = Utils.findRequiredViewAsType(source, R.id.constrain1, "field 'constrain1'", ConstraintLayout.class);
    target.constrain2 = Utils.findRequiredViewAsType(source, R.id.constrain2, "field 'constrain2'", ConstraintLayout.class);
    target.constrain3 = Utils.findRequiredViewAsType(source, R.id.constrain3, "field 'constrain3'", ConstraintLayout.class);
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

    view7f090352.setOnClickListener(null);
    view7f090352 = null;
    view7f0904da.setOnClickListener(null);
    view7f0904da = null;
    view7f09023d.setOnClickListener(null);
    view7f09023d = null;
    view7f090205.setOnClickListener(null);
    view7f090205 = null;
    view7f09052f.setOnClickListener(null);
    view7f09052f = null;
    view7f09052e.setOnClickListener(null);
    view7f09052e = null;
    view7f09049b.setOnClickListener(null);
    view7f09049b = null;
    view7f09049c.setOnClickListener(null);
    view7f09049c = null;
    view7f090596.setOnClickListener(null);
    view7f090596 = null;
    view7f090597.setOnClickListener(null);
    view7f090597 = null;
    view7f0904bb.setOnClickListener(null);
    view7f0904bb = null;
    view7f090224.setOnClickListener(null);
    view7f090224 = null;
    view7f090570.setOnClickListener(null);
    view7f090570 = null;
    view7f090383.setOnClickListener(null);
    view7f090383 = null;
    view7f090475.setOnClickListener(null);
    view7f090475 = null;
    view7f09042f.setOnClickListener(null);
    view7f09042f = null;
    view7f0901e0.setOnClickListener(null);
    view7f0901e0 = null;
    view7f0901f4.setOnClickListener(null);
    view7f0901f4 = null;
    view7f0901f3.setOnClickListener(null);
    view7f0901f3 = null;
    view7f09045d.setOnClickListener(null);
    view7f09045d = null;
    view7f0901f6.setOnClickListener(null);
    view7f0901f6 = null;
    view7f09022e.setOnClickListener(null);
    view7f09022e = null;
    view7f0904ef.setOnClickListener(null);
    view7f0904ef = null;
    view7f0901f8.setOnClickListener(null);
    view7f0901f8 = null;
    view7f09037a.setOnClickListener(null);
    view7f09037a = null;
    view7f090481.setOnClickListener(null);
    view7f090481 = null;
    view7f0901e9.setOnClickListener(null);
    view7f0901e9 = null;
    view7f0901ea.setOnClickListener(null);
    view7f0901ea = null;
    view7f09044e.setOnClickListener(null);
    view7f09044e = null;
    view7f090221.setOnClickListener(null);
    view7f090221 = null;
    view7f0904cf.setOnClickListener(null);
    view7f0904cf = null;
    view7f090253.setOnClickListener(null);
    view7f090253 = null;
    view7f090530.setOnClickListener(null);
    view7f090530 = null;
    view7f090231.setOnClickListener(null);
    view7f090231 = null;
    view7f09037b.setOnClickListener(null);
    view7f09037b = null;
    view7f090595.setOnClickListener(null);
    view7f090595 = null;
    view7f090256.setOnClickListener(null);
    view7f090256 = null;
    view7f09021a.setOnClickListener(null);
    view7f09021a = null;
    view7f0904bd.setOnClickListener(null);
    view7f0904bd = null;
    view7f0901ec.setOnClickListener(null);
    view7f0901ec = null;
    view7f090450.setOnClickListener(null);
    view7f090450 = null;
    view7f090242.setOnClickListener(null);
    view7f090242 = null;
    view7f090575.setOnClickListener(null);
    view7f090575 = null;
    view7f090254.setOnClickListener(null);
    view7f090254 = null;
    view7f090385.setOnClickListener(null);
    view7f090385 = null;
    view7f09024b.setOnClickListener(null);
    view7f09024b = null;
    view7f09055c.setOnClickListener(null);
    view7f09055c = null;
    view7f0901f5.setOnClickListener(null);
    view7f0901f5 = null;
    view7f0901da.setOnClickListener(null);
    view7f0901da = null;
    view7f0905ad.setOnClickListener(null);
    view7f0905ad = null;
    view7f090378.setOnClickListener(null);
    view7f090378 = null;
    view7f0902a3.setOnClickListener(null);
    view7f0902a3 = null;
    view7f090460.setOnClickListener(null);
    view7f090460 = null;
    view7f090461.setOnClickListener(null);
    view7f090461 = null;
    view7f090465.setOnClickListener(null);
    view7f090465 = null;
  }
}
