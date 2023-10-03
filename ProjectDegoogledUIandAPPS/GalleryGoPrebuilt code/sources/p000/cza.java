package p000;

import java.util.concurrent.Callable;

/* renamed from: cza */
/* compiled from: PG */
final /* synthetic */ class cza implements Callable {

    /* renamed from: a */
    private final cze f6083a;

    public cza(cze cze) {
        this.f6083a = cze;
    }

    public final Object call() {
        cze cze = this.f6083a;
        cze.f6091c.mo4101b();
        cwn.m5509a(cze.f6092d.mo4114a(), "Deleter: Failed to sync with MediaStore following deletion.", new Object[0]);
        return bip.f2457a;
    }
}
