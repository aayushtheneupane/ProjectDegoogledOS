package p000;

import android.graphics.Typeface;
import java.lang.ref.WeakReference;

/* renamed from: ui */
/* compiled from: PG */
final class C0555ui implements Runnable {

    /* renamed from: a */
    private final WeakReference f15992a;

    /* renamed from: b */
    private final Typeface f15993b;

    public C0555ui(WeakReference weakReference, Typeface typeface) {
        this.f15992a = weakReference;
        this.f15993b = typeface;
    }

    public final void run() {
        C0557uk ukVar = (C0557uk) this.f15992a.get();
        if (ukVar != null) {
            Typeface typeface = this.f15993b;
            if (ukVar.f15999c) {
                ukVar.f15997a.setTypeface(typeface);
                ukVar.f15998b = typeface;
            }
        }
    }
}
