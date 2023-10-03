package p000;

import android.support.p002v7.widget.RecyclerView;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* renamed from: ihh */
/* compiled from: PG */
final class ihh implements iks {

    /* renamed from: a */
    private final byte[] f14182a;

    /* renamed from: b */
    private int f14183b;

    /* renamed from: c */
    private int f14184c;

    /* renamed from: d */
    private int f14185d;

    /* renamed from: e */
    private int f14186e;

    public ihh(ByteBuffer byteBuffer) {
        this.f14182a = byteBuffer.array();
        this.f14183b = byteBuffer.arrayOffset() + byteBuffer.position();
        this.f14184c = byteBuffer.arrayOffset() + byteBuffer.limit();
    }

    /* renamed from: u */
    private final boolean m13073u() {
        return this.f14183b == this.f14184c;
    }

    /* renamed from: b */
    public final int mo8547b() {
        return this.f14185d;
    }

    /* renamed from: a */
    public final int mo8541a() {
        if (!m13073u()) {
            int v = m13074v();
            this.f14185d = v;
            if (v != this.f14186e) {
                return imd.m14074b(v);
            }
        }
        return Integer.MAX_VALUE;
    }

    /* renamed from: k */
    public final boolean mo8569k() {
        m13068c(0);
        if (m13074v() != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: h */
    public final void mo8563h(List list) {
        int i;
        int i2;
        if (list instanceof ihj) {
            ihj ihj = (ihj) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 0) {
                do {
                    ihj.mo8586a(mo8569k());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = this.f14183b + m13074v();
                while (this.f14183b < v) {
                    ihj.mo8586a(m13074v() != 0);
                }
                m13072f(v);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 0) {
                do {
                    list.add(Boolean.valueOf(mo8569k()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = this.f14183b + m13074v();
                while (this.f14183b < v2) {
                    list.add(Boolean.valueOf(m13074v() != 0));
                }
                m13072f(v2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: y */
    private final byte m13077y() {
        int i = this.f14183b;
        if (i != this.f14184c) {
            byte[] bArr = this.f14182a;
            this.f14183b = i + 1;
            return bArr[i];
        }
        throw ijh.m13654a();
    }

    /* renamed from: n */
    public final ihw mo8574n() {
        m13068c(2);
        int v = m13074v();
        if (v == 0) {
            return ihw.f14203b;
        }
        m13066b(v);
        ihw b = ihw.m13165b(this.f14182a, this.f14183b, v);
        this.f14183b += v;
        return b;
    }

    /* renamed from: k */
    public final void mo8568k(List list) {
        int i;
        if (imd.m14072a(this.f14185d) == 2) {
            do {
                list.add(mo8574n());
                if (!m13073u()) {
                    i = this.f14183b;
                } else {
                    return;
                }
            } while (m13074v() == this.f14185d);
            this.f14183b = i;
            return;
        }
        throw ijh.m13659f();
    }

    /* renamed from: d */
    public final double mo8554d() {
        m13068c(1);
        return Double.longBitsToDouble(m13059A());
    }

    /* renamed from: a */
    public final void mo8544a(List list) {
        int i;
        int i2;
        if (list instanceof iig) {
            iig iig = (iig) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 1) {
                do {
                    iig.mo8706a(mo8554d());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = m13074v();
                m13070d(v);
                int i3 = this.f14183b + v;
                while (this.f14183b < i3) {
                    iig.mo8706a(Double.longBitsToDouble(m13061C()));
                }
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 1) {
                do {
                    list.add(Double.valueOf(mo8554d()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = m13074v();
                m13070d(v2);
                int i4 = this.f14183b + v2;
                while (this.f14183b < i4) {
                    list.add(Double.valueOf(Double.longBitsToDouble(m13061C())));
                }
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: p */
    public final int mo8578p() {
        m13068c(0);
        return m13074v();
    }

    /* renamed from: m */
    public final void mo8573m(List list) {
        int i;
        int i2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 0) {
                do {
                    iiy.mo8801d(mo8578p());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = this.f14183b + m13074v();
                while (this.f14183b < v) {
                    iiy.mo8801d(m13074v());
                }
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 0) {
                do {
                    list.add(Integer.valueOf(mo8578p()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = this.f14183b + m13074v();
                while (this.f14183b < v2) {
                    list.add(Integer.valueOf(m13074v()));
                }
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: a */
    private final Object m13062a(imb imb, Class cls, iij iij) {
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
        m13068c(5);
        return m13078z();
    }

    /* renamed from: g */
    public final void mo8561g(List list) {
        int i;
        int i2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 2) {
                int v = m13074v();
                m13071e(v);
                int i3 = this.f14183b + v;
                while (this.f14183b < i3) {
                    iiy.mo8801d(m13060B());
                }
            } else if (a == 5) {
                do {
                    iiy.mo8801d(mo8566j());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 2) {
                int v2 = m13074v();
                m13071e(v2);
                int i4 = this.f14183b + v2;
                while (this.f14183b < i4) {
                    list.add(Integer.valueOf(m13060B()));
                }
            } else if (a2 == 5) {
                do {
                    list.add(Integer.valueOf(mo8566j()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: i */
    public final long mo8564i() {
        m13068c(1);
        return m13059A();
    }

    /* renamed from: f */
    public final void mo8559f(List list) {
        int i;
        int i2;
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 1) {
                do {
                    ijs.mo8805a(mo8564i());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = m13074v();
                m13070d(v);
                int i3 = this.f14183b + v;
                while (this.f14183b < i3) {
                    ijs.mo8805a(m13061C());
                }
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 1) {
                do {
                    list.add(Long.valueOf(mo8564i()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = m13074v();
                m13070d(v2);
                int i4 = this.f14183b + v2;
                while (this.f14183b < i4) {
                    list.add(Long.valueOf(m13061C()));
                }
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: e */
    public final float mo8556e() {
        m13068c(5);
        return Float.intBitsToFloat(m13078z());
    }

    /* renamed from: b */
    public final void mo8550b(List list) {
        int i;
        int i2;
        if (list instanceof iio) {
            iio iio = (iio) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 2) {
                int v = m13074v();
                m13071e(v);
                int i3 = this.f14183b + v;
                while (this.f14183b < i3) {
                    iio.mo8736a(Float.intBitsToFloat(m13060B()));
                }
            } else if (a == 5) {
                do {
                    iio.mo8736a(mo8556e());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 2) {
                int v2 = m13074v();
                m13071e(v2);
                int i4 = this.f14183b + v2;
                while (this.f14183b < i4) {
                    list.add(Float.valueOf(Float.intBitsToFloat(m13060B())));
                }
            } else if (a2 == 5) {
                do {
                    list.add(Float.valueOf(mo8556e()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: d */
    private final Object m13069d(iky iky, iij iij) {
        int i = this.f14186e;
        this.f14186e = imd.m14073a(imd.m14074b(this.f14185d), 4);
        try {
            Object a = iky.mo8864a();
            iky.mo8865a(a, this, iij);
            iky.mo8871c(a);
            if (this.f14185d == this.f14186e) {
                return a;
            }
            throw ijh.m13661h();
        } finally {
            this.f14186e = i;
        }
    }

    /* renamed from: b */
    public final Object mo8549b(Class cls, iij iij) {
        m13068c(3);
        return m13069d(ikp.f14397a.mo8875a(cls), iij);
    }

    /* renamed from: b */
    public final Object mo8548b(iky iky, iij iij) {
        m13068c(3);
        return m13069d(iky, iij);
    }

    /* renamed from: b */
    public final void mo8551b(List list, iky iky, iij iij) {
        int i;
        if (imd.m14072a(this.f14185d) == 3) {
            int i2 = this.f14185d;
            do {
                list.add(m13069d(iky, iij));
                if (!m13073u()) {
                    i = this.f14183b;
                } else {
                    return;
                }
            } while (m13074v() == i2);
            this.f14183b = i;
            return;
        }
        throw ijh.m13659f();
    }

    /* renamed from: h */
    public final int mo8562h() {
        m13068c(0);
        return m13074v();
    }

    /* renamed from: e */
    public final void mo8557e(List list) {
        int i;
        int i2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 0) {
                do {
                    iiy.mo8801d(mo8562h());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = this.f14183b + m13074v();
                while (this.f14183b < v) {
                    iiy.mo8801d(m13074v());
                }
                m13072f(v);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 0) {
                do {
                    list.add(Integer.valueOf(mo8562h()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = this.f14183b + m13074v();
                while (this.f14183b < v2) {
                    list.add(Integer.valueOf(m13074v()));
                }
                m13072f(v2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: g */
    public final long mo8560g() {
        m13068c(0);
        return m13075w();
    }

    /* renamed from: d */
    public final void mo8555d(List list) {
        int i;
        int i2;
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 0) {
                do {
                    ijs.mo8805a(mo8560g());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = this.f14183b + m13074v();
                while (this.f14183b < v) {
                    ijs.mo8805a(m13075w());
                }
                m13072f(v);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 0) {
                do {
                    list.add(Long.valueOf(mo8560g()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = this.f14183b + m13074v();
                while (this.f14183b < v2) {
                    list.add(Long.valueOf(m13075w()));
                }
                m13072f(v2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: z */
    private final int m13078z() {
        m13066b(4);
        return m13060B();
    }

    /* renamed from: B */
    private final int m13060B() {
        int i = this.f14183b;
        byte[] bArr = this.f14182a;
        this.f14183b = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    /* renamed from: A */
    private final long m13059A() {
        m13066b(8);
        return m13061C();
    }

    /* renamed from: C */
    private final long m13061C() {
        int i = this.f14183b;
        byte[] bArr = this.f14182a;
        this.f14183b = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    /* renamed from: a */
    public final void mo8546a(Map map, ijw ijw, iij iij) {
        m13068c(2);
        int v = m13074v();
        m13066b(v);
        int i = this.f14184c;
        this.f14184c = this.f14183b + v;
        try {
            Object obj = ijw.f14364b;
            Object obj2 = ijw.f14366d;
            while (true) {
                int a = mo8541a();
                if (a == Integer.MAX_VALUE) {
                    map.put(obj, obj2);
                    this.f14184c = i;
                    return;
                } else if (a == 1) {
                    obj = m13062a(ijw.f14363a, (Class) null, (iij) null);
                } else if (a == 2) {
                    obj2 = m13062a(ijw.f14365c, (Class) ijw.f14366d.getClass(), iij);
                } else if (!mo8553c()) {
                    throw new ijh("Unable to parse map entry.");
                }
            }
        } catch (ijg e) {
            if (!mo8553c()) {
                throw new ijh("Unable to parse map entry.");
            }
        } catch (Throwable th) {
            this.f14184c = i;
            throw th;
        }
    }

    /* renamed from: c */
    private final Object m13067c(iky iky, iij iij) {
        int v = m13074v();
        m13066b(v);
        int i = this.f14184c;
        int i2 = this.f14183b + v;
        this.f14184c = i2;
        try {
            Object a = iky.mo8864a();
            iky.mo8865a(a, this, iij);
            iky.mo8871c(a);
            if (this.f14183b == i2) {
                return a;
            }
            throw ijh.m13661h();
        } finally {
            this.f14184c = i;
        }
    }

    /* renamed from: a */
    public final Object mo8543a(Class cls, iij iij) {
        m13068c(2);
        return m13067c(ikp.f14397a.mo8875a(cls), iij);
    }

    /* renamed from: a */
    public final Object mo8542a(iky iky, iij iij) {
        m13068c(2);
        return m13067c(iky, iij);
    }

    /* renamed from: a */
    public final void mo8545a(List list, iky iky, iij iij) {
        int i;
        if (imd.m14072a(this.f14185d) == 2) {
            int i2 = this.f14185d;
            do {
                list.add(m13067c(iky, iij));
                if (!m13073u()) {
                    i = this.f14183b;
                } else {
                    return;
                }
            } while (m13074v() == i2);
            this.f14183b = i;
            return;
        }
        throw ijh.m13659f();
    }

    /* renamed from: q */
    public final int mo8580q() {
        m13068c(5);
        return m13078z();
    }

    /* renamed from: n */
    public final void mo8575n(List list) {
        int i;
        int i2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 2) {
                int v = m13074v();
                m13071e(v);
                int i3 = this.f14183b + v;
                while (this.f14183b < i3) {
                    iiy.mo8801d(m13060B());
                }
            } else if (a == 5) {
                do {
                    iiy.mo8801d(mo8580q());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 2) {
                int v2 = m13074v();
                m13071e(v2);
                int i4 = this.f14183b + v2;
                while (this.f14183b < i4) {
                    list.add(Integer.valueOf(m13060B()));
                }
            } else if (a2 == 5) {
                do {
                    list.add(Integer.valueOf(mo8580q()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: r */
    public final long mo8582r() {
        m13068c(1);
        return m13059A();
    }

    /* renamed from: o */
    public final void mo8577o(List list) {
        int i;
        int i2;
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 1) {
                do {
                    ijs.mo8805a(mo8582r());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = m13074v();
                m13070d(v);
                int i3 = this.f14183b + v;
                while (this.f14183b < i3) {
                    ijs.mo8805a(m13061C());
                }
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 1) {
                do {
                    list.add(Long.valueOf(mo8582r()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = m13074v();
                m13070d(v2);
                int i4 = this.f14183b + v2;
                while (this.f14183b < i4) {
                    list.add(Long.valueOf(m13061C()));
                }
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: s */
    public final int mo8583s() {
        m13068c(0);
        return ihz.m13264e(m13074v());
    }

    /* renamed from: p */
    public final void mo8579p(List list) {
        int i;
        int i2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 0) {
                do {
                    iiy.mo8801d(mo8583s());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = this.f14183b + m13074v();
                while (this.f14183b < v) {
                    iiy.mo8801d(ihz.m13264e(m13074v()));
                }
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 0) {
                do {
                    list.add(Integer.valueOf(mo8583s()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = this.f14183b + m13074v();
                while (this.f14183b < v2) {
                    list.add(Integer.valueOf(ihz.m13264e(m13074v())));
                }
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: t */
    public final long mo8584t() {
        m13068c(0);
        return ihz.m13260a(m13075w());
    }

    /* renamed from: q */
    public final void mo8581q(List list) {
        int i;
        int i2;
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 0) {
                do {
                    ijs.mo8805a(mo8584t());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = this.f14183b + m13074v();
                while (this.f14183b < v) {
                    ijs.mo8805a(ihz.m13260a(m13075w()));
                }
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 0) {
                do {
                    list.add(Long.valueOf(mo8584t()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = this.f14183b + m13074v();
                while (this.f14183b < v2) {
                    list.add(Long.valueOf(ihz.m13260a(m13075w())));
                }
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: l */
    public final String mo8570l() {
        return m13063a(false);
    }

    /* renamed from: a */
    private final String m13063a(boolean z) {
        m13068c(2);
        int v = m13074v();
        if (v == 0) {
            return "";
        }
        m13066b(v);
        if (z) {
            byte[] bArr = this.f14182a;
            int i = this.f14183b;
            if (!ima.m14069a(bArr, i, i + v)) {
                throw ijh.m13662i();
            }
        }
        String str = new String(this.f14182a, this.f14183b, v, ijf.f14336a);
        this.f14183b += v;
        return str;
    }

    /* renamed from: i */
    public final void mo8565i(List list) {
        m13065a(list, false);
    }

    /* renamed from: a */
    private final void m13065a(List list, boolean z) {
        int i;
        int i2;
        if (imd.m14072a(this.f14185d) != 2) {
            throw ijh.m13659f();
        } else if ((list instanceof ijo) && !z) {
            ijo ijo = (ijo) list;
            do {
                ijo.mo8817a(mo8574n());
                if (!m13073u()) {
                    i2 = this.f14183b;
                } else {
                    return;
                }
            } while (m13074v() == this.f14185d);
            this.f14183b = i2;
        } else {
            do {
                list.add(m13063a(z));
                if (!m13073u()) {
                    i = this.f14183b;
                } else {
                    return;
                }
            } while (m13074v() == this.f14185d);
            this.f14183b = i;
        }
    }

    /* renamed from: j */
    public final void mo8567j(List list) {
        m13065a(list, true);
    }

    /* renamed from: m */
    public final String mo8572m() {
        return m13063a(true);
    }

    /* renamed from: o */
    public final int mo8576o() {
        m13068c(0);
        return m13074v();
    }

    /* renamed from: l */
    public final void mo8571l(List list) {
        int i;
        int i2;
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 0) {
                do {
                    iiy.mo8801d(mo8576o());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = this.f14183b + m13074v();
                while (this.f14183b < v) {
                    iiy.mo8801d(m13074v());
                }
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 0) {
                do {
                    list.add(Integer.valueOf(mo8576o()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = this.f14183b + m13074v();
                while (this.f14183b < v2) {
                    list.add(Integer.valueOf(m13074v()));
                }
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: f */
    public final long mo8558f() {
        m13068c(0);
        return m13075w();
    }

    /* renamed from: c */
    public final void mo8552c(List list) {
        int i;
        int i2;
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int a = imd.m14072a(this.f14185d);
            if (a == 0) {
                do {
                    ijs.mo8805a(mo8558f());
                    if (!m13073u()) {
                        i2 = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i2;
            } else if (a == 2) {
                int v = this.f14183b + m13074v();
                while (this.f14183b < v) {
                    ijs.mo8805a(m13075w());
                }
                m13072f(v);
            } else {
                throw ijh.m13659f();
            }
        } else {
            int a2 = imd.m14072a(this.f14185d);
            if (a2 == 0) {
                do {
                    list.add(Long.valueOf(mo8558f()));
                    if (!m13073u()) {
                        i = this.f14183b;
                    } else {
                        return;
                    }
                } while (m13074v() == this.f14185d);
                this.f14183b = i;
            } else if (a2 == 2) {
                int v2 = this.f14183b + m13074v();
                while (this.f14183b < v2) {
                    list.add(Long.valueOf(m13075w()));
                }
                m13072f(v2);
            } else {
                throw ijh.m13659f();
            }
        }
    }

    /* renamed from: v */
    private final int m13074v() {
        byte b;
        int i = this.f14183b;
        int i2 = this.f14184c;
        if (i2 != i) {
            byte[] bArr = this.f14182a;
            int i3 = i + 1;
            byte b2 = bArr[i];
            if (b2 >= 0) {
                this.f14183b = i3;
                return b2;
            } else if (i2 - i3 < 9) {
                return (int) m13076x();
            } else {
                int i4 = i3 + 1;
                byte b3 = b2 ^ (bArr[i3] << 7);
                if (b3 >= 0) {
                    int i5 = i4 + 1;
                    byte b4 = b3 ^ (bArr[i4] << 14);
                    if (b4 < 0) {
                        i4 = i5 + 1;
                        byte b5 = b4 ^ (bArr[i5] << 21);
                        if (b5 >= 0) {
                            int i6 = i4 + 1;
                            byte b6 = bArr[i4];
                            b = (b5 ^ (b6 << 28)) ^ 266354560;
                            if (b6 < 0) {
                                i4 = i6 + 1;
                                if (bArr[i6] < 0) {
                                    i6 = i4 + 1;
                                    if (bArr[i4] < 0) {
                                        i4 = i6 + 1;
                                        if (bArr[i6] < 0) {
                                            i6 = i4 + 1;
                                            if (bArr[i4] < 0) {
                                                i4 = i6 + 1;
                                                if (bArr[i6] < 0) {
                                                    throw ijh.m13656c();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            i4 = i6;
                        } else {
                            b = b5 ^ -2080896;
                        }
                    } else {
                        b = b4 ^ 16256;
                        i4 = i5;
                    }
                } else {
                    b = b3 ^ Byte.MIN_VALUE;
                }
                this.f14183b = i4;
                return b;
            }
        } else {
            throw ijh.m13654a();
        }
    }

    /* renamed from: w */
    private final long m13075w() {
        int i;
        long j;
        byte b;
        long j2;
        long j3;
        int i2 = this.f14183b;
        int i3 = this.f14184c;
        if (i3 != i2) {
            byte[] bArr = this.f14182a;
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 >= 0) {
                this.f14183b = i4;
                return (long) b2;
            } else if (i3 - i4 < 9) {
                return m13076x();
            } else {
                int i5 = i4 + 1;
                byte b3 = b2 ^ (bArr[i4] << 7);
                if (b3 >= 0) {
                    i = i5 + 1;
                    byte b4 = b3 ^ (bArr[i5] << 14);
                    if (b4 < 0) {
                        i5 = i + 1;
                        byte b5 = b4 ^ (bArr[i] << 21);
                        if (b5 >= 0) {
                            i = i5 + 1;
                            long j4 = (((long) bArr[i5]) << 28) ^ ((long) b5);
                            if (j4 < 0) {
                                int i6 = i + 1;
                                long j5 = j4 ^ (((long) bArr[i]) << 35);
                                if (j5 >= 0) {
                                    i = i6 + 1;
                                    j4 = j5 ^ (((long) bArr[i6]) << 42);
                                    if (j4 < 0) {
                                        i6 = i + 1;
                                        j5 = j4 ^ (((long) bArr[i]) << 49);
                                        if (j5 >= 0) {
                                            i = i6 + 1;
                                            j = (j5 ^ (((long) bArr[i6]) << 56)) ^ 71499008037633920L;
                                            if (j < 0) {
                                                int i7 = i + 1;
                                                if (((long) bArr[i]) >= 0) {
                                                    i = i7;
                                                } else {
                                                    throw ijh.m13656c();
                                                }
                                            }
                                        } else {
                                            j3 = -558586000294016L;
                                        }
                                    } else {
                                        j2 = 4363953127296L;
                                    }
                                } else {
                                    j3 = -34093383808L;
                                }
                                j = j5 ^ j3;
                                i = i6;
                            } else {
                                j2 = 266354560;
                            }
                            j = j4 ^ j2;
                        } else {
                            b = b5 ^ -2080896;
                        }
                    } else {
                        j = (long) (b4 ^ 16256);
                    }
                    this.f14183b = i;
                    return j;
                }
                b = b3 ^ Byte.MIN_VALUE;
                i = i5;
                j = (long) b;
                this.f14183b = i;
                return j;
            }
        } else {
            throw ijh.m13654a();
        }
    }

    /* renamed from: x */
    private final long m13076x() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte y = m13077y();
            j |= ((long) (y & Byte.MAX_VALUE)) << i;
            if ((y & 128) == 0) {
                return j;
            }
        }
        throw ijh.m13656c();
    }

    /* renamed from: b */
    private final void m13066b(int i) {
        if (i < 0 || i > this.f14184c - this.f14183b) {
            throw ijh.m13654a();
        }
    }

    /* renamed from: f */
    private final void m13072f(int i) {
        if (this.f14183b != i) {
            throw ijh.m13654a();
        }
    }

    /* renamed from: c */
    private final void m13068c(int i) {
        if (imd.m14072a(this.f14185d) != i) {
            throw ijh.m13659f();
        }
    }

    /* renamed from: a */
    private final void m13064a(int i) {
        m13066b(i);
        this.f14183b += i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038 A[LOOP:0: B:18:0x0038->B:21:0x0046, LOOP_START] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo8553c() {
        /*
            r7 = this;
            boolean r0 = r7.m13073u()
            r1 = 0
            if (r0 != 0) goto L_0x0094
            int r0 = r7.f14185d
            int r2 = r7.f14186e
            if (r0 == r2) goto L_0x0094
            int r0 = p000.imd.m14072a(r0)
            r2 = 1
            if (r0 == 0) goto L_0x0065
            if (r0 == r2) goto L_0x005f
            r1 = 2
            if (r0 == r1) goto L_0x0057
            r1 = 3
            r3 = 4
            if (r0 == r1) goto L_0x002a
            r1 = 5
            if (r0 != r1) goto L_0x0025
            r7.m13064a((int) r3)
            return r2
        L_0x0025:
            ijg r0 = p000.ijh.m13659f()
            throw r0
        L_0x002a:
            int r0 = r7.f14186e
            int r1 = r7.f14185d
            int r1 = p000.imd.m14074b(r1)
            int r1 = p000.imd.m14073a(r1, r3)
            r7.f14186e = r1
        L_0x0038:
            int r1 = r7.mo8541a()
            r3 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r3) goto L_0x0042
        L_0x0041:
            goto L_0x0049
        L_0x0042:
            boolean r1 = r7.mo8553c()
            if (r1 != 0) goto L_0x0038
            goto L_0x0041
        L_0x0049:
            int r1 = r7.f14185d
            int r3 = r7.f14186e
            if (r1 != r3) goto L_0x0052
            r7.f14186e = r0
            return r2
        L_0x0052:
            ijh r0 = p000.ijh.m13661h()
            throw r0
        L_0x0057:
            int r0 = r7.m13074v()
            r7.m13064a((int) r0)
            return r2
        L_0x005f:
            r0 = 8
            r7.m13064a((int) r0)
            return r2
        L_0x0065:
            int r0 = r7.f14184c
            int r3 = r7.f14183b
            int r0 = r0 - r3
            r4 = 10
            if (r0 < r4) goto L_0x0081
            byte[] r0 = r7.f14182a
            r5 = 0
        L_0x0071:
            if (r5 >= r4) goto L_0x0082
            int r6 = r3 + 1
            byte r3 = r0[r3]
            if (r3 >= 0) goto L_0x007e
            int r5 = r5 + 1
            r3 = r6
            goto L_0x0071
        L_0x007e:
            r7.f14183b = r6
            goto L_0x008e
        L_0x0081:
        L_0x0082:
        L_0x0083:
            if (r1 >= r4) goto L_0x008f
            byte r0 = r7.m13077y()
            if (r0 >= 0) goto L_0x008e
            int r1 = r1 + 1
            goto L_0x0083
        L_0x008e:
            return r2
        L_0x008f:
            ijh r0 = p000.ijh.m13656c()
            throw r0
        L_0x0094:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ihh.mo8553c():boolean");
    }

    /* renamed from: e */
    private final void m13071e(int i) {
        m13066b(i);
        if ((i & 3) != 0) {
            throw ijh.m13661h();
        }
    }

    /* renamed from: d */
    private final void m13070d(int i) {
        m13066b(i);
        if ((i & 7) != 0) {
            throw ijh.m13661h();
        }
    }

    private ihh() {
    }
}
