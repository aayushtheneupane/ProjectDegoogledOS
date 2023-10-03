package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.List;
import java.util.Map;

/* renamed from: iia */
/* compiled from: PG */
public final class iia implements iks {

    /* renamed from: a */
    private final ihz f14223a;

    /* renamed from: b */
    private int f14224b;

    /* renamed from: c */
    private int f14225c;

    /* renamed from: d */
    private int f14226d = 0;

    private iia(ihz ihz) {
        ihz ihz2 = (ihz) ijf.m13648a((Object) ihz, "input");
        this.f14223a = ihz2;
        ihz2.f14221b = this;
    }

    /* renamed from: b */
    public final int mo8547b() {
        return this.f14224b;
    }

    /* renamed from: a */
    public static iia m13289a(ihz ihz) {
        iia iia = ihz.f14221b;
        return iia == null ? new iia(ihz) : iia;
    }

    /* renamed from: a */
    public final int mo8541a() {
        int i = this.f14226d;
        if (i == 0) {
            i = this.f14223a.mo8628a();
            this.f14224b = i;
        } else {
            this.f14224b = i;
            this.f14226d = 0;
        }
        if (i == 0 || i == this.f14225c) {
            return Integer.MAX_VALUE;
        }
        return imd.m14074b(i);
    }

    /* renamed from: k */
    public final boolean mo8569k() {
        m13291a(0);
        return this.f14223a.mo8640i();
    }

    /* renamed from: h */
    public final void mo8563h(List list) {
        int a;
        int a2;
        if (list instanceof ihj) {
            ihj ihj = (ihj) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 0) {
                do {
                    ihj.mo8586a(this.f14223a.mo8640i());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int t = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    ihj.mo8586a(this.f14223a.mo8640i());
                } while (this.f14223a.mo8651t() < t);
                m13293b(t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 0) {
                do {
                    list.add(Boolean.valueOf(this.f14223a.mo8640i()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int t2 = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    list.add(Boolean.valueOf(this.f14223a.mo8640i()));
                } while (this.f14223a.mo8651t() < t2);
                m13293b(t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: n */
    public final ihw mo8574n() {
        m13291a(2);
        return this.f14223a.mo8643l();
    }

    /* renamed from: k */
    public final void mo8568k(List list) {
        int a;
        if (imd.m14072a(this.f14224b) == 2) {
            do {
                list.add(mo8574n());
                if (!this.f14223a.mo8650s()) {
                    a = this.f14223a.mo8628a();
                } else {
                    return;
                }
            } while (a == this.f14224b);
            this.f14226d = a;
            return;
        }
        throw ijh.m13659f();
    }

    /* renamed from: d */
    public final double mo8554d() {
        m13291a(1);
        return this.f14223a.mo8630b();
    }

    /* renamed from: a */
    public final void mo8544a(List list) {
        int a;
        int a2;
        if (list instanceof iig) {
            iig iig = (iig) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 1) {
                do {
                    iig.mo8706a(this.f14223a.mo8630b());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int m = this.f14223a.mo8644m();
                m13297d(m);
                int t = this.f14223a.mo8651t() + m;
                do {
                    iig.mo8706a(this.f14223a.mo8630b());
                } while (this.f14223a.mo8651t() < t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 1) {
                do {
                    list.add(Double.valueOf(this.f14223a.mo8630b()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int m2 = this.f14223a.mo8644m();
                m13297d(m2);
                int t2 = this.f14223a.mo8651t() + m2;
                do {
                    list.add(Double.valueOf(this.f14223a.mo8630b()));
                } while (this.f14223a.mo8651t() < t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: p */
    public final int mo8578p() {
        m13291a(0);
        return this.f14223a.mo8645n();
    }

    /* renamed from: m */
    public final void mo8573m(List list) {
        int a;
        int a2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 0) {
                do {
                    iiy.mo8801d(this.f14223a.mo8645n());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int t = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    iiy.mo8801d(this.f14223a.mo8645n());
                } while (this.f14223a.mo8651t() < t);
                m13293b(t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 0) {
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8645n()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int t2 = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8645n()));
                } while (this.f14223a.mo8651t() < t2);
                m13293b(t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: a */
    private final Object m13290a(imb imb, Class cls, iij iij) {
        imb imb2 = imb.DOUBLE;
        switch (imb.ordinal()) {
            case 0:
                return Double.valueOf(mo8554d());
            case 1:
                return Float.valueOf(mo8556e());
            case RecyclerView.SCROLL_STATE_SETTLING:
                return Long.valueOf(mo8560g());
            case 3:
                return Long.valueOf(mo8558f());
            case 4:
                return Integer.valueOf(mo8562h());
            case 5:
                return Long.valueOf(mo8564i());
            case 6:
                return Integer.valueOf(mo8566j());
            case 7:
                return Boolean.valueOf(mo8569k());
            case 8:
                return mo8572m();
            case 10:
                return mo8543a(cls, iij);
            case 11:
                return mo8574n();
            case 12:
                return Integer.valueOf(mo8576o());
            case 13:
                return Integer.valueOf(mo8578p());
            case 14:
                return Integer.valueOf(mo8580q());
            case 15:
                return Long.valueOf(mo8582r());
            case 16:
                return Integer.valueOf(mo8583s());
            case 17:
                return Long.valueOf(mo8584t());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* renamed from: j */
    public final int mo8566j() {
        m13291a(5);
        return this.f14223a.mo8639h();
    }

    /* renamed from: g */
    public final void mo8561g(List list) {
        int a;
        int a2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 2) {
                int m = this.f14223a.mo8644m();
                m13295c(m);
                int t = this.f14223a.mo8651t() + m;
                do {
                    iiy.mo8801d(this.f14223a.mo8639h());
                } while (this.f14223a.mo8651t() < t);
            } else if (a3 == 5) {
                do {
                    iiy.mo8801d(this.f14223a.mo8639h());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 2) {
                int m2 = this.f14223a.mo8644m();
                m13295c(m2);
                int t2 = this.f14223a.mo8651t() + m2;
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8639h()));
                } while (this.f14223a.mo8651t() < t2);
            } else if (a4 == 5) {
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8639h()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: i */
    public final long mo8564i() {
        m13291a(1);
        return this.f14223a.mo8638g();
    }

    /* renamed from: f */
    public final void mo8559f(List list) {
        int a;
        int a2;
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 1) {
                do {
                    ijs.mo8805a(this.f14223a.mo8638g());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int m = this.f14223a.mo8644m();
                m13297d(m);
                int t = this.f14223a.mo8651t() + m;
                do {
                    ijs.mo8805a(this.f14223a.mo8638g());
                } while (this.f14223a.mo8651t() < t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 1) {
                do {
                    list.add(Long.valueOf(this.f14223a.mo8638g()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int m2 = this.f14223a.mo8644m();
                m13297d(m2);
                int t2 = this.f14223a.mo8651t() + m2;
                do {
                    list.add(Long.valueOf(this.f14223a.mo8638g()));
                } while (this.f14223a.mo8651t() < t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: e */
    public final float mo8556e() {
        m13291a(5);
        return this.f14223a.mo8632c();
    }

    /* renamed from: b */
    public final void mo8550b(List list) {
        int a;
        int a2;
        if (list instanceof iio) {
            iio iio = (iio) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 2) {
                int m = this.f14223a.mo8644m();
                m13295c(m);
                int t = this.f14223a.mo8651t() + m;
                do {
                    iio.mo8736a(this.f14223a.mo8632c());
                } while (this.f14223a.mo8651t() < t);
            } else if (a3 == 5) {
                do {
                    iio.mo8736a(this.f14223a.mo8632c());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 2) {
                int m2 = this.f14223a.mo8644m();
                m13295c(m2);
                int t2 = this.f14223a.mo8651t() + m2;
                do {
                    list.add(Float.valueOf(this.f14223a.mo8632c()));
                } while (this.f14223a.mo8651t() < t2);
            } else if (a4 == 5) {
                do {
                    list.add(Float.valueOf(this.f14223a.mo8632c()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: d */
    private final Object m13296d(iky iky, iij iij) {
        int i = this.f14225c;
        this.f14225c = imd.m14073a(imd.m14074b(this.f14224b), 4);
        try {
            Object a = iky.mo8864a();
            iky.mo8865a(a, this, iij);
            iky.mo8871c(a);
            if (this.f14224b == this.f14225c) {
                return a;
            }
            throw ijh.m13661h();
        } finally {
            this.f14225c = i;
        }
    }

    /* renamed from: b */
    public final Object mo8549b(Class cls, iij iij) {
        m13291a(3);
        return m13296d(ikp.f14397a.mo8875a(cls), iij);
    }

    /* renamed from: b */
    public final Object mo8548b(iky iky, iij iij) {
        m13291a(3);
        return m13296d(iky, iij);
    }

    /* renamed from: b */
    public final void mo8551b(List list, iky iky, iij iij) {
        int a;
        if (imd.m14072a(this.f14224b) == 3) {
            int i = this.f14224b;
            do {
                list.add(m13296d(iky, iij));
                if (!this.f14223a.mo8650s() && this.f14226d == 0) {
                    a = this.f14223a.mo8628a();
                } else {
                    return;
                }
            } while (a == i);
            this.f14226d = a;
            return;
        }
        throw ijh.m13659f();
    }

    /* renamed from: h */
    public final int mo8562h() {
        m13291a(0);
        return this.f14223a.mo8637f();
    }

    /* renamed from: e */
    public final void mo8557e(List list) {
        int a;
        int a2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 0) {
                do {
                    iiy.mo8801d(this.f14223a.mo8637f());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int t = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    iiy.mo8801d(this.f14223a.mo8637f());
                } while (this.f14223a.mo8651t() < t);
                m13293b(t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 0) {
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8637f()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int t2 = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8637f()));
                } while (this.f14223a.mo8651t() < t2);
                m13293b(t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: g */
    public final long mo8560g() {
        m13291a(0);
        return this.f14223a.mo8636e();
    }

    /* renamed from: d */
    public final void mo8555d(List list) {
        int a;
        int a2;
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 0) {
                do {
                    ijs.mo8805a(this.f14223a.mo8636e());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int t = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    ijs.mo8805a(this.f14223a.mo8636e());
                } while (this.f14223a.mo8651t() < t);
                m13293b(t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 0) {
                do {
                    list.add(Long.valueOf(this.f14223a.mo8636e()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int t2 = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    list.add(Long.valueOf(this.f14223a.mo8636e()));
                } while (this.f14223a.mo8651t() < t2);
                m13293b(t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: a */
    public final void mo8546a(Map map, ijw ijw, iij iij) {
        m13291a(2);
        int c = this.f14223a.mo8633c(this.f14223a.mo8644m());
        Object obj = ijw.f14364b;
        Object obj2 = ijw.f14366d;
        while (true) {
            try {
                int a = mo8541a();
                if (a == Integer.MAX_VALUE || this.f14223a.mo8650s()) {
                    map.put(obj, obj2);
                } else if (a == 1) {
                    obj = m13290a(ijw.f14363a, (Class) null, (iij) null);
                } else if (a == 2) {
                    obj2 = m13290a(ijw.f14365c, (Class) ijw.f14366d.getClass(), iij);
                } else if (!mo8553c()) {
                    throw new ijh("Unable to parse map entry.");
                }
            } catch (ijg e) {
                if (!mo8553c()) {
                    throw new ijh("Unable to parse map entry.");
                }
            } catch (Throwable th) {
                this.f14223a.mo8635d(c);
                throw th;
            }
        }
        map.put(obj, obj2);
        this.f14223a.mo8635d(c);
    }

    /* renamed from: c */
    private final Object m13294c(iky iky, iij iij) {
        int m = this.f14223a.mo8644m();
        ihz ihz = this.f14223a;
        if (ihz.f14220a < 100) {
            int c = ihz.mo8633c(m);
            Object a = iky.mo8864a();
            this.f14223a.f14220a++;
            iky.mo8865a(a, this, iij);
            iky.mo8871c(a);
            this.f14223a.mo8629a(0);
            ihz ihz2 = this.f14223a;
            ihz2.f14220a--;
            ihz2.mo8635d(c);
            return a;
        }
        throw new ijh("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    /* renamed from: a */
    public final Object mo8543a(Class cls, iij iij) {
        m13291a(2);
        return m13294c(ikp.f14397a.mo8875a(cls), iij);
    }

    /* renamed from: a */
    public final Object mo8542a(iky iky, iij iij) {
        m13291a(2);
        return m13294c(iky, iij);
    }

    /* renamed from: a */
    public final void mo8545a(List list, iky iky, iij iij) {
        int a;
        if (imd.m14072a(this.f14224b) == 2) {
            int i = this.f14224b;
            do {
                list.add(m13294c(iky, iij));
                if (!this.f14223a.mo8650s() && this.f14226d == 0) {
                    a = this.f14223a.mo8628a();
                } else {
                    return;
                }
            } while (a == i);
            this.f14226d = a;
            return;
        }
        throw ijh.m13659f();
    }

    /* renamed from: q */
    public final int mo8580q() {
        m13291a(5);
        return this.f14223a.mo8646o();
    }

    /* renamed from: n */
    public final void mo8575n(List list) {
        int a;
        int a2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 2) {
                int m = this.f14223a.mo8644m();
                m13295c(m);
                int t = this.f14223a.mo8651t() + m;
                do {
                    iiy.mo8801d(this.f14223a.mo8646o());
                } while (this.f14223a.mo8651t() < t);
            } else if (a3 == 5) {
                do {
                    iiy.mo8801d(this.f14223a.mo8646o());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 2) {
                int m2 = this.f14223a.mo8644m();
                m13295c(m2);
                int t2 = this.f14223a.mo8651t() + m2;
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8646o()));
                } while (this.f14223a.mo8651t() < t2);
            } else if (a4 == 5) {
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8646o()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: r */
    public final long mo8582r() {
        m13291a(1);
        return this.f14223a.mo8647p();
    }

    /* renamed from: o */
    public final void mo8577o(List list) {
        int a;
        int a2;
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 1) {
                do {
                    ijs.mo8805a(this.f14223a.mo8647p());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int m = this.f14223a.mo8644m();
                m13297d(m);
                int t = this.f14223a.mo8651t() + m;
                do {
                    ijs.mo8805a(this.f14223a.mo8647p());
                } while (this.f14223a.mo8651t() < t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 1) {
                do {
                    list.add(Long.valueOf(this.f14223a.mo8647p()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int m2 = this.f14223a.mo8644m();
                m13297d(m2);
                int t2 = this.f14223a.mo8651t() + m2;
                do {
                    list.add(Long.valueOf(this.f14223a.mo8647p()));
                } while (this.f14223a.mo8651t() < t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: s */
    public final int mo8583s() {
        m13291a(0);
        return this.f14223a.mo8648q();
    }

    /* renamed from: p */
    public final void mo8579p(List list) {
        int a;
        int a2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 0) {
                do {
                    iiy.mo8801d(this.f14223a.mo8648q());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int t = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    iiy.mo8801d(this.f14223a.mo8648q());
                } while (this.f14223a.mo8651t() < t);
                m13293b(t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 0) {
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8648q()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int t2 = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8648q()));
                } while (this.f14223a.mo8651t() < t2);
                m13293b(t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: t */
    public final long mo8584t() {
        m13291a(0);
        return this.f14223a.mo8649r();
    }

    /* renamed from: q */
    public final void mo8581q(List list) {
        int a;
        int a2;
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 0) {
                do {
                    ijs.mo8805a(this.f14223a.mo8649r());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int t = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    ijs.mo8805a(this.f14223a.mo8649r());
                } while (this.f14223a.mo8651t() < t);
                m13293b(t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 0) {
                do {
                    list.add(Long.valueOf(this.f14223a.mo8649r()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int t2 = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    list.add(Long.valueOf(this.f14223a.mo8649r()));
                } while (this.f14223a.mo8651t() < t2);
                m13293b(t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: l */
    public final String mo8570l() {
        m13291a(2);
        return this.f14223a.mo8641j();
    }

    /* renamed from: i */
    public final void mo8565i(List list) {
        m13292a(list, false);
    }

    /* renamed from: a */
    private final void m13292a(List list, boolean z) {
        int a;
        int a2;
        if (imd.m14072a(this.f14224b) != 2) {
            throw ijh.m13659f();
        } else if ((list instanceof ijo) && !z) {
            ijo ijo = (ijo) list;
            do {
                ijo.mo8817a(mo8574n());
                if (!this.f14223a.mo8650s()) {
                    a2 = this.f14223a.mo8628a();
                } else {
                    return;
                }
            } while (a2 == this.f14224b);
            this.f14226d = a2;
        } else {
            do {
                list.add(z ? mo8572m() : mo8570l());
                if (!this.f14223a.mo8650s()) {
                    a = this.f14223a.mo8628a();
                } else {
                    return;
                }
            } while (a == this.f14224b);
            this.f14226d = a;
        }
    }

    /* renamed from: j */
    public final void mo8567j(List list) {
        m13292a(list, true);
    }

    /* renamed from: m */
    public final String mo8572m() {
        m13291a(2);
        return this.f14223a.mo8642k();
    }

    /* renamed from: o */
    public final int mo8576o() {
        m13291a(0);
        return this.f14223a.mo8644m();
    }

    /* renamed from: l */
    public final void mo8571l(List list) {
        int a;
        int a2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 0) {
                do {
                    iiy.mo8801d(this.f14223a.mo8644m());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int t = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    iiy.mo8801d(this.f14223a.mo8644m());
                } while (this.f14223a.mo8651t() < t);
                m13293b(t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 0) {
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8644m()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int t2 = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    list.add(Integer.valueOf(this.f14223a.mo8644m()));
                } while (this.f14223a.mo8651t() < t2);
                m13293b(t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: f */
    public final long mo8558f() {
        m13291a(0);
        return this.f14223a.mo8634d();
    }

    /* renamed from: c */
    public final void mo8552c(List list) {
        int a;
        int a2;
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int a3 = imd.m14072a(this.f14224b);
            if (a3 == 0) {
                do {
                    ijs.mo8805a(this.f14223a.mo8634d());
                    if (!this.f14223a.mo8650s()) {
                        a2 = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a2 == this.f14224b);
                this.f14226d = a2;
            } else if (a3 == 2) {
                int t = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    ijs.mo8805a(this.f14223a.mo8634d());
                } while (this.f14223a.mo8651t() < t);
                m13293b(t);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a4 = imd.m14072a(this.f14224b);
            if (a4 == 0) {
                do {
                    list.add(Long.valueOf(this.f14223a.mo8634d()));
                    if (!this.f14223a.mo8650s()) {
                        a = this.f14223a.mo8628a();
                    } else {
                        return;
                    }
                } while (a == this.f14224b);
                this.f14226d = a;
            } else if (a4 == 2) {
                int t2 = this.f14223a.mo8651t() + this.f14223a.mo8644m();
                do {
                    list.add(Long.valueOf(this.f14223a.mo8634d()));
                } while (this.f14223a.mo8651t() < t2);
                m13293b(t2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: b */
    private final void m13293b(int i) {
        if (this.f14223a.mo8651t() != i) {
            throw ijh.m13654a();
        }
    }

    /* renamed from: a */
    private final void m13291a(int i) {
        if (imd.m14072a(this.f14224b) != i) {
            throw ijh.m13659f();
        }
    }

    /* renamed from: c */
    public final boolean mo8553c() {
        int i;
        if (this.f14223a.mo8650s() || (i = this.f14224b) == this.f14225c) {
            return false;
        }
        return this.f14223a.mo8631b(i);
    }

    /* renamed from: c */
    private static final void m13295c(int i) {
        if ((i & 3) != 0) {
            throw ijh.m13661h();
        }
    }

    /* renamed from: d */
    private static final void m13297d(int i) {
        if ((i & 7) != 0) {
            throw ijh.m13661h();
        }
    }
}
