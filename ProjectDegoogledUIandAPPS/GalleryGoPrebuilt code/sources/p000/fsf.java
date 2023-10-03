package p000;

import android.support.p002v7.widget.RecyclerView;
import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* renamed from: fsf */
/* compiled from: PG */
public final class fsf extends FilterOutputStream {

    /* renamed from: a */
    public fsd f10482a;

    /* renamed from: b */
    private int f10483b = 0;

    /* renamed from: c */
    private int f10484c;

    /* renamed from: d */
    private int f10485d;

    /* renamed from: e */
    private final byte[] f10486e = new byte[1];

    /* renamed from: f */
    private final ByteBuffer f10487f = ByteBuffer.allocate(4);

    /* renamed from: g */
    private final fsc f10488g;

    public fsf(OutputStream outputStream, fsc fsc) {
        super(new BufferedOutputStream(outputStream, 65536));
        this.f10488g = fsc;
    }

    /* renamed from: a */
    private static final int m9527a(fsm fsm, int i) {
        int b = i + (fsm.mo6150b() * 12) + 6;
        for (fsl fsl : fsm.mo6149a()) {
            if (fsl.mo6134a() > 4) {
                fsl.f10530g = b;
                b = (int) (((long) b) + fsl.mo6134a());
            }
        }
        return b;
    }

    /* renamed from: a */
    private final int m9526a(int i, byte[] bArr, int i2, int i3) {
        int min = Math.min(i - this.f10487f.position(), i3);
        this.f10487f.put(bArr, i2, min);
        return min;
    }

    public final void write(int i) {
        byte[] bArr = this.f10486e;
        bArr[0] = (byte) i;
        write(bArr);
    }

    public final void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public final void write(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        while (true) {
            int i5 = this.f10484c;
            if ((i5 > 0 || this.f10485d > 0 || this.f10483b != 2) && i4 > 0) {
                if (i5 > 0) {
                    int min = Math.min(i5, i4);
                    i4 -= min;
                    this.f10484c -= min;
                    i3 += min;
                }
                int i6 = this.f10485d;
                if (i6 > 0) {
                    int min2 = Math.min(i6, i4);
                    this.out.write(bArr2, i3, min2);
                    i4 -= min2;
                    this.f10485d -= min2;
                    i3 += min2;
                }
                if (i4 != 0) {
                    int i7 = this.f10483b;
                    if (i7 == 0) {
                        int a = m9526a(2, bArr2, i3, i4);
                        i3 += a;
                        i4 -= a;
                        if (this.f10487f.position() >= 2) {
                            this.f10487f.rewind();
                            if (this.f10487f.getShort() == -40) {
                                this.out.write(this.f10487f.array(), 0, 2);
                                this.f10483b = 1;
                                this.f10487f.rewind();
                                fsd fsd = this.f10482a;
                                if (fsd != null) {
                                    ArrayList arrayList = new ArrayList();
                                    List d = fsd.mo6124d();
                                    if (d != null) {
                                        for (int i8 = 0; i8 < d.size(); i8++) {
                                            fsl fsl = (fsl) fsd.mo6124d().get(0);
                                            if (fsl.f10529f == null && !fsc.m9496a(fsl.f10524a)) {
                                                fsd.mo6118a(fsl.f10524a, fsl.f10528e);
                                                arrayList.add(fsl);
                                            }
                                        }
                                    }
                                    fsm b = this.f10482a.mo6122b(0);
                                    if (b == null) {
                                        b = new fsm(0);
                                        this.f10482a.mo6117a(b);
                                    }
                                    fsl e = this.f10488g.mo6115e(fsc.f10454g);
                                    if (e != null) {
                                        b.mo6148a(e);
                                        fsm b2 = this.f10482a.mo6122b(2);
                                        if (b2 == null) {
                                            b2 = new fsm(2);
                                            this.f10482a.mo6117a(b2);
                                        }
                                        if (this.f10482a.mo6122b(4) != null) {
                                            fsl e2 = this.f10488g.mo6115e(fsc.f10455h);
                                            if (e2 != null) {
                                                b.mo6148a(e2);
                                            } else {
                                                int i9 = fsc.f10455h;
                                                StringBuilder sb = new StringBuilder(47);
                                                sb.append("No definition for crucial exif tag: ");
                                                sb.append(i9);
                                                throw new IOException(sb.toString());
                                            }
                                        }
                                        if (this.f10482a.mo6122b(3) != null) {
                                            fsl e3 = this.f10488g.mo6115e(fsc.f10463p);
                                            if (e3 != null) {
                                                b2.mo6148a(e3);
                                            } else {
                                                int i10 = fsc.f10463p;
                                                StringBuilder sb2 = new StringBuilder(47);
                                                sb2.append("No definition for crucial exif tag: ");
                                                sb2.append(i10);
                                                throw new IOException(sb2.toString());
                                            }
                                        }
                                        fsm b3 = this.f10482a.mo6122b(1);
                                        if (this.f10482a.mo6119a()) {
                                            if (b3 == null) {
                                                b3 = new fsm(1);
                                                this.f10482a.mo6117a(b3);
                                            }
                                            fsl e4 = this.f10488g.mo6115e(fsc.f10456i);
                                            if (e4 != null) {
                                                b3.mo6148a(e4);
                                                fsl e5 = this.f10488g.mo6115e(fsc.f10457j);
                                                if (e5 != null) {
                                                    e5.mo6141b(this.f10482a.f10478b.length);
                                                    b3.mo6148a(e5);
                                                    b3.mo6151b(fsc.m9495a(fsc.f10427b));
                                                    b3.mo6151b(fsc.m9495a(fsc.f10451d));
                                                } else {
                                                    int i11 = fsc.f10457j;
                                                    StringBuilder sb3 = new StringBuilder(47);
                                                    sb3.append("No definition for crucial exif tag: ");
                                                    sb3.append(i11);
                                                    throw new IOException(sb3.toString());
                                                }
                                            } else {
                                                int i12 = fsc.f10456i;
                                                StringBuilder sb4 = new StringBuilder(47);
                                                sb4.append("No definition for crucial exif tag: ");
                                                sb4.append(i12);
                                                throw new IOException(sb4.toString());
                                            }
                                        } else if (this.f10482a.mo6123c()) {
                                            if (b3 == null) {
                                                b3 = new fsm(1);
                                                this.f10482a.mo6117a(b3);
                                            }
                                            int b4 = this.f10482a.mo6121b();
                                            fsl e6 = this.f10488g.mo6115e(fsc.f10427b);
                                            if (e6 != null) {
                                                fsl e7 = this.f10488g.mo6115e(fsc.f10451d);
                                                if (e7 != null) {
                                                    long[] jArr = new long[b4];
                                                    for (int i13 = 0; i13 < this.f10482a.mo6121b(); i13++) {
                                                        jArr[i13] = (long) this.f10482a.mo6120a(i13).length;
                                                    }
                                                    e7.mo6138a(jArr);
                                                    b3.mo6148a(e6);
                                                    b3.mo6148a(e7);
                                                    b3.mo6151b(fsc.m9495a(fsc.f10456i));
                                                    b3.mo6151b(fsc.m9495a(fsc.f10457j));
                                                } else {
                                                    int i14 = fsc.f10451d;
                                                    StringBuilder sb5 = new StringBuilder(47);
                                                    sb5.append("No definition for crucial exif tag: ");
                                                    sb5.append(i14);
                                                    throw new IOException(sb5.toString());
                                                }
                                            } else {
                                                int i15 = fsc.f10427b;
                                                StringBuilder sb6 = new StringBuilder(47);
                                                sb6.append("No definition for crucial exif tag: ");
                                                sb6.append(i15);
                                                throw new IOException(sb6.toString());
                                            }
                                        } else if (b3 != null) {
                                            b3.mo6151b(fsc.m9495a(fsc.f10427b));
                                            b3.mo6151b(fsc.m9495a(fsc.f10451d));
                                            b3.mo6151b(fsc.m9495a(fsc.f10456i));
                                            b3.mo6151b(fsc.m9495a(fsc.f10457j));
                                        }
                                        fsm b5 = this.f10482a.mo6122b(0);
                                        int a2 = m9527a(b5, 8);
                                        b5.mo6147a(fsc.m9495a(fsc.f10454g)).mo6141b(a2);
                                        fsm b6 = this.f10482a.mo6122b(2);
                                        int a3 = m9527a(b6, a2);
                                        fsm b7 = this.f10482a.mo6122b(3);
                                        if (b7 != null) {
                                            b6.mo6147a(fsc.m9495a(fsc.f10463p)).mo6141b(a3);
                                            a3 = m9527a(b7, a3);
                                        }
                                        fsm b8 = this.f10482a.mo6122b(4);
                                        if (b8 != null) {
                                            b5.mo6147a(fsc.m9495a(fsc.f10455h)).mo6141b(a3);
                                            a3 = m9527a(b8, a3);
                                        }
                                        fsm b9 = this.f10482a.mo6122b(1);
                                        if (b9 != null) {
                                            b5.f10533b = a3;
                                            a3 = m9527a(b9, a3);
                                        }
                                        if (this.f10482a.mo6119a()) {
                                            b9.mo6147a(fsc.m9495a(fsc.f10456i)).mo6141b(a3);
                                            a3 += this.f10482a.f10478b.length;
                                        } else if (this.f10482a.mo6123c()) {
                                            long[] jArr2 = new long[this.f10482a.mo6121b()];
                                            for (int i16 = 0; i16 < this.f10482a.mo6121b(); i16++) {
                                                jArr2[i16] = (long) a3;
                                                a3 += this.f10482a.mo6120a(i16).length;
                                            }
                                            b9.mo6147a(fsc.m9495a(fsc.f10427b)).mo6138a(jArr2);
                                        }
                                        if (a3 + 8 <= 65535) {
                                            fso fso = new fso(this.out);
                                            fso.mo6155a(ByteOrder.BIG_ENDIAN);
                                            fso.mo6156a(-31);
                                            fso.mo6156a((short) (a3 + 2 + fsn.f10535a.length));
                                            fso.write(fsn.f10535a);
                                            if (this.f10482a.f10480d == ByteOrder.BIG_ENDIAN) {
                                                fso.mo6156a(19789);
                                            } else {
                                                fso.mo6156a(18761);
                                            }
                                            fso.mo6155a(this.f10482a.f10480d);
                                            fso.mo6156a(42);
                                            fso.mo6154a(8);
                                            m9529a(this.f10482a.mo6122b(0), fso);
                                            m9529a(this.f10482a.mo6122b(2), fso);
                                            fsm b10 = this.f10482a.mo6122b(3);
                                            if (b10 != null) {
                                                m9529a(b10, fso);
                                            }
                                            fsm b11 = this.f10482a.mo6122b(4);
                                            if (b11 != null) {
                                                m9529a(b11, fso);
                                            }
                                            if (this.f10482a.mo6122b(1) != null) {
                                                m9529a(this.f10482a.mo6122b(1), fso);
                                            }
                                            if (this.f10482a.mo6119a()) {
                                                fso.write(this.f10482a.f10478b);
                                            } else if (this.f10482a.mo6123c()) {
                                                for (int i17 = 0; i17 < this.f10482a.mo6121b(); i17++) {
                                                    fso.write(this.f10482a.mo6120a(i17));
                                                }
                                            }
                                            int size = arrayList.size();
                                            for (int i18 = 0; i18 < size; i18++) {
                                                this.f10482a.mo6116a((fsl) arrayList.get(i18));
                                            }
                                            byte[] bArr3 = this.f10482a.f10481e;
                                            if (bArr3 != null) {
                                                fso.mo6155a(ByteOrder.BIG_ENDIAN);
                                                fso.mo6156a(-31);
                                                fso.mo6156a((short) (bArr3.length + 2 + fsn.f10536b.length));
                                                fso.write(fsn.f10536b);
                                                fso.write(bArr3);
                                            }
                                        } else {
                                            throw new IOException("Exif header is too large (>64Kb)");
                                        }
                                    } else {
                                        int i19 = fsc.f10454g;
                                        StringBuilder sb7 = new StringBuilder(47);
                                        sb7.append("No definition for crucial exif tag: ");
                                        sb7.append(i19);
                                        throw new IOException(sb7.toString());
                                    }
                                } else {
                                    continue;
                                }
                            } else {
                                throw new IOException("Not a valid jpeg image, cannot write exif");
                            }
                        } else {
                            return;
                        }
                    } else if (i7 != 1) {
                        continue;
                    } else {
                        int a4 = m9526a(4, bArr2, i3, i4);
                        i3 += a4;
                        i4 -= a4;
                        if (this.f10487f.position() == 2 && this.f10487f.getShort() == -39) {
                            this.out.write(this.f10487f.array(), 0, 2);
                            this.f10487f.rewind();
                        }
                        if (this.f10487f.position() >= 4) {
                            this.f10487f.rewind();
                            short s = this.f10487f.getShort();
                            if (s == -31) {
                                this.f10484c = ((char) this.f10487f.getShort()) - 2;
                            } else if (!fsn.m9565a(s)) {
                                this.out.write(this.f10487f.array(), 0, 4);
                                this.f10485d = ((char) this.f10487f.getShort()) - 2;
                            } else {
                                this.out.write(this.f10487f.array(), 0, 4);
                                this.f10483b = 2;
                            }
                            this.f10487f.rewind();
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }
        if (i4 > 0) {
            this.out.write(bArr2, i3, i4);
        }
    }

    /* renamed from: a */
    private static final void m9529a(fsm fsm, fso fso) {
        fsl[] a = fsm.mo6149a();
        fso.mo6156a((short) r1);
        for (fsl fsl : a) {
            fso.mo6156a(fsl.f10524a);
            fso.mo6156a(fsl.f10525b);
            fso.mo6154a(fsl.f10527d);
            if (fsl.mo6134a() > 4) {
                fso.mo6154a(fsl.f10530g);
            } else {
                m9528a(fsl, fso);
                int a2 = 4 - ((int) fsl.mo6134a());
                for (int i = 0; i < a2; i++) {
                    fso.write(0);
                }
            }
        }
        fso.mo6154a(fsm.f10533b);
        for (fsl fsl2 : a) {
            if (fsl2.mo6134a() > 4) {
                m9528a(fsl2, fso);
            }
        }
    }

    /* renamed from: a */
    private static void m9528a(fsl fsl, fso fso) {
        if (fsl.mo6140b()) {
            short s = fsl.f10525b;
            int i = 0;
            switch (s) {
                case 1:
                case 7:
                    int i2 = fsl.f10527d;
                    byte[] bArr = new byte[i2];
                    if (s == 7 || s == 1) {
                        System.arraycopy(fsl.f10529f, 0, bArr, 0, Math.min(i2, i2));
                        fso.write(bArr);
                        return;
                    }
                    String b = fsl.m9548b(s);
                    throw new IllegalArgumentException(b.length() == 0 ? new String("Cannot get BYTE value from ") : "Cannot get BYTE value from ".concat(b));
                case RecyclerView.SCROLL_STATE_SETTLING:
                    byte[] bArr2 = (byte[]) fsl.f10529f;
                    int length = bArr2.length;
                    if (length != fsl.f10527d || length <= 0) {
                        fso.write(bArr2);
                        fso.write(0);
                        return;
                    }
                    bArr2[length - 1] = 0;
                    fso.write(bArr2);
                    return;
                case 3:
                    int i3 = fsl.f10527d;
                    while (i < i3) {
                        fso.mo6156a((short) ((int) fsl.mo6142c(i)));
                        i++;
                    }
                    return;
                case 4:
                case 9:
                    int i4 = fsl.f10527d;
                    while (i < i4) {
                        fso.mo6154a((int) fsl.mo6142c(i));
                        i++;
                    }
                    return;
                case 5:
                case 10:
                    int i5 = fsl.f10527d;
                    while (i < i5) {
                        short s2 = fsl.f10525b;
                        if (s2 == 10 || s2 == 5) {
                            fsp fsp = ((fsp[]) fsl.f10529f)[i];
                            fso.mo6154a((int) fsp.f10538a);
                            fso.mo6154a((int) fsp.f10539b);
                            i++;
                        } else {
                            String b2 = fsl.m9548b(s2);
                            throw new IllegalArgumentException(b2.length() == 0 ? new String("Cannot get RATIONAL value from ") : "Cannot get RATIONAL value from ".concat(b2));
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
