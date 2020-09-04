package com.smarthome.magic.util;

import android.content.Context;
import android.util.Log;

import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;

public class DoMqttValue {
    public static String KONGZHIXIANGMU = "0";//控制硬件项目  1. 智能家居 2.风暖加热器 3.水暖加热器 4.驻车空调 5.神灯控车
    public Context context;//上下文
    public String message;//消息

    public void doValue(Context context, String topic, String message) {
        if (message.contains("p.")) {
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_P;
            RxBus.getDefault().sendRx(n);
        } else if (topic.contains("zn/")) {//智能家居 主题
            String messageData = message.toString().substring(2, message.toString().length() - 1);

        } else if (message.toString().equals("j_s")) {
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_SN_DATA;
//                            n.content = message.toString();
            n.content = "j_s345611166666661102640265050070600017002310.";
            RxBus.getDefault().sendRx(n);
        } else if (message.toString().contains("j_s")) {
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_SN_DATA;
            n.content = message.toString();
            RxBus.getDefault().sendRx(n);
        } else if (message.toString().contains("_")) {
            String messageData = message.toString().substring(2, message.toString().length() - 1);
            String[] arr = messageData.split("_");

            for (int i = 0; i < arr.length; i++) {
                if (arr[i].contains("g")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_J_G;
                    n.content = arr[i];
                    Log.i("g--n.content", n.content.toString());
                    RxBus.getDefault().sendRx(n);

                } else if (arr[i].contains("M")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_J_M;
                    n.content = arr[i];
                    RxBus.getDefault().sendRx(n);
                    Log.i("MSG_CAR_J_M", n.content.toString());

                } else if (arr[i].contains("h")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_h;
                    n.content = arr[i];
                    RxBus.getDefault().sendRx(n);
                    Log.i("MSG_CAR_J_M", n.content.toString());

                } else if (arr[i].contains("l")) {

                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_l;
                    n.content = arr[i];
                    RxBus.getDefault().sendRx(n);
                    Log.i("MSG_CAR_l", n.content.toString());

                } else if (arr[i].contains("m")) {

                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_l;
                    n.content = arr[i];
                    RxBus.getDefault().sendRx(n);
                    Log.i("MSG_CAR_l", n.content.toString());
                } else if (arr[i].contains("n")) {

                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_n;
                    n.content = arr[i];
                    RxBus.getDefault().sendRx(n);
                    Log.i("MSG_CAR_n", n.content.toString());
                } else if (arr[i].contains("p")) {

                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_p;
                    n.content = arr[i];
                    RxBus.getDefault().sendRx(n);
                    Log.i("MSG_CAR_p", n.content.toString());
                } else if (arr[i].contains("r")) {

                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_r;
                    n.content = arr[i];
                    RxBus.getDefault().sendRx(n);
                    Log.i("MSG_CAR_r", n.content.toString());

                } else if (arr[i].contains("s")) {

                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_s;
                    n.content = arr[i];
                    RxBus.getDefault().sendRx(n);
                    Log.i("MSG_CAR_s", n.content.toString());

                } else if (arr[i].contains("Z")) {

                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_Z;
                    n.content = arr[i];
                    RxBus.getDefault().sendRx(n);
                    Log.i("MSG_CAR_Z", n.content.toString());

                } else if (message.toString().contains("k")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_K;
                    n.content = message.toString();
                    RxBus.getDefault().sendRx(n);

                } else if (message.toString().contains("i")) {

                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_I;
                    n.content = message.toString();
                    RxBus.getDefault().sendRx(n);
                }
            }

        } else if (message.toString().equals("M691.")) { //清除故障成功
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_CLEARGUZHANGSUCCESS;
            n.content = message.toString();
            RxBus.getDefault().sendRx(n);
        } else if (message.toString().contains("i")) {

//                            Notice n = new Notice();
//                            n.type = ConstanceValue.MSG_CAR_I;
//                            n.content = message.toString();
//                            RxBus.getDefault().sendRx(n);

        } else if (message.toString().equals("k5011.")) {
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_CAR_HUI_FU_CHU_CHAGN;
            RxBus.getDefault().sendRx(n);

        } else if (message.toString().contains("h")) {//h是风油比
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_CAR_FEGNYOUBI;
            n.content = message.toString();
            RxBus.getDefault().sendRx(n);
        } else if (message.toString().equals("M001.")) {
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_CAR_J_G;
//                            n.content = message.toString();
            n.content = "g0011108122015500026-02500041";
            RxBus.getDefault().sendRx(n);
        }
    }

    public static void setKongZhiXiangMu(String string) {
        KONGZHIXIANGMU = string;
    }
}
