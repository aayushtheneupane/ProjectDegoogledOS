package p000;

import android.graphics.Bitmap;

/* renamed from: bwa */
/* compiled from: PG */
final /* synthetic */ class bwa implements Runnable {

    /* renamed from: a */
    private final bwe f3753a;

    /* renamed from: b */
    private final Bitmap f3754b;

    public bwa(bwe bwe, Bitmap bitmap) {
        this.f3753a = bwe;
        this.f3754b = bitmap;
    }

    public final void run() {
        bwe bwe = this.f3753a;
        bwe.f3761b.initializeThumbnailProcessor(bwe.f3760a, this.f3754b);
    }
}
