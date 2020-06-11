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

  private View view7f09034c;

  private View view7f0904c6;

  private View view7f090237;

  private View view7f0901ff;

  private View view7f09051b;

  private View view7f09051a;

  private View view7f09048a;

  private View view7f09048b;

  private View view7f090580;

  private View view7f090581;

  private View view7f0904a9;

  private View view7f09021e;

  private View view7f09055a;

  private View view7f09037a;

  private View view7f090465;

  private View view7f090425;

  private View view7f0901da;

  private View view7f0901ee;

  private View view7f0901ed;

  private View view7f09044f;

  private View view7f0901f0;

  private View view7f090228;

  private View view7f0904db;

  private View view7f0901f2;

  private View view7f090371;

  private View view7f090471;

  private View view7f0901e3;

  private View view7f0901e4;

  private View view7f090442;

  private View view7f09021b;

  private View view7f0904bb;

  private View view7f09024d;

  private View view7f09051c;

  private View view7f09022b;

  private View view7f090372;

  private View view7f09057f;

  private View view7f090250;

  private View view7f090214;

  private View view7f0904ab;

  private View view7f0901e6;

  private View view7f090444;

  private View view7f09023c;

  private View view7f09055f;

  private View view7f09024e;

  private View view7f09037c;

  private View view7f090245;

  private View view7f090547;

  private View view7f0901ef;

  private View view7f0901d4;

  private View view7f090597;

  private View view7f09036f;

  private View view7f09029d;

  private View view7f090452;

  private View view7f090453;

  private View view7f090457;

  @UiThread
  public MineFragment_ViewBinding(final MineFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.riv_image, "field 'rivImage' and method 'onViewClicked'");
    target.rivImage = Utils.castView(view, R.id.riv_image, "field 'rivImage'", CircleImageView.class);
    view7f09034c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_name, "field 'tvName' and method 'onViewClicked'");
    target.tvName = Utils.castView(view, R.id.tv_name, "field 'tvName'", TextView.class);
    view7f0904c6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_shezhi, "field 'ivShezhi' and method 'onViewClicked'");
    target.ivShezhi = Utils.castView(view, R.id.iv_shezhi, "field 'ivShezhi'", ImageView.class);
    view7f090237 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_gouwuche, "field 'ivGouwuche' and method 'onViewClicked'");
    target.ivGouwuche = Utils.castView(view, R.id.iv_gouwuche, "field 'ivGouwuche'", ImageView.class);
    view7f0901ff = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shoucangjia_number, "field 'tvShoucangjiaNumber' and method 'onViewClicked'");
    target.tvShoucangjiaNumber = Utils.castView(view, R.id.tv_shoucangjia_number, "field 'tvShoucangjiaNumber'", TextView.class);
    view7f09051b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shoucangjia, "field 'tvShoucangjia' and method 'onViewClicked'");
    target.tvShoucangjia = Utils.castView(view, R.id.tv_shoucangjia, "field 'tvShoucangjia'", TextView.class);
    view7f09051a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_guanzhu_dianpu, "field 'tvGuanzhuDianpu' and method 'onViewClicked'");
    target.tvGuanzhuDianpu = Utils.castView(view, R.id.tv_guanzhu_dianpu, "field 'tvGuanzhuDianpu'", TextView.class);
    view7f09048a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_guanzhu_dianpu_num, "field 'tvGuanzhuDianpuNum' and method 'onViewClicked'");
    target.tvGuanzhuDianpuNum = Utils.castView(view, R.id.tv_guanzhu_dianpu_num, "field 'tvGuanzhuDianpuNum'", TextView.class);
    view7f09048b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_jifen, "field 'tvZhanghuJifen' and method 'onViewClicked'");
    target.tvZhanghuJifen = Utils.castView(view, R.id.tv_zhanghu_jifen, "field 'tvZhanghuJifen'", TextView.class);
    view7f090580 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_jifen_number, "field 'tvZhanghuJifenNumber' and method 'onViewClicked'");
    target.tvZhanghuJifenNumber = Utils.castView(view, R.id.tv_zhanghu_jifen_number, "field 'tvZhanghuJifenNumber'", TextView.class);
    view7f090581 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_kajuan, "field 'tvKajuan' and method 'onViewClicked'");
    target.tvKajuan = Utils.castView(view, R.id.tv_kajuan, "field 'tvKajuan'", TextView.class);
    view7f0904a9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_mine_icon_qianbao, "field 'ivMineIconQianbao' and method 'onViewClicked'");
    target.ivMineIconQianbao = Utils.castView(view, R.id.iv_mine_icon_qianbao, "field 'ivMineIconQianbao'", ImageView.class);
    view7f09021e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_wodeqianbao, "field 'tvWodeqianbao' and method 'onViewClicked'");
    target.tvWodeqianbao = Utils.castView(view, R.id.tv_wodeqianbao, "field 'tvWodeqianbao'", TextView.class);
    view7f09055a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_qianbao, "field 'rlvQianbao' and method 'onViewClicked'");
    target.rlvQianbao = Utils.castView(view, R.id.rlv_qianbao, "field 'rlvQianbao'", RoundRelativeLayout.class);
    view7f09037a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_dingdan, "field 'tvDingdan' and method 'onViewClicked'");
    target.tvDingdan = Utils.castView(view, R.id.tv_dingdan, "field 'tvDingdan'", TextView.class);
    view7f090465 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_all, "field 'tvAll' and method 'onViewClicked'");
    target.tvAll = Utils.castView(view, R.id.tv_all, "field 'tvAll'", TextView.class);
    view7f090425 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view7f0901da = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daifukuan, "field 'ivDaifukuan' and method 'onViewClicked'");
    target.ivDaifukuan = Utils.castView(view, R.id.iv_daifukuan, "field 'ivDaifukuan'", ImageView.class);
    view7f0901ee = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daifahuo, "field 'ivDaifahuo' and method 'onViewClicked'");
    target.ivDaifahuo = Utils.castView(view, R.id.iv_daifahuo, "field 'ivDaifahuo'", ImageView.class);
    view7f0901ed = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daifahuo, "field 'tvDaifahuo' and method 'onViewClicked'");
    target.tvDaifahuo = Utils.castView(view, R.id.tv_daifahuo, "field 'tvDaifahuo'", TextView.class);
    view7f09044f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daishouhuo, "field 'ivDaishouhuo' and method 'onViewClicked'");
    target.ivDaishouhuo = Utils.castView(view, R.id.iv_daishouhuo, "field 'ivDaishouhuo'", ImageView.class);
    view7f0901f0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_pingjia, "field 'ivPingjia' and method 'onViewClicked'");
    target.ivPingjia = Utils.castView(view, R.id.iv_pingjia, "field 'ivPingjia'", ImageView.class);
    view7f090228 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_pingjia, "field 'tvPingjia' and method 'onViewClicked'");
    target.tvPingjia = Utils.castView(view, R.id.tv_pingjia, "field 'tvPingjia'", TextView.class);
    view7f0904db = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_daodian, "field 'ivDaodian' and method 'onViewClicked'");
    target.ivDaodian = Utils.castView(view, R.id.iv_daodian, "field 'ivDaodian'", ImageView.class);
    view7f0901f2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_dingdan, "field 'rlvDingdan' and method 'onViewClicked'");
    target.rlvDingdan = Utils.castView(view, R.id.rlv_dingdan, "field 'rlvDingdan'", RoundRelativeLayout.class);
    view7f090371 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_ershou_che, "field 'tvErshouChe' and method 'onViewClicked'");
    target.tvErshouChe = Utils.castView(view, R.id.tv_ershou_che, "field 'tvErshouChe'", TextView.class);
    view7f090471 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_che_yuyue, "field 'ivCheYuyue' and method 'onViewClicked'");
    target.ivCheYuyue = Utils.castView(view, R.id.iv_che_yuyue, "field 'ivCheYuyue'", ImageView.class);
    view7f0901e3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_chejindu, "field 'ivChejindu' and method 'onViewClicked'");
    target.ivChejindu = Utils.castView(view, R.id.iv_chejindu, "field 'ivChejindu'", ImageView.class);
    view7f0901e4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_chejindu, "field 'tvChejindu' and method 'onViewClicked'");
    target.tvChejindu = Utils.castView(view, R.id.tv_chejindu, "field 'tvChejindu'", TextView.class);
    view7f090442 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_maiche_jindu, "field 'ivMaicheJindu' and method 'onViewClicked'");
    target.ivMaicheJindu = Utils.castView(view, R.id.iv_maiche_jindu, "field 'ivMaicheJindu'", ImageView.class);
    view7f09021b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_maiche_jindu, "field 'tvMaicheJindu' and method 'onViewClicked'");
    target.tvMaicheJindu = Utils.castView(view, R.id.tv_maiche_jindu, "field 'tvMaicheJindu'", TextView.class);
    view7f0904bb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_xiaofeijilu, "field 'ivXiaofeijilu' and method 'onViewClicked'");
    target.ivXiaofeijilu = Utils.castView(view, R.id.iv_xiaofeijilu, "field 'ivXiaofeijilu'", ImageView.class);
    view7f09024d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shouhou, "field 'tvShouhou' and method 'onViewClicked'");
    target.tvShouhou = Utils.castView(view, R.id.tv_shouhou, "field 'tvShouhou'", TextView.class);
    view7f09051c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_quanbu_dingdan, "field 'ivQuanbuDingdan' and method 'onViewClicked'");
    target.ivQuanbuDingdan = Utils.castView(view, R.id.iv_quanbu_dingdan, "field 'ivQuanbuDingdan'", ImageView.class);
    view7f09022b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_ershouche, "field 'rlvErshouche' and method 'onViewClicked'");
    target.rlvErshouche = Utils.castView(view, R.id.rlv_ershouche, "field 'rlvErshouche'", RoundRelativeLayout.class);
    view7f090372 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhanghu_chongzhi, "field 'tvZhanghuChongzhi' and method 'onViewClicked'");
    target.tvZhanghuChongzhi = Utils.castView(view, R.id.tv_zhanghu_chongzhi, "field 'tvZhanghuChongzhi'", TextView.class);
    view7f09057f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_zhanghu_chongzhi, "field 'ivZhanghuChongzhi' and method 'onViewClicked'");
    target.ivZhanghuChongzhi = Utils.castView(view, R.id.iv_zhanghu_chongzhi, "field 'ivZhanghuChongzhi'", ImageView.class);
    view7f090250 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_kapianchongzhi, "field 'ivKapianchongzhi' and method 'onViewClicked'");
    target.ivKapianchongzhi = Utils.castView(view, R.id.iv_kapianchongzhi, "field 'ivKapianchongzhi'", ImageView.class);
    view7f090214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_kapianchongzhi, "field 'tvKapianchongzhi' and method 'onViewClicked'");
    target.tvKapianchongzhi = Utils.castView(view, R.id.tv_kapianchongzhi, "field 'tvKapianchongzhi'", TextView.class);
    view7f0904ab = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_chongzhi_jilu, "field 'ivChongzhiJilu' and method 'onViewClicked'");
    target.ivChongzhiJilu = Utils.castView(view, R.id.iv_chongzhi_jilu, "field 'ivChongzhiJilu'", ImageView.class);
    view7f0901e6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_chongzhi_jilu, "field 'tvChongzhiJilu' and method 'onViewClicked'");
    target.tvChongzhiJilu = Utils.castView(view, R.id.tv_chongzhi_jilu, "field 'tvChongzhiJilu'", TextView.class);
    view7f090444 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_shouhou_fuwu, "field 'ivShouhouFuwu' and method 'onViewClicked'");
    target.ivShouhouFuwu = Utils.castView(view, R.id.iv_shouhou_fuwu, "field 'ivShouhouFuwu'", ImageView.class);
    view7f09023c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_xiaofeijilu, "field 'tvXiaofeijilu' and method 'onViewClicked'");
    target.tvXiaofeijilu = Utils.castView(view, R.id.tv_xiaofeijilu, "field 'tvXiaofeijilu'", TextView.class);
    view7f09055f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_xiche_erwei, "field 'ivXicheErwei' and method 'onViewClicked'");
    target.ivXicheErwei = Utils.castView(view, R.id.iv_xiche_erwei, "field 'ivXicheErwei'", ImageView.class);
    view7f09024e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_xiche, "field 'rlvXiche' and method 'onViewClicked'");
    target.rlvXiche = Utils.castView(view, R.id.rlv_xiche, "field 'rlvXiche'", RoundRelativeLayout.class);
    view7f09037c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_tuiguangma, "field 'ivTuiguangma' and method 'onViewClicked'");
    target.ivTuiguangma = Utils.castView(view, R.id.iv_tuiguangma, "field 'ivTuiguangma'", ImageView.class);
    view7f090245 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_tuiguangma, "field 'tvTuiguangma' and method 'onViewClicked'");
    target.tvTuiguangma = Utils.castView(view, R.id.tv_tuiguangma, "field 'tvTuiguangma'", TextView.class);
    view7f090547 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_dailishang, "field 'ivDailishang' and method 'onViewClicked'");
    target.ivDailishang = Utils.castView(view, R.id.iv_dailishang, "field 'ivDailishang'", ImageView.class);
    view7f0901ef = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_about_us, "field 'ivAboutUs' and method 'onViewClicked'");
    target.ivAboutUs = Utils.castView(view, R.id.iv_about_us, "field 'ivAboutUs'", ImageView.class);
    view7f0901d4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.view, "field 'view' and method 'onViewClicked'");
    target.view = view;
    view7f090597 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rlv_about_us, "field 'rlvAboutUs' and method 'onViewClicked'");
    target.rlvAboutUs = Utils.castView(view, R.id.rlv_about_us, "field 'rlvAboutUs'", RoundRelativeLayout.class);
    view7f09036f = view;
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
    view7f09029d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvKajuanNumber = Utils.findRequiredViewAsType(source, R.id.tv_kajuan_number, "field 'tvKajuanNumber'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_daifukuan, "field 'tvDaifukuan' and method 'onViewClicked'");
    target.tvDaifukuan = Utils.castView(view, R.id.tv_daifukuan, "field 'tvDaifukuan'", TextView.class);
    view7f090452 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daishouhuo, "field 'tvDaishouhuo' and method 'onViewClicked'");
    target.tvDaishouhuo = Utils.castView(view, R.id.tv_daishouhuo, "field 'tvDaishouhuo'", TextView.class);
    view7f090453 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_daodian, "field 'tvDaodian' and method 'onViewClicked'");
    target.tvDaodian = Utils.castView(view, R.id.tv_daodian, "field 'tvDaodian'", TextView.class);
    view7f090457 = view;
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

    view7f09034c.setOnClickListener(null);
    view7f09034c = null;
    view7f0904c6.setOnClickListener(null);
    view7f0904c6 = null;
    view7f090237.setOnClickListener(null);
    view7f090237 = null;
    view7f0901ff.setOnClickListener(null);
    view7f0901ff = null;
    view7f09051b.setOnClickListener(null);
    view7f09051b = null;
    view7f09051a.setOnClickListener(null);
    view7f09051a = null;
    view7f09048a.setOnClickListener(null);
    view7f09048a = null;
    view7f09048b.setOnClickListener(null);
    view7f09048b = null;
    view7f090580.setOnClickListener(null);
    view7f090580 = null;
    view7f090581.setOnClickListener(null);
    view7f090581 = null;
    view7f0904a9.setOnClickListener(null);
    view7f0904a9 = null;
    view7f09021e.setOnClickListener(null);
    view7f09021e = null;
    view7f09055a.setOnClickListener(null);
    view7f09055a = null;
    view7f09037a.setOnClickListener(null);
    view7f09037a = null;
    view7f090465.setOnClickListener(null);
    view7f090465 = null;
    view7f090425.setOnClickListener(null);
    view7f090425 = null;
    view7f0901da.setOnClickListener(null);
    view7f0901da = null;
    view7f0901ee.setOnClickListener(null);
    view7f0901ee = null;
    view7f0901ed.setOnClickListener(null);
    view7f0901ed = null;
    view7f09044f.setOnClickListener(null);
    view7f09044f = null;
    view7f0901f0.setOnClickListener(null);
    view7f0901f0 = null;
    view7f090228.setOnClickListener(null);
    view7f090228 = null;
    view7f0904db.setOnClickListener(null);
    view7f0904db = null;
    view7f0901f2.setOnClickListener(null);
    view7f0901f2 = null;
    view7f090371.setOnClickListener(null);
    view7f090371 = null;
    view7f090471.setOnClickListener(null);
    view7f090471 = null;
    view7f0901e3.setOnClickListener(null);
    view7f0901e3 = null;
    view7f0901e4.setOnClickListener(null);
    view7f0901e4 = null;
    view7f090442.setOnClickListener(null);
    view7f090442 = null;
    view7f09021b.setOnClickListener(null);
    view7f09021b = null;
    view7f0904bb.setOnClickListener(null);
    view7f0904bb = null;
    view7f09024d.setOnClickListener(null);
    view7f09024d = null;
    view7f09051c.setOnClickListener(null);
    view7f09051c = null;
    view7f09022b.setOnClickListener(null);
    view7f09022b = null;
    view7f090372.setOnClickListener(null);
    view7f090372 = null;
    view7f09057f.setOnClickListener(null);
    view7f09057f = null;
    view7f090250.setOnClickListener(null);
    view7f090250 = null;
    view7f090214.setOnClickListener(null);
    view7f090214 = null;
    view7f0904ab.setOnClickListener(null);
    view7f0904ab = null;
    view7f0901e6.setOnClickListener(null);
    view7f0901e6 = null;
    view7f090444.setOnClickListener(null);
    view7f090444 = null;
    view7f09023c.setOnClickListener(null);
    view7f09023c = null;
    view7f09055f.setOnClickListener(null);
    view7f09055f = null;
    view7f09024e.setOnClickListener(null);
    view7f09024e = null;
    view7f09037c.setOnClickListener(null);
    view7f09037c = null;
    view7f090245.setOnClickListener(null);
    view7f090245 = null;
    view7f090547.setOnClickListener(null);
    view7f090547 = null;
    view7f0901ef.setOnClickListener(null);
    view7f0901ef = null;
    view7f0901d4.setOnClickListener(null);
    view7f0901d4 = null;
    view7f090597.setOnClickListener(null);
    view7f090597 = null;
    view7f09036f.setOnClickListener(null);
    view7f09036f = null;
    view7f09029d.setOnClickListener(null);
    view7f09029d = null;
    view7f090452.setOnClickListener(null);
    view7f090452 = null;
    view7f090453.setOnClickListener(null);
    view7f090453 = null;
    view7f090457.setOnClickListener(null);
    view7f090457 = null;
  }
}
