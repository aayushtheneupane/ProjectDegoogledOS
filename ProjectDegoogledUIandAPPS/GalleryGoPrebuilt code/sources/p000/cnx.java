package p000;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.google.android.apps.photosgo.R;

/* renamed from: cnx */
/* compiled from: PG */
public final class cnx {

    /* renamed from: a */
    public final cny f4748a;

    /* renamed from: b */
    public final coa f4749b;

    /* renamed from: c */
    public final Drawable f4750c;

    /* renamed from: d */
    public final LayerDrawable f4751d;

    public cnx(cny cny, coa coa, Context context) {
        this.f4748a = cny;
        this.f4749b = coa;
        this.f4750c = new ColorDrawable(C0071co.m4669b(context, R.color.photosgo_placeholder_background_color));
        this.f4751d = (LayerDrawable) context.getDrawable(R.drawable.grid_failed_load);
    }

    /* renamed from: a */
    public final bdx mo3293a() {
        return this.f4748a.mo3295a().mo1851a(this.f4750c);
    }

    /* renamed from: b */
    public final bdx mo3294b() {
        return this.f4748a.mo3297b().mo1851a(this.f4750c);
    }
}
