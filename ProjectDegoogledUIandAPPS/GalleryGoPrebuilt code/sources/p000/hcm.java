package p000;

import android.app.Application;
import android.content.Context;

/* renamed from: hcm */
/* compiled from: PG */
public final /* synthetic */ class hcm implements hbc {

    /* renamed from: a */
    private final Context f12473a;

    /* renamed from: b */
    private final iqk f12474b;

    /* renamed from: c */
    private final iqk f12475c;

    public hcm(Context context, iqk iqk, iqk iqk2) {
        this.f12473a = context;
        this.f12474b = iqk;
        this.f12475c = iqk2;
    }

    /* renamed from: a */
    public final void mo7253a() {
        Application application = (Application) this.f12473a;
        application.registerActivityLifecycleCallbacks(new hcn(application, this.f12474b, this.f12475c));
    }
}
