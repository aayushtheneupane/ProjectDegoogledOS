package p000;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.apps.photosgo.oneup.photo.ScalePhotoView;

/* renamed from: dre */
/* compiled from: PG */
final class dre extends bek {

    /* renamed from: a */
    private final /* synthetic */ drg f7205a;

    public dre(drg drg) {
        this.f7205a = drg;
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
    }

    /* renamed from: a */
    public final void mo1432a(Drawable drawable) {
        cwn.m5510a("OneUpPhotoViewPeer: Unable to load preview bitmap", new Object[0]);
        this.f7205a.mo4369j();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1433a(Object obj, bex bex) {
        Bitmap bitmap = (Bitmap) obj;
        if (!this.f7205a.f7215g.isPresent()) {
            cwn.m5510a("OneUpPhotoViewPeer: no media after preview loaded", new Object[0]);
            return;
        }
        cxi cxi = (cxi) this.f7205a.f7215g.get();
        this.f7205a.f7213e.mo1993a(cxi.f5925q);
        Bitmap copy = bitmap.copy(bitmap.getConfig(), false);
        ScalePhotoView scalePhotoView = this.f7205a.f7213e;
        bfz a = bfz.m2453a(Uri.parse(cxi.f5910b));
        int i = cxi.f5923o;
        int i2 = cxi.f5924p;
        if (a.f2227b == null) {
            a.f2230e = i;
            a.f2231f = i2;
        }
        if (copy != null) {
            scalePhotoView.mo1999a(a, new bfz(copy));
            this.f7205a.mo4369j();
            return;
        }
        throw new NullPointerException("Bitmap must not be null");
    }
}
