package p000;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

/* renamed from: iki */
/* compiled from: PG */
final class iki implements iky {

    /* renamed from: a */
    private final ikf f14392a;

    /* renamed from: b */
    private final boolean f14393b;

    /* renamed from: c */
    private final imu f14394c;

    private iki(imu imu, ikf ikf, byte[] bArr) {
        this.f14394c = imu;
        this.f14393b = ikf instanceof iiu;
        this.f14392a = ikf;
    }

    /* renamed from: a */
    public final boolean mo8868a(Object obj, Object obj2) {
        if (!imu.m14135a(obj).equals(imu.m14135a(obj2))) {
            return false;
        }
        if (this.f14393b) {
            return imi.m14098a(obj).equals(imi.m14098a(obj2));
        }
        return true;
    }

    /* renamed from: b */
    public final int mo8869b(Object obj) {
        ilm a = imu.m14135a(obj);
        int i = a.f14453e;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < a.f14450b; i2++) {
                int f = iie.m13420f(1);
                i += f + f + iie.m13425g(2, imd.m14074b(a.f14451c[i2])) + iie.m13413c(3, (ihw) a.f14452d[i2]);
            }
            a.f14453e = i;
        }
        if (!this.f14393b) {
            return i;
        }
        iim a2 = imi.m14098a(obj);
        int i3 = 0;
        for (int i4 = 0; i4 < a2.f14255a.mo8916a(); i4++) {
            i3 += a2.mo8727b(a2.f14255a.mo8919b(i4));
        }
        for (Map.Entry b : a2.f14255a.mo8918b()) {
            i3 += a2.mo8727b(b);
        }
        return i + i3;
    }

    /* renamed from: a */
    public final int mo8862a(Object obj) {
        int hashCode = imu.m14135a(obj).hashCode();
        return this.f14393b ? (hashCode * 53) + imi.m14098a(obj).hashCode() : hashCode;
    }

    /* renamed from: d */
    public final boolean mo8872d(Object obj) {
        return imi.m14098a(obj).mo8733e();
    }

    /* renamed from: c */
    public final void mo8871c(Object obj) {
        imu.m14142c(obj);
        imi.m14118c(obj);
    }

    /* renamed from: a */
    public final void mo8865a(Object obj, iks iks, iij iij) {
        boolean z;
        imu imu = this.f14394c;
        Object b = imu.m14141b(obj);
        iim b2 = imi.m14114b(obj);
        while (iks.mo8541a() != Integer.MAX_VALUE) {
            try {
                int b3 = iks.mo8547b();
                if (b3 == imd.f14506a) {
                    int i = 0;
                    iih iih = null;
                    ihw ihw = null;
                    while (true) {
                        if (iks.mo8541a() == Integer.MAX_VALUE) {
                            break;
                        }
                        int b4 = iks.mo8547b();
                        if (b4 == imd.f14508c) {
                            i = iks.mo8576o();
                            iih = iij.mo8715a(this.f14392a, i);
                        } else if (b4 != imd.f14509d) {
                            if (!iks.mo8553c()) {
                                break;
                            }
                        } else if (iih != null) {
                            imi.m14110a(iks, (Object) iih, iij, b2);
                        } else {
                            ihw = iks.mo8574n();
                        }
                    }
                    if (iks.mo8547b() != imd.f14507b) {
                        throw ijh.m13658e();
                    } else if (ihw == null) {
                        continue;
                    } else if (iih != null) {
                        ikf f = iih.f14243c.mo8797n().mo8766f();
                        ByteBuffer wrap = ByteBuffer.wrap(ihw.mo8625j());
                        if (wrap.hasArray()) {
                            ihh ihh = new ihh(wrap);
                            ikp.f14397a.mo8876a((Object) f).mo8865a(f, ihh, iij);
                            b2.mo8723a(iih.f14244d, f);
                            if (ihh.mo8541a() != Integer.MAX_VALUE) {
                                throw ijh.m13658e();
                            }
                        } else {
                            throw new IllegalArgumentException("Direct buffers not yet supported");
                        }
                    } else {
                        ((ilm) b).mo8943a(imd.m14073a(i, 2), (Object) ihw);
                    }
                } else {
                    if (imd.m14072a(b3) == 2) {
                        iih a = iij.mo8715a(this.f14392a, imd.m14074b(b3));
                        if (a == null) {
                            z = imu.mo9005a(b, iks);
                        } else {
                            imi.m14110a(iks, (Object) a, iij, b2);
                        }
                    } else {
                        z = iks.mo8553c();
                    }
                    if (!z) {
                        break;
                    }
                }
            } finally {
                imu.m14139a(obj, (ilm) b);
            }
        }
    }

    /* renamed from: b */
    public final void mo8870b(Object obj, Object obj2) {
        ila.m13929c(obj, obj2);
        if (this.f14393b) {
            ila.m13925b(obj, obj2);
        }
    }

    /* renamed from: a */
    public final void mo8867a(Object obj, byte[] bArr, int i, int i2, ihf ihf) {
        iix iix = (iix) obj;
        ilm ilm = iix.f14326z;
        if (ilm == ilm.f14449a) {
            ilm = ilm.m13974a();
            iix.f14326z = ilm;
        }
        iim a = ((iiu) obj).mo8785a();
        iih iih = null;
        while (i < i2) {
            int a2 = ihg.m13023a(bArr, i, ihf);
            int i3 = ihf.f14178a;
            if (i3 == imd.f14506a) {
                int i4 = 0;
                ihw ihw = null;
                while (a2 < i2) {
                    a2 = ihg.m13023a(bArr, a2, ihf);
                    int i5 = ihf.f14178a;
                    int a3 = imd.m14072a(i5);
                    int b = imd.m14074b(i5);
                    if (b != 2) {
                        if (b == 3) {
                            if (iih != null) {
                                a2 = ihg.m13021a(ikp.f14397a.mo8875a((Class) iih.f14243c.getClass()), bArr, a2, i2, ihf);
                                a.mo8723a(iih.f14244d, ihf.f14180c);
                            } else if (a3 == 2) {
                                a2 = ihg.m13057e(bArr, a2, ihf);
                                ihw = (ihw) ihf.f14180c;
                            }
                        }
                    } else if (a3 == 0) {
                        a2 = ihg.m13023a(bArr, a2, ihf);
                        i4 = ihf.f14178a;
                        iih = ihf.f14181d.mo8715a(this.f14392a, i4);
                    }
                    if (i5 == imd.f14507b) {
                        break;
                    }
                    a2 = ihg.m13014a(i5, bArr, a2, i2, ihf);
                }
                if (ihw != null) {
                    ilm.mo8943a(imd.m14073a(i4, 2), (Object) ihw);
                }
                i = a2;
            } else if (imd.m14072a(i3) == 2) {
                iih a4 = ihf.f14181d.mo8715a(this.f14392a, imd.m14074b(i3));
                if (a4 != null) {
                    i = ihg.m13021a(ikp.f14397a.mo8875a((Class) a4.f14243c.getClass()), bArr, a2, i2, ihf);
                    a.mo8723a(a4.f14244d, ihf.f14180c);
                    iih = a4;
                } else {
                    i = ihg.m13016a(i3, bArr, a2, i2, ilm, ihf);
                    iih = a4;
                }
            } else {
                i = ihg.m13014a(i3, bArr, a2, i2, ihf);
            }
        }
        if (i != i2) {
            throw ijh.m13661h();
        }
    }

    /* renamed from: a */
    public final Object mo8864a() {
        return this.f14392a.mo8797n().mo8766f();
    }

    /* renamed from: a */
    static iki m13798a(imu imu, imi imi, ikf ikf) {
        return new iki(imu, ikf, (byte[]) null);
    }

    /* renamed from: a */
    public final void mo8866a(Object obj, ime ime) {
        Iterator d = imi.m14098a(obj).mo8732d();
        while (d.hasNext()) {
            Map.Entry entry = (Map.Entry) d.next();
            iil iil = (iil) entry.getKey();
            if (iil.mo8719c() == imc.MESSAGE) {
                iil.mo8722f();
                iil.mo8721e();
                if (entry instanceof ijj) {
                    ime.mo8692a(iil.mo8716a(), (Object) ((ijl) ((ijj) entry).f14349a.getValue()).mo8816a());
                } else {
                    ime.mo8692a(iil.mo8716a(), entry.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        ilm a = imu.m14135a(obj);
        for (int i = 0; i < a.f14450b; i++) {
            ime.mo8692a(imd.m14074b(a.f14451c[i]), a.f14452d[i]);
        }
    }
}
