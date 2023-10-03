package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: fkq */
/* compiled from: PG */
final class fkq implements fhs, fmx {

    /* renamed from: a */
    public final List f9910a = new ArrayList();

    /* renamed from: b */
    public boolean f9911b;

    /* renamed from: c */
    private final fid f9912c;

    public fkq(fid fid) {
        this.f9912c = fid;
        fid.mo5747a((fic) this);
    }

    /* renamed from: b */
    public final void mo5735b() {
        synchronized (this) {
            this.f9911b = true;
        }
        this.f9912c.mo5748b(this);
        List list = this.f9910a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((fmc) list.get(i)).mo5834f();
        }
    }

    /* renamed from: a */
    public final void mo5727a() {
        this.f9912c.mo5748b(this);
    }
}
