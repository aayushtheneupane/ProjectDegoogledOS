package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.security.MessageDigest;

/* renamed from: baf */
/* compiled from: PG */
public final class baf implements ard {

    /* renamed from: b */
    private final ard f1944b;

    /* renamed from: c */
    private final boolean f1945c;

    public baf(ard ard, boolean z) {
        this.f1944b = ard;
        this.f1945c = z;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof baf) {
            return this.f1944b.equals(((baf) obj).f1944b);
        }
        return false;
    }

    public final int hashCode() {
        return this.f1944b.hashCode();
    }

    /* renamed from: a */
    public final aua mo1497a(Context context, aua aua, int i, int i2) {
        auk auk = aow.m1346a(context).f1289b;
        Drawable drawable = (Drawable) aua.mo1605b();
        aua a = bae.m2022a(auk, drawable, i, i2);
        if (a != null) {
            aua a2 = this.f1944b.mo1497a(context, a, i, i2);
            if (!a2.equals(a)) {
                return bam.m2043a(context.getResources(), a2);
            }
            a2.mo1607d();
            return aua;
        } else if (!this.f1945c) {
            return aua;
        } else {
            String valueOf = String.valueOf(drawable);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
            sb.append("Unable to convert ");
            sb.append(valueOf);
            sb.append(" to a Bitmap");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        this.f1944b.mo1494a(messageDigest);
    }
}
