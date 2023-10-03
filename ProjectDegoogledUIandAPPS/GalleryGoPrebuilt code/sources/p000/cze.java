package p000;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import p003j$.util.Optional;

/* renamed from: cze */
/* compiled from: PG */
public final class cze {

    /* renamed from: a */
    public final iqk f6089a;

    /* renamed from: b */
    public final imp f6090b;

    /* renamed from: c */
    public final deo f6091c;

    /* renamed from: d */
    public final dge f6092d;

    /* renamed from: e */
    public final bqq f6093e;

    /* renamed from: f */
    public final AtomicBoolean f6094f = new AtomicBoolean(false);

    /* renamed from: g */
    public final AtomicBoolean f6095g = new AtomicBoolean(false);

    /* renamed from: h */
    private final iel f6096h;

    /* renamed from: i */
    private final ble f6097i;

    /* renamed from: j */
    private final Optional f6098j;

    /* renamed from: k */
    private final Optional f6099k;

    public cze(iqk iqk, imp imp, iel iel, deo deo, dge dge, ble ble, Optional optional, Optional optional2, bqq bqq) {
        this.f6089a = iqk;
        this.f6090b = imp;
        this.f6096h = iel;
        this.f6091c = deo;
        this.f6092d = dge;
        this.f6097i = ble;
        this.f6093e = bqq;
        this.f6099k = optional;
        this.f6098j = optional2;
    }

    /* renamed from: a */
    public final void mo4014a() {
        this.f6095g.set(true);
    }

    /* renamed from: a */
    public final ieh mo4012a(cyd cyd) {
        return mo4013a((List) hso.m12033a((Object) cyd));
    }

    /* renamed from: a */
    public final ieh mo4013a(List list) {
        if (list.isEmpty()) {
            cwn.m5510a("Tried to delete an empty list of items", new Object[0]);
            return ife.m12822a((Throwable) new IllegalArgumentException());
        } else if (this.f6094f.get()) {
            return ife.m12820a((Object) czd.DELETION_ALREADY_RUNNING);
        } else {
            hlj a = hnb.m11765a("delete");
            try {
                this.f6095g.set(false);
                this.f6094f.set(true);
                ieh ieh = bip.f2458b;
                this.f6091c.mo4100a();
                int i = 0;
                while (i < list.size()) {
                    int i2 = i + 10;
                    List subList = list.subList(i, Math.min(list.size(), i2));
                    int i3 = i / 10;
                    ieh[] iehArr = {ieh};
                    ieh = gte.m10769a(iehArr).mo7611a((ice) new cyz(this, (Math.min(i2, list.size()) * 100) / list.size(), subList, i3, list), (Executor) this.f6096h);
                    i = i2;
                }
                ieh a2 = this.f6097i.mo2553a(ieh, this.f6096h);
                if (this.f6099k.isPresent()) {
                    a2 = ((ble) this.f6099k.get()).mo2553a(a2, this.f6096h);
                }
                if (this.f6098j.isPresent()) {
                    a2 = ((ble) this.f6098j.get()).mo2553a(a2, this.f6096h);
                }
                ieh a3 = a.mo7548a(gte.m10770a(gte.m10769a(a2).mo7613a((Callable) new cza(this), (Executor) this.f6096h), (hpr) new czb(this), (Executor) this.f6096h));
                if (a != null) {
                    a.close();
                }
                return a3;
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        }
        throw th;
    }
}
