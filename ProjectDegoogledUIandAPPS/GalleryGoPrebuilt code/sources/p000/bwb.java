package p000;

import android.graphics.Bitmap;
import com.google.android.apps.photosgo.editor.nativerenderer.NativeRenderer;

/* renamed from: bwb */
/* compiled from: PG */
final /* synthetic */ class bwb implements Runnable {

    /* renamed from: a */
    private final bwe f3755a;

    /* renamed from: b */
    private final Bitmap f3756b;

    public bwb(bwe bwe, Bitmap bitmap) {
        this.f3755a = bwe;
        this.f3756b = bitmap;
    }

    public final void run() {
        bwe bwe = this.f3755a;
        Bitmap bitmap = this.f3756b;
        ((NativeRenderer) bwe.f3761b).nativeInitializeImage(bwe.f3760a, bitmap);
    }
}
