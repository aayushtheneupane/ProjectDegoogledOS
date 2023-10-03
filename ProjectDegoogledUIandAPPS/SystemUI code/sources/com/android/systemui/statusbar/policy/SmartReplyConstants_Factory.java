package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Handler;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SmartReplyConstants_Factory implements Factory<SmartReplyConstants> {
    private final Provider<Context> contextProvider;
    private final Provider<Handler> handlerProvider;

    public SmartReplyConstants_Factory(Provider<Handler> provider, Provider<Context> provider2) {
        this.handlerProvider = provider;
        this.contextProvider = provider2;
    }

    public SmartReplyConstants get() {
        return provideInstance(this.handlerProvider, this.contextProvider);
    }

    public static SmartReplyConstants provideInstance(Provider<Handler> provider, Provider<Context> provider2) {
        return new SmartReplyConstants(provider.get(), provider2.get());
    }

    public static SmartReplyConstants_Factory create(Provider<Handler> provider, Provider<Context> provider2) {
        return new SmartReplyConstants_Factory(provider, provider2);
    }
}
