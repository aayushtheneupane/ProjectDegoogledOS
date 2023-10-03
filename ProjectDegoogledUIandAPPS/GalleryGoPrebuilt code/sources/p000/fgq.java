package p000;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: fgq */
/* compiled from: PG */
public final class fgq {

    /* renamed from: a */
    public final CopyOnWriteArrayList f9519a = new CopyOnWriteArrayList();

    /* renamed from: b */
    public double f9520b;

    /* renamed from: c */
    public double f9521c;

    /* renamed from: d */
    public double f9522d;

    /* renamed from: e */
    public double f9523e;

    /* renamed from: f */
    public double f9524f;

    /* renamed from: g */
    public double f9525g;

    /* renamed from: h */
    public double f9526h;

    /* renamed from: i */
    public boolean f9527i = true;

    /* renamed from: j */
    public boolean f9528j = false;

    /* renamed from: k */
    public double f9529k = 0.0d;

    /* renamed from: a */
    public static boolean m8792a(double d, double d2, double d3) {
        return d2 < d3 ? d > d3 : d < d3;
    }

    /* renamed from: a */
    public final void mo5598a(fgp fgp) {
        this.f9519a.add(fgp);
    }

    /* renamed from: b */
    public final void mo5600b() {
        this.f9528j = true;
    }

    /* renamed from: a */
    public final boolean mo5599a() {
        if (Math.abs(this.f9523e) > 0.005d || Math.abs(this.f9526h - this.f9522d) > 0.005d) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public final void mo5597a(double d) {
        if (d != this.f9526h) {
            this.f9526h = d;
            this.f9525g = this.f9522d;
            Iterator it = this.f9519a.iterator();
            while (it.hasNext()) {
                ((fgp) it.next()).mo5595a(this);
            }
        }
    }

    /* renamed from: b */
    public final void mo5601b(double d) {
        if (d != this.f9522d) {
            this.f9522d = d;
            this.f9525g = d;
            Iterator it = this.f9519a.iterator();
            while (it.hasNext()) {
                fgp fgp = (fgp) it.next();
                fgp.mo5595a(this);
                fgp.mo5594a(d);
            }
        }
    }
}
