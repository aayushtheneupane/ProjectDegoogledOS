package p000;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;

/* renamed from: ggq */
/* compiled from: PG */
public final class ggq {
    /* renamed from: a */
    static gqb m10278a(int i) {
        if (i == 0) {
            return new ggx();
        }
        if (i != 1) {
            return m10282b();
        }
        return new ggp();
    }

    /* renamed from: b */
    static gqb m10282b() {
        return new ggx();
    }

    /* renamed from: a */
    static ggq m10277a() {
        return new ggq();
    }

    /* renamed from: a */
    public static void m10281a(View view, ggu ggu) {
        gfk gfk = ggu.f11293a.f11275b;
        if (gfk != null && gfk.f11167a) {
            float f = 0.0f;
            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                f += C0340mj.m14721l((View) parent);
            }
            ggt ggt = ggu.f11293a;
            if (ggt.f11286m != f) {
                ggt.f11286m = f;
                ggu.mo6638c();
            }
        }
    }

    /* renamed from: a */
    public static Object m10280a(Object obj) {
        ife.m12876b(obj instanceof hbf, (Object) "Given class does not have a peer");
        return ((hbf) obj).mo2635n();
    }

    /* renamed from: a */
    public static Object m10279a(Context context, Class cls, gkn gkn) {
        Context applicationContext = context.getApplicationContext();
        ife.m12845a(applicationContext instanceof fti, (Object) "Given application context does not implement AccountComponentManager");
        try {
            return cls.cast(((fti) applicationContext).mo2452a(gkn));
        } catch (ClassCastException e) {
            throw new IllegalStateException("Failed to get an entry point. Did you mark your interface with @SingletonAccountEntryPoint?", e);
        }
    }
}
