package p000;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.io.IOException;

/* renamed from: bvt */
/* compiled from: PG */
final class bvt extends bek {

    /* renamed from: a */
    private final /* synthetic */ bvv f3686a;

    public bvt(bvv bvv) {
        this.f3686a = bvv;
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
    }

    /* renamed from: a */
    public final void mo1432a(Drawable drawable) {
        this.f3686a.mo2805a((Throwable) new IOException("EditorFragment: Glide failed to load image for saving."));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1433a(Object obj, bex bex) {
        bvv bvv = this.f3686a;
        bvv.f3736r.mo6987a(bvv.f3733o.mo2829a((Bitmap) obj), bvv.f3706S);
    }
}
