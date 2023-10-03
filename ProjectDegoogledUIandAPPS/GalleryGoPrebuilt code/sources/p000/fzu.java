package p000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: fzu */
/* compiled from: PG */
final class fzu implements ice {

    /* renamed from: a */
    public List f10755a;

    /* renamed from: b */
    public final /* synthetic */ fzx f10756b;

    public /* synthetic */ fzu(fzx fzx) {
        this.f10756b = fzx;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        ArrayList arrayList;
        String valueOf = String.valueOf(this.f10756b.f10761a);
        hlj a = hnb.m11766a(valueOf.length() == 0 ? new String("Initialize ") : "Initialize ".concat(valueOf), hnf.f13084a);
        try {
            synchronized (this.f10756b.f10764d) {
                if (this.f10755a == null) {
                    fzx fzx = this.f10756b;
                    this.f10755a = fzx.f10765e;
                    fzx.f10765e = Collections.emptyList();
                }
            }
            arrayList = new ArrayList(this.f10755a.size());
            fzw fzw = new fzw(this.f10756b);
            for (icf a2 : this.f10755a) {
                arrayList.add(a2.mo2538a(fzw));
            }
        } catch (Exception e) {
            arrayList.add(ife.m12822a((Throwable) e));
        } catch (Throwable th) {
            if (a != null) {
                try {
                    a.close();
                } catch (Throwable th2) {
                    ifn.m12935a(th, th2);
                }
            }
            throw th;
        }
        ieh a3 = a.mo7548a(ife.m12883c((Iterable) arrayList).mo8443a((Callable) new fzt(this), (Executor) idh.f13918a));
        if (a != null) {
            a.close();
        }
        return a3;
    }
}
