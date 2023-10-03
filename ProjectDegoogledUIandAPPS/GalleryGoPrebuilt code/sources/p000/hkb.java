package p000;

import android.os.StrictMode;

/* renamed from: hkb */
/* compiled from: PG */
final class hkb implements hkd {
    /* renamed from: a */
    public final StrictMode.ThreadPolicy mo7505a() {
        return new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
    }

    /* renamed from: b */
    public final StrictMode.ThreadPolicy mo7506b() {
        return new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyLog().build();
    }
}
