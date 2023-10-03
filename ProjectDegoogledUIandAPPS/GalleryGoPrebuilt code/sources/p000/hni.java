package p000;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: hni */
/* compiled from: PG */
final class hni implements Runnable, hle {

    /* renamed from: a */
    public final AtomicReference f13099a;

    /* renamed from: b */
    public ieh f13100b;

    /* renamed from: c */
    public int f13101c = 0;

    /* renamed from: d */
    private final hmh f13102d;

    /* renamed from: e */
    private final UUID f13103e;

    /* renamed from: f */
    private final long f13104f;

    /* renamed from: g */
    private final boolean f13105g;

    /* renamed from: h */
    private final hmt f13106h;

    public hni(hmh hmh, UUID uuid, hmt hmt, hng hng, long j, boolean z) {
        this.f13102d = hmh;
        this.f13103e = uuid;
        this.f13106h = hmt;
        this.f13104f = j;
        this.f13105g = z;
        this.f13099a = new AtomicReference(hng);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final hnh mo7606a() {
        int i;
        boolean z;
        hni hni = this;
        synchronized (this) {
            try {
                hng hng = (hng) hni.f13099a.get();
                int i2 = hng.f13090e + 1;
                hng[] hngArr = new hng[i2];
                while (hng != null) {
                    hngArr[hng.f13090e] = hng;
                    hng = hng.f13091f;
                }
                hmt hmt = hni.f13106h;
                iir iir = (iir) hmt.mo8790b(5);
                iir.mo8503a((iix) hmt);
                SparseArray sparseArray = new SparseArray(i2);
                boolean z2 = false;
                int i3 = 0;
                int i4 = 0;
                while (i3 < i2) {
                    try {
                        hng hng2 = hngArr[i3];
                        boolean z3 = hni.f13105g;
                        iir g = hlh.f12966i.mo8793g();
                        String str = hng2.f13087b;
                        if (g.f14319c) {
                            g.mo8751b();
                            g.f14319c = z2;
                        }
                        hlh hlh = (hlh) g.f14318b;
                        str.getClass();
                        int i5 = hlh.f12968a | 1;
                        hlh.f12968a = i5;
                        hlh.f12969b = str;
                        int i6 = hng2.f13090e;
                        int i7 = i5 | 2;
                        hlh.f12968a = i7;
                        hlh.f12970c = i6;
                        hng hng3 = hng2.f13086a;
                        if (hng3 != null) {
                            i = hng3.f13090e;
                        } else {
                            i = -1;
                        }
                        int i8 = i7 | 4;
                        hlh.f12968a = i8;
                        hlh.f12971d = i;
                        long j = (long) hng2.f13088c;
                        hlh.f12968a = i8 | 8;
                        hlh.f12972e = j;
                        int i9 = hng2.f13092g;
                        if (i9 != 0) {
                            ife.m12896d(true);
                            long j2 = (long) (1073741823 & i9);
                            if (g.f14319c) {
                                g.mo8751b();
                                g.f14319c = false;
                            }
                            hlh hlh2 = (hlh) g.f14318b;
                            hlh2.f12968a |= 16;
                            hlh2.f12973f = j2;
                            ife.m12896d(true);
                            if ((i9 & 1073741824) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (g.f14319c) {
                                g.mo8751b();
                                g.f14319c = false;
                            }
                            hlh hlh3 = (hlh) g.f14318b;
                            hlh3.f12968a |= 64;
                            hlh3.f12975h = z;
                        }
                        if (z3) {
                            int i10 = hng2.get();
                            if (g.f14319c) {
                                g.mo8751b();
                                g.f14319c = false;
                            }
                            hlh hlh4 = (hlh) g.f14318b;
                            hlh4.f12968a |= 32;
                            hlh4.f12974g = i10;
                        }
                        hlh hlh5 = (hlh) g.mo8770g();
                        if (iir.f14319c) {
                            iir.mo8751b();
                            iir.f14319c = false;
                        }
                        hmt hmt2 = (hmt) iir.f14318b;
                        hmt hmt3 = hmt.f13055i;
                        hlh5.getClass();
                        if (!hmt2.f13060d.mo8521a()) {
                            hmt2.f13060d = iix.m13608a(hmt2.f13060d);
                        }
                        hmt2.f13060d.add(hlh5);
                        sparseArray.append(hng2.f13090e, hng2.f13089d);
                        if (!hng2.mo7605a()) {
                            i4++;
                        }
                        i3++;
                        z2 = false;
                        hni = this;
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                if (this.f13101c != 0) {
                    iir g2 = hkq.f12942d.mo8793g();
                    iir g3 = hkp.f12938c.mo8793g();
                    int i11 = this.f13101c;
                    if (g3.f14319c) {
                        g3.mo8751b();
                        g3.f14319c = false;
                    }
                    hkp hkp = (hkp) g3.f14318b;
                    hkp.f12940a |= 1;
                    hkp.f12941b = i11;
                    hkp hkp2 = (hkp) g3.mo8770g();
                    if (g2.f14319c) {
                        g2.mo8751b();
                        g2.f14319c = false;
                    }
                    hkq hkq = (hkq) g2.f14318b;
                    hkp2.getClass();
                    hkq.f12945b = hkp2;
                    hkq.f12944a |= 1;
                    hkq hkq2 = (hkq) g2.mo8770g();
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    hmt hmt4 = (hmt) iir.f14318b;
                    hmt hmt5 = hmt.f13055i;
                    hkq2.getClass();
                    hmt4.f13063g = hkq2;
                    hmt4.f13057a |= 16;
                }
                hnh hnh = new hnh(hngArr[0].f13087b, this.f13103e, this.f13104f, (hmt) iir.mo8770g(), sparseArray, i4);
                return hnh;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final void run() {
        hmh hmh = this.f13102d;
        ieh ieh = this.f13100b;
        hnh a = mo7606a();
        ife.m12896d(hmh.f13025d.remove(a.f13094b) != null);
        if (!ieh.isCancelled()) {
            try {
                ife.m12871b((Future) ieh);
                long j = a.f13095c;
                while (true) {
                    long j2 = hmh.f13026e.get();
                    if (j <= j2) {
                        break;
                    } else if (hmh.f13026e.compareAndSet(j2, 300000 + j)) {
                        long j3 = j - 2100000;
                        for (hni hni : hmh.f13025d.values()) {
                            if (hni.f13104f < j3) {
                                iej a2 = hmh.f13024c.mo5935a(hmf.f13020a, 10, TimeUnit.SECONDS);
                                hni.getClass();
                                a2.mo53a(new hmg(hni), idh.f13918a);
                            }
                        }
                    }
                }
                hmh.mo7583a(a.f13096d, a.f13097e, a.f13093a);
            } catch (ExecutionException e) {
                ((hvv) ((hvv) ((hvv) hmh.f13022a.mo8178a()).mo8202a(e.getCause())).mo8201a("com/google/apps/tiktok/tracing/TraceManagerImpl", "handleTraceComplete", 197, "TraceManagerImpl.java")).mo8206a("Trace %s failed collection", (Object) a.f13093a);
            }
        } else {
            hkq hkq = a.f13096d.f13063g;
            if (hkq == null) {
                hkq = hkq.f12942d;
            }
            long c = hmh.f13023b.mo5372c() - a.f13095c;
            hmt hmt = a.f13096d;
            iir iir = (iir) hmt.mo8790b(5);
            iir.mo8503a((iix) hmt);
            iir iir2 = (iir) hkq.mo8790b(5);
            iir2.mo8503a((iix) hkq);
            iir g = hko.f12933d.mo8793g();
            int i = a.f13098f;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            hko hko = (hko) g.f14318b;
            int i2 = hko.f12935a | 2;
            hko.f12935a = i2;
            hko.f12937c = i;
            hko.f12935a = 1 | i2;
            hko.f12936b = c;
            hko hko2 = (hko) g.mo8770g();
            if (iir2.f14319c) {
                iir2.mo8751b();
                iir2.f14319c = false;
            }
            hkq hkq2 = (hkq) iir2.f14318b;
            hko2.getClass();
            hkq2.f12946c = hko2;
            hkq2.f12944a |= 2;
            hkq hkq3 = (hkq) iir2.mo8770g();
            if (iir.f14319c) {
                iir.mo8751b();
                iir.f14319c = false;
            }
            hmt hmt2 = (hmt) iir.f14318b;
            hmt hmt3 = hmt.f13055i;
            hkq3.getClass();
            hmt2.f13063g = hkq3;
            hmt2.f13057a |= 16;
            hmt hmt4 = (hmt) iir.mo8770g();
            int size = hmt4.f13060d.size() - 1;
            Iterator it = ife.m12836a((List) hmt4.f13060d).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                hlh hlh = (hlh) it.next();
                int i3 = hlh.f12968a;
                if ((i3 & 16) == 0 && (i3 & 4) != 0) {
                    size = hlh.f12970c;
                    break;
                }
            }
            ArrayList arrayList = new ArrayList();
            int i4 = 0;
            while (true) {
                String str = "";
                if (size >= 0) {
                    hlh hlh2 = (hlh) hmt4.f13060d.get(size);
                    int i5 = (int) hlh2.f12972e;
                    String valueOf = String.valueOf(hlh2.f12969b);
                    if ((hlh2.f12968a & 16) == 0) {
                        str = " (Timed Out)";
                    }
                    arrayList.add(new StackTraceElement("tk_trace", str.length() == 0 ? new String(valueOf) : valueOf.concat(str), "Started After", (i5 - i4) / 1000));
                    size = ((hlh) hmt4.f13060d.get(size)).f12971d;
                    i4 = i5;
                } else {
                    ((hvv) ((hvv) ((hvv) hmh.f13022a.mo8178a()).mo8202a((Throwable) new hmw(str, (Throwable) null, (StackTraceElement[]) arrayList.toArray(new StackTraceElement[0])))).mo8201a("com/google/apps/tiktok/tracing/TraceManagerImpl", "handleTraceTimeout", 253, "TraceManagerImpl.java")).mo8207a("Trace %s timed out after %d ms. Complete trace: %s", (Object) a.f13093a, (Object) Long.valueOf(c), (Object) hmt4);
                    hmh.mo7583a(hmt4, a.f13097e, a.f13093a);
                    return;
                }
            }
        }
    }
}
