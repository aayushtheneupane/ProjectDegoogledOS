package androidx.appcompat.p018a.p019a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.appcompat.widget.ResourceManagerInternal;
import java.util.WeakHashMap;

@SuppressLint({"RestrictedAPI"})
/* renamed from: androidx.appcompat.a.a.a */
public final class C0130a {

    /* renamed from: Ml */
    private static final WeakHashMap f132Ml = new WeakHashMap(0);

    /* renamed from: Nl */
    private static final Object f133Nl = new Object();
    private static final ThreadLocal TL_TYPED_VALUE = new ThreadLocal();

    public static ColorStateList getColorStateList(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return context.getColorStateList(i);
    }

    public static Drawable getDrawable(Context context, int i) {
        return ResourceManagerInternal.get().getDrawable(context, i);
    }
}
