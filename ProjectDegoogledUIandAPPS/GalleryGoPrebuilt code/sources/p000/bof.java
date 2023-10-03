package p000;

import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: bof */
/* compiled from: PG */
public final class bof implements cnc {

    /* renamed from: a */
    public final cnh f3243a;

    /* renamed from: b */
    public final boc f3244b;

    /* renamed from: c */
    public final hnw f3245c;

    /* renamed from: d */
    public final bnc f3246d;

    /* renamed from: e */
    public final blu f3247e;

    /* renamed from: f */
    public final dwj f3248f;

    /* renamed from: g */
    public final fee f3249g;

    /* renamed from: h */
    public final fdv f3250h;

    /* renamed from: i */
    public fdr f3251i;

    /* renamed from: j */
    public View f3252j;

    public bof(cnh cnh, boc boc, hnw hnw, bnc bnc, blu blu, dwj dwj, fee fee, fdv fdv) {
        this.f3243a = cnh;
        this.f3244b = boc;
        this.f3245c = hnw;
        this.f3246d = bnc;
        this.f3247e = blu;
        this.f3248f = dwj;
        this.f3249g = fee;
        this.f3250h = fdv;
    }

    /* renamed from: j */
    public final void mo2638j() {
        this.f3252j.findViewById(R.id.category_photo_grid_appbar).setVisibility(0);
    }

    /* renamed from: b */
    public final void mo2637b() {
        this.f3252j.findViewById(R.id.category_photo_grid_appbar).setVisibility(4);
    }
}
