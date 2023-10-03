package p000;

import android.support.p002v7.widget.RecyclerView;
import android.util.TypedValue;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.devicefolders.DeviceFoldersGridView;
import java.util.List;

/* renamed from: bru */
/* compiled from: PG */
public final class bru {

    /* renamed from: a */
    public final cqd f3456a;

    /* renamed from: b */
    public final RecyclerView f3457b;

    /* renamed from: c */
    public final gwd f3458c;

    /* renamed from: d */
    public final C0598vy f3459d;

    /* renamed from: e */
    public final brt f3460e;

    /* renamed from: f */
    public final bsd f3461f;

    /* renamed from: g */
    public List f3462g = hso.m12047f();

    /* renamed from: h */
    private final hbl f3463h;

    /* renamed from: i */
    private final cps f3464i;

    public bru(DeviceFoldersGridView deviceFoldersGridView, C0147fh fhVar, hbl hbl, cps cps) {
        this.f3463h = hbl;
        this.f3464i = cps;
        cqd cqd = new cqd(hbl);
        this.f3456a = cqd;
        cqd.mo3753a(R.layout.empty_state_view);
        deviceFoldersGridView.addView(this.f3456a);
        this.f3457b = this.f3456a.f5411a;
        int applyDimension = (int) TypedValue.applyDimension(1, 8.0f, hbl.getResources().getDisplayMetrics());
        RecyclerView recyclerView = this.f3457b;
        recyclerView.setPadding(applyDimension, applyDimension, applyDimension, recyclerView.getPaddingBottom());
        this.f3461f = new bsd(hbl);
        gwb c = gwd.m10934c();
        c.mo7127a((gwe) this.f3461f);
        this.f3458c = c.mo7126a();
        int a = mo2712a();
        brt brt = new brt(this, a);
        this.f3460e = brt;
        brt.mo10420c();
        fhVar.mo2634k();
        C0598vy vyVar = new C0598vy(a, (byte[]) null);
        this.f3459d = vyVar;
        vyVar.f16174b = this.f3460e;
        this.f3457b.setHasFixedSize(true);
        this.f3457b.setLayoutManager(this.f3459d);
        this.f3457b.setAdapter(this.f3458c);
    }

    /* renamed from: a */
    public final int mo2712a() {
        return this.f3464i.mo3740a(this.f3463h.getResources().getInteger(R.integer.device_folders_grid_portrait_column_count));
    }
}
