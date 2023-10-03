package p000;

import android.graphics.Bitmap;

/* renamed from: bom */
/* compiled from: PG */
final class bom implements bee {

    /* renamed from: a */
    private final /* synthetic */ bnc f3257a;

    /* renamed from: b */
    private final /* synthetic */ bon f3258b;

    public bom(bon bon, bnc bnc) {
        this.f3258b = bon;
        this.f3257a = bnc;
    }

    /* renamed from: a */
    public final boolean mo1486a(atu atu) {
        int i = this.f3257a.f3182b;
        if (i == 3 || i == 5) {
            this.f3258b.f3263e.setVisibility(0);
        }
        return false;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1487a(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        this.f3258b.f3260b.setVisibility(4);
        this.f3258b.f3261c.setVisibility(4);
        this.f3258b.f3262d.setVisibility(0);
        this.f3258b.f3263e.setVisibility(0);
        return false;
    }
}
