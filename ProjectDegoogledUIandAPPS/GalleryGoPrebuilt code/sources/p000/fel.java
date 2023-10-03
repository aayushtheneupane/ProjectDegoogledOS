package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: fel */
/* compiled from: PG */
final /* synthetic */ class fel implements fct {

    /* renamed from: a */
    private final fep f9372a;

    public fel(fep fep) {
        this.f9372a = fep;
    }

    /* renamed from: a */
    public final List mo5488a(fcy fcy) {
        boolean z;
        boolean z2;
        fep fep = this.f9372a;
        if (!fep.f9393k) {
            hlj a = hnb.m11765a("GIL:FindInsertRoots");
            try {
                for (fdr fdr : fep.f9385c) {
                    fdq e = fdr.mo5544e(ffa.f9433a);
                    if (!e.mo5523a()) {
                        fdr b = e.mo5524b();
                        if (b != null) {
                            z2 = b.mo5542c(ffa.f9433a);
                        } else {
                            z2 = fep.m8711a(fdr);
                        }
                        if (!z2) {
                        }
                    }
                    fep.f9387e.add(fdr);
                }
                fep.f9385c.clear();
                if (a != null) {
                    a.close();
                }
                hlj a2 = hnb.m11765a("GIL:LogInsertRoots");
                try {
                    for (fdr feq : fep.f9387e) {
                        feu feu = fep.f9384b;
                        feu.f9408a.mo5490a((fct) new feq(feu, feq));
                    }
                    fep.f9387e.clear();
                    if (a2 != null) {
                        a2.close();
                    }
                } catch (Throwable th) {
                    ifn.m12935a(th, th);
                }
            } catch (Throwable th2) {
                ifn.m12935a(th, th2);
            }
        } else {
            hlj a3 = hnb.m11765a("GIL:CreateInsertGrafts");
            try {
                for (fdr fdr2 : fep.f9385c) {
                    if (fdr2.mo5547h(ffa.f9433a) == -1) {
                        fep.mo5572b(fdr2);
                    }
                }
                fep.f9385c.clear();
                for (fdr a4 : fep.f9390h) {
                    a4.mo5535a(-1, ffa.f9433a);
                }
                fep.f9390h.clear();
                if (a3 != null) {
                    a3.close();
                }
            } catch (Throwable th3) {
                ifn.m12935a(th, th3);
            }
        }
        ArrayList arrayList = null;
        if (!fep.f9394l) {
            hlj a5 = hnb.m11765a("GIL:LogVisibilityRoots");
            try {
                for (fdr fdr3 : fep.f9386d) {
                    if (fdr3.mo5541c() != 1) {
                        feu feu2 = fep.f9384b;
                        feu2.f9408a.mo5490a((fct) new fes(feu2, fdr3));
                    } else {
                        feu feu3 = fep.f9384b;
                        feu3.f9408a.mo5490a((fct) new fer(feu3, fdr3));
                    }
                }
                fep.f9386d.clear();
                fep.f9391i.clear();
                if (a5 != null) {
                    a5.close();
                }
            } catch (Throwable th4) {
                ifn.m12935a(th, th4);
            }
        } else {
            hlj a6 = hnb.m11765a("GIL:CreateVisibilityGrafts");
            try {
                for (fdr fdr4 : fep.f9386d) {
                    ife.m12878b(fdr4.mo5542c(ffa.f9433a), "Not impressed: %s", (Object) fdr4);
                    int c = fdr4.mo5541c();
                    fdh d = fdr4.mo5543d(ffa.f9433a);
                    int b2 = ife.m12861b(d.f9313d);
                    if (b2 == 0) {
                        b2 = 1;
                    }
                    if (b2 != c) {
                        int b3 = ife.m12861b(d.f9313d);
                        if (b3 == 0) {
                            b3 = 1;
                        }
                        int i = b3 - 1;
                        if (i == 2 || i == 4) {
                            if (c == 2) {
                                continue;
                            } else {
                                if (c != 1) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                ife.m12876b(z, (Object) "Repressed VE was visible.");
                            }
                        }
                        iir iir = (iir) d.mo8790b(5);
                        iir.mo8503a((iix) d);
                        if (iir.f14319c) {
                            iir.mo8751b();
                            iir.f14319c = false;
                        }
                        fdh fdh = (fdh) iir.f14318b;
                        fdh fdh2 = fdh.f9308e;
                        int i2 = c - 1;
                        if (c != 0) {
                            fdh.f9313d = i2;
                            fdh.f9310a |= 4;
                            fdr4.mo5536a((fdh) iir.mo8770g(), ffa.f9433a);
                            ArrayList arrayList2 = new ArrayList();
                            fej.m8700a(fdr4, (List) arrayList2);
                            feo a7 = fep.mo5570a(arrayList2, 0);
                            fdh fdh3 = ((fdx) arrayList2.get(0)).f9343d;
                            if (fdh3 == null) {
                                fdh3 = fdh.f9308e;
                            }
                            int b4 = ife.m12861b(fdh3.f9313d);
                            if (b4 != 0) {
                                if (b4 != 1) {
                                    a7.mo5569a(new ffd(3, arrayList2, -1));
                                }
                            }
                            a7.mo5569a(new ffd(2, arrayList2, a7.f9379d.size()));
                            fen fen = new fen(a7);
                            ial ial = fdr4.mo5543d(ffa.f9433a).f9311b;
                            if (ial == null) {
                                ial = ial.f13725d;
                            }
                            if ((ial.f13727a & 2) != 0) {
                                fen.mo5515a(fdr4);
                            }
                        } else {
                            throw null;
                        }
                    }
                }
                fep.f9386d.clear();
                if (a6 != null) {
                    a6.close();
                }
            } catch (Throwable th5) {
                ifn.m12935a(th, th5);
            }
        }
        if (fep.f9393k || fep.f9394l) {
            hlj a8 = hnb.m11765a("GIL:LogBatch");
            try {
                arrayList = new ArrayList(fep.f9389g.size());
                for (feo feo : fep.f9389g) {
                    arrayList.add(new ffe(feo.f9376a, feo.f9377b, feo.f9378c, feo.f9379d, feo.f9380e));
                }
                fep.f9389g.clear();
                fep.f9388f.clear();
                if (a8 != null) {
                    a8.close();
                    return arrayList;
                }
            } catch (Throwable th6) {
                ifn.m12935a(th, th6);
            }
        }
        return arrayList;
        throw th;
        throw th;
        throw th;
        throw th;
        throw th;
        throw th;
    }
}
