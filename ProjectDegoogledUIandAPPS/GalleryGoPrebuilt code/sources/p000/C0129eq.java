package p000;

import android.app.Application;

/* renamed from: eq */
/* compiled from: PG */
final class C0129eq implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Application f8820a;

    /* renamed from: b */
    private final /* synthetic */ C0131es f8821b;

    public C0129eq(Application application, C0131es esVar) {
        this.f8820a = application;
        this.f8821b = esVar;
    }

    public final void run() {
        this.f8820a.unregisterActivityLifecycleCallbacks(this.f8821b);
    }
}
