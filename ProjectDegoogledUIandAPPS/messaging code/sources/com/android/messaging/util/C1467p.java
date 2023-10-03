package com.android.messaging.util;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/* renamed from: com.android.messaging.util.p */
public class C1467p {
    /* access modifiers changed from: private */

    /* renamed from: PJ */
    public volatile int f2333PJ = 3;

    /* renamed from: QJ */
    private final PhoneStateListener f2334QJ = new C1463n(this);
    private C1465o mListener;
    private final TelephonyManager mTelephonyManager;

    public C1467p(Context context) {
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
    }

    /* renamed from: b */
    static /* synthetic */ void m3766b(C1467p pVar, int i) {
        C1465o oVar = pVar.mListener;
        if (oVar != null) {
            oVar.mo6075b(i);
        }
    }

    public void unregister() {
        TelephonyManager telephonyManager;
        if (!(this.mListener == null || (telephonyManager = this.mTelephonyManager) == null)) {
            telephonyManager.listen(this.f2334QJ, 0);
            this.f2333PJ = 3;
        }
        this.mListener = null;
    }

    /* renamed from: a */
    public void mo8199a(C1465o oVar) {
        C1465o oVar2 = this.mListener;
        int i = 0;
        C1424b.m3592ia(oVar2 == null || oVar2 == oVar);
        if (this.mListener == null && this.mTelephonyManager != null) {
            if (C1474sa.getDefault().mo8227jk()) {
                i = 3;
            }
            this.f2333PJ = i;
            this.mTelephonyManager.listen(this.f2334QJ, 1);
        }
        this.mListener = oVar;
    }

    public C1467p(Context context, int i) {
        this.mTelephonyManager = ((TelephonyManager) context.getSystemService("phone")).createForSubscriptionId(i);
    }
}
