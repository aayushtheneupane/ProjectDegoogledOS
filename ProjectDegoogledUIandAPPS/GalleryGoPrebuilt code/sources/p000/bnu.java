package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.category.CategoryListView;
import java.util.List;

/* renamed from: bnu */
/* compiled from: PG */
public final class bnu implements cqg {

    /* renamed from: a */
    public final hbl f3227a;

    /* renamed from: b */
    public final gwd f3228b;

    /* renamed from: c */
    public final RecyclerView f3229c;

    /* renamed from: d */
    public final cqh f3230d;

    /* renamed from: e */
    public final bnr f3231e;

    /* renamed from: f */
    public final bnt f3232f;

    public bnu(hbl hbl, CategoryListView categoryListView, cqh cqh, bnt bnt) {
        this.f3230d = cqh;
        this.f3227a = hbl;
        this.f3232f = bnt;
        LayoutInflater.from(hbl).inflate(R.layout.category_list_view_content, categoryListView, true);
        gwb c = gwd.m10934c();
        c.mo7128a(bns.f3223a);
        c.f12171a = new gwa(new gvy());
        c.mo7127a((gwe) new bol());
        this.f3228b = c.mo7126a();
        this.f3229c = (RecyclerView) categoryListView.findViewById(R.id.recycler);
        bnr bnr = new bnr(hbl);
        this.f3231e = bnr;
        this.f3229c.setLayoutManager(bnr);
        this.f3229c.setAdapter(this.f3228b);
    }

    /* renamed from: a */
    public final void mo2621a() {
        this.f3229c.setNestedScrollingEnabled(!this.f3230d.mo3764d());
        this.f3231e.f3220a = !this.f3230d.mo3764d();
    }

    /* renamed from: a */
    public final void mo2622a(List list) {
        this.f3232f.f3224a = this;
        this.f3228b.mo7129a(list);
        this.f3230d.mo3758a((cqg) this);
        mo2621a();
    }
}
