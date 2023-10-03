package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.sharing.SharingAppGridView;
import java.util.List;

/* renamed from: ect */
/* compiled from: PG */
public final class ect {

    /* renamed from: a */
    public final hbl f7938a;

    /* renamed from: b */
    public final gwd f7939b;

    /* renamed from: c */
    private final RecyclerView f7940c;

    public ect(SharingAppGridView sharingAppGridView, hbl hbl) {
        LayoutInflater from = LayoutInflater.from(hbl);
        from.inflate(R.layout.sharing_app_grid_view_contents, sharingAppGridView, true);
        this.f7938a = hbl;
        this.f7940c = (RecyclerView) sharingAppGridView.findViewById(R.id.recycler_view);
        gwb c = gwd.m10934c();
        c.mo7127a((gwe) new edm(from));
        c.mo7128a(ecq.f7936a);
        this.f7939b = c.mo7126a();
        this.f7940c.setLayoutManager(new C0598vy(hbl.getResources().getInteger(R.integer.appshare_grid_column_count), (byte[]) null));
        this.f7940c.setAdapter(this.f7939b);
        this.f7939b.mo7129a((List) hso.m12033a((Object) new ecs()));
    }
}
