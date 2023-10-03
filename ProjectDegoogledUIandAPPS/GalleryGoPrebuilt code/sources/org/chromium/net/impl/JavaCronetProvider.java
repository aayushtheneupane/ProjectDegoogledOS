package org.chromium.net.impl;

import android.content.Context;
import java.util.Arrays;

/* compiled from: PG */
public class JavaCronetProvider extends ise {
    public JavaCronetProvider(Context context) {
        super(context);
    }

    /* renamed from: a */
    public final String mo9071a() {
        return "Fallback-Cronet-Provider";
    }

    /* renamed from: b */
    public final String mo9072b() {
        return "80.0.3970.3";
    }

    /* renamed from: c */
    public final boolean mo9073c() {
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj == this || ((obj instanceof JavaCronetProvider) && this.f14999a.equals(((JavaCronetProvider) obj).f14999a))) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{JavaCronetProvider.class, this.f14999a});
    }
}
