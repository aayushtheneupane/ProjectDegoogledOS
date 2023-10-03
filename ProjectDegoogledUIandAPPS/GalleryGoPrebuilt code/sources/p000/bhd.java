package p000;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: bhd */
/* compiled from: PG */
public final class bhd {

    /* renamed from: a */
    public static final Map f2361a = new C0290kn();

    /* renamed from: b */
    public final bhn f2362b = new bhm(this);

    /* renamed from: c */
    public final bgw f2363c;

    /* renamed from: d */
    public final Context f2364d;

    /* renamed from: e */
    public final bgy f2365e;

    /* renamed from: f */
    public final bhc f2366f;

    /* renamed from: g */
    public final ScheduledExecutorService f2367g;

    public bhd(bgw bgw, Context context, bgy bgy, bhc bhc) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new bgz());
        this.f2363c = bgw;
        this.f2364d = context;
        this.f2365e = bgy;
        this.f2367g = newSingleThreadScheduledExecutor;
        this.f2366f = bhc;
    }

    /* renamed from: a */
    public final void mo2037a(bhw bhw) {
        if (bhw != null) {
            this.f2367g.execute(new bha(this, bhw));
        }
    }

    /* renamed from: a */
    static void m2528a(bhw bhw, boolean z) {
        bic bic;
        synchronized (f2361a) {
            bic = (bic) f2361a.get(bhw.f2413b);
        }
        if (bic != null) {
            bic.mo2076a(bhw, z);
            if (bic.mo2077a()) {
                synchronized (f2361a) {
                    f2361a.remove(bhw.f2413b);
                }
            }
        }
    }
}
