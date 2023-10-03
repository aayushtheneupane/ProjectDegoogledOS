package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import com.google.android.apps.photosgo.R;

/* renamed from: djt */
/* compiled from: PG */
final class djt extends bek {

    /* renamed from: a */
    private final C0394oj f6685a;

    public djt(C0394oj ojVar) {
        this.f6685a = ojVar;
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
        this.f6685a.mo9527a(drawable);
    }

    /* renamed from: c */
    public final void mo1898c(Drawable drawable) {
        this.f6685a.mo9527a(drawable);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1433a(Object obj, bex bex) {
        Drawable drawable = (Drawable) obj;
        drawable.setAlpha(255);
        int i = Build.VERSION.SDK_INT;
        Drawable mutate = drawable.mutate();
        Context context = this.f6685a.getContext();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorOnSurfaceVariant, typedValue, true);
        C0257jh.m14473a(mutate, typedValue.data);
        this.f6685a.mo9527a(drawable);
    }
}
