package com.smarthome.magic.basicmvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.app.RxUtils;

import butterknife.ButterKnife;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment<T extends BasicPresenter, E extends BasicModel> extends BasicFragment<T, E> {

    protected CompositeSubscription _subscriptions = new CompositeSubscription();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!_subscriptions.isUnsubscribed()) {
            _subscriptions.unsubscribe();
        }
        // ButterKnife.unbind(this);
    }

    /**
     * 注册事件通知
     */
    public Observable<Notice> toObservable() {
        return RxBus.getDefault().toObservable(Notice.class);
    }

    /**
     * 发送消息
     */
    public void sendRx(Notice msg) {
        RxBus.getDefault().sendRx(msg);
    }

}
