package p000;

import android.graphics.Bitmap;
import java.util.concurrent.Callable;

/* renamed from: bwq */
/* compiled from: PG */
final /* synthetic */ class bwq implements Callable {

    /* renamed from: a */
    private final bwr f3773a;

    /* renamed from: b */
    private final Bitmap f3774b;

    /* renamed from: c */
    private final bwy f3775c;

    public bwq(bwr bwr, Bitmap bitmap, bwy bwy) {
        this.f3773a = bwr;
        this.f3774b = bitmap;
        this.f3775c = bwy;
    }

    public final Object call() {
        bwr bwr = this.f3773a;
        Bitmap bitmap = this.f3774b;
        bwy bwy = this.f3775c;
        return Bitmap.createScaledBitmap(bitmap, bwy.f3798b, bwy.f3799c, bwr.f3777b.mo3175a());
    }
}
