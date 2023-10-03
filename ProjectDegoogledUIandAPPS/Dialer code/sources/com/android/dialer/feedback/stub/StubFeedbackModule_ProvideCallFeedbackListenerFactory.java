package com.android.dialer.feedback.stub;

import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import com.android.dialer.common.LogUtil;
import com.android.incallui.call.CallList;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StubFeedbackModule_ProvideCallFeedbackListenerFactory implements Factory<CallList.Listener> {
    private final Provider<Context> contextProvider;

    public StubFeedbackModule_ProvideCallFeedbackListenerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public Object get() {
        LogUtil.m9i("StubFeedbackModule.provideCallFeedbackListener", "returning stub", new Object[0]);
        CallFeedbackListenerStub callFeedbackListenerStub = new CallFeedbackListenerStub(this.contextProvider.get());
        R$style.checkNotNull1(callFeedbackListenerStub, "Cannot return null from a non-@Nullable @Provides method");
        return callFeedbackListenerStub;
    }
}
