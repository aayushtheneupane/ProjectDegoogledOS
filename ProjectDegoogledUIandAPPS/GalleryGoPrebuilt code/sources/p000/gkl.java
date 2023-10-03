package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.R;

/* renamed from: gkl */
/* compiled from: PG */
public final class gkl {

    /* renamed from: a */
    private static final int[] f11545a = {16842752, R.attr.theme};

    /* renamed from: b */
    private static final int[] f11546b = {R.attr.materialThemeOverlay};

    /* renamed from: a */
    public static Context m10444a(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f11546b, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        boolean z = (context instanceof C0445qg) && ((C0445qg) context).f15605a == resourceId;
        if (resourceId == 0 || z) {
            return context;
        }
        C0445qg qgVar = new C0445qg(context, resourceId);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, f11545a);
        int resourceId2 = obtainStyledAttributes2.getResourceId(0, 0);
        int resourceId3 = obtainStyledAttributes2.getResourceId(1, 0);
        obtainStyledAttributes2.recycle();
        if (resourceId2 == 0) {
            resourceId2 = resourceId3;
        }
        if (resourceId2 != 0) {
            qgVar.getTheme().applyStyle(resourceId2, true);
        }
        return qgVar;
    }
}
