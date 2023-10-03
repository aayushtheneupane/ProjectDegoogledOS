package p000;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/* renamed from: dps */
/* compiled from: PG */
final class dps extends bek {

    /* renamed from: a */
    private final /* synthetic */ dpt f6998a;

    public dps(dpt dpt) {
        this.f6998a = dpt;
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
    }

    /* renamed from: a */
    public final void mo1432a(Drawable drawable) {
        cwn.m5510a("AutoEnhanceFragment: Unable to load preview bitmap", new Object[0]);
        this.f6998a.f7011c.mo3274e();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1433a(Object obj, bex bex) {
        Bitmap bitmap = (Bitmap) obj;
        Bitmap copy = bitmap.copy(bitmap.getConfig(), false);
        this.f6998a.f7024p.setImageBitmap(copy);
        dpt dpt = this.f6998a;
        dpt.f7016h.mo6987a(dpt.f7017i.mo2829a(copy), dpt.f7034z);
        dpt.f7025q.setImageBitmap(copy);
    }
}
