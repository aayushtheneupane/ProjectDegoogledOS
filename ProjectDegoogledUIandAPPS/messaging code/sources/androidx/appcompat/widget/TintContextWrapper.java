package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.util.ArrayList;

public class TintContextWrapper extends ContextWrapper {
    private static final Object CACHE_LOCK = new Object();
    private static ArrayList sCache;
    private final Resources mResources;
    private final Resources.Theme mTheme = null;

    private TintContextWrapper(Context context) {
        super(context);
        VectorEnabledTintResources.shouldBeUsed();
        this.mResources = new TintResources(this, context.getResources());
    }

    private static boolean shouldWrap(Context context) {
        if (!(context instanceof TintContextWrapper) && !(context.getResources() instanceof TintResources) && !(context.getResources() instanceof VectorEnabledTintResources)) {
            int i = Build.VERSION.SDK_INT;
            VectorEnabledTintResources.shouldBeUsed();
        }
        return false;
    }

    public static Context wrap(Context context) {
        shouldWrap(context);
        return context;
    }

    public AssetManager getAssets() {
        return this.mResources.getAssets();
    }

    public Resources getResources() {
        return this.mResources;
    }

    public Resources.Theme getTheme() {
        Resources.Theme theme = this.mTheme;
        return theme == null ? super.getTheme() : theme;
    }

    public void setTheme(int i) {
        Resources.Theme theme = this.mTheme;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
