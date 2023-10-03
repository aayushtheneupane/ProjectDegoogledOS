package p000;

import android.content.Context;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;

/* renamed from: aqv */
/* compiled from: PG */
public final class aqv implements ard {

    /* renamed from: b */
    private final Collection f1467b;

    @SafeVarargs
    public aqv(ard... ardArr) {
        this.f1467b = Arrays.asList(ardArr);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof aqv) {
            return this.f1467b.equals(((aqv) obj).f1467b);
        }
        return false;
    }

    public final int hashCode() {
        return this.f1467b.hashCode();
    }

    /* renamed from: a */
    public final aua mo1497a(Context context, aua aua, int i, int i2) {
        aua aua2 = aua;
        for (ard a : this.f1467b) {
            aua a2 = a.mo1497a(context, aua2, i, i2);
            if (aua2 != null && !aua2.equals(aua) && !aua2.equals(a2)) {
                aua2.mo1607d();
            }
            aua2 = a2;
        }
        return aua2;
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        for (ard a : this.f1467b) {
            a.mo1494a(messageDigest);
        }
    }
}
