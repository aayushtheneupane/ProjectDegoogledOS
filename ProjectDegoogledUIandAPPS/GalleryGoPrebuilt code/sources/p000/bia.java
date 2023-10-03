package p000;

import android.os.Handler;
import android.os.SystemClock;
import android.support.p002v7.widget.RecyclerView;
import android.util.Log;
import java.util.Locale;

/* renamed from: bia */
/* compiled from: PG */
final class bia implements Runnable {

    /* renamed from: a */
    private final int f2427a;

    /* renamed from: b */
    private final bib f2428b;

    /* renamed from: c */
    private final bhx f2429c;

    /* renamed from: d */
    private final bhn f2430d;

    /* renamed from: e */
    private final bhz f2431e;

    /* renamed from: f */
    private final int f2432f;

    /* renamed from: g */
    private final boolean f2433g;

    public bia(int i, bib bib, bhx bhx, bhn bhn, bhz bhz, boolean z, int i2) {
        this.f2427a = i;
        this.f2428b = bib;
        this.f2429c = bhx;
        this.f2430d = bhn;
        this.f2431e = bhz;
        this.f2433g = z;
        this.f2432f = i2;
    }

    /* renamed from: a */
    private static bia m2594a(bib bib, bhz bhz, boolean z, int i) {
        return new bia(2, bib, (bhx) null, (bhn) null, bhz, z, i);
    }

    public final void run() {
        switch (this.f2427a) {
            case 1:
                bib bib = this.f2428b;
                bhx bhx = this.f2429c;
                Handler handler = bib.f2434a;
                bib.mo2067a(bhx);
                return;
            case RecyclerView.SCROLL_STATE_SETTLING:
                bib bib2 = this.f2428b;
                bhz bhz = this.f2431e;
                boolean z = this.f2433g;
                Handler handler2 = bib.f2434a;
                bib2.mo2069b(bhz.f2423a);
                if (z) {
                    bib2.f2435b.execute(new bia(6, (bib) null, (bhx) null, (bhn) null, bhz, false, 1));
                    return;
                }
                return;
            case 3:
                bib bib3 = this.f2428b;
                Handler handler3 = bib.f2434a;
                synchronized (bib3.f2436c) {
                    for (int i = bib3.f2436c.f15193b - 1; i >= 0; i--) {
                        C0309lf lfVar = bib3.f2436c;
                        bhz bhz2 = (bhz) lfVar.remove(lfVar.mo9320b(i));
                        if (bhz2 != null) {
                            bib.f2434a.post(m2594a(bib3, bhz2, false, 2));
                        }
                    }
                }
                return;
            case 4:
                bib bib4 = this.f2428b;
                bhx bhx2 = this.f2429c;
                bhn bhn = this.f2430d;
                Handler handler4 = bib.f2434a;
                synchronized (bib4.f2436c) {
                    if (bib4.f2436c.containsKey(((bhw) bhx2).f2412a)) {
                        Log.w("FJD.JobService", String.format(Locale.US, "Job with tag = %s was already running.", new Object[]{((bhw) bhx2).f2412a}));
                        return;
                    }
                    bib4.f2436c.put(((bhw) bhx2).f2412a, new bhz(bhx2, bhn, SystemClock.elapsedRealtime()));
                    bib.f2434a.post(new bia(1, bib4, bhx2, (bhn) null, (bhz) null, false, 0));
                    return;
                }
            case 5:
                bib bib5 = this.f2428b;
                bhx bhx3 = this.f2429c;
                boolean z2 = this.f2433g;
                Handler handler5 = bib.f2434a;
                synchronized (bib5.f2436c) {
                    bhz bhz3 = (bhz) bib5.f2436c.remove(((bhw) bhx3).f2412a);
                    if (bhz3 != null) {
                        bib.f2434a.post(m2594a(bib5, bhz3, z2, 0));
                        return;
                    }
                    return;
                }
            case 6:
                this.f2431e.mo2064a(this.f2432f);
                return;
            default:
                bib bib6 = this.f2428b;
                bhx bhx4 = this.f2429c;
                int i2 = this.f2432f;
                Handler handler6 = bib.f2434a;
                synchronized (bib6.f2436c) {
                    bhz bhz4 = (bhz) bib6.f2436c.remove(((bhw) bhx4).f2412a);
                    if (bhz4 != null) {
                        bhz4.mo2064a(i2);
                    }
                }
                return;
        }
    }
}
