package com.android.dialer.common.concurrent;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class SupportUiListener<OutputT> extends Fragment {
    private CallbackWrapper<OutputT> callbackWrapper;

    private static class CallbackWrapper<OutputT> implements FutureCallback<OutputT> {
        /* access modifiers changed from: private */
        public DialerExecutor.FailureListener failureListener;
        /* access modifiers changed from: private */
        public DialerExecutor.SuccessListener<OutputT> successListener;

        /* synthetic */ CallbackWrapper(DialerExecutor.SuccessListener successListener2, DialerExecutor.FailureListener failureListener2, C04561 r3) {
            this.successListener = successListener2;
            this.failureListener = failureListener2;
        }

        public void onFailure(Throwable th) {
            LogUtil.m7e("SupportUiListener.runTask", "task failed", th);
            DialerExecutor.FailureListener failureListener2 = this.failureListener;
            if (failureListener2 == null) {
                LogUtil.m9i("SupportUiListener.runTask", "task failed but UI is dead", new Object[0]);
            } else {
                failureListener2.onFailure(th);
            }
        }

        public void onSuccess(OutputT outputt) {
            DialerExecutor.SuccessListener<OutputT> successListener2 = this.successListener;
            if (successListener2 == null) {
                LogUtil.m9i("SupportUiListener.runTask", "task succeeded but UI is dead", new Object[0]);
            } else {
                successListener2.onSuccess(outputt);
            }
        }
    }

    static <OutputT> SupportUiListener<OutputT> create(FragmentManager fragmentManager, String str) {
        SupportUiListener<OutputT> supportUiListener = (SupportUiListener) fragmentManager.findFragmentByTag(str);
        if (supportUiListener != null) {
            return supportUiListener;
        }
        LogUtil.m9i("SupportUiListener.create", GeneratedOutlineSupport.outline8("creating new SupportUiListener for ", str), new Object[0]);
        SupportUiListener<OutputT> supportUiListener2 = new SupportUiListener<>();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add((Fragment) supportUiListener2, str);
        beginTransaction.commitAllowingStateLoss();
        return supportUiListener2;
    }

    public void listen(Context context, ListenableFuture<OutputT> listenableFuture, DialerExecutor.SuccessListener<OutputT> successListener, DialerExecutor.FailureListener failureListener) {
        Assert.isNotNull(successListener);
        Assert.isNotNull(failureListener);
        this.callbackWrapper = new CallbackWrapper<>(successListener, failureListener, (C04561) null);
        Assert.isNotNull(listenableFuture);
        Futures.addCallback(listenableFuture, this.callbackWrapper, DialerExecutorComponent.get(context).uiExecutor());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void onDetach() {
        super.onDetach();
        LogUtil.enterBlock("SupportUiListener.onDetach");
        CallbackWrapper<OutputT> callbackWrapper2 = this.callbackWrapper;
        if (callbackWrapper2 != null) {
            DialerExecutor.SuccessListener unused = callbackWrapper2.successListener = null;
            DialerExecutor.FailureListener unused2 = this.callbackWrapper.failureListener = null;
        }
    }
}
