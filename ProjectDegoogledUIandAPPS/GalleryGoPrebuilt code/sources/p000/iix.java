package p000;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: iix */
/* compiled from: PG */
public abstract class iix extends iha {

    /* renamed from: A */
    public static final Map f14324A = new ConcurrentHashMap();

    /* renamed from: a */
    private int f14325a = -1;

    /* renamed from: z */
    public ilm f14326z = ilm.f14449a;

    /* renamed from: j */
    public static ijc m13613j() {
        return iiy.f14327b;
    }

    /* renamed from: k */
    public static ijd m13614k() {
        return ijs.f14357b;
    }

    /* renamed from: l */
    public static ije m13615l() {
        return ikq.f14400b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo2350a(int i, Object obj);

    /* renamed from: d */
    public final int mo8515d() {
        return this.f14325a;
    }

    /* renamed from: b */
    public static iix m13612b(iix iix) {
        if (iix == null || iix.mo8757c()) {
            return iix;
        }
        throw m12994e().mo8942a();
    }

    /* renamed from: g */
    public final iir mo8793g() {
        return (iir) mo8790b(5);
    }

    /* renamed from: a */
    public final iir mo8788a(iix iix) {
        return mo8793g().mo8503a(iix);
    }

    /* renamed from: b */
    public final Object mo8790b(int i) {
        return mo2350a(i, (Object) null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((iix) mo8790b(6)).getClass().isInstance(obj)) {
            return ikp.f14397a.mo8876a((Object) this).mo8868a((Object) this, (Object) (iix) obj);
        }
        return false;
    }

    /* renamed from: h */
    public final /* bridge */ /* synthetic */ ikf mo8774h() {
        return (iix) mo8790b(6);
    }

    /* renamed from: f */
    public final ikn mo8792f() {
        return (ikn) mo8790b(7);
    }

    /* renamed from: i */
    public final int mo8795i() {
        int i = this.f14325a;
        if (i != -1) {
            return i;
        }
        int b = ikp.f14397a.mo8876a((Object) this).mo8869b(this);
        this.f14325a = b;
        return b;
    }

    public final int hashCode() {
        int i = this.f14173y;
        if (i != 0) {
            return i;
        }
        int a = ikp.f14397a.mo8876a((Object) this).mo8862a(this);
        this.f14173y = a;
        return a;
    }

    /* renamed from: a */
    public static Object m13610a(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    /* renamed from: c */
    public final boolean mo8757c() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) mo8790b(1)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean d = ikp.f14397a.mo8876a((Object) this).mo8872d(this);
        if (!booleanValue) {
            return d;
        }
        mo2350a(2, (Object) !d ? null : this);
        return d;
    }

    /* renamed from: a */
    public static ijc m13606a(ijc ijc) {
        int i;
        int size = ijc.size();
        if (size != 0) {
            i = size + size;
        } else {
            i = 10;
        }
        return ijc.mo8799b(i);
    }

    /* renamed from: a */
    public static ijd m13607a(ijd ijd) {
        int i;
        int size = ijd.size();
        if (size != 0) {
            i = size + size;
        } else {
            i = 10;
        }
        return ijd.mo8806b(i);
    }

    /* renamed from: a */
    public static ije m13608a(ije ije) {
        int i;
        int size = ije.size();
        if (size != 0) {
            i = size + size;
        } else {
            i = 10;
        }
        return ije.mo8585a(i);
    }

    /* renamed from: n */
    public final /* bridge */ /* synthetic */ ike mo8797n() {
        return (iir) mo8790b(5);
    }

    /* renamed from: a */
    protected static Object m13609a(ikf ikf, String str, Object[] objArr) {
        return new ikr(ikf, str, objArr);
    }

    /* renamed from: a */
    public static iih m13600a(ikf ikf, Object obj, ikf ikf2, int i, imb imb) {
        return new iih(ikf, obj, ikf2, new iiw(i, imb));
    }

    /* renamed from: a */
    public static iix m13602a(iix iix, InputStream inputStream) {
        iix iix2;
        iij a = iij.m13501a();
        try {
            int read = inputStream.read();
            if (read != -1) {
                ihz a2 = ihz.m13261a((InputStream) new igy(inputStream, ihz.m13259a(read, inputStream)));
                iix2 = (iix) iix.mo8790b(4);
                try {
                    iky a3 = ikp.f14397a.mo8876a((Object) iix2);
                    a3.mo8865a(iix2, iia.m13289a(a2), a);
                    a3.mo8871c(iix2);
                    try {
                        a2.mo8629a(0);
                    } catch (ijh e) {
                        throw e;
                    }
                } catch (IOException e2) {
                    if (e2.getCause() instanceof ijh) {
                        throw ((ijh) e2.getCause());
                    }
                    throw new ijh(e2.getMessage());
                } catch (RuntimeException e3) {
                    if (e3.getCause() instanceof ijh) {
                        throw ((ijh) e3.getCause());
                    }
                    throw e3;
                }
            } else {
                iix2 = null;
            }
            return m13612b(iix2);
        } catch (IOException e4) {
            throw new ijh(e4.getMessage());
        }
    }

    /* renamed from: a */
    public static iix m13603a(iix iix, byte[] bArr) {
        return m13612b(m13604a(iix, bArr, 0, bArr.length, iij.m13501a()));
    }

    /* renamed from: a */
    public static iix m13605a(iix iix, byte[] bArr, iij iij) {
        return m13612b(m13604a(iix, bArr, 0, bArr.length, iij));
    }

    /* renamed from: a */
    static iix m13601a(iix iix, ihz ihz, iij iij) {
        iix iix2 = (iix) iix.mo8790b(4);
        try {
            iky a = ikp.f14397a.mo8876a((Object) iix2);
            a.mo8865a(iix2, iia.m13289a(ihz), iij);
            a.mo8871c(iix2);
            return iix2;
        } catch (IOException e) {
            if (e.getCause() instanceof ijh) {
                throw ((ijh) e.getCause());
            }
            throw new ijh(e.getMessage());
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof ijh) {
                throw ((ijh) e2.getCause());
            }
            throw e2;
        }
    }

    /* renamed from: a */
    public static iix m13604a(iix iix, byte[] bArr, int i, int i2, iij iij) {
        iix iix2 = (iix) iix.mo8790b(4);
        try {
            iky a = ikp.f14397a.mo8876a((Object) iix2);
            a.mo8867a(iix2, bArr, i, i + i2, new ihf(iij));
            a.mo8871c(iix2);
            if (iix2.f14173y == 0) {
                return iix2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof ijh) {
                throw ((ijh) e.getCause());
            }
            throw new ijh(e.getMessage());
        } catch (IndexOutOfBoundsException e2) {
            throw ijh.m13654a();
        }
    }

    /* renamed from: a */
    protected static void m13611a(Class cls, iix iix) {
        f14324A.put(cls, iix);
    }

    /* renamed from: a */
    public final void mo8510a(int i) {
        this.f14325a = i;
    }

    /* renamed from: m */
    public final /* bridge */ /* synthetic */ ike mo8796m() {
        iir iir = (iir) mo8790b(5);
        iir.mo8503a(this);
        return iir;
    }

    public final String toString() {
        String obj = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(obj);
        imi.m14109a((ikf) this, sb, 0);
        return sb.toString();
    }

    /* renamed from: a */
    public final void mo8789a(iie iie) {
        iky a = ikp.f14397a.mo8876a((Object) this);
        iif iif = iie.f14237b;
        if (iif == null) {
            iif = new iif(iie);
        }
        a.mo8866a((Object) this, (ime) iif);
    }
}
