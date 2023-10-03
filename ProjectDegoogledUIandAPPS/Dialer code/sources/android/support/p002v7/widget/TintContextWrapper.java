package android.support.p002v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

/* renamed from: android.support.v7.widget.TintContextWrapper */
public class TintContextWrapper extends ContextWrapper {
    private static final Object CACHE_LOCK = new Object();

    public static Context wrap(Context context) {
        if (!(context instanceof TintContextWrapper) && !(context.getResources() instanceof TintResources) && !(context.getResources() instanceof VectorEnabledTintResources)) {
            int i = Build.VERSION.SDK_INT;
            VectorEnabledTintResources.shouldBeUsed();
        }
        return context;
    }
}
