package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* renamed from: iim */
/* compiled from: PG */
public final class iim {

    /* renamed from: c */
    public static final iim f14254c = new iim((byte[]) null);

    /* renamed from: a */
    public final ilh f14255a = ilh.m13960a(16);

    /* renamed from: b */
    public boolean f14256b;

    /* renamed from: d */
    private boolean f14257d;

    private iim() {
    }

    private iim(byte[] bArr) {
        mo8729b();
        mo8729b();
    }

    /* renamed from: c */
    public final iim clone() {
        iim iim = new iim();
        for (int i = 0; i < this.f14255a.mo8916a(); i++) {
            Map.Entry b = this.f14255a.mo8919b(i);
            iim.mo8723a((iil) b.getKey(), b.getValue());
        }
        for (Map.Entry entry : this.f14255a.mo8918b()) {
            iim.mo8723a((iil) entry.getKey(), entry.getValue());
        }
        iim.f14257d = this.f14257d;
        return iim;
    }

    /* renamed from: a */
    private static Object m13513a(Object obj) {
        if (obj instanceof ikj) {
            return ((ikj) obj).mo8873a();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    /* renamed from: a */
    static int m13512a(imb imb, int i, Object obj) {
        int f = iie.m13420f(i);
        if (imb == imb.GROUP) {
            ijf.m13649a((ikf) obj);
            f += f;
        }
        imc imc = imc.INT;
        int i2 = 4;
        switch (imb.ordinal()) {
            case 0:
                ((Double) obj).doubleValue();
                i2 = 8;
                break;
            case 1:
                ((Float) obj).floatValue();
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                i2 = iie.m13416d(((Long) obj).longValue());
                break;
            case 3:
                i2 = iie.m13416d(((Long) obj).longValue());
                break;
            case 4:
                i2 = iie.m13424g(((Integer) obj).intValue());
                break;
            case 5:
                ((Long) obj).longValue();
                i2 = 8;
                break;
            case 6:
                ((Integer) obj).intValue();
                break;
            case 7:
                ((Boolean) obj).booleanValue();
                i2 = 1;
                break;
            case 8:
                if (!(obj instanceof ihw)) {
                    i2 = iie.m13411b((String) obj);
                    break;
                } else {
                    i2 = iie.m13409b((ihw) obj);
                    break;
                }
            case 9:
                i2 = iie.m13417d((ikf) obj);
                break;
            case 10:
                if (!(obj instanceof ijl)) {
                    i2 = iie.m13410b((ikf) obj);
                    break;
                } else {
                    i2 = iie.m13403a((ijm) (ijl) obj);
                    break;
                }
            case 11:
                if (!(obj instanceof ihw)) {
                    i2 = iie.m13412b((byte[]) obj);
                    break;
                } else {
                    i2 = iie.m13409b((ihw) obj);
                    break;
                }
            case 12:
                i2 = iie.m13426h(((Integer) obj).intValue());
                break;
            case 13:
                if (!(obj instanceof iiz)) {
                    i2 = iie.m13424g(((Integer) obj).intValue());
                    break;
                } else {
                    i2 = iie.m13424g(((iiz) obj).getNumber());
                    break;
                }
            case 14:
                ((Integer) obj).intValue();
                break;
            case 15:
                ((Long) obj).longValue();
                i2 = 8;
                break;
            case 16:
                i2 = iie.m13428i(((Integer) obj).intValue());
                break;
            case 17:
                i2 = iie.m13419e(((Long) obj).longValue());
                break;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return f + i2;
    }

    /* renamed from: b */
    public static int m13515b(iil iil, Object obj) {
        imb b = iil.mo8718b();
        int a = iil.mo8716a();
        iil.mo8722f();
        return m13512a(b, a, obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof iim) {
            return this.f14255a.equals(((iim) obj).f14255a);
        }
        return false;
    }

    /* renamed from: b */
    public final Object mo8728b(iil iil) {
        Object obj = this.f14255a.get(iil);
        if (!(obj instanceof ijl)) {
            return obj;
        }
        ijl ijl = (ijl) obj;
        throw null;
    }

    /* renamed from: b */
    public final int mo8727b(Map.Entry entry) {
        iil iil = (iil) entry.getKey();
        Object value = entry.getValue();
        if (iil.mo8719c() != imc.MESSAGE) {
            return m13515b(iil, value);
        }
        iil.mo8722f();
        iil.mo8721e();
        if (value instanceof ijl) {
            int f = iie.m13420f(1);
            return f + f + iie.m13425g(2, ((iil) entry.getKey()).mo8716a()) + iie.m13402a(3, (ijm) (ijl) value);
        }
        int f2 = iie.m13420f(1);
        return f2 + f2 + iie.m13425g(2, ((iil) entry.getKey()).mo8716a()) + iie.m13420f(3) + iie.m13410b((ikf) value);
    }

    /* renamed from: a */
    public final boolean mo8726a(iil iil) {
        return this.f14255a.get(iil) != null;
    }

    public final int hashCode() {
        return this.f14255a.hashCode();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo8725a() {
        return this.f14255a.isEmpty();
    }

    /* renamed from: e */
    public final boolean mo8733e() {
        for (int i = 0; i < this.f14255a.mo8916a(); i++) {
            if (!m13516c(this.f14255a.mo8919b(i))) {
                return false;
            }
        }
        for (Map.Entry c : this.f14255a.mo8918b()) {
            if (!m13516c(c)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    private static boolean m13516c(Map.Entry entry) {
        iil iil = (iil) entry.getKey();
        if (iil.mo8719c() == imc.MESSAGE) {
            iil.mo8722f();
            Object value = entry.getValue();
            if (value instanceof ikf) {
                if (!((ikf) value).mo8757c()) {
                    return false;
                }
            } else if (value instanceof ijl) {
                return true;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    /* renamed from: d */
    public final Iterator mo8732d() {
        if (this.f14257d) {
            return new ijk(this.f14255a.entrySet().iterator());
        }
        return this.f14255a.entrySet().iterator();
    }

    /* renamed from: b */
    public final void mo8729b() {
        Map map;
        Map map2;
        if (!this.f14256b) {
            ilh ilh = this.f14255a;
            if (!ilh.f14444c) {
                for (int i = 0; i < ilh.mo8916a(); i++) {
                    ((iil) ilh.mo8919b(i).getKey()).mo8722f();
                }
                for (Map.Entry key : ilh.mo8918b()) {
                    ((iil) key.getKey()).mo8722f();
                }
            }
            if (!ilh.f14444c) {
                if (ilh.f14443b.isEmpty()) {
                    map = Collections.emptyMap();
                } else {
                    map = Collections.unmodifiableMap(ilh.f14443b);
                }
                ilh.f14443b = map;
                if (ilh.f14445d.isEmpty()) {
                    map2 = Collections.emptyMap();
                } else {
                    map2 = Collections.unmodifiableMap(ilh.f14445d);
                }
                ilh.f14445d = map2;
                ilh.f14444c = true;
            }
            this.f14256b = true;
        }
    }

    /* renamed from: a */
    public final void mo8724a(Map.Entry entry) {
        Object obj;
        iil iil = (iil) entry.getKey();
        Object value = entry.getValue();
        if (!(value instanceof ijl)) {
            iil.mo8722f();
            if (iil.mo8719c() == imc.MESSAGE) {
                Object b = mo8728b(iil);
                if (b == null) {
                    this.f14255a.put(iil, m13513a(value));
                    return;
                }
                if (b instanceof ikj) {
                    ikj ikj = (ikj) b;
                    ikj ikj2 = (ikj) value;
                    obj = iil.mo8720d();
                } else {
                    obj = iil.mo8717a(((ikf) b).mo8796m(), (ikf) value).mo8770g();
                }
                this.f14255a.put(iil, obj);
                return;
            }
            this.f14255a.put(iil, m13513a(value));
            return;
        }
        ijl ijl = (ijl) value;
        throw null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        if ((r4 instanceof byte[]) == false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
        if (r0 != false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        if ((r4 instanceof p000.ijl) != false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004b, code lost:
        r2.f14257d = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004e, code lost:
        r2.f14255a.mo8917a(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0053, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005b, code lost:
        throw new java.lang.IllegalArgumentException("Wrong object type used with protocol message reflection.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001e, code lost:
        if ((r4 instanceof p000.ijl) == false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0027, code lost:
        if ((r4 instanceof p000.iiz) == false) goto L_0x0054;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8723a(p000.iil r3, java.lang.Object r4) {
        /*
            r2 = this;
            r3.mo8722f()
            imb r0 = r3.mo8718b()
            p000.ijf.m13650a((java.lang.Object) r4)
            imb r1 = p000.imb.DOUBLE
            imc r1 = p000.imc.INT
            imc r0 = r0.f14494i
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L_0x0042;
                case 1: goto L_0x003f;
                case 2: goto L_0x003c;
                case 3: goto L_0x0039;
                case 4: goto L_0x0036;
                case 5: goto L_0x0033;
                case 6: goto L_0x002a;
                case 7: goto L_0x0021;
                case 8: goto L_0x0018;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x0054
        L_0x0018:
            boolean r0 = r4 instanceof p000.ikf
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof p000.ijl
            if (r0 == 0) goto L_0x0054
            goto L_0x0046
        L_0x0021:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof p000.iiz
            if (r0 == 0) goto L_0x0054
            goto L_0x0046
        L_0x002a:
            boolean r0 = r4 instanceof p000.ihw
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof byte[]
            if (r0 == 0) goto L_0x0054
            goto L_0x0046
        L_0x0033:
            boolean r0 = r4 instanceof java.lang.String
            goto L_0x0044
        L_0x0036:
            boolean r0 = r4 instanceof java.lang.Boolean
            goto L_0x0044
        L_0x0039:
            boolean r0 = r4 instanceof java.lang.Double
            goto L_0x0044
        L_0x003c:
            boolean r0 = r4 instanceof java.lang.Float
            goto L_0x0044
        L_0x003f:
            boolean r0 = r4 instanceof java.lang.Long
            goto L_0x0044
        L_0x0042:
            boolean r0 = r4 instanceof java.lang.Integer
        L_0x0044:
            if (r0 == 0) goto L_0x0054
        L_0x0046:
            boolean r0 = r4 instanceof p000.ijl
            if (r0 != 0) goto L_0x004b
            goto L_0x004e
        L_0x004b:
            r0 = 1
            r2.f14257d = r0
        L_0x004e:
            ilh r0 = r2.f14255a
            r0.put(r3, r4)
            return
        L_0x0054:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Wrong object type used with protocol message reflection."
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.iim.mo8723a(iil, java.lang.Object):void");
    }

    /* renamed from: a */
    static void m13514a(iie iie, imb imb, int i, Object obj) {
        if (imb != imb.GROUP) {
            iie.mo8655a(i, imb.f14495j);
            imc imc = imc.INT;
            switch (imb.ordinal()) {
                case 0:
                    iie.mo8676a(((Double) obj).doubleValue());
                    return;
                case 1:
                    iie.mo8677a(((Float) obj).floatValue());
                    return;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    iie.mo8662a(((Long) obj).longValue());
                    return;
                case 3:
                    iie.mo8662a(((Long) obj).longValue());
                    return;
                case 4:
                    iie.mo8654a(((Integer) obj).intValue());
                    return;
                case 5:
                    iie.mo8672b(((Long) obj).longValue());
                    return;
                case 6:
                    iie.mo8673c(((Integer) obj).intValue());
                    return;
                case 7:
                    iie.mo8653a(((Boolean) obj).booleanValue() ? (byte) 1 : 0);
                    return;
                case 8:
                    if (obj instanceof ihw) {
                        iie.mo8663a((ihw) obj);
                        return;
                    } else {
                        iie.mo8665a((String) obj);
                        return;
                    }
                case 9:
                    iie.mo8684c((ikf) obj);
                    return;
                case 10:
                    iie.mo8664a((ikf) obj);
                    return;
                case 11:
                    if (obj instanceof ihw) {
                        iie.mo8663a((ihw) obj);
                        return;
                    }
                    byte[] bArr = (byte[]) obj;
                    iie.mo8666a(bArr, bArr.length);
                    return;
                case 12:
                    iie.mo8668b(((Integer) obj).intValue());
                    return;
                case 13:
                    if (obj instanceof iiz) {
                        iie.mo8654a(((iiz) obj).getNumber());
                        return;
                    } else {
                        iie.mo8654a(((Integer) obj).intValue());
                        return;
                    }
                case 14:
                    iie.mo8673c(((Integer) obj).intValue());
                    return;
                case 15:
                    iie.mo8672b(((Long) obj).longValue());
                    return;
                case 16:
                    iie.mo8685e(((Integer) obj).intValue());
                    return;
                case 17:
                    iie.mo8683c(((Long) obj).longValue());
                    return;
                default:
                    return;
            }
        } else {
            ikf ikf = (ikf) obj;
            ijf.m13649a(ikf);
            iie.mo8655a(i, 3);
            iie.mo8684c(ikf);
            iie.mo8655a(i, 4);
        }
    }
}
