package p000;

import android.support.p002v7.widget.RecyclerView;
import android.util.Log;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: fsj */
/* compiled from: PG */
final class fsj {

    /* renamed from: i */
    private static final Charset f10495i = Charset.forName("US-ASCII");

    /* renamed from: t */
    private static final short f10496t = fsc.m9495a(fsc.f10454g);

    /* renamed from: u */
    private static final short f10497u = fsc.m9495a(fsc.f10455h);

    /* renamed from: v */
    private static final short f10498v = fsc.m9495a(fsc.f10463p);

    /* renamed from: w */
    private static final short f10499w = fsc.m9495a(fsc.f10456i);

    /* renamed from: x */
    private static final short f10500x = fsc.m9495a(fsc.f10457j);

    /* renamed from: y */
    private static final short f10501y = fsc.m9495a(fsc.f10427b);

    /* renamed from: z */
    private static final short f10502z = fsc.m9495a(fsc.f10451d);

    /* renamed from: a */
    public final fsb f10503a;

    /* renamed from: b */
    public int f10504b;

    /* renamed from: c */
    public fsl f10505c;

    /* renamed from: d */
    public fsi f10506d;

    /* renamed from: e */
    public fsl f10507e;

    /* renamed from: f */
    public fsl f10508f;

    /* renamed from: g */
    public int f10509g;

    /* renamed from: h */
    public final TreeMap f10510h = new TreeMap();

    /* renamed from: j */
    private final int f10511j;

    /* renamed from: k */
    private int f10512k = 0;

    /* renamed from: l */
    private int f10513l = 0;

    /* renamed from: m */
    private boolean f10514m;

    /* renamed from: n */
    private boolean f10515n = false;

    /* renamed from: o */
    private int f10516o;

    /* renamed from: p */
    private int f10517p = 0;

    /* renamed from: q */
    private byte[] f10518q;

    /* renamed from: r */
    private int f10519r;

    /* renamed from: s */
    private final fsc f10520s;

    /* renamed from: a */
    private final boolean m9531a(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? (this.f10511j & 8) != 0 : (this.f10511j & 16) != 0 : (this.f10511j & 4) != 0 : (this.f10511j & 2) != 0 : (this.f10511j & 1) != 0;
    }

    /* renamed from: b */
    private final boolean m9536b() {
        return (this.f10511j & 32) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0128 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fsj(java.io.InputStream r7, p000.fsc r8) {
        /*
            r6 = this;
            r6.<init>()
            r0 = 0
            r6.f10512k = r0
            r6.f10513l = r0
            r6.f10515n = r0
            r6.f10517p = r0
            java.util.TreeMap r1 = new java.util.TreeMap
            r1.<init>()
            r6.f10510h = r1
            r6.f10520s = r8
            fsb r8 = new fsb
            r8.<init>(r7)
            short r1 = r8.mo6092a()
            r2 = -40
            if (r1 != r2) goto L_0x0129
            short r1 = r8.mo6092a()
        L_0x0026:
            r2 = -39
            if (r1 == r2) goto L_0x0085
            boolean r2 = p000.fsn.m9565a(r1)
            if (r2 != 0) goto L_0x0084
            int r2 = r8.mo6095b()
            r3 = -31
            r4 = 2
            if (r1 != r3) goto L_0x005f
            byte[] r1 = p000.fsn.f10535a
            int r1 = r1.length
            int r1 = r1 + r4
            if (r2 < r1) goto L_0x005f
            byte[] r1 = p000.fsn.f10535a
            int r1 = r1.length
            byte[] r1 = new byte[r1]
            byte[] r3 = p000.fsn.f10535a
            int r3 = r3.length
            r8.read(r1, r0, r3)
            byte[] r3 = p000.fsn.f10535a
            int r3 = r3.length
            int r2 = r2 - r3
            byte[] r3 = p000.fsn.f10535a
            boolean r1 = java.util.Arrays.equals(r1, r3)
            if (r1 == 0) goto L_0x005f
            int r8 = r8.f10345a
            r6.f10516o = r2
            int r8 = r8 + r2
            r6.f10517p = r8
            r8 = 1
            goto L_0x0087
        L_0x005f:
            if (r2 < r4) goto L_0x0072
            int r2 = r2 + -2
            long r1 = (long) r2
            long r3 = r8.skip(r1)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x006d
            goto L_0x0072
        L_0x006d:
            short r1 = r8.mo6092a()
            goto L_0x0026
        L_0x0072:
            r8 = 5
            java.lang.String r1 = "ExifParser"
            boolean r8 = android.util.Log.isLoggable(r1, r8)
            if (r8 == 0) goto L_0x0083
            java.lang.String r8 = "Invalid JPEG format."
            android.util.Log.w(r1, r8)
            r8 = 0
            goto L_0x0087
        L_0x0083:
            goto L_0x0086
        L_0x0084:
            goto L_0x0086
        L_0x0085:
        L_0x0086:
            r8 = 0
        L_0x0087:
            r6.f10515n = r8
            fsb r8 = new fsb
            r8.<init>(r7)
            r6.f10503a = r8
            r7 = 63
            r6.f10511j = r7
            boolean r7 = r6.f10515n
            if (r7 == 0) goto L_0x0128
            short r7 = r8.mo6092a()
            r8 = 18761(0x4949, float:2.629E-41)
            java.lang.String r1 = "Invalid TIFF header"
            if (r7 != r8) goto L_0x00aa
            fsb r7 = r6.f10503a
            java.nio.ByteOrder r8 = java.nio.ByteOrder.LITTLE_ENDIAN
            r7.mo6093a(r8)
            goto L_0x00b5
        L_0x00aa:
            r8 = 19789(0x4d4d, float:2.773E-41)
            if (r7 != r8) goto L_0x0122
            fsb r7 = r6.f10503a
            java.nio.ByteOrder r8 = java.nio.ByteOrder.BIG_ENDIAN
            r7.mo6093a(r8)
        L_0x00b5:
            fsb r7 = r6.f10503a
            short r7 = r7.mo6092a()
            r8 = 42
            if (r7 != r8) goto L_0x011c
            fsb r7 = r6.f10503a
            long r7 = r7.mo6097d()
            r1 = 2147483647(0x7fffffff, double:1.060997895E-314)
            java.lang.String r3 = "Invalid offset "
            r4 = 35
            int r5 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r5 > 0) goto L_0x0107
            int r1 = (int) r7
            r6.f10519r = r1
            r6.f10504b = r0
            boolean r2 = r6.m9531a((int) r0)
            if (r2 != 0) goto L_0x00e1
            boolean r2 = r6.m9537c()
            if (r2 == 0) goto L_0x00f1
        L_0x00e1:
            r6.m9530a((int) r0, (long) r7)
            int r1 = r1 + -8
            if (r1 < 0) goto L_0x00f2
            if (r1 <= 0) goto L_0x00f1
            byte[] r7 = new byte[r1]
            r6.f10518q = r7
            r6.mo6131a((byte[]) r7)
        L_0x00f1:
            return
        L_0x00f2:
            fse r0 = new fse
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r4)
            r1.append(r3)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7)
            throw r0
        L_0x0107:
            fse r0 = new fse
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r4)
            r1.append(r3)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7)
            throw r0
        L_0x011c:
            fse r7 = new fse
            r7.<init>(r1)
            throw r7
        L_0x0122:
            fse r7 = new fse
            r7.<init>(r1)
            throw r7
        L_0x0128:
            return
        L_0x0129:
            fse r7 = new fse
            java.lang.String r8 = "Invalid JPEG format"
            r7.<init>(r8)
            goto L_0x0132
        L_0x0131:
            throw r7
        L_0x0132:
            goto L_0x0131
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fsj.<init>(java.io.InputStream, fsc):void");
    }

    /* renamed from: a */
    private final boolean m9532a(int i, int i2) {
        int i3 = this.f10520s.mo6114d().get(i2);
        if (i3 != 0) {
            return fsc.m9497b(i3, i);
        }
        return false;
    }

    /* renamed from: b */
    private final void m9535b(fsl fsl) {
        if (fsl.f10527d != 0) {
            short s = fsl.f10524a;
            int i = fsl.f10528e;
            if (s != f10496t || !m9532a(i, fsc.f10454g)) {
                if (s != f10497u || !m9532a(i, fsc.f10455h)) {
                    if (s != f10498v || !m9532a(i, fsc.f10463p)) {
                        if (s != f10499w || !m9532a(i, fsc.f10456i)) {
                            if (s != f10500x || !m9532a(i, fsc.f10457j)) {
                                if (s != f10501y || !m9532a(i, fsc.f10427b)) {
                                    if (s == f10502z && m9532a(i, fsc.f10451d) && m9536b() && fsl.mo6140b()) {
                                        this.f10507e = fsl;
                                    }
                                } else if (!m9536b()) {
                                } else {
                                    if (fsl.mo6140b()) {
                                        for (int i2 = 0; i2 < fsl.f10527d; i2++) {
                                            if (fsl.f10525b == 3) {
                                                m9534b(i2, fsl.mo6142c(i2));
                                            } else {
                                                m9534b(i2, fsl.mo6142c(i2));
                                            }
                                        }
                                        return;
                                    }
                                    this.f10510h.put(Integer.valueOf(fsl.f10530g), new fsg(fsl, false));
                                }
                            } else if (m9536b()) {
                                this.f10508f = fsl;
                            }
                        } else if (m9536b()) {
                            this.f10510h.put(Integer.valueOf((int) fsl.mo6142c(0)), new fsi());
                        }
                    } else if (m9531a(3)) {
                        m9530a(3, fsl.mo6142c(0));
                    }
                } else if (m9531a(4)) {
                    m9530a(4, fsl.mo6142c(0));
                }
            } else if (m9531a(2) || m9531a(3)) {
                m9530a(2, fsl.mo6142c(0));
            }
        }
    }

    /* renamed from: c */
    private final boolean m9537c() {
        int i = this.f10504b;
        if (i == 0) {
            return m9531a(2) || m9531a(4) || m9531a(3) || m9531a(1);
        }
        if (i == 1) {
            return m9536b();
        }
        if (i != 2) {
            return false;
        }
        return m9531a(3);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final int mo6130a() {
        if (!this.f10515n) {
            return 6;
        }
        int i = this.f10503a.f10345a;
        int i2 = this.f10512k + 2 + (this.f10513l * 12);
        if (i < i2) {
            fsl d = m9538d();
            this.f10505c = d;
            if (d == null) {
                return mo6130a();
            }
            if (this.f10514m) {
                m9535b(d);
            }
            return 1;
        }
        if (i == i2) {
            if (this.f10504b != 0) {
                int intValue = !this.f10510h.isEmpty() ? ((Integer) this.f10510h.firstEntry().getKey()).intValue() - this.f10503a.f10345a : 4;
                if (intValue >= 4) {
                    long f = m9540f();
                    if (f != 0 && Log.isLoggable("ExifParser", 5)) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append("Invalid link to next IFD: ");
                        sb.append(f);
                        Log.w("ExifParser", sb.toString());
                    }
                } else if (Log.isLoggable("ExifParser", 5)) {
                    StringBuilder sb2 = new StringBuilder(45);
                    sb2.append("Invalid size of link to next IFD: ");
                    sb2.append(intValue);
                    Log.w("ExifParser", sb2.toString());
                }
            } else {
                long f2 = m9540f();
                if ((m9531a(1) || m9536b()) && f2 != 0) {
                    m9530a(1, f2);
                }
            }
        }
        while (!this.f10510h.isEmpty()) {
            Map.Entry pollFirstEntry = this.f10510h.pollFirstEntry();
            Object value = pollFirstEntry.getValue();
            try {
                m9533b(((Integer) pollFirstEntry.getKey()).intValue());
                if (value instanceof fsh) {
                    fsh fsh = (fsh) value;
                    this.f10504b = fsh.f10491a;
                    this.f10513l = this.f10503a.mo6095b();
                    int intValue2 = ((Integer) pollFirstEntry.getKey()).intValue();
                    this.f10512k = intValue2;
                    if ((this.f10513l * 12) + intValue2 + 2 > this.f10516o) {
                        if (Log.isLoggable("ExifParser", 5)) {
                            int i3 = this.f10504b;
                            StringBuilder sb3 = new StringBuilder(31);
                            sb3.append("Invalid size of IFD ");
                            sb3.append(i3);
                            Log.w("ExifParser", sb3.toString());
                        }
                        return 6;
                    }
                    boolean c = m9537c();
                    this.f10514m = c;
                    if (fsh.f10492b) {
                        return 0;
                    }
                    int i4 = this.f10512k + 2 + (this.f10513l * 12);
                    int i5 = this.f10503a.f10345a;
                    if (i5 <= i4) {
                        if (c) {
                            while (i5 < i4) {
                                fsl d2 = m9538d();
                                this.f10505c = d2;
                                i5 += 12;
                                if (d2 != null) {
                                    m9535b(d2);
                                }
                            }
                        } else {
                            m9533b(i4);
                        }
                        long f3 = m9540f();
                        if (this.f10504b == 0 && ((m9531a(1) || m9536b()) && f3 > 0)) {
                            m9530a(1, f3);
                        }
                    }
                } else if (!(value instanceof fsi)) {
                    fsg fsg = (fsg) value;
                    fsl fsl = fsg.f10489a;
                    this.f10505c = fsl;
                    if (fsl.f10525b != 7) {
                        mo6132a(fsl);
                        m9535b(this.f10505c);
                    }
                    if (fsg.f10490b) {
                        return 2;
                    }
                } else {
                    fsi fsi = (fsi) value;
                    this.f10506d = fsi;
                    return fsi.f10494b;
                }
            } catch (IOException e) {
                if (Log.isLoggable("ExifParser", 5)) {
                    String valueOf = String.valueOf(pollFirstEntry.getKey());
                    String name = value.getClass().getName();
                    StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf).length() + 57 + String.valueOf(name).length());
                    sb4.append("Failed to skip to data at: ");
                    sb4.append(valueOf);
                    sb4.append(" for ");
                    sb4.append(name);
                    sb4.append(", the file may be broken.");
                    Log.w("ExifParser", sb4.toString());
                }
            }
        }
        if (this.f10509g != 0 || !m9539e()) {
            return 6;
        }
        return 5;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final int mo6131a(byte[] bArr) {
        return this.f10503a.read(bArr);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6133a(byte[] bArr, int i) {
        this.f10503a.read(bArr, 0, i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6132a(fsl fsl) {
        String str;
        short s = fsl.f10525b;
        if (s == 2 || s == 7 || s == 1) {
            int i = fsl.f10527d;
            if (!this.f10510h.isEmpty() && ((Integer) this.f10510h.firstEntry().getKey()).intValue() < this.f10503a.f10345a + i) {
                Object value = this.f10510h.firstEntry().getValue();
                if (value instanceof fsi) {
                    if (Log.isLoggable("ExifParser", 5)) {
                        String valueOf = String.valueOf(fsl);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 35);
                        sb.append("Thumbnail overlaps value for tag: \n");
                        sb.append(valueOf);
                        Log.w("ExifParser", sb.toString());
                    }
                    Map.Entry pollFirstEntry = this.f10510h.pollFirstEntry();
                    if (Log.isLoggable("ExifParser", 5)) {
                        String valueOf2 = String.valueOf(pollFirstEntry.getKey());
                        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 26);
                        sb2.append("Invalid thumbnail offset: ");
                        sb2.append(valueOf2);
                        Log.w("ExifParser", sb2.toString());
                    }
                } else {
                    if (value instanceof fsh) {
                        if (Log.isLoggable("ExifParser", 5)) {
                            int i2 = ((fsh) value).f10491a;
                            String valueOf3 = String.valueOf(fsl);
                            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 41);
                            sb3.append("Ifd ");
                            sb3.append(i2);
                            sb3.append(" overlaps value for tag: \n");
                            sb3.append(valueOf3);
                            Log.w("ExifParser", sb3.toString());
                        }
                    } else if ((value instanceof fsg) && Log.isLoggable("ExifParser", 5)) {
                        String valueOf4 = String.valueOf(((fsg) value).f10489a);
                        String valueOf5 = String.valueOf(fsl);
                        StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf4).length() + 46 + String.valueOf(valueOf5).length());
                        sb4.append("Tag value for tag: \n");
                        sb4.append(valueOf4);
                        sb4.append(" overlaps value for tag: \n");
                        sb4.append(valueOf5);
                        Log.w("ExifParser", sb4.toString());
                    }
                    int intValue = ((Integer) this.f10510h.firstEntry().getKey()).intValue() - this.f10503a.f10345a;
                    if (intValue >= 0) {
                        if (Log.isLoggable("ExifParser", 5)) {
                            String valueOf6 = String.valueOf(fsl);
                            StringBuilder sb5 = new StringBuilder(String.valueOf(valueOf6).length() + 52);
                            sb5.append("Invalid size of tag: \n");
                            sb5.append(valueOf6);
                            sb5.append(" setting count to: ");
                            sb5.append(intValue);
                            Log.w("ExifParser", sb5.toString());
                        }
                        if (intValue > 0) {
                            fsl.f10527d = intValue;
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } else if (Log.isLoggable("ExifParser", 5)) {
                        String valueOf7 = String.valueOf(fsl);
                        StringBuilder sb6 = new StringBuilder(String.valueOf(valueOf7).length() + 46);
                        sb6.append("Invalid component count: ");
                        sb6.append(intValue);
                        sb6.append(" of tag: \n");
                        sb6.append(valueOf7);
                        Log.w("ExifParser", sb6.toString());
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
        if (fsl.mo6134a() > ((long) (this.f10517p - this.f10503a.f10345a))) {
            if (Log.isLoggable("ExifParser", 5)) {
                int i3 = fsl.f10527d;
                long a = fsl.mo6134a();
                int i4 = this.f10516o;
                StringBuilder sb7 = new StringBuilder(138);
                sb7.append("Tag component data size greater than exif data size: componentCount=");
                sb7.append(i3);
                sb7.append(", dataSize=");
                sb7.append(a);
                sb7.append(", EXIF data size=");
                sb7.append(i4);
                Log.w("ExifParser", sb7.toString());
            }
            String valueOf8 = String.valueOf(fsl);
            StringBuilder sb8 = new StringBuilder(String.valueOf(valueOf8).length() + 52);
            sb8.append("component data size is greater than remaining data: ");
            sb8.append(valueOf8);
            throw new fse(sb8.toString());
        }
        int i5 = 0;
        switch (fsl.f10525b) {
            case 1:
            case 7:
                byte[] bArr = new byte[fsl.f10527d];
                mo6131a(bArr);
                fsl.mo6136a(bArr);
                return;
            case RecyclerView.SCROLL_STATE_SETTLING:
                int i6 = fsl.f10527d;
                Charset charset = f10495i;
                if (i6 > 0) {
                    byte[] bArr2 = new byte[i6];
                    this.f10503a.mo6094a(bArr2, i6);
                    str = new String(bArr2, charset);
                } else {
                    str = "";
                }
                fsl.mo6135a(str);
                return;
            case 3:
                int i7 = fsl.f10527d;
                int[] iArr = new int[i7];
                while (i5 < i7) {
                    iArr[i5] = (char) this.f10503a.mo6092a();
                    i5++;
                }
                fsl.mo6137a(iArr);
                return;
            case 4:
                int i8 = fsl.f10527d;
                long[] jArr = new long[i8];
                while (i5 < i8) {
                    jArr[i5] = m9540f();
                    i5++;
                }
                fsl.mo6138a(jArr);
                return;
            case 5:
                int i9 = fsl.f10527d;
                fsp[] fspArr = new fsp[i9];
                while (i5 < i9) {
                    fspArr[i5] = new fsp(m9540f(), m9540f());
                    i5++;
                }
                fsl.mo6139a(fspArr);
                return;
            case 9:
                int i10 = fsl.f10527d;
                int[] iArr2 = new int[i10];
                while (i5 < i10) {
                    iArr2[i5] = m9541g();
                    i5++;
                }
                fsl.mo6137a(iArr2);
                return;
            case 10:
                int i11 = fsl.f10527d;
                fsp[] fspArr2 = new fsp[i11];
                while (i5 < i11) {
                    fspArr2[i5] = new fsp((long) m9541g(), (long) m9541g());
                    i5++;
                }
                fsl.mo6139a(fspArr2);
                return;
            default:
                return;
        }
    }

    /* renamed from: g */
    private final int m9541g() {
        return this.f10503a.mo6096c();
    }

    /* renamed from: d */
    private final fsl m9538d() {
        short a = this.f10503a.mo6092a();
        short a2 = this.f10503a.mo6092a();
        long d = this.f10503a.mo6097d();
        if (d > 2147483647L) {
            throw new fse("Number of component is larger then Integer.MAX_VALUE");
        } else if (fsl.m9547a(a2)) {
            int i = (int) d;
            fsl fsl = new fsl(a, a2, i, this.f10504b, i != 0);
            long a3 = fsl.mo6134a();
            if (a3 > 4) {
                long d2 = this.f10503a.mo6097d();
                if (d2 <= 2147483647L) {
                    byte[] bArr = this.f10518q;
                    if (bArr == null || d2 >= ((long) this.f10519r) || a2 != 7) {
                        fsl.f10530g = (int) d2;
                    } else {
                        byte[] bArr2 = new byte[i];
                        System.arraycopy(bArr, ((int) d2) - 8, bArr2, 0, i);
                        fsl.mo6136a(bArr2);
                    }
                } else {
                    throw new fse("offset is larger then Integer.MAX_VALUE");
                }
            } else {
                boolean z = fsl.f10526c;
                fsl.f10526c = false;
                mo6132a(fsl);
                fsl.f10526c = z;
                this.f10503a.skip(4 - a3);
                fsl.f10530g = this.f10503a.f10345a - 4;
            }
            return fsl;
        } else {
            if (Log.isLoggable("ExifParser", 5)) {
                Log.w("ExifParser", String.format("Tag %04x: Invalid data type %d", new Object[]{Short.valueOf(a), Short.valueOf(a2)}));
            }
            this.f10503a.skip(4);
            return null;
        }
    }

    /* renamed from: f */
    private final long m9540f() {
        return ((long) m9541g()) & 4294967295L;
    }

    /* renamed from: a */
    private final void m9530a(int i, long j) {
        this.f10510h.put(Integer.valueOf((int) j), new fsh(i, m9531a(i)));
    }

    /* renamed from: b */
    private final void m9534b(int i, long j) {
        this.f10510h.put(Integer.valueOf((int) j), new fsi(i));
    }

    /* renamed from: e */
    private final boolean m9539e() {
        int i = this.f10516o;
        fsb fsb = this.f10503a;
        int i2 = (i - fsb.f10345a) - 2;
        if (i2 > 0) {
            long j = (long) i2;
            if (fsb.skip(j) != j) {
                if (Log.isLoggable("ExifParser", 5)) {
                    Log.w("ExifParser", "Invalid JPEG format.");
                }
                return false;
            }
        }
        this.f10503a.mo6093a(ByteOrder.BIG_ENDIAN);
        try {
            short a = this.f10503a.mo6092a();
            while (a != -39 && !fsn.m9565a(a)) {
                int b = this.f10503a.mo6095b();
                if (a == -31 && b >= fsn.f10536b.length + 2) {
                    byte[] bArr = new byte[fsn.f10536b.length];
                    mo6133a(bArr, fsn.f10536b.length);
                    b -= fsn.f10536b.length;
                    if (Arrays.equals(bArr, fsn.f10536b)) {
                        this.f10509g = b - 2;
                        return true;
                    }
                }
                if (b >= 2) {
                    long j2 = (long) (b - 2);
                    if (j2 == this.f10503a.skip(j2)) {
                        a = this.f10503a.mo6092a();
                    }
                }
                if (Log.isLoggable("ExifParser", 5)) {
                    Log.w("ExifParser", "Invalid JPEG format.");
                }
                return false;
            }
            return false;
        } catch (EOFException e) {
            if (Log.isLoggable("ExifParser", 5)) {
                Log.w("ExifParser", "Invalid JPEG format.");
            }
            return false;
        }
    }

    /* renamed from: b */
    private final void m9533b(int i) {
        fsb fsb = this.f10503a;
        long j = ((long) i) - ((long) fsb.f10345a);
        if (j < 0) {
            throw new IOException();
        } else if (fsb.skip(j) == j) {
            while (!this.f10510h.isEmpty() && ((Integer) this.f10510h.firstKey()).intValue() < i) {
                this.f10510h.pollFirstEntry();
            }
        } else {
            throw new EOFException();
        }
    }
}
