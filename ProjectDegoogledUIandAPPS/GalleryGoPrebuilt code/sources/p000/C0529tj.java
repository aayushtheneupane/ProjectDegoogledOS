package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;

/* renamed from: tj */
/* compiled from: PG */
public final class C0529tj {

    /* renamed from: a */
    public static final PorterDuff.Mode f15934a = PorterDuff.Mode.SRC_IN;

    /* renamed from: b */
    private static C0529tj f15935b;

    /* renamed from: c */
    private C0671yq f15936c;

    /* renamed from: b */
    public static synchronized C0529tj m15440b() {
        C0529tj tjVar;
        synchronized (C0529tj.class) {
            if (f15935b == null) {
                m15438a();
            }
            tjVar = f15935b;
        }
        return tjVar;
    }

    /* renamed from: a */
    public final synchronized Drawable mo10131a(Context context, int i) {
        return this.f15936c.mo10663a(context, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final synchronized Drawable mo10134c(Context context, int i) {
        return this.f15936c.mo10664a(context, i, true);
    }

    /* renamed from: a */
    public static synchronized PorterDuffColorFilter m15437a(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter a;
        synchronized (C0529tj.class) {
            a = C0671yq.m16158a(i, mode);
        }
        return a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final synchronized ColorStateList mo10133b(Context context, int i) {
        return this.f15936c.mo10667b(context, i);
    }

    /* renamed from: a */
    public final synchronized void mo10132a(Context context) {
        this.f15936c.mo10665a(context);
    }

    /* renamed from: a */
    public static synchronized void m15438a() {
        synchronized (C0529tj.class) {
            if (f15935b == null) {
                C0529tj tjVar = new C0529tj();
                f15935b = tjVar;
                tjVar.f15936c = C0671yq.m16160a();
                f15935b.f15936c.mo10666a((C0670yp) new C0528ti());
            }
        }
    }

    /* renamed from: a */
    static void m15439a(Drawable drawable, C0682za zaVar, int[] iArr) {
        C0671yq.m16162a(drawable, zaVar, iArr);
    }
}
