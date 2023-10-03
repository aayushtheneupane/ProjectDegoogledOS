package p000;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Window;

/* renamed from: egt */
/* compiled from: PG */
public final class egt {

    /* renamed from: a */
    public final egv f8226a;

    public egt(egv egv) {
        this.f8226a = egv;
    }

    /* renamed from: a */
    public final void mo4801a(Window window) {
        Drawable drawable;
        window.setFlags(this.f8226a.f8232c, 201326592);
        window.getDecorView().setSystemUiVisibility(this.f8226a.f8231b);
        TypedValue typedValue = new TypedValue();
        Context context = window.getContext();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(16842836, typedValue, true);
        if (typedValue.data < 28 || typedValue.data > 31) {
            drawable = context.getResources().getDrawable(typedValue.resourceId, theme);
        } else {
            drawable = new ColorDrawable(typedValue.data);
        }
        window.setBackgroundDrawable(drawable);
    }
}
