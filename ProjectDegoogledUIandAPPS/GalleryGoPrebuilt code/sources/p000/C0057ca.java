package p000;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: ca */
/* compiled from: PG */
public final class C0057ca implements C0036bg, C0035bf {

    /* renamed from: i */
    private static final TreeMap f3949i = new TreeMap();

    /* renamed from: a */
    public final long[] f3950a;

    /* renamed from: b */
    public final int[] f3951b;

    /* renamed from: c */
    private volatile String f3952c;

    /* renamed from: d */
    private final double[] f3953d;

    /* renamed from: e */
    private final String[] f3954e;

    /* renamed from: f */
    private final byte[][] f3955f;

    /* renamed from: g */
    private final int f3956g;

    /* renamed from: h */
    private int f3957h;

    /* renamed from: a */
    public final String mo1726a() {
        return this.f3952c;
    }

    /* renamed from: a */
    public final void mo1921a(int i, long j) {
        throw null;
    }

    /* renamed from: a */
    public final void mo1923a(int i, byte[] bArr) {
        throw null;
    }

    public final void close() {
    }

    private C0057ca(int i) {
        this.f3956g = i;
        int i2 = i + 1;
        this.f3951b = new int[i2];
        this.f3950a = new long[i2];
        this.f3953d = new double[i2];
        this.f3954e = new String[i2];
        this.f3955f = new byte[i2][];
    }

    /* renamed from: a */
    public static C0057ca m3923a(String str, int i) {
        synchronized (f3949i) {
            Map.Entry ceilingEntry = f3949i.ceilingEntry(Integer.valueOf(i));
            if (ceilingEntry != null) {
                f3949i.remove(ceilingEntry.getKey());
                C0057ca caVar = (C0057ca) ceilingEntry.getValue();
                caVar.m3924b(str, i);
                return caVar;
            }
            C0057ca caVar2 = new C0057ca(i);
            caVar2.m3924b(str, i);
            return caVar2;
        }
    }

    /* renamed from: a */
    public final void mo1920a(int i) {
        this.f3951b[i] = 1;
    }

    /* renamed from: a */
    public final void mo1922a(int i, String str) {
        this.f3951b[i] = 4;
        this.f3954e[i] = str;
    }

    /* renamed from: a */
    public final void mo1727a(C0035bf bfVar) {
        for (int i = 1; i <= this.f3957h; i++) {
            int i2 = this.f3951b[i];
            if (i2 == 1) {
                bfVar.mo1920a(i);
            } else if (i2 == 2) {
                bfVar.mo1921a(i, this.f3950a[i]);
            } else if (i2 == 3) {
                ((C0044bo) bfVar).f3236a.bindDouble(i, this.f3953d[i]);
            } else if (i2 == 4) {
                bfVar.mo1922a(i, this.f3954e[i]);
            } else if (i2 == 5) {
                bfVar.mo1923a(i, this.f3955f[i]);
            }
        }
    }

    /* renamed from: b */
    private final void m3924b(String str, int i) {
        this.f3952c = str;
        this.f3957h = i;
    }

    /* renamed from: b */
    public final void mo2953b() {
        synchronized (f3949i) {
            f3949i.put(Integer.valueOf(this.f3956g), this);
            if (f3949i.size() > 15) {
                int size = f3949i.size() - 10;
                Iterator it = f3949i.descendingKeySet().iterator();
                while (true) {
                    int i = size - 1;
                    if (size <= 0) {
                        break;
                    }
                    it.next();
                    it.remove();
                    size = i;
                }
            }
        }
    }
}
