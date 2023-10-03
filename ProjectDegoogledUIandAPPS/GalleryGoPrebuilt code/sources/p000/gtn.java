package p000;

import android.net.Uri;

/* renamed from: gtn */
/* compiled from: PG */
final /* synthetic */ class gtn implements Runnable {

    /* renamed from: a */
    private final gto f12024a;

    /* renamed from: b */
    private final boolean f12025b;

    /* renamed from: c */
    private final Uri f12026c;

    public gtn(gto gto, boolean z, Uri uri) {
        this.f12024a = gto;
        this.f12025b = z;
        this.f12026c = uri;
    }

    public final void run() {
        gto gto = this.f12024a;
        boolean z = this.f12025b;
        Object[] objArr = {this.f12026c, Boolean.valueOf(z)};
        deo deo = gto.f12027a;
        if (!deo.f6403h && !z) {
            ((dge) deo.f6397b.mo9034a()).mo4114a();
        }
    }
}
