package p000;

import android.animation.TimeInterpolator;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: bvs */
/* compiled from: PG */
final class bvs extends bek {

    /* renamed from: a */
    private final /* synthetic */ bvv f3685a;

    public bvs(bvv bvv) {
        this.f3685a = bvv;
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
        TimeInterpolator timeInterpolator = bvv.f3687a;
    }

    /* renamed from: a */
    public final void mo1432a(Drawable drawable) {
        cwn.m5510a("EditorFragment: Unable to load bitmap for editing.", new Object[0]);
        bvv bvv = this.f3685a;
        bvv.f3724f.mo2572a((int) R.string.editor_image_unsupported);
        if (!bvv.f3720b.f3633d) {
            bvv.f3721c.mo3275f();
        } else {
            ihg.m13041a((hoi) new buj(false, Optional.empty()), (C0147fh) bvv.f3722d);
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1433a(Object obj, bex bex) {
        Bitmap bitmap = (Bitmap) obj;
        this.f3685a.mo2804a(bitmap.copy(bitmap.getConfig(), false));
    }
}
