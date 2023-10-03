package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.util.WeakHashMap;

/* renamed from: py */
/* compiled from: PG */
public final class C0436py {
    static {
        new ThreadLocal();
        new WeakHashMap(0);
    }

    /* renamed from: a */
    public static ColorStateList m15104a(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return context.getColorStateList(i);
    }

    /* renamed from: b */
    public static Drawable m15105b(Context context, int i) {
        return C0671yq.m16160a().mo10663a(context, i);
    }
}
