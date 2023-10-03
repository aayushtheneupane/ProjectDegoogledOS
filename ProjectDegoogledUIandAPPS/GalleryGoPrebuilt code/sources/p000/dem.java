package p000;

import android.provider.MediaStore;

/* renamed from: dem */
/* compiled from: PG */
final /* synthetic */ class dem implements Runnable {

    /* renamed from: a */
    private final deo f6394a;

    public dem(deo deo) {
        this.f6394a = deo;
    }

    public final void run() {
        deo deo = this.f6394a;
        deo.f6396a.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, deo.f6398c);
        deo.f6396a.registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, deo.f6399d);
        deo.f6396a.registerContentObserver(MediaStore.Files.getContentUri("external"), true, deo.f6400e);
        deo.f6402g = true;
        deo.f6403h = false;
    }
}
