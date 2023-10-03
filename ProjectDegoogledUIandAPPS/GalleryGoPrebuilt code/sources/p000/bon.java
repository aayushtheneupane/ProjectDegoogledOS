package p000;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.category.CategoryView;

/* renamed from: bon */
/* compiled from: PG */
public final class bon implements cqg {

    /* renamed from: a */
    public final CategoryView f3259a;

    /* renamed from: b */
    public final ImageView f3260b;

    /* renamed from: c */
    public final ImageView f3261c;

    /* renamed from: d */
    public final ImageView f3262d;

    /* renamed from: e */
    public final TextView f3263e;

    /* renamed from: f */
    public final hos f3264f;

    /* renamed from: g */
    public final hdt f3265g;

    /* renamed from: h */
    public final int f3266h;

    /* renamed from: i */
    public final cny f3267i;

    /* renamed from: j */
    public final cqh f3268j;

    /* renamed from: k */
    public final fee f3269k;

    /* renamed from: l */
    public final fdv f3270l;

    /* renamed from: m */
    private final Context f3271m;

    public bon(hbl hbl, CategoryView categoryView, hdt hdt, cny cny, cqh cqh, hos hos, fee fee, fdv fdv) {
        this.f3264f = hos;
        this.f3271m = hbl;
        this.f3259a = categoryView;
        this.f3265g = hdt;
        this.f3267i = cny;
        this.f3268j = cqh;
        this.f3269k = fee;
        this.f3270l = fdv;
        LayoutInflater.from(hbl).inflate(R.layout.category_view_content, categoryView, true);
        this.f3260b = (ImageView) categoryView.findViewById(R.id.placeholder_background);
        this.f3261c = (ImageView) categoryView.findViewById(R.id.placeholder_image);
        this.f3262d = (ImageView) categoryView.findViewById(R.id.canonical_image);
        this.f3263e = (TextView) categoryView.findViewById(R.id.category_name);
        this.f3266h = hbl.getResources().getDimensionPixelSize(R.dimen.category_rounded_corner_radius);
    }

    /* renamed from: a */
    public final void mo2621a() {
        ColorDrawable colorDrawable;
        if (this.f3268j.mo3764d()) {
            colorDrawable = new ColorDrawable(C0071co.m4669b(this.f3271m, R.color.category_disabled_color));
        } else {
            colorDrawable = null;
        }
        this.f3259a.setForeground(colorDrawable);
        this.f3259a.setEnabled(!this.f3268j.mo3764d());
    }
}
