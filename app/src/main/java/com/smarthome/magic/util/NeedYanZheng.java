package com.smarthome.magic.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.activity.taokeshagncheng.QueRenDingDanActivity;
import com.smarthome.magic.app.App;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.GouWuCheZhengHeModel;
import com.smarthome.magic.model.TuiGuangMaModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeedYanZheng {
//新增逻辑,判断wares_id在{179,188,189,190,191,192,193,194,195,196,197,198,199,200}之内,则必须有上一级的邀请码信息

    public static String shiFouYanzheng = "0";//没验证过

    public static boolean yanZheng(Context cnt, String id, List<String> strings) {

        shiFouYanzheng = PreferenceHelper.getInstance(cnt).getString(App.SHIFOUYOUSHANGJI, "0x");

        if (shiFouYanzheng.equals("1")) {

            return false;
        }


        if (strings.contains(id)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean yanZheng(Context cnt, List<GouWuCheZhengHeModel> mDatas, List<String> strings) {

        shiFouYanzheng = PreferenceHelper.getInstance(cnt).getString(App.SHIFOUYOUSHANGJI, "0x");

        if (shiFouYanzheng.equals("1")) {
            return false;
        }

        boolean flag = false;

        for (int i = 0; i < mDatas.size(); i++) {
            if (!mDatas.get(i).isHeader){
                for (int j = 0; j < strings.size(); j++) {
                    if (mDatas.get(i).getWares_id().equals(strings.get(j))) {
                        flag = true;
                        break;
                    }
                }
            }

        }
        return flag;
    }


}






