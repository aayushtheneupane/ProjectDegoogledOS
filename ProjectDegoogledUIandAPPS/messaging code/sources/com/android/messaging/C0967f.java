package com.android.messaging;

import android.content.Context;
import com.android.messaging.datamodel.C0761B;
import com.android.messaging.datamodel.C0774O;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.p038b.C0840C;
import com.android.messaging.datamodel.p038b.C0866f;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.sms.C1014j;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1451h;
import com.android.messaging.util.C1452ha;
import com.android.messaging.util.C1474sa;

/* renamed from: com.android.messaging.f */
public abstract class C0967f {
    protected static boolean sInitialized;
    private static volatile C0967f sInstance;
    protected static boolean sRegistered;

    /* renamed from: c */
    protected static void m2176c(C0967f fVar) {
        C1424b.m3592ia(!sRegistered);
        C1424b.m3592ia(!sInitialized);
        sInstance = fVar;
    }

    public static C0967f get() {
        return sInstance;
    }

    /* renamed from: Hd */
    public abstract C1451h mo6645Hd();

    /* renamed from: Id */
    public abstract C1449g mo6646Id();

    /* renamed from: Jd */
    public abstract C0774O mo6647Jd();

    /* renamed from: Kd */
    public abstract C0866f mo6648Kd();

    /* renamed from: Ld */
    public abstract C0840C mo6649Ld();

    /* renamed from: Md */
    public abstract C1452ha mo6650Md();

    /* renamed from: Nd */
    public abstract C0761B mo6651Nd();

    /* renamed from: Od */
    public abstract C1040Ea mo6652Od();

    /* renamed from: Pd */
    public abstract C1451h mo6653Pd();

    /* renamed from: Qd */
    public abstract void mo6654Qd();

    /* renamed from: Rd */
    public abstract void mo6655Rd();

    /* renamed from: Sd */
    public abstract void mo6656Sd();

    /* renamed from: ga */
    public abstract C1474sa mo6657ga(int i);

    public abstract Context getApplicationContext();

    public abstract C1014j getCarrierConfigValuesLoader();

    public abstract C0947h getDataModel();

    /* renamed from: ha */
    public abstract C1451h mo6661ha(int i);
}
