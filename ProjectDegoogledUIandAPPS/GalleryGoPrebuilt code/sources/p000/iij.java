package p000;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: iij */
/* compiled from: PG */
public class iij {

    /* renamed from: a */
    private static volatile boolean f14247a = false;

    /* renamed from: b */
    private static volatile iij f14248b;

    /* renamed from: c */
    private static volatile iij f14249c;

    /* renamed from: d */
    private static final iij f14250d = new iij((byte[]) null);

    /* renamed from: e */
    private final Map f14251e;

    iij() {
        this.f14251e = new HashMap();
    }

    public iij(byte[] bArr) {
        this.f14251e = Collections.emptyMap();
    }

    /* renamed from: a */
    public iih mo8715a(ikf ikf, int i) {
        return (iih) this.f14251e.get(new iii(ikf, i));
    }

    /* renamed from: a */
    public static iij m13501a() {
        iij iij = f14248b;
        if (iij == null) {
            synchronized (iij.class) {
                iij = f14248b;
                if (iij == null) {
                    iij = f14250d;
                    f14248b = iij;
                }
            }
        }
        return iij;
    }

    /* renamed from: b */
    public static iij m13502b() {
        Class<iij> cls = iij.class;
        iij iij = f14249c;
        if (iij != null) {
            return iij;
        }
        synchronized (cls) {
            iij iij2 = f14249c;
            if (iij2 != null) {
                return iij2;
            }
            iij a = iip.m13531a(cls);
            f14249c = a;
            return a;
        }
    }
}
