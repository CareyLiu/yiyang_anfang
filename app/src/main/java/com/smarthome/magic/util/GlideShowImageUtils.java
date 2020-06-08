package com.smarthome.magic.util;

import com.bumptech.glide.request.RequestOptions;
import com.smarthome.magic.R;

public class GlideShowImageUtils {

    public static RequestOptions showBannerCelve() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.preview_banner_375)
                .error(R.mipmap.preview_banner_375);
        return requestOptions;
    }
}
