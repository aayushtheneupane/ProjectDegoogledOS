package p000;

import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: dzt */
/* compiled from: PG */
public final class dzt implements cnc {

    /* renamed from: a */
    public final dzr f7744a;

    /* renamed from: b */
    public final dzq f7745b;

    /* renamed from: c */
    public final cnh f7746c;

    /* renamed from: d */
    public final blu f7747d;

    /* renamed from: e */
    public final fee f7748e;

    /* renamed from: f */
    public View f7749f;

    public dzt(dzr dzr, dzq dzq, cnh cnh, blu blu, fee fee) {
        this.f7744a = dzr;
        this.f7745b = dzq;
        this.f7746c = cnh;
        this.f7747d = blu;
        this.f7748e = fee;
    }

    /* renamed from: j */
    public final void mo2638j() {
        this.f7749f.findViewById(R.id.external_picker_appbar).setVisibility(0);
    }

    /* renamed from: b */
    public final void mo2637b() {
        this.f7749f.findViewById(R.id.external_picker_appbar).setVisibility(4);
    }
}
