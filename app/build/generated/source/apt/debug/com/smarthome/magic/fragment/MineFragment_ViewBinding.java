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

  private View view7f09050e;

  private View view7f0906fb;

  private View view7f090292;

  private View view7f090248;

  private View view7f090777;

  private View view7f090776;

  private View view7f0906a9;

  private View view7f0906aa;

  private View view7f0907f3;

  private View view7f0907f4;

  private View view7f0906d1;

  private View view7f09026c;

  private View view7f0907c8;

  private View view7f090551;

  private View view7f090675;

  private View view7f090612;

  private View view7f09021e;

  private View view7f090235;

  private View view7f090234;

  private View view7f090652;

  private View view7f090237;

  private View view7f09027a;

  private View view7f09071e;

  private View view7f090239;

  private View view7f090548;

  private View view7f090683;

  private View view7f090228;

  private View view7f09022a;

  private View view7f09063d;

  private View view7f090269;

  private View view7f0906eb;

  private View view7f0902ac;

  private View view7f090778;

  private View view7f09027d;

  private View view7f090549;

  private View view7f0907f2;

  private View view7f0902b2;

  private View view7f090261;

  private View view7f0906d3;

  private View view7f09022c;

  private View view7f090641;

  private View view7f090298;

  private View view7f0907cd;

  private View view7f0902ad;

  private View view7f090554;

  private View view7f0902a4;

  private View view7f0907ae;

  private View view7f090236;

  private View view7f090217;

  private View view7f090221;

  private View view7f090814;

  private View view7f090546;

  private View view7f09032c;

  private View view7f090304;

  private View view7f090311;

  private View view7f090655;

  private View view7f090656;

  private View view7f09065c;

  private View view7f090657;

  @UiThread
  public MineFragment_ViewBinding(final MineFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.riv_image, "field 'rivImage' and method 'onViewClicked'");
    target.rivImage = Utils.castView(view, R.id.riv_image, "field 'rivImage'", CircleImageView.class);
    view7f09050e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_name, "field 'tvName' and method 'onViewClicked'");
    target.tvName = Utils.castView(view, R.id.tv_name, "field 'tvName'", TextView.class);
    view7f0906fb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_shezhi, "field 'ivShezhi' and method 'onViewClicked'");
    target.ivShezhi = Utils.castView(view, R.id.iv_shezhi, "field 'ivShezhi'", ImageView.class);
    view7f090292 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_gouwuche, "field 'ivGouwuche' and method 'onViewClicked'");
    target.ivGouwuche = Utils.castView(view, R.id.iv_gouwuche, "field 'ivGouwuche'", ImageView.class);
    view7f090248 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shoucangjia_number, "field 'tvShoucangjiaNumber' and method 'onViewClicked'");
    target.tvShoucangjiaNumber = Utils.castView(view, R.id.tv_shoucangjia_number, "field 'tvShoucangjiaNumber'", TextView.class);
    view7f090777 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shoucangjia, "field 'tvShoucangjia' and method 'onViewClicked'");
    target.tvShoucangjia = Utils.castView(view, R.id.tv_shoucangjia, "field 'tvShoucangjia'", TextView.class);
    view7f090776 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_guanzhu_dianpu, "field 'tvGuanzhuDianpu' and method 'onViewClicked'");
    target.tvGuanzhuDianpu = Utils.castView(view, R.id.tv_guanzhu_dianpu, "field 'tvGuanzhuDianpu'", TextView.class);
    view7f0906a9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_guanzhu_dianpu_num, "field 'tvGuanzhuDianpuNum' and method 'onViewClicked'");
    target.tvGuanzhuDianpuNum = Utils.castView(view, R.id.tv_guanzhu_dianpu_num, "field 'tvGuanzhuDianpuNum'", TextView.class);
    view7f0906aa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_jifen, "field 'tvZhanghuJifen' and method 'onViewClicked'");
    target.tvZhanghuJifen = Utils.castView(view, R.id.tv_zhanghu_jifen, "field 'tvZhanghuJifen'", TextView.class);
    view7f0907f3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_jifen_number, "field 'tvZhanghuJifenNumber' and method 'onViewClicked'");
    target.tvZhanghuJifenNumber = Utils.castView(view, R.id.tv_zhanghu_jifen_number, "field 'tvZhanghuJifenNumber'", TextView.class);
    view7f0907f4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_kajuan, "field 'tvKajuan' and method 'onViewClicked'");
    target.tvKajuan = Utils.castView(view, R.id.tv_kajuan, "field 'tvKajuan'", TextView.class);
    view7f0906d1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_mine_icon_qianbao, "field 'ivMineIconQianbao' and method 'onViewClicked'");
    target.ivMineIconQianbao = Utils.castView(view, R.id.iv_mine_icon_qianbao, "field 'ivMineIconQianbao'", ImageView.class);
    view7f09026c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_wodeqianbao, "field 'tvWodeqianbao' and method 'onViewClicked'");
    target.tvWodeqianbao = Utils.castView(view, R.id.tv_wodeqianbao, "field 'tvWodeqianbao'", TextView.class);
    view7f0907c8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_qianbao, "field 'rlvQianbao' and method 'onViewClicked'");
    target.rlvQianbao = Utils.castView(view, R.id.rlv_qianbao, "field 'rlvQianbao'", RoundRelativeLayout.class);
    view7f090551 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_dingdan, "field 'tvDingdan' and method 'onViewClicked'");
    target.tvDingdan = Utils.castView(view, R.id.tv_dingdan, "field 'tvDingdan'", TextView.class);
    view7f090675 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_all, "field 'tvAll' and method 'onViewClicked'");
    target.tvAll = Utils.castView(view, R.id.tv_all, "field 'tvAll'", TextView.class);
    view7f090612 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view7f09021e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daifukuan, "field 'ivDaifukuan' and method 'onViewClicked'");
    target.ivDaifukuan = Utils.castView(view, R.id.iv_daifukuan, "field 'ivDaifukuan'", ImageView.class);
    view7f090235 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daifahuo, "field 'ivDaifahuo' and method 'onViewClicked'");
    target.ivDaifahuo = Utils.castView(view, R.id.iv_daifahuo, "field 'ivDaifahuo'", ImageView.class);
    view7f090234 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daifahuo, "field 'tvDaifahuo' and method 'onViewClicked'");
    target.tvDaifahuo = Utils.castView(view, R.id.tv_daifahuo, "field 'tvDaifahuo'", TextView.class);
    view7f090652 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daishouhuo, "field 'ivDaishouhuo' and method 'onViewClicked'");
    target.ivDaishouhuo = Utils.castView(view, R.id.iv_daishouhuo, "field 'ivDaishouhuo'", ImageView.class);
    view7f090237 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_pingjia, "field 'ivPingjia' and method 'onViewClicked'");
    target.ivPingjia = Utils.castView(view, R.id.iv_pingjia, "field 'ivPingjia'", ImageView.class);
    view7f09027a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_pingjia, "field 'tvPingjia' and method 'onViewClicked'");
    target.tvPingjia = Utils.castView(view, R.id.tv_pingjia, "field 'tvPingjia'", TextView.class);
    view7f09071e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daodian, "field 'ivDaodian' and method 'onViewClicked'");
    target.ivDaodian = Utils.castView(view, R.id.iv_daodian, "field 'ivDaodian'", ImageView.class);
    view7f090239 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_dingdan, "field 'rlvDingdan' and method 'onViewClicked'");
    target.rlvDingdan = Utils.castView(view, R.id.rlv_dingdan, "field 'rlvDingdan'", RoundRelativeLayout.class);
    view7f090548 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_ershou_che, "field 'tvErshouChe' and method 'onViewClicked'");
    target.tvErshouChe = Utils.castView(view, R.id.tv_ershou_che, "field 'tvErshouChe'", TextView.class);
    view7f090683 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_che_yuyue, "field 'ivCheYuyue' and method 'onViewClicked'");
    target.ivCheYuyue = Utils.castView(view, R.id.iv_che_yuyue, "field 'ivCheYuyue'", ImageView.class);
    view7f090228 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_chejindu, "field 'ivChejindu' and method 'onViewClicked'");
    target.ivChejindu = Utils.castView(view, R.id.iv_chejindu, "field 'ivChejindu'", ImageView.class);
    view7f09022a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_chejindu, "field 'tvChejindu' and method 'onViewClicked'");
    target.tvChejindu = Utils.castView(view, R.id.tv_chejindu, "field 'tvChejindu'", TextView.class);
    view7f09063d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_maiche_jindu, "field 'ivMaicheJindu' and method 'onViewClicked'");
    target.ivMaicheJindu = Utils.castView(view, R.id.iv_maiche_jindu, "field 'ivMaicheJindu'", ImageView.class);
    view7f090269 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_maiche_jindu, "field 'tvMaicheJindu' and method 'onViewClicked'");
    target.tvMaicheJindu = Utils.castView(view, R.id.tv_maiche_jindu, "field 'tvMaicheJindu'", TextView.class);
    view7f0906eb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_xiaofeijilu, "field 'ivXiaofeijilu' and method 'onViewClicked'");
    target.ivXiaofeijilu = Utils.castView(view, R.id.iv_xiaofeijilu, "field 'ivXiaofeijilu'", ImageView.class);
    view7f0902ac = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shouhou, "field 'tvShouhou' and method 'onViewClicked'");
    target.tvShouhou = Utils.castView(view, R.id.tv_shouhou, "field 'tvShouhou'", TextView.class);
    view7f090778 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_quanbu_dingdan, "field 'ivQuanbuDingdan' and method 'onViewClicked'");
    target.ivQuanbuDingdan = Utils.castView(view, R.id.iv_quanbu_dingdan, "field 'ivQuanbuDingdan'", ImageView.class);
    view7f09027d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_ershouche, "field 'rlvErshouche' and method 'onViewClicked'");
    target.rlvErshouche = Utils.castView(view, R.id.rlv_ershouche, "field 'rlvErshouche'", RoundRelativeLayout.class);
    view7f090549 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_chongzhi, "field 'tvZhanghuChongzhi' and method 'onViewClicked'");
    target.tvZhanghuChongzhi = Utils.castView(view, R.id.tv_zhanghu_chongzhi, "field 'tvZhanghuChongzhi'", TextView.class);
    view7f0907f2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_zhanghu_chongzhi, "field 'ivZhanghuChongzhi' and method 'onViewClicked'");
    target.ivZhanghuChongzhi = Utils.castView(view, R.id.iv_zhanghu_chongzhi, "field 'ivZhanghuChongzhi'", ImageView.class);
    view7f0902b2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_kapianchongzhi, "field 'ivKapianchongzhi' and method 'onViewClicked'");
    target.ivKapianchongzhi = Utils.castView(view, R.id.iv_kapianchongzhi, "field 'ivKapianchongzhi'", ImageView.class);
    view7f090261 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_kapianchongzhi, "field 'tvKapianchongzhi' and method 'onViewClicked'");
    target.tvKapianchongzhi = Utils.castView(view, R.id.tv_kapianchongzhi, "field 'tvKapianchongzhi'", TextView.class);
    view7f0906d3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_chongzhi_jilu, "field 'ivChongzhiJilu' and method 'onViewClicked'");
    target.ivChongzhiJilu = Utils.castView(view, R.id.iv_chongzhi_jilu, "field 'ivChongzhiJilu'", ImageView.class);
    view7f09022c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_chongzhi_jilu, "field 'tvChongzhiJilu' and method 'onViewClicked'");
    target.tvChongzhiJilu = Utils.castView(view, R.id.tv_chongzhi_jilu, "field 'tvChongzhiJilu'", TextView.class);
    view7f090641 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_shouhou_fuwu, "field 'ivShouhouFuwu' and method 'onViewClicked'");
    target.ivShouhouFuwu = Utils.castView(view, R.id.iv_shouhou_fuwu, "field 'ivShouhouFuwu'", ImageView.class);
    view7f090298 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_xiaofeijilu, "field 'tvXiaofeijilu' and method 'onViewClicked'");
    target.tvXiaofeijilu = Utils.castView(view, R.id.tv_xiaofeijilu, "field 'tvXiaofeijilu'", TextView.class);
    view7f0907cd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_xiche_erwei, "field 'ivXicheErwei' and method 'onViewClicked'");
    target.ivXicheErwei = Utils.castView(view, R.id.iv_xiche_erwei, "field 'ivXicheErwei'", ImageView.class);
    view7f0902ad = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_xiche, "field 'rlvXiche' and method 'onViewClicked'");
    target.rlvXiche = Utils.castView(view, R.id.rlv_xiche, "field 'rlvXiche'", RoundRelativeLayout.class);
    view7f090554 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_tuiguangma, "field 'ivTuiguangma' and method 'onViewClicked'");
    target.ivTuiguangma = Utils.castView(view, R.id.iv_tuiguangma, "field 'ivTuiguangma'", ImageView.class);
    view7f0902a4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_tuiguangma, "field 'tvTuiguangma' and method 'onViewClicked'");
    target.tvTuiguangma = Utils.castView(view, R.id.tv_tuiguangma, "field 'tvTuiguangma'", TextView.class);
    view7f0907ae = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_dailishang, "field 'ivDailishang' and method 'onViewClicked'");
    target.ivDailishang = Utils.castView(view, R.id.iv_dailishang, "field 'ivDailishang'", ImageView.class);
    view7f090236 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_about_us, "field 'ivAboutUs' and method 'onViewClicked'");
    target.ivAboutUs = Utils.castView(view, R.id.iv_about_us, "field 'ivAboutUs'", ImageView.class);
    view7f090217 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_bazism, "field 'ivBazism' and method 'onViewClicked'");
    target.ivBazism = Utils.castView(view, R.id.iv_bazism, "field 'ivBazism'", ImageView.class);
    view7f090221 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.view, "field 'view' and method 'onViewClicked'");
    target.view = view;
    view7f090814 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_about_us, "field 'rlvAboutUs' and method 'onViewClicked'");
    target.rlvAboutUs = Utils.castView(view, R.id.rlv_about_us, "field 'rlvAboutUs'", RoundRelativeLayout.class);
    view7f090546 = view;
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
    view7f09032c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_guanzhudianpu, "field 'llGuanzhudianpu' and method 'onViewClicked'");
    target.llGuanzhudianpu = Utils.castView(view, R.id.ll_guanzhudianpu, "field 'llGuanzhudianpu'", LinearLayout.class);
    view7f090304 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llZhanghujifen = Utils.findRequiredViewAsType(source, R.id.ll_zhanghujifen, "field 'llZhanghujifen'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ll_kaquan, "field 'llKaquan' and method 'onViewClicked'");
    target.llKaquan = Utils.castView(view, R.id.ll_kaquan, "field 'llKaquan'", LinearLayout.class);
    view7f090311 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvKajuanNumber = Utils.findRequiredViewAsType(source, R.id.tv_kajuan_number, "field 'tvKajuanNumber'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_daifukuan, "field 'tvDaifukuan' and method 'onViewClicked'");
    target.tvDaifukuan = Utils.castView(view, R.id.tv_daifukuan, "field 'tvDaifukuan'", TextView.class);
    view7f090655 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daishouhuo, "field 'tvDaishouhuo' and method 'onViewClicked'");
    target.tvDaishouhuo = Utils.castView(view, R.id.tv_daishouhuo, "field 'tvDaishouhuo'", TextView.class);
    view7f090656 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daodian, "field 'tvDaodian' and method 'onViewClicked'");
    target.tvDaodian = Utils.castView(view, R.id.tv_daodian, "field 'tvDaodian'", TextView.class);
    view7f09065c = view;
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
    view7f090657 = view;
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
    target.ivBazism = null;
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

    view7f09050e.setOnClickListener(null);
    view7f09050e = null;
    view7f0906fb.setOnClickListener(null);
    view7f0906fb = null;
    view7f090292.setOnClickListener(null);
    view7f090292 = null;
    view7f090248.setOnClickListener(null);
    view7f090248 = null;
    view7f090777.setOnClickListener(null);
    view7f090777 = null;
    view7f090776.setOnClickListener(null);
    view7f090776 = null;
    view7f0906a9.setOnClickListener(null);
    view7f0906a9 = null;
    view7f0906aa.setOnClickListener(null);
    view7f0906aa = null;
    view7f0907f3.setOnClickListener(null);
    view7f0907f3 = null;
    view7f0907f4.setOnClickListener(null);
    view7f0907f4 = null;
    view7f0906d1.setOnClickListener(null);
    view7f0906d1 = null;
    view7f09026c.setOnClickListener(null);
    view7f09026c = null;
    view7f0907c8.setOnClickListener(null);
    view7f0907c8 = null;
    view7f090551.setOnClickListener(null);
    view7f090551 = null;
    view7f090675.setOnClickListener(null);
    view7f090675 = null;
    view7f090612.setOnClickListener(null);
    view7f090612 = null;
    view7f09021e.setOnClickListener(null);
    view7f09021e = null;
    view7f090235.setOnClickListener(null);
    view7f090235 = null;
    view7f090234.setOnClickListener(null);
    view7f090234 = null;
    view7f090652.setOnClickListener(null);
    view7f090652 = null;
    view7f090237.setOnClickListener(null);
    view7f090237 = null;
    view7f09027a.setOnClickListener(null);
    view7f09027a = null;
    view7f09071e.setOnClickListener(null);
    view7f09071e = null;
    view7f090239.setOnClickListener(null);
    view7f090239 = null;
    view7f090548.setOnClickListener(null);
    view7f090548 = null;
    view7f090683.setOnClickListener(null);
    view7f090683 = null;
    view7f090228.setOnClickListener(null);
    view7f090228 = null;
    view7f09022a.setOnClickListener(null);
    view7f09022a = null;
    view7f09063d.setOnClickListener(null);
    view7f09063d = null;
    view7f090269.setOnClickListener(null);
    view7f090269 = null;
    view7f0906eb.setOnClickListener(null);
    view7f0906eb = null;
    view7f0902ac.setOnClickListener(null);
    view7f0902ac = null;
    view7f090778.setOnClickListener(null);
    view7f090778 = null;
    view7f09027d.setOnClickListener(null);
    view7f09027d = null;
    view7f090549.setOnClickListener(null);
    view7f090549 = null;
    view7f0907f2.setOnClickListener(null);
    view7f0907f2 = null;
    view7f0902b2.setOnClickListener(null);
    view7f0902b2 = null;
    view7f090261.setOnClickListener(null);
    view7f090261 = null;
    view7f0906d3.setOnClickListener(null);
    view7f0906d3 = null;
    view7f09022c.setOnClickListener(null);
    view7f09022c = null;
    view7f090641.setOnClickListener(null);
    view7f090641 = null;
    view7f090298.setOnClickListener(null);
    view7f090298 = null;
    view7f0907cd.setOnClickListener(null);
    view7f0907cd = null;
    view7f0902ad.setOnClickListener(null);
    view7f0902ad = null;
    view7f090554.setOnClickListener(null);
    view7f090554 = null;
    view7f0902a4.setOnClickListener(null);
    view7f0902a4 = null;
    view7f0907ae.setOnClickListener(null);
    view7f0907ae = null;
    view7f090236.setOnClickListener(null);
    view7f090236 = null;
    view7f090217.setOnClickListener(null);
    view7f090217 = null;
    view7f090221.setOnClickListener(null);
    view7f090221 = null;
    view7f090814.setOnClickListener(null);
    view7f090814 = null;
    view7f090546.setOnClickListener(null);
    view7f090546 = null;
    view7f09032c.setOnClickListener(null);
    view7f09032c = null;
    view7f090304.setOnClickListener(null);
    view7f090304 = null;
    view7f090311.setOnClickListener(null);
    view7f090311 = null;
    view7f090655.setOnClickListener(null);
    view7f090655 = null;
    view7f090656.setOnClickListener(null);
    view7f090656 = null;
    view7f09065c.setOnClickListener(null);
    view7f09065c = null;
    view7f090657.setOnClickListener(null);
    view7f090657 = null;
  }
}
