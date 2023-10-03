package p000;

import android.content.Context;
import android.os.Build;

/* renamed from: bm */
/* compiled from: PG */
public final class C0042bm implements C0034be {

    /* renamed from: a */
    private final Context f3128a;

    /* renamed from: b */
    private final String f3129b;

    /* renamed from: c */
    private final C0030ba f3130c;

    /* renamed from: d */
    private final Object f3131d = new Object();

    /* renamed from: e */
    private C0041bl f3132e;

    /* renamed from: f */
    private boolean f3133f;

    public C0042bm(Context context, String str, C0030ba baVar) {
        this.f3128a = context;
        this.f3129b = str;
        this.f3130c = baVar;
    }

    public final void close() {
        m3222b().close();
    }

    /* renamed from: b */
    private final C0041bl m3222b() {
        C0041bl blVar;
        synchronized (this.f3131d) {
            if (this.f3132e == null) {
                int i = Build.VERSION.SDK_INT;
                this.f3132e = new C0041bl(this.f3128a, this.f3129b, new C0039bj[1], this.f3130c);
                int i2 = Build.VERSION.SDK_INT;
                this.f3132e.setWriteAheadLoggingEnabled(this.f3133f);
            }
            blVar = this.f3132e;
        }
        return blVar;
    }

    /* renamed from: a */
    public final C0028az mo1892a() {
        return m3222b().mo2541a();
    }

    /* renamed from: a */
    public final void mo1893a(boolean z) {
        synchronized (this.f3131d) {
            C0041bl blVar = this.f3132e;
            if (blVar != null) {
                blVar.setWriteAheadLoggingEnabled(z);
            }
            this.f3133f = z;
        }
    }
}
