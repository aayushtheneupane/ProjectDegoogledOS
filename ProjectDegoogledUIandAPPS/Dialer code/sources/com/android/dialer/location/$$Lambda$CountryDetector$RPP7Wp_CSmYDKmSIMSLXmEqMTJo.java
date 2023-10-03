package com.android.dialer.location;

import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;

/* renamed from: com.android.dialer.location.-$$Lambda$CountryDetector$RPP7Wp_CSmYDKmSIMSLXmEqMTJo  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CountryDetector$RPP7Wp_CSmYDKmSIMSLXmEqMTJo implements DialerExecutor.FailureListener {
    public static final /* synthetic */ $$Lambda$CountryDetector$RPP7Wp_CSmYDKmSIMSLXmEqMTJo INSTANCE = new $$Lambda$CountryDetector$RPP7Wp_CSmYDKmSIMSLXmEqMTJo();

    private /* synthetic */ $$Lambda$CountryDetector$RPP7Wp_CSmYDKmSIMSLXmEqMTJo() {
    }

    public final void onFailure(Throwable th) {
        LogUtil.m10w("CountryDetector.processLocationUpdate", "exception occurred when getting geocoded country from location", th);
    }
}
