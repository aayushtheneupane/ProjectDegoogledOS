package p000;

import java.io.InputStream;

/* renamed from: bau */
/* compiled from: PG */
public final class bau implements arb {

    /* renamed from: a */
    private final bac f1977a;

    /* renamed from: b */
    private final aui f1978b;

    public bau(bac bac, aui aui) {
        this.f1977a = bac;
        this.f1978b = aui;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ aua mo1507a(Object obj, int i, int i2, aqz aqz) {
        bap bap;
        boolean z;
        bfg bfg;
        InputStream inputStream = (InputStream) obj;
        if (inputStream instanceof bap) {
            bap = (bap) inputStream;
            z = false;
        } else {
            bap = new bap(inputStream, this.f1978b);
            z = true;
        }
        synchronized (bfg.f2204a) {
            bfg = (bfg) bfg.f2204a.poll();
        }
        if (bfg == null) {
            bfg = new bfg();
        }
        bfg.f2205b = bap;
        try {
            aua a = this.f1977a.mo1754a((InputStream) new bfm(bfg), i, i2, aqz, (bab) new bat(bap, bfg));
            bfg.mo1942a();
            if (z) {
                bap.mo1762b();
            }
            return a;
        } catch (Throwable th) {
            bfg.mo1942a();
            if (z) {
                bap.mo1762b();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1508a(Object obj, aqz aqz) {
        InputStream inputStream = (InputStream) obj;
        return true;
    }
}
