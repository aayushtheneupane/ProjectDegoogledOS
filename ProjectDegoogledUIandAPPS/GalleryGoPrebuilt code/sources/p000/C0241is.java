package p000;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;

/* renamed from: is */
/* compiled from: PG */
public final class C0241is {

    /* renamed from: a */
    public static final C0250ja f14960a;

    /* renamed from: b */
    public static final C0297ku f14961b = new C0297ku(16);

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            f14960a = new C0245iw();
        } else if (Build.VERSION.SDK_INT >= 28) {
            f14960a = new C0244iv();
        } else {
            int i = Build.VERSION.SDK_INT;
            f14960a = new C0243iu();
        }
    }

    /* renamed from: a */
    public static Typeface m14372a(Context context, Typeface typeface, int i) {
        if (context != null) {
            int i2 = Build.VERSION.SDK_INT;
            return Typeface.create(typeface, i);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    /* renamed from: a */
    public static Typeface m14373a(Context context, C0230ih ihVar, Resources resources, int i, int i2, C0237io ioVar, boolean z) {
        Typeface typeface;
        if (ihVar instanceof C0233ik) {
            C0233ik ikVar = (C0233ik) ihVar;
            typeface = C0273jx.m14497a(context, ikVar.f14370a, ioVar, z && ikVar.f14372c == 0, z ? ikVar.f14371b : -1, i2);
        } else {
            typeface = f14960a.mo9109a(context, (C0231ii) ihVar, resources, i2);
            if (typeface != null) {
                ioVar.mo9036b(typeface);
            } else {
                ioVar.mo9035a(-3);
            }
        }
        if (typeface != null) {
            f14961b.mo9238a((Object) m14374a(resources, i, i2), (Object) typeface);
        }
        return typeface;
    }

    /* renamed from: a */
    public static Typeface m14371a(Context context, Resources resources, int i, String str, int i2) {
        Typeface a = f14960a.mo9111a(context, resources, i, str, i2);
        if (a != null) {
            f14961b.mo9238a((Object) m14374a(resources, i, i2), (Object) a);
        }
        return a;
    }

    /* renamed from: a */
    public static String m14374a(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }
}
