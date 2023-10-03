package com.android.dialer.main.impl.bottomnav;

import com.android.dialer.common.concurrent.DialerExecutor;

/* renamed from: com.android.dialer.main.impl.bottomnav.-$$Lambda$MissedCallCountObserver$r6w374MbDCYDIZevAOMh7AbyepU  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MissedCallCountObserver$r6w374MbDCYDIZevAOMh7AbyepU implements DialerExecutor.FailureListener {
    public static final /* synthetic */ $$Lambda$MissedCallCountObserver$r6w374MbDCYDIZevAOMh7AbyepU INSTANCE = new $$Lambda$MissedCallCountObserver$r6w374MbDCYDIZevAOMh7AbyepU();

    private /* synthetic */ $$Lambda$MissedCallCountObserver$r6w374MbDCYDIZevAOMh7AbyepU() {
    }

    public final void onFailure(Throwable th) {
        MissedCallCountObserver.lambda$onChange$2(th);
        throw null;
    }
}
