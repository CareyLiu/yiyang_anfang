package com.smarthome.magic.aakefudan.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.bazinew.model.TuiguangModel;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.model.ConsultModel;

import java.util.List;

import androidx.annotation.Nullable;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class ZixunAdapter extends BaseQuickAdapter<ConsultModel.DataBean, BaseViewHolder> {
    public ZixunAdapter(int layoutResId, @Nullable List<ConsultModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConsultModel.DataBean item) {
        Glide.with(mContext).load(item.getUser_car_img_url()).into((ImageView) helper.getView(R.id.iv_head));
        helper.setText(R.id.tv_name, item.getUser_name_car());
        helper.setText(R.id.tv_model, item.getPlate_number());
        helper.setText(R.id.tv_fault, item.getError_text());
        helper.setText(R.id.tv_date, item.getCreate_time());
        helper.setText(R.id.tv_state, item.getState_name());

        Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
        String targetId = item.getOf_user_accid();
        RongIMClient.getInstance().getUnreadCount(conversationType, targetId,
                new RongIMClient.ResultCallback<Integer>() {
                    /**
                     * 成功回调
                     * @param unReadCount 未读数
                     */
                    @Override
                    public void onSuccess(Integer unReadCount) {
                        View tv_num = helper.getView(R.id.tv_num);
                        if (unReadCount > 0) {
                            tv_num.setVisibility(View.VISIBLE);
                            if (unReadCount > 99) {
                                helper.setText(R.id.tv_num, "99+");
                            } else {
                                helper.setText(R.id.tv_num, unReadCount + "");
                            }
                        } else {
                            tv_num.setVisibility(View.GONE);
                            helper.setText(R.id.tv_num, unReadCount + "");
                        }
                    }

                    /**
                     * 错误回调
                     */
                    @Override
                    public void onError(RongIMClient.ErrorCode ErrorCode) {

                    }
                });
    }
}
