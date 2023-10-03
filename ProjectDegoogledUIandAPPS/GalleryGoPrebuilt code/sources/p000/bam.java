package p000;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* renamed from: bam */
/* compiled from: PG */
public final class bam implements aua, atv {

    /* renamed from: a */
    private final Resources f1960a;

    /* renamed from: b */
    private final aua f1961b;

    private bam(Resources resources, aua aua) {
        this.f1960a = (Resources) cns.m4632a((Object) resources);
        this.f1961b = (aua) cns.m4632a((Object) aua);
    }

    /* renamed from: a */
    public final Class mo1604a() {
        return BitmapDrawable.class;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo1605b() {
        return new BitmapDrawable(this.f1960a, (Bitmap) this.f1961b.mo1605b());
    }

    /* renamed from: c */
    public final int mo1606c() {
        return this.f1961b.mo1606c();
    }

    /* renamed from: e */
    public final void mo1621e() {
        aua aua = this.f1961b;
        if (aua instanceof atv) {
            ((atv) aua).mo1621e();
        }
    }

    /* renamed from: a */
    public static aua m2043a(Resources resources, aua aua) {
        if (aua != null) {
            return new bam(resources, aua);
        }
        return null;
    }

    /* renamed from: d */
    public final void mo1607d() {
        this.f1961b.mo1607d();
    }
}
