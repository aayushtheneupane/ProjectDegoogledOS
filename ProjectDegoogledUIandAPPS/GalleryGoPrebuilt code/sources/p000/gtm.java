package p000;

import java.util.Set;

/* renamed from: gtm */
/* compiled from: PG */
public final class gtm implements gtj {

    /* renamed from: a */
    public static final hvy f12022a = hvy.m12261a("com/google/apps/tiktok/core/UncaughtExceptionHandlerProcessInitializer");

    /* renamed from: b */
    public final Set f12023b;

    public gtm(Set set) {
        this.f12023b = set;
    }

    /* renamed from: a */
    public final void mo7033a() {
        Thread.setDefaultUncaughtExceptionHandler(new gtl(this, Thread.getDefaultUncaughtExceptionHandler()));
    }
}
