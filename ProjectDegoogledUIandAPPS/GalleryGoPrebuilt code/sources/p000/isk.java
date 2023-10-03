package p000;

import android.widget.ImageView;

/* renamed from: isk */
/* compiled from: PG */
final class isk implements Runnable {

    /* renamed from: a */
    private final float f15005a;

    /* renamed from: b */
    private final float f15006b;

    /* renamed from: c */
    private final long f15007c = System.currentTimeMillis();

    /* renamed from: d */
    private final float f15008d;

    /* renamed from: e */
    private final float f15009e;

    /* renamed from: f */
    private final /* synthetic */ isn f15010f;

    public isk(isn isn, float f, float f2, float f3, float f4) {
        this.f15010f = isn;
        this.f15005a = f3;
        this.f15006b = f4;
        this.f15008d = f;
        this.f15009e = f2;
    }

    public final void run() {
        ImageView c = this.f15010f.mo9095c();
        if (c != null) {
            float interpolation = this.f15010f.f15015a.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.f15007c)) / ((float) this.f15010f.f15016b)));
            float f = this.f15008d;
            float f2 = this.f15009e;
            this.f15010f.mo9090a((f + ((f2 - f) * interpolation)) / this.f15010f.mo9096d(), this.f15005a, this.f15006b);
            if (interpolation < 1.0f) {
                isw.m14418a(c, this);
            }
        }
    }
}
