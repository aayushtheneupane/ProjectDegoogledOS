package p000;

import java.util.Iterator;
import java.util.List;

/* renamed from: cyh */
/* compiled from: PG */
final /* synthetic */ class cyh implements hga {

    /* renamed from: a */
    private final List f6035a;

    public cyh(List list) {
        this.f6035a = list;
    }

    /* renamed from: a */
    public final void mo2584a(hfz hfz) {
        long j;
        List list = this.f6035a;
        hgn hgn = new hgn();
        hgn.mo7409a("INSERT OR IGNORE INTO mt(b,c,d,h,i,j,k,l,e,f,ah,m,g,n,ad,at) VALUES ");
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            cyg cyg = (cyg) it.next();
            i++;
            hgn.mo7409a("(?,");
            hgn.mo7408a(Long.valueOf(cyg.mo3908b()));
            hgn.mo7409a("?,");
            hgn.mo7408a(Long.valueOf((long) cyg.mo3909c()));
            hgn.mo7409a("?,");
            hgn.mo7411b(cyg.mo3910d());
            hgn.mo7409a("?,");
            if (cyg.mo3917i().isPresent()) {
                hgn.mo7408a((Long) cyg.mo3917i().get());
            } else {
                hgn.mo7410b();
            }
            hgn.mo7409a("?,");
            hgn.mo7408a(Long.valueOf(cyg.mo3918j()));
            hgn.mo7409a("?,");
            hgn.mo7408a(Long.valueOf(cyg.mo3919k()));
            hgn.mo7409a("?,");
            hgn.mo7408a(Long.valueOf(cyg.mo3920l()));
            hgn.mo7409a("?,");
            hgn.mo7411b(cyg.mo3921m());
            hgn.mo7409a("?,");
            hgn.mo7408a(Long.valueOf((long) cyg.mo3911e()));
            hgn.mo7409a("?,");
            hgn.mo7408a(Long.valueOf((long) cyg.mo3913f()));
            hgn.mo7409a("?,");
            hgn.mo7408a(Long.valueOf((long) cyg.mo3914g()));
            hgn.mo7409a("?,");
            hgn.mo7411b(cyg.mo3922n());
            hgn.mo7409a("?,");
            hgn.mo7408a(Long.valueOf(cyg.mo3915h()));
            hgn.mo7409a("?,");
            hgn.mo7411b(cyg.mo3923o());
            hgn.mo7409a("?,");
            if (!cyg.mo3935z()) {
                j = 0;
            } else {
                j = 1;
            }
            hgn.mo7408a(Long.valueOf(j));
            hgn.mo7409a("?");
            if (cyg.mo3897D().isPresent()) {
                hgn.mo7411b((String) cyg.mo3897D().get());
            } else {
                hgn.mo7410b();
            }
            hgn.mo7409a(")");
            if (!it.hasNext() || i == 62) {
                hfz.mo7388a(hgn.mo7407a());
                hgn = new hgn();
                hgn.mo7409a("INSERT OR IGNORE INTO mt(b,c,d,h,i,j,k,l,e,f,ah,m,g,n,ad,at) VALUES ");
                i = 0;
            } else {
                hgn.mo7409a(",");
            }
        }
    }
}
