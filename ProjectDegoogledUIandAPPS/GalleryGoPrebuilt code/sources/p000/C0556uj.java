package p000;

import android.graphics.Typeface;
import android.os.Build;
import java.lang.ref.WeakReference;

/* renamed from: uj */
/* compiled from: PG */
final class C0556uj extends C0237io {

    /* renamed from: a */
    private final WeakReference f15994a;

    /* renamed from: b */
    private final int f15995b;

    /* renamed from: c */
    private final int f15996c;

    public C0556uj(C0557uk ukVar, int i, int i2) {
        this.f15994a = new WeakReference(ukVar);
        this.f15995b = i;
        this.f15996c = i2;
    }

    /* renamed from: a */
    public final void mo6613a() {
    }

    /* renamed from: a */
    public final void mo6614a(Typeface typeface) {
        int i;
        boolean z;
        C0557uk ukVar = (C0557uk) this.f15994a.get();
        if (ukVar != null) {
            if (Build.VERSION.SDK_INT >= 28 && (i = this.f15995b) != -1) {
                if ((this.f15996c & 2) != 0) {
                    z = true;
                } else {
                    z = false;
                }
                typeface = Typeface.create(typeface, i, z);
            }
            ukVar.f15997a.post(new C0555ui(this.f15994a, typeface));
        }
    }
}
