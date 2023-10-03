package p000;

import android.app.Activity;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: fkr */
/* compiled from: PG */
final class fkr implements fhz, fmx {

    /* renamed from: a */
    private final fid f9913a;

    /* renamed from: b */
    private final hqk f9914b;

    /* renamed from: c */
    private final ArrayList f9915c = new ArrayList();

    /* renamed from: d */
    private boolean f9916d;

    public fkr(fid fid, hqk hqk) {
        this.f9913a = fid;
        this.f9914b = hqk;
        fid.mo5747a((fic) this);
    }

    /* renamed from: b */
    public final void mo5742b(Activity activity) {
        synchronized (this.f9915c) {
            if (!this.f9916d) {
                this.f9916d = true;
                this.f9913a.mo5748b(this);
                Iterator it = this.f9915c.iterator();
                while (it.hasNext()) {
                    ((iel) this.f9914b.mo2652a()).mo5931a((Runnable) it.next());
                }
                this.f9915c.clear();
            }
        }
    }

    /* renamed from: a */
    public final void mo5727a() {
        this.f9913a.mo5748b(this);
    }
}
