package p000;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.io.IOException;

/* renamed from: dpr */
/* compiled from: PG */
final class dpr extends bek {

    /* renamed from: a */
    private final /* synthetic */ dpt f6997a;

    public dpr(dpt dpt) {
        this.f6997a = dpt;
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
    }

    /* renamed from: a */
    public final void mo1432a(Drawable drawable) {
        this.f6997a.mo4327a(new IOException("Failed to load full size image"));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1433a(Object obj, bex bex) {
        dpt dpt = this.f6997a;
        dpt.f7016h.mo6987a(dpt.f7017i.mo2829a((Bitmap) obj), dpt.f7001C);
    }
}
