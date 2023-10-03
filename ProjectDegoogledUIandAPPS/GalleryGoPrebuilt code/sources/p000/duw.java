package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.peoplegrid.PeopleGridView;
import java.util.List;

/* renamed from: duw */
/* compiled from: PG */
public final class duw {

    /* renamed from: a */
    public final C0598vy f7431a;

    /* renamed from: b */
    public List f7432b = hso.m12047f();

    /* renamed from: c */
    public int f7433c;

    /* renamed from: d */
    private final hbl f7434d;

    /* renamed from: e */
    private final gwd f7435e;

    /* renamed from: f */
    private final cps f7436f;

    public duw(PeopleGridView peopleGridView, hbl hbl, cps cps) {
        this.f7434d = hbl;
        this.f7436f = cps;
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(hbl).inflate(R.layout.people_grid_view_contents, peopleGridView, true).findViewById(R.id.recycler_view);
        hpq a = hpp.f13230a.mo7660a(dus.f7427a);
        a.getClass();
        gwa a2 = gwa.m10928a(new dut(a));
        gwb c = gwd.m10934c();
        c.mo7127a((gwe) new duo(hbl));
        c.mo7128a(duu.f7429a);
        c.f12171a = a2;
        this.f7435e = c.mo7126a();
        int a3 = mo4463a();
        this.f7433c = a3;
        C0598vy vyVar = new C0598vy(a3, (byte[]) null);
        this.f7431a = vyVar;
        vyVar.f16174b = new duv(this);
        recyclerView.setLayoutManager(this.f7431a);
        recyclerView.setAdapter(this.f7435e);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(this.f7433c);
    }

    /* renamed from: a */
    public final void mo4464a(List list) {
        this.f7432b = list;
        this.f7435e.mo7129a(list);
    }

    /* renamed from: a */
    public final int mo4463a() {
        return this.f7436f.mo3740a(this.f7434d.getResources().getInteger(R.integer.people_grid_default_column_count));
    }
}
