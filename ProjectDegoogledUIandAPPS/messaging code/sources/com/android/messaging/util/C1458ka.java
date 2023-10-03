package com.android.messaging.util;

import android.net.Uri;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.ka */
final class C1458ka {
    int code;
    boolean looping;
    long requestTime;
    Uri uri;
    float volume;

    /* renamed from: xK */
    int f2310xK;

    /* renamed from: yK */
    boolean f2311yK;

    private C1458ka() {
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("{ code=");
        Pa.append(this.code);
        Pa.append(" looping=");
        Pa.append(this.looping);
        Pa.append(" stream=");
        Pa.append(this.f2310xK);
        Pa.append(" uri=");
        Pa.append(this.uri);
        Pa.append(" }");
        return Pa.toString();
    }

    /* synthetic */ C1458ka(C1454ia iaVar) {
    }
}
