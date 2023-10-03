package p000;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* renamed from: bbj */
/* compiled from: PG */
public final class bbj {

    /* renamed from: a */
    private static volatile boolean f1994a = true;

    /* renamed from: a */
    public static Drawable m2098a(Context context, Context context2, int i) {
        try {
            if (f1994a) {
                return C0436py.m15105b(context2, i);
            }
        } catch (NoClassDefFoundError e) {
            f1994a = false;
        } catch (IllegalStateException e2) {
            if (!context.getPackageName().equals(context2.getPackageName())) {
                return C0071co.m4660a(context2, i);
            }
            throw e2;
        } catch (Resources.NotFoundException e3) {
        }
        Resources.Theme theme = context2.getTheme();
        Resources resources = context2.getResources();
        int i2 = Build.VERSION.SDK_INT;
        return resources.getDrawable(i, theme);
    }
}
