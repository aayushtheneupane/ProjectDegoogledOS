package p000;

import android.content.Context;

/* renamed from: ise */
/* compiled from: PG */
public abstract class ise {

    /* renamed from: a */
    public final Context f14999a;

    static {
        ise.class.getSimpleName();
    }

    /* renamed from: a */
    public abstract String mo9071a();

    /* renamed from: b */
    public abstract String mo9072b();

    /* renamed from: c */
    public abstract boolean mo9073c();

    protected ise(Context context) {
        if (context != null) {
            this.f14999a = context;
            return;
        }
        throw new IllegalArgumentException("Context must not be null");
    }

    public final String toString() {
        return "[class=" + getClass().getName() + ", name=" + mo9071a() + ", version=" + mo9072b() + ", enabled=" + mo9073c() + "]";
    }
}
