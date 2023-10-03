package p000;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: grs */
/* compiled from: PG */
public final class grs {

    /* renamed from: a */
    public static final hvy f11925a = hvy.m12261a("com/google/apps/tiktok/concurrent/futuresmixin/CallbackIdMap");

    /* renamed from: b */
    public static final AtomicInteger f11926b = new AtomicInteger(123051698);

    /* renamed from: c */
    public final String f11927c;

    /* renamed from: d */
    public final C0290kn f11928d = new C0290kn();

    /* renamed from: e */
    public final C0290kn f11929e = new C0290kn();

    public grs(String str) {
        ife.m12845a(!hpz.m11899a(str), (Object) "mapKey must be a non-empty, non-null static String unique to the class using CallbackIdMap.");
        this.f11927c = str;
    }

    /* renamed from: b */
    public final void mo6985b() {
        fxk.m9830b();
        this.f11928d.clear();
    }

    /* renamed from: a */
    public final Object mo6983a(int i) {
        fxk.m9830b();
        Object obj = this.f11928d.get(Integer.valueOf(i));
        if (obj == null) {
            for (Map.Entry entry : this.f11929e.entrySet()) {
                if (((Integer) entry.getValue()).intValue() == i) {
                    String valueOf = String.valueOf(((Class) entry.getKey()).getName());
                    throw new NullPointerException(valueOf.length() == 0 ? new String("Callback not re-registered for: ") : "Callback not re-registered for: ".concat(valueOf));
                }
            }
        }
        return ife.m12828a(obj, "No callback existed for %s", i);
    }

    /* renamed from: a */
    public final void mo6984a() {
        fxk.m9830b();
        for (Map.Entry entry : this.f11929e.entrySet()) {
            ife.m12878b(this.f11928d.containsKey(entry.getValue()), "Did not restore a callback for %s. You must re-register all callbacks you previously had after a configuration change, so that you don't lose user state.", entry.getKey());
        }
    }
}
