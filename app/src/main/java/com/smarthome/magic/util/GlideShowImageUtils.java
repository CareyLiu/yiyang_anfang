package com.smarthome.magic.util;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.smarthome.magic.R;

public class GlideShowImageUtils {

    public static RequestOptions showBannerCelve() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.preview_banner_375)
                .error(R.mipmap.preview_banner_375);
        return requestOptions;
    }

    public static RequestOptions showZhengFangXing() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.preview_shop_ffffff)
                .error(R.mipmap.preview_shop_ffffff);
        return requestOptions;
    }

    public static RequestOptions showFace() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.home_pic_touxiang_man)
                .error(R.mipmap.home_pic_touxiang_man);
        return requestOptions;
    }


    public static RequestOptions showNull() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(null)
                .error(null);
        return requestOptions;
    }
}
