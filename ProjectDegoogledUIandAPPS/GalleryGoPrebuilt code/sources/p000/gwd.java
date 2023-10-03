package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;

/* renamed from: gwd */
/* compiled from: PG */
public final class gwd extends C0634xg {

    /* renamed from: c */
    private final gwf f12176c = new gwf();

    /* renamed from: d */
    private final hpr f12177d;

    /* renamed from: e */
    private final hpq f12178e;

    /* renamed from: f */
    private final gvx f12179f;

    /* renamed from: g */
    private List f12180g;

    public /* synthetic */ gwd(hpr hpr, hpq hpq, gvx gvx) {
        this.f12177d = hpr;
        this.f12178e = hpq;
        this.f12179f = gvx;
    }

    /* renamed from: a */
    public final int mo220a() {
        List list = this.f12180g;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* renamed from: a */
    public final int mo221a(int i) {
        gwf gwf = this.f12176c;
        hpr hpr = this.f12177d;
        this.f12180g.get(i);
        Object obj = ((hpt) hpr).f13233a;
        Integer num = (Integer) gwf.f12181a.get(obj);
        if (num == null) {
            int i2 = gwf.f12183c;
            gwf.f12183c = i2 + 1;
            num = Integer.valueOf(i2);
            gwf.f12181a.put(obj, num);
            gwf.f12182b.put(num.intValue(), obj);
        }
        return num.intValue();
    }

    /* renamed from: c */
    public static gwb m10934c() {
        return new gwb();
    }

    /* renamed from: a */
    public final void mo4519a(RecyclerView recyclerView) {
        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
        boolean z = false;
        if (!recyclerView.hasFixedSize() || mo220a() > 0 || !(layoutParams.height == -2 || layoutParams.width == -2)) {
            z = true;
        }
        ife.m12876b(z, (Object) "RecyclerViews that use WRAP_CONTENT with setHasFixedSize(true) won't resize on new data. If you have static data, you should set it on the adapter before setAdapter().");
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo223a(C0667ym ymVar, int i) {
        gwc gwc = (gwc) ymVar;
        gwe a = this.f12176c.mo7130a(gwc.f16387f);
        try {
            int i2 = gwc.f12174q;
            a.mo2643a(gwc.f12175p, this.f12180g.get(i));
        } catch (ClassCastException e) {
            throw new IllegalStateException(String.format("Attempting to bind data with an incompatible ViewBinder class (%s). Check that your ViewBinder function is correct.", new Object[]{a}), e);
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C0667ym mo222a(ViewGroup viewGroup, int i) {
        gwe a = this.f12176c.mo7130a(i);
        ife.m12869b((Object) a, (Object) "No ViewBinder for the provided viewType");
        return new gwc(a.mo2641a(viewGroup));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo4520a(C0667ym ymVar) {
        gwc gwc = (gwc) ymVar;
        gwe a = this.f12176c.mo7130a(gwc.f16387f);
        int i = gwc.f12174q;
        a.mo2642a(gwc.f12175p);
    }

    /* renamed from: a */
    public final void mo7129a(List list) {
        fxk.m9830b();
        List list2 = this.f12180g;
        this.f12180g = list;
        if (list2 == null && list != null) {
            mo10537b(0, list.size());
            return;
        } else if (list2 != null && list == null) {
            mo10539c(0, list2.size());
            return;
        } else if (list2 == null || this.f12178e == null || this.f12179f == null) {
            mo10540d();
            return;
        } else if (hnb.m11774a(hnf.f13084a)) {
            hlj a = hnb.m11765a("RecyclerView Data Diff");
            try {
                this.f12179f.mo2661a(list2, list, this.f12178e, this);
                if (a != null) {
                    a.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else {
            this.f12179f.mo2661a(list2, list, this.f12178e, this);
            return;
        }
        throw th;
    }
}
