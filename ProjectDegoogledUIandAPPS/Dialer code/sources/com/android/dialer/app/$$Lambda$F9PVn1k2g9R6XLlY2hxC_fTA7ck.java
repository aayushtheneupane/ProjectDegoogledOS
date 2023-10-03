package com.android.dialer.app;

import android.content.Context;
import android.provider.CallLog;
import com.android.dialer.common.concurrent.DialerExecutor;

/* renamed from: com.android.dialer.app.-$$Lambda$F9PVn1k2g9R6XLlY2hxC_fTA7ck  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$F9PVn1k2g9R6XLlY2hxC_fTA7ck implements DialerExecutor.Worker {
    public static final /* synthetic */ $$Lambda$F9PVn1k2g9R6XLlY2hxC_fTA7ck INSTANCE = new $$Lambda$F9PVn1k2g9R6XLlY2hxC_fTA7ck();

    private /* synthetic */ $$Lambda$F9PVn1k2g9R6XLlY2hxC_fTA7ck() {
    }

    public final Object doInBackground(Object obj) {
        return CallLog.Calls.getLastOutgoingCall((Context) obj);
    }
}
