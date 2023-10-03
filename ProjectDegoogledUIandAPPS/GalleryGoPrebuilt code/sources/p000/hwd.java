package p000;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.logging.Level;

/* renamed from: hwd */
/* compiled from: PG */
public abstract class hwd implements hwm, hwz {

    /* renamed from: a */
    private static final String f13507a = new String();

    /* renamed from: b */
    private final Level f13508b;

    /* renamed from: c */
    private final long f13509c;

    /* renamed from: d */
    private hwb f13510d = null;

    /* renamed from: e */
    private hwg f13511e = null;

    /* renamed from: f */
    private hxk f13512f = null;

    /* renamed from: g */
    private Object[] f13513g = null;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract hys mo8182a();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract hvt mo8183b();

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract hwm mo8184c();

    /* renamed from: d */
    public final Level mo8209d() {
        return this.f13508b;
    }

    /* renamed from: e */
    public final long mo8210e() {
        return this.f13509c;
    }

    /* renamed from: h */
    public final hxk mo8213h() {
        return this.f13512f;
    }

    /* renamed from: l */
    public final hxd mo8217l() {
        hwb hwb = this.f13510d;
        return hwb == null ? hxc.f13571a : hwb;
    }

    protected hwd(Level level, boolean z) {
        long e = hxg.m12382e();
        this.f13508b = (Level) ife.m12827a((Object) level, "level");
        this.f13509c = e;
        if (z) {
            m12273a(hwa.f13500e, (Object) Boolean.TRUE);
        }
    }

    /* renamed from: a */
    private final void m12273a(hwn hwn, Object obj) {
        if (this.f13510d == null) {
            this.f13510d = new hwb();
        }
        hwb hwb = this.f13510d;
        int a = hwb.mo8192a(hwn);
        if (a == -1) {
            int i = hwb.f13504b + 1;
            Object[] objArr = hwb.f13503a;
            int length = objArr.length;
            if (i + i > length) {
                hwb.f13503a = Arrays.copyOf(objArr, length + length);
            }
            Object[] objArr2 = hwb.f13503a;
            int i2 = hwb.f13504b;
            objArr2[i2 + i2] = ife.m12827a((Object) hwn, "metadata key");
            Object[] objArr3 = hwb.f13503a;
            int i3 = hwb.f13504b;
            objArr3[i3 + i3 + 1] = ife.m12827a(obj, "metadata value");
            hwb.f13504b++;
            return;
        }
        hwb.f13503a[a + a + 1] = ife.m12827a(obj, "metadata value");
    }

    /* renamed from: i */
    public final Object[] mo8214i() {
        if (this.f13512f != null) {
            return this.f13513g;
        }
        throw new IllegalStateException("cannot get arguments unless a template context exists");
    }

    /* renamed from: j */
    public final Object mo8215j() {
        if (this.f13512f == null) {
            return this.f13513g[0];
        }
        throw new IllegalStateException("cannot get literal argument if a template context exists");
    }

    /* renamed from: g */
    public final hwg mo8212g() {
        hwg hwg = this.f13511e;
        if (hwg != null) {
            return hwg;
        }
        throw new IllegalStateException("cannot request log site information prior to postProcess()");
    }

    /* renamed from: f */
    public final String mo8211f() {
        return mo8183b().f13492a.mo8243a();
    }

    /* renamed from: m */
    public final void mo8218m() {
        if (m12275n()) {
            m12274b(f13507a, "");
        }
    }

    /* renamed from: a */
    public final void mo8203a(String str) {
        if (m12275n()) {
            m12274b(f13507a, str);
        }
    }

    /* renamed from: a */
    public final void mo8204a(String str, int i) {
        if (m12275n()) {
            m12274b(str, Integer.valueOf(i));
        }
    }

    /* renamed from: a */
    public final void mo8205a(String str, int i, int i2) {
        if (m12275n()) {
            m12274b(str, Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    /* renamed from: a */
    public final void mo8206a(String str, Object obj) {
        if (m12275n()) {
            m12274b(str, obj);
        }
    }

    /* renamed from: a */
    public final void mo8207a(String str, Object obj, Object obj2, Object obj3) {
        if (m12275n()) {
            m12274b(str, obj, obj2, obj3);
        }
    }

    /* renamed from: b */
    private final void m12274b(String str, Object... objArr) {
        this.f13513g = objArr;
        for (int i = 0; i < objArr.length; i++) {
            hvz hvz = objArr[i];
            if (hvz instanceof hvz) {
                objArr[i] = hvz.mo8186a();
            }
        }
        if (str != f13507a) {
            this.f13512f = new hxk(mo8182a(), str);
        }
        hvt b = mo8183b();
        ife.m12827a((Object) this, "data");
        try {
            b.f13492a.mo7299a((hwz) this);
        } catch (RuntimeException e) {
            try {
                b.f13492a.mo7300a(e, this);
            } catch (hxb e2) {
                throw e2;
            } catch (RuntimeException e3) {
                PrintStream printStream = System.err;
                String valueOf = String.valueOf(e3.getMessage());
                printStream.println(valueOf.length() == 0 ? new String("logging error: ") : "logging error: ".concat(valueOf));
                ifn.m12933a((Throwable) e3, System.err);
            }
        }
    }

    /* renamed from: a */
    public final void mo8208a(String str, Object[] objArr) {
        if (m12275n()) {
            m12274b(str, Arrays.copyOf(objArr, objArr.length));
        }
    }

    /* renamed from: n */
    private final boolean m12275n() {
        Object obj;
        int a;
        int i;
        Class<hwd> cls = hwd.class;
        if (this.f13511e == null) {
            this.f13511e = (hwg) ife.m12827a((Object) hxg.m12379a().mo8244a(cls, 1), "logger backend must not return a null LogSite");
        }
        if (this.f13511e != hwg.f13519a) {
            obj = this.f13511e;
            String str = (String) mo8217l().mo8195b(hwa.f13499d);
            if (str != null) {
                obj = new hwc(this.f13511e, str);
            }
        } else {
            obj = null;
        }
        hwb hwb = this.f13510d;
        if (!(hwb == null || obj == null)) {
            Integer num = (Integer) hwb.mo8195b(hwa.f13497b);
            hwi hwi = (hwi) this.f13510d.mo8195b(hwa.f13498c);
            hwj hwj = hwk.f13521a;
            hwk hwk = (hwk) hwj.f13520a.get(obj);
            if (hwk == null) {
                hwk = new hwk();
                hwk hwk2 = (hwk) hwj.f13520a.putIfAbsent(obj, hwk);
                if (hwk2 != null) {
                    hwk = hwk2;
                }
            }
            if (num != null) {
                if (hwk.f13522b.getAndIncrement() % ((long) num.intValue()) != 0) {
                    return false;
                }
            }
            if (hwi != null) {
                hwk.f13523c.get();
                throw null;
            }
        }
        hwo hwo = (hwo) mo8217l().mo8195b(hwa.f13502g);
        if (hwo != null) {
            hwn hwn = hwa.f13502g;
            hwb hwb2 = this.f13510d;
            if (hwb2 != null && (a = hwb2.mo8192a(hwn)) >= 0) {
                int i2 = a + a;
                int i3 = i2 + 2;
                while (true) {
                    i = hwb2.f13504b;
                    if (i3 >= i + i) {
                        break;
                    }
                    Object obj2 = hwb2.f13503a[i3];
                    if (!obj2.equals(hwn)) {
                        Object[] objArr = hwb2.f13503a;
                        objArr[i2] = obj2;
                        objArr[i2 + 1] = objArr[i3 + 1];
                        i2 += 2;
                    }
                    i3 += 2;
                }
                hwb2.f13504b = i - ((i3 - i2) >> 1);
                while (i2 < i3) {
                    hwb2.f13503a[i2] = null;
                    i2++;
                }
            }
            m12273a(hwa.f13496a, (Object) new hwh((Throwable) mo8217l().mo8195b(hwa.f13496a), hwo, hyv.m12484b(cls, new Throwable(), hwo.f13532c)));
        }
        hxj c = hxg.m12381c();
        if (!c.f13582b.isEmpty()) {
            m12273a(hwa.f13501f, (Object) c);
        }
        return true;
    }

    /* renamed from: k */
    public final boolean mo8216k() {
        return this.f13510d != null && Boolean.TRUE.equals(this.f13510d.mo8195b(hwa.f13500e));
    }

    /* renamed from: a */
    public final hwm mo8202a(Throwable th) {
        if (th != null) {
            m12273a(hwa.f13496a, (Object) th);
        }
        return mo8184c();
    }

    /* renamed from: a */
    public final hwm mo8201a(String str, String str2, int i, String str3) {
        hwg a = hwg.m12306a(str, str2, i, str3);
        if (this.f13511e == null) {
            this.f13511e = (hwg) ife.m12827a((Object) a, "log site");
        }
        return mo8184c();
    }

    /* renamed from: a */
    public final hwm mo8200a(hwo hwo) {
        if (ife.m12827a((Object) hwo, "stack size") != hwo.NONE) {
            m12273a(hwa.f13502g, (Object) hwo);
        }
        return mo8184c();
    }
}
