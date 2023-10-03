package p000;

import android.os.Build;
import android.util.Log;

/* renamed from: hfn */
/* compiled from: PG */
public final /* synthetic */ class hfn implements hbc {

    /* renamed from: a */
    public static final hbc f12657a = new hfn();

    private hfn() {
    }

    /* renamed from: a */
    public final void mo7253a() {
        try {
            int i = Build.VERSION.SDK_INT;
            int i2 = Build.VERSION.SDK_INT;
        } catch (SecurityException e) {
            Log.w("PrngFixes", "Failed to apply the fix for OpenSSL PRNG having low entropy", e);
        }
        try {
            int i3 = Build.VERSION.SDK_INT;
        } catch (SecurityException e2) {
            Log.w("PrngFixes", "Failed to install a Linux PRNG-backed SecureRandom impl as default", e2);
        }
    }
}
