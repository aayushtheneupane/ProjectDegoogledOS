package p000;

import android.content.Context;
import android.graphics.Bitmap;
import java.security.MessageDigest;

/* renamed from: bbw */
/* compiled from: PG */
public final class bbw implements ard {

    /* renamed from: b */
    private final ard f2016b;

    public bbw(ard ard) {
        this.f2016b = (ard) cns.m4632a((Object) ard);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof bbw) {
            return this.f2016b.equals(((bbw) obj).f2016b);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2016b.hashCode();
    }

    /* renamed from: a */
    public final aua mo1497a(Context context, aua aua, int i, int i2) {
        bbt bbt = (bbt) aua.mo1605b();
        azk azk = new azk(bbt.mo1783a(), aow.m1346a(context).f1289b);
        aua a = this.f2016b.mo1497a(context, azk, i, i2);
        if (!azk.equals(a)) {
            azk.mo1607d();
        }
        ard ard = this.f2016b;
        bbt.f2006a.f2005a.mo1801a(ard, (Bitmap) a.mo1605b());
        return aua;
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        this.f2016b.mo1494a(messageDigest);
    }
}
