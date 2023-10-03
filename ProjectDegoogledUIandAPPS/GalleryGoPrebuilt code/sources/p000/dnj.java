package p000;

import android.os.Bundle;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p003j$.util.function.Consumer;

/* renamed from: dnj */
/* compiled from: PG */
final class dnj implements gvc {

    /* renamed from: a */
    private final /* synthetic */ dnn f6866a;

    public dnj(dnn dnn) {
        this.f6866a = dnn;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        cwn.m5515b(th, "OneUpFragment: Failed to fetch data; navigating up.", new Object[0]);
        this.f6866a.mo4274c();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        dqa dqa = (dqa) obj;
        if (!this.f6866a.f6882L) {
            if (dqa.mo4333d()) {
                this.f6866a.mo4274c();
                return;
            } else if (dqa.mo4331b().isPresent()) {
                cxi cxi = (cxi) dqa.mo4331b().get();
                new Object[1][0] = cxi.f5910b;
                this.f6866a.mo4269a(cxi, (Bundle) null);
                dnn dnn = this.f6866a;
                dme dme = dnn.f6895d;
                if ((dme.f6825a & 8) != 0) {
                    dmd a = dmd.m6351a(dme.f6829e);
                    if (a == null) {
                        a = dmd.UNKNOWN;
                    }
                    new Object[1][0] = a;
                    cxh cxh = cxh.UNKNOWN_MEDIA_TYPE;
                    int ordinal = a.ordinal();
                    if (ordinal == 0) {
                        cwn.m5510a("OneUpFragment: Unknown behavior", new Object[0]);
                    } else if (ordinal == 1) {
                        dnn.mo4270a("share", (Consumer) new dmt(dnn));
                    } else if (ordinal == 2) {
                        dnn.mo4270a("edit in", (Consumer) new dmu(dnn));
                    } else if (ordinal == 3) {
                        dnn.mo4270a("use as", (Consumer) new dmv(dnn));
                    }
                }
                ees ees = (ees) this.f6866a.f6900i.mo9034a();
                eep eep = new eep(ees);
                cwn.m5509a(ife.m12815a(hmq.m11743a((ice) eep), 1, TimeUnit.SECONDS, (ScheduledExecutorService) ees.f8115a), "SharingDataService: Failed to warm up", new Object[0]);
                ((bkz) this.f6866a.f6913v.mo9034a()).mo2540a(this.f6866a.f6897f.mo5653m());
            } else {
                return;
            }
        }
        if (dqa.mo4332c().isPresent()) {
            this.f6866a.f6894c = (Map) dqa.mo4332c().get();
        }
        if (dqa.mo4330a().isPresent()) {
            this.f6866a.f6893b = dqa.mo4330a();
            this.f6866a.f6912u.mo2551b();
        }
        if (this.f6866a.f6893b.isPresent()) {
            dnn dnn2 = this.f6866a;
            if (!dnn2.f6878H.mo4298a((List) dnn2.f6893b.get(), this.f6866a.f6894c)) {
                this.f6866a.mo4274c();
            } else {
                this.f6866a.mo4272b();
            }
        }
    }
}
