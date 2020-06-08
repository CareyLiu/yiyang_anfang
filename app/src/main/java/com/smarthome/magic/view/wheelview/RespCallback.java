package com.smarthome.magic.view.wheelview;
public abstract class RespCallback {

	public abstract void onFinished(Object object);

	public void onError() { }

	public void onCancel() { }

}
