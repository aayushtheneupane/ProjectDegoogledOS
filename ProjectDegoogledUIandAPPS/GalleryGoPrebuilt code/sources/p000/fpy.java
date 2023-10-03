package p000;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;

/* renamed from: fpy */
/* compiled from: PG */
public final class fpy implements fpv {

    /* renamed from: b */
    private static fpy f10236b;

    /* renamed from: a */
    public final Context f10237a;

    /* renamed from: c */
    private final ContentObserver f10238c;

    private fpy() {
        this.f10237a = null;
        this.f10238c = null;
    }

    private fpy(Context context) {
        this.f10237a = context;
        this.f10238c = new fpx();
        context.getContentResolver().registerContentObserver(exi.f9170a, true, this.f10238c);
    }

    /* renamed from: a */
    public static synchronized void m9390a() {
        synchronized (fpy.class) {
            if (!(f10236b == null || f10236b.f10237a == null || f10236b.f10238c == null)) {
                f10236b.f10237a.getContentResolver().unregisterContentObserver(f10236b.f10238c);
            }
            f10236b = null;
        }
    }

    /* renamed from: b */
    public final String mo6022a(String str) {
        if (this.f10237a == null) {
            return null;
        }
        try {
            return (String) fpt.m9378a((fpu) new fpw(this, str));
        } catch (IllegalStateException | SecurityException e) {
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() == 0 ? new String("Unable to read GServices for: ") : "Unable to read GServices for: ".concat(valueOf), e);
            return null;
        }
    }

    /* renamed from: a */
    static fpy m9389a(Context context) {
        fpy fpy;
        synchronized (fpy.class) {
            if (f10236b == null) {
                f10236b = C0071co.m4653a(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") != 0 ? new fpy() : new fpy(context);
            }
            fpy = f10236b;
        }
        return fpy;
    }
}
