package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: vk */
/* compiled from: PG */
final class C0584vk extends C0652xy {

    /* renamed from: a */
    private final /* synthetic */ C0587vn f16105a;

    public C0584vk(C0587vn vnVar) {
        this.f16105a = vnVar;
    }

    /* renamed from: a */
    public final void mo4654a(RecyclerView recyclerView, int i, int i2) {
        C0587vn vnVar = this.f16105a;
        int computeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
        int computeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        int computeVerticalScrollRange = vnVar.f16125j.computeVerticalScrollRange();
        int i3 = vnVar.f16124i;
        vnVar.f16126k = computeVerticalScrollRange - i3 > 0 && i3 >= vnVar.f16116a;
        int computeHorizontalScrollRange = vnVar.f16125j.computeHorizontalScrollRange();
        int i4 = vnVar.f16123h;
        boolean z = computeHorizontalScrollRange - i4 > 0 && i4 >= vnVar.f16116a;
        vnVar.f16127l = z;
        if (vnVar.f16126k) {
            float f = (float) i3;
            vnVar.f16120e = (int) ((f * (((float) computeVerticalScrollOffset) + (f / 2.0f))) / ((float) computeVerticalScrollRange));
            vnVar.f16119d = Math.min(i3, (i3 * i3) / computeVerticalScrollRange);
        } else if (!z) {
            if (vnVar.f16128m != 0) {
                vnVar.mo10393a(0);
                return;
            }
            return;
        }
        if (vnVar.f16127l) {
            float f2 = (float) i4;
            vnVar.f16122g = (int) ((f2 * (((float) computeHorizontalScrollOffset) + (f2 / 2.0f))) / ((float) computeHorizontalScrollRange));
            vnVar.f16121f = Math.min(i4, (i4 * i4) / computeHorizontalScrollRange);
        }
        int i5 = vnVar.f16128m;
        if (i5 == 0 || i5 == 1) {
            vnVar.mo10393a(1);
        }
    }
}
