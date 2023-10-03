package p000;

import android.graphics.PointF;

/* renamed from: bgf */
/* compiled from: PG */
public final class bgf {

    /* renamed from: a */
    public long f2250a = 500;

    /* renamed from: b */
    public int f2251b = 2;

    /* renamed from: c */
    public int f2252c = 1;

    /* renamed from: d */
    public boolean f2253d = true;

    /* renamed from: e */
    private final float f2254e;

    /* renamed from: f */
    private final PointF f2255f;

    /* renamed from: g */
    private final PointF f2256g;

    /* renamed from: h */
    private boolean f2257h = true;

    /* renamed from: i */
    private final /* synthetic */ bgo f2258i;

    public /* synthetic */ bgf(bgo bgo, float f, PointF pointF, PointF pointF2) {
        this.f2258i = bgo;
        this.f2254e = f;
        this.f2255f = pointF;
        this.f2256g = pointF2;
    }

    public /* synthetic */ bgf(bgo bgo, float f, PointF pointF) {
        this.f2258i = bgo;
        this.f2254e = f;
        this.f2255f = pointF;
        this.f2256g = null;
    }

    public /* synthetic */ bgf(bgo bgo, PointF pointF) {
        this.f2258i = bgo;
        String str = bgo.f2287a;
        this.f2254e = bgo.f2334j;
        this.f2255f = pointF;
        this.f2256g = null;
    }

    /* renamed from: a */
    public final void mo1980a() {
        PointF pointF;
        bgo bgo = this.f2258i;
        String str = bgo.f2287a;
        bge bge = bgo.f2291B;
        int paddingLeft = bgo.getPaddingLeft() + (((this.f2258i.getWidth() - this.f2258i.getPaddingRight()) - this.f2258i.getPaddingLeft()) / 2);
        int paddingTop = this.f2258i.getPaddingTop() + (((this.f2258i.getHeight() - this.f2258i.getPaddingBottom()) - this.f2258i.getPaddingTop()) / 2);
        float a = this.f2258i.mo1989a(this.f2254e);
        if (this.f2253d) {
            bgo bgo2 = this.f2258i;
            float f = this.f2255f.x;
            float f2 = this.f2255f.y;
            pointF = new PointF();
            PointF a2 = bgo2.mo1990a(f, f2, a);
            int paddingLeft2 = bgo2.getPaddingLeft();
            int width = bgo2.getWidth();
            int paddingRight = bgo2.getPaddingRight();
            int paddingLeft3 = bgo2.getPaddingLeft();
            int paddingTop2 = bgo2.getPaddingTop();
            int height = bgo2.getHeight();
            int paddingBottom = bgo2.getPaddingBottom();
            pointF.set((((float) (paddingLeft2 + (((width - paddingRight) - paddingLeft3) / 2))) - a2.x) / a, (((float) (paddingTop2 + (((height - paddingBottom) - bgo2.getPaddingTop()) / 2))) - a2.y) / a);
        } else {
            pointF = this.f2255f;
        }
        this.f2258i.f2291B = new bge((byte[]) null);
        bgo bgo3 = this.f2258i;
        bge bge2 = bgo3.f2291B;
        bge2.f2237a = bgo3.f2334j;
        bge2.f2238b = a;
        bge2.f2248l = System.currentTimeMillis();
        bgo bgo4 = this.f2258i;
        bge bge3 = bgo4.f2291B;
        bge3.f2241e = pointF;
        bge3.f2239c = bgo4.mo2008f();
        bgo bgo5 = this.f2258i;
        bge bge4 = bgo5.f2291B;
        bge4.f2240d = pointF;
        bge4.f2242f = bgo5.mo2004b(pointF);
        this.f2258i.f2291B.f2243g = new PointF((float) paddingLeft, (float) paddingTop);
        bge bge5 = this.f2258i.f2291B;
        bge5.f2244h = this.f2250a;
        bge5.f2245i = this.f2257h;
        bge5.f2246j = this.f2251b;
        bge5.f2247k = this.f2252c;
        bge5.f2248l = System.currentTimeMillis();
        this.f2258i.f2291B.f2249m = null;
        PointF pointF2 = this.f2256g;
        if (pointF2 != null) {
            float f3 = pointF2.x - (this.f2258i.f2291B.f2239c.x * a);
            float f4 = this.f2256g.y - (this.f2258i.f2291B.f2239c.y * a);
            bgk bgk = new bgk(a, new PointF(f3, f4));
            this.f2258i.mo2002a(true, bgk);
            this.f2258i.f2291B.f2243g = new PointF(this.f2256g.x + (bgk.f2267b.x - f3), this.f2256g.y + (bgk.f2267b.y - f4));
        }
        this.f2258i.invalidate();
    }

    /* renamed from: b */
    public final void mo1981b() {
        this.f2257h = false;
    }
}
