package p000;

import android.os.health.TimerStat;

/* renamed from: fnu */
/* compiled from: PG */
public final class fnu extends fns {

    /* renamed from: a */
    public static final fnu f10112a = new fnu();

    private fnu() {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ikf mo5985a(String str, Object obj) {
        return fpt.m9376a(str, (TimerStat) obj);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ String mo5986a(ikf ikf) {
        iqv iqv = (iqv) ikf;
        iqq iqq = iqv.f14715d;
        if (iqq == null) {
            iqq = iqq.f14681d;
        }
        if ((iqq.f14683a & 2) == 0) {
            iqq iqq2 = iqv.f14715d;
            if (iqq2 == null) {
                iqq2 = iqq.f14681d;
            }
            return Long.toHexString(iqq2.f14684b);
        }
        iqq iqq3 = iqv.f14715d;
        if (iqq3 == null) {
            iqq3 = iqq.f14681d;
        }
        return iqq3.f14685c;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ikf mo5984a(ikf ikf, ikf ikf2) {
        return fpt.m9375a((iqv) ikf, (iqv) ikf2);
    }
}
