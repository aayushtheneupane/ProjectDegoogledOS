package com.android.dialer.common.concurrent;

import android.app.FragmentManager;
import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;
import com.google.common.util.concurrent.ListeningExecutorService;

public abstract class DialerExecutorComponent {

    public interface HasComponent {
    }

    public static DialerExecutorComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).dialerExecutorComponent();
    }

    public abstract ListeningExecutorService backgroundExecutor();

    public <OutputT> UiListener<OutputT> createUiListener(FragmentManager fragmentManager, String str) {
        return UiListener.create(fragmentManager, str);
    }

    public abstract DialerExecutorFactory dialerExecutorFactory();

    public abstract ListeningExecutorService lightweightExecutor();

    public abstract ListeningExecutorService uiExecutor();

    public <OutputT> SupportUiListener<OutputT> createUiListener(android.support.p000v4.app.FragmentManager fragmentManager, String str) {
        return SupportUiListener.create(fragmentManager, str);
    }
}
