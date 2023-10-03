package p000;

import android.view.View;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.peoplegrid.PeopleGridView;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* renamed from: dug */
/* compiled from: PG */
public final class dug implements cqg, cnc {

    /* renamed from: a */
    public final bnc f7391a;

    /* renamed from: b */
    public final hnw f7392b;

    /* renamed from: c */
    public final dvf f7393c;

    /* renamed from: d */
    public final cnh f7394d;

    /* renamed from: e */
    public final blu f7395e;

    /* renamed from: f */
    public final gvi f7396f;

    /* renamed from: g */
    public final ble f7397g;

    /* renamed from: h */
    public final Executor f7398h;

    /* renamed from: i */
    public final bod f7399i;

    /* renamed from: j */
    public final fee f7400j;

    /* renamed from: k */
    public final cqh f7401k;

    /* renamed from: l */
    public final cpp f7402l;

    /* renamed from: m */
    public final cjh f7403m;

    /* renamed from: n */
    public View f7404n;

    /* renamed from: o */
    public PeopleGridView f7405o;

    /* renamed from: p */
    public hso f7406p = hso.m12047f();

    /* renamed from: q */
    public final gvc f7407q = new duf(this);

    /* renamed from: r */
    public int f7408r = 1;

    public dug(bnc bnc, hnw hnw, dvf dvf, cnh cnh, blu blu, gvi gvi, ble ble, Executor executor, bod bod, fee fee, cqh cqh, cpp cpp, cjh cjh) {
        this.f7391a = bnc;
        this.f7392b = hnw;
        this.f7393c = dvf;
        this.f7394d = cnh;
        this.f7395e = blu;
        this.f7396f = gvi;
        this.f7397g = ble;
        this.f7398h = executor;
        this.f7399i = bod;
        this.f7400j = fee;
        this.f7402l = cpp;
        this.f7401k = cqh;
        iir g = cqe.f5414d.mo8793g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cqe cqe = (cqe) g.f14318b;
        cqe.f5416a |= 2;
        cqe.f5418c = 999;
        cqh.mo3757a((cqe) g.mo8770g());
        this.f7403m = cjh;
    }

    /* renamed from: c */
    public final void mo4457c() {
        if (this.f7401k.mo3764d()) {
            this.f7405o.mo2635n().mo4464a(this.f7406p);
            this.f7401k.f5421b = this.f7406p;
            this.f7402l.mo3730a();
            return;
        }
        ArrayList arrayList = new ArrayList();
        hvs i = this.f7406p.listIterator();
        while (i.hasNext()) {
            dul dul = (dul) i.next();
            if (dul.mo4432a() == 2 || !dul.mo4437c().mo3113f()) {
                arrayList.add(dul);
            }
        }
        this.f7405o.mo2635n().mo4464a(arrayList);
        this.f7401k.f5421b = arrayList;
        this.f7402l.mo3730a();
    }

    /* renamed from: a */
    public final void mo2621a() {
        if (this.f7401k.mo3764d()) {
            this.f7402l.mo3731a(cpi.SHOW_AND_HIDE);
            return;
        }
        this.f7402l.mo3732b();
        if (this.f7408r == 2) {
            mo4457c();
        }
    }

    /* renamed from: j */
    public final void mo2638j() {
        this.f7404n.findViewById(R.id.people_grid_appbar).setVisibility(0);
    }

    /* renamed from: b */
    public final void mo2637b() {
        this.f7404n.findViewById(R.id.people_grid_appbar).setVisibility(4);
    }
}
