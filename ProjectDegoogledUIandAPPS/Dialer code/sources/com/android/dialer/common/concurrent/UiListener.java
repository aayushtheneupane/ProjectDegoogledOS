package com.android.dialer.common.concurrent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class UiListener<OutputT> extends Fragment {
    private CallbackWrapper<OutputT> callbackWrapper;

    private static class CallbackWrapper<OutputT> implements FutureCallback<OutputT> {
        /* access modifiers changed from: private */
        public DialerExecutor.FailureListener failureListener;
        /* access modifiers changed from: private */
        public DialerExecutor.SuccessListener<OutputT> successListener;

        /* synthetic */ CallbackWrapper(DialerExecutor.SuccessListener successListener2, DialerExecutor.FailureListener failureListener2, C04571 r3) {
            this.successListener = successListener2;
            this.failureListener = failureListener2;
        }

        public void onFailure(Throwable th) {
            LogUtil.m7e("UiListener.runTask", "task failed", th);
            DialerExecutor.FailureListener failureListener2 = this.failureListener;
            if (failureListener2 == null) {
                LogUtil.m9i("UiListener.runTask", "task failed but UI is dead", new Object[0]);
            } else {
                failureListener2.onFailure(th);
            }
        }

        public void onSuccess(OutputT outputt) {
            DialerExecutor.SuccessListener<OutputT> successListener2 = this.successListener;
            if (successListener2 == null) {
                LogUtil.m9i("UiListener.runTask", "task succeeded but UI is dead", new Object[0]);
            } else {
                successListener2.onSuccess(outputt);
            }
        }
    }

    static <OutputT> UiListener<OutputT> create(FragmentManager fragmentManager, String str) {
        UiListener<OutputT> uiListener = (UiListener) fragmentManager.findFragmentByTag(str);
        if (uiListener != null) {
            return uiListener;
        }
        LogUtil.m9i("UiListener.create", GeneratedOutlineSupport.outline8("creating new UiListener for ", str), new Object[0]);
        UiListener<OutputT> uiListener2 = new UiListener<>();
        fragmentManager.beginTransaction().add(uiListener2, str).commitAllowingStateLoss();
        return uiListener2;
    }

    public void listen(Context context, ListenableFuture<OutputT> listenableFuture, DialerExecutor.SuccessListener<OutputT> successListener, DialerExecutor.FailureListener failureListener) {
        Assert.isNotNull(successListener);
        Assert.isNotNull(failureListener);
        this.callbackWrapper = new CallbackWrapper<>(successListener, failureListener, (C04571) null);
        Assert.isNotNull(listenableFuture);
        Futures.addCallback(listenableFuture, this.callbackWrapper, DialerExecutorComponent.get(context).uiExecutor());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void onDetach() {
        super.onDetach();
        LogUtil.enterBlock("UiListener.onDetach");
        CallbackWrapper<OutputT> callbackWrapper2 = this.callbackWrapper;
        if (callbackWrapper2 != null) {
            DialerExecutor.SuccessListener unused = callbackWrapper2.successListener = null;
            DialerExecutor.FailureListener unused2 = this.callbackWrapper.failureListener = null;
        }
    }
}
