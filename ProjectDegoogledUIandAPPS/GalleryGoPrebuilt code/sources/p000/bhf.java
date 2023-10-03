package p000;

import android.os.Bundle;
import android.os.Parcel;

/* renamed from: bhf */
/* compiled from: PG */
public final class bhf {

    /* renamed from: a */
    private static Boolean f2370a = null;

    /* renamed from: a */
    private static void m2531a(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    public static synchronized boolean m2532a() {
        boolean booleanValue;
        synchronized (bhf.class) {
            if (f2370a == null) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "value");
                Parcel a = m2530a(bundle);
                try {
                    boolean z = false;
                    m2531a(a.readInt() > 0);
                    m2531a(a.readInt() == 1279544898);
                    if (a.readInt() == 1) {
                        z = true;
                    }
                    m2531a(z);
                    f2370a = Boolean.valueOf("key".equals(a.readString()));
                } catch (RuntimeException e) {
                    try {
                        f2370a = Boolean.FALSE;
                    } catch (Throwable th) {
                        a.recycle();
                        throw th;
                    }
                }
                a.recycle();
            }
            booleanValue = f2370a.booleanValue();
        }
        return booleanValue;
    }

    /* renamed from: a */
    public static Parcel m2530a(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        bundle.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        return obtain;
    }
}
