package com.android.dialer.speeddial;

import com.android.dialer.common.concurrent.DialerExecutor;

/* renamed from: com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$gcZVSGNwrDGK6-iy-qrYfRDKehE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SpeedDialFragment$gcZVSGNwrDGK6iyqrYfRDKehE implements DialerExecutor.FailureListener {
    public static final /* synthetic */ $$Lambda$SpeedDialFragment$gcZVSGNwrDGK6iyqrYfRDKehE INSTANCE = new $$Lambda$SpeedDialFragment$gcZVSGNwrDGK6iyqrYfRDKehE();

    private /* synthetic */ $$Lambda$SpeedDialFragment$gcZVSGNwrDGK6iyqrYfRDKehE() {
    }

    public final void onFailure(Throwable th) {
        SpeedDialFragment.lambda$loadContacts$1(th);
        throw null;
    }
}
