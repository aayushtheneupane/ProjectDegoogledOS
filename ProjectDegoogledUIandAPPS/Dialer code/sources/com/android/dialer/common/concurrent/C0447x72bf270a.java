package com.android.dialer.common.concurrent;

import com.android.dialer.common.concurrent.DialerExecutor;

/* renamed from: com.android.dialer.common.concurrent.-$$Lambda$DefaultDialerExecutorFactory$BaseTaskBuilder$D6vDBb1osF1Sb57-3h9xIG737pg */
/* compiled from: lambda */
public final /* synthetic */ class C0447x72bf270a implements DialerExecutor.FailureListener {
    public static final /* synthetic */ C0447x72bf270a INSTANCE = new C0447x72bf270a();

    private /* synthetic */ C0447x72bf270a() {
    }

    public final void onFailure(Throwable th) {
        throw new RuntimeException(th);
    }
}
