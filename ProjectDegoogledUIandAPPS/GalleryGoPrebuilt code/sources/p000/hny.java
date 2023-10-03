package p000;

import android.support.p002v7.widget.RecyclerView;
import android.util.SparseArray;

/* renamed from: hny */
/* compiled from: PG */
public final class hny implements hmd {

    /* renamed from: a */
    private final inw f13133a;

    public hny(inw inw) {
        this.f13133a = inw;
    }

    /* renamed from: a */
    public final void mo7541a(hmt hmt, SparseArray sparseArray) {
        hpy hpy;
        boolean z;
        iqx iqx;
        hmt hmt2 = hmt;
        hpy hpy2 = (hpy) this.f13133a.mo9034a();
        if (!hpy2.mo7646a() || !((fmq) hpy2.mo7647b()).mo5811a()) {
            hpy = hph.f13219a;
        } else {
            hpy = ((fmq) hpy2.mo7647b()).mo5813c().mo5974a(hmt2);
        }
        if (hpy.mo7646a()) {
            hoa hoa = new hoa((byte[]) null);
            hoa.f13137a = hmt2;
            hoa.f13138b = sparseArray;
            hoa.f13143g = ((Float) hpy.mo7647b()).floatValue();
            ife.m12898e((Object) hoa.f13137a);
            boolean z2 = false;
            if (hoa.f13144h == null) {
                z = true;
            } else {
                z = false;
            }
            ife.m12896d(z);
            iir g = iri.f14843h.mo8793g();
            float f = hoa.f13143g;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iri iri = (iri) g.f14318b;
            iri.f14845a |= 4;
            iri.f14849e = f;
            hoa.f13144h = g;
            hmt hmt3 = hoa.f13137a;
            hoa.f13139c = hmt3.f13062f;
            iir iir = hoa.f13144h;
            long j = (hmt3.f13058b ^ hmt3.f13059c) & RecyclerView.FOREVER_NS;
            long j2 = 1;
            if (j == 0) {
                j = 1;
            }
            if (iir.f14319c) {
                iir.mo8751b();
                iir.f14319c = false;
            }
            iri iri2 = (iri) iir.f14318b;
            int i = iri2.f14845a | 1;
            iri2.f14845a = i;
            iri2.f14846b = j;
            iri2.f14847c = 1;
            iri2.f14845a = i | 2;
            ije ije = hoa.f13137a.f13060d;
            int size = ije.size();
            int i2 = 0;
            while (i2 < size) {
                hlh hlh = (hlh) ije.get(i2);
                iir g2 = irk.f14853m.mo8793g();
                String str = hlh.f12969b;
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = z2;
                }
                irk irk = (irk) g2.f14318b;
                str.getClass();
                int i3 = irk.f14855a | 1;
                irk.f14855a = i3;
                irk.f14856b = str;
                int i4 = hlh.f12970c;
                int i5 = i3 | 8;
                irk.f14855a = i5;
                irk.f14859e = ((long) i4) + j2;
                int i6 = hlh.f12971d;
                int i7 = i5 | 16;
                irk.f14855a = i7;
                int i8 = size;
                irk.f14860f = ((long) i6) + j2;
                long j3 = hoa.f13139c;
                long j4 = hlh.f12972e;
                int i9 = i7 | 32;
                irk.f14855a = i9;
                irk.f14861g = j3 + j4;
                if ((hlh.f12968a & 16) != 0) {
                    long j5 = hlh.f12973f;
                    i9 |= 64;
                    irk.f14855a = i9;
                    irk.f14862h = j5;
                }
                if ((hlh.f12968a & 32) != 0) {
                    int i10 = hlh.f12974g;
                    irk.f14855a = i9 | 128;
                    irk.f14863i = i10;
                }
                hlk a = hln.m11700a(hnz.f13134a, (hln) hoa.f13138b.get(i4, hlm.f12987a), hnf.f13084a);
                if (a.mo7551b()) {
                    hnx hnx = (hnx) a.mo7550a();
                    if (hnx.mo7621a()) {
                        if (g2.f14319c) {
                            g2.mo8751b();
                            g2.f14319c = false;
                        }
                        irk irk2 = (irk) g2.f14318b;
                        irk2.f14865k = 5;
                        irk2.f14855a |= 512;
                    }
                    hpy b = hnx.mo7622b();
                    if (b.mo7646a()) {
                        iqx iqx2 = (iqx) b.mo7647b();
                        if (g2.f14319c) {
                            g2.mo8751b();
                            g2.f14319c = false;
                        }
                        irk irk3 = (irk) g2.f14318b;
                        iqx2.getClass();
                        irk3.f14866l = iqx2;
                        irk3.f14855a |= 1024;
                    }
                }
                irk irk4 = (irk) g2.mo8770g();
                if (irk4.f14860f != 0) {
                    int a2 = isw.m14417a(irk4.f14865k);
                    if (a2 != 0 && a2 == 6 && hoa.f13142f == null) {
                        hoa.f13142f = irk4;
                    }
                } else {
                    hoa.f13141e = irk4;
                }
                iir iir2 = hoa.f13144h;
                if (iir2.f14319c) {
                    iir2.mo8751b();
                    iir2.f14319c = false;
                }
                iri iri3 = (iri) iir2.f14318b;
                irk4.getClass();
                iri3.mo9068a();
                iri3.f14848d.add(irk4);
                long j6 = irk4.f14861g + irk4.f14862h;
                if (j6 > hoa.f13140d) {
                    hoa.f13140d = j6;
                }
                i2++;
                size = i8;
                z2 = false;
                j2 = 1;
            }
            hmt hmt4 = hoa.f13137a;
            if ((hmt4.f13057a & 16) != 0) {
                hkq hkq = hmt4.f13063g;
                if (hkq == null) {
                    hkq = hkq.f12942d;
                }
                iir g3 = irf.f14836d.mo8793g();
                if ((hkq.f12944a & 1) != 0) {
                    hkp hkp = hkq.f12945b;
                    if (hkp == null) {
                        hkp = hkp.f12938c;
                    }
                    int i11 = hkp.f12941b;
                    if (g3.f14319c) {
                        g3.mo8751b();
                        g3.f14319c = false;
                    }
                    irf irf = (irf) g3.f14318b;
                    irf.f14838a = 1 | irf.f14838a;
                    irf.f14839b = i11;
                }
                if ((hkq.f12944a & 2) != 0) {
                    hko hko = hkq.f12946c;
                    if (hko == null) {
                        hko = hko.f12933d;
                    }
                    long j7 = hko.f12936b;
                    if (g3.f14319c) {
                        g3.mo8751b();
                        g3.f14319c = false;
                    }
                    irf irf2 = (irf) g3.f14318b;
                    irf2.f14838a |= 2;
                    irf2.f14840c = j7;
                }
                iir iir3 = hoa.f13144h;
                irf irf3 = (irf) g3.mo8770g();
                if (iir3.f14319c) {
                    iir3.mo8751b();
                    iir3.f14319c = false;
                }
                iri iri4 = (iri) iir3.f14318b;
                irf3.getClass();
                iri4.f14850f = irf3;
                iri4.f14845a |= 8;
            }
            if (hoa.f13142f == null) {
                hoa.f13142f = hoa.f13141e;
            }
            irk irk5 = hoa.f13142f;
            String str2 = irk5.f14856b;
            iqx iqx3 = irk5.f14866l;
            if (iqx3 != null) {
                iqx = iqx3;
            } else {
                iqx = iqx.f14783a;
            }
            hob hob = new hob(str2, iqx, hoa.f13139c, hoa.f13140d, (iri) hoa.f13144h.mo8770g());
            fkl.m9082a().f9896b.mo5836a(fnb.f10078a, hob.f13149e);
            if (((hpy) this.f13133a.mo9034a()).mo7646a() && ((fmq) ((hpy) this.f13133a.mo9034a()).mo7647b()).mo5814d()) {
                long j8 = hob.f13147c;
                long j9 = hob.f13148d;
                fkl.m9082a().f9896b.mo5837a(fnj.f10089a, hob.f13145a, j8, j9, hob.f13146b);
            }
        }
    }
}
