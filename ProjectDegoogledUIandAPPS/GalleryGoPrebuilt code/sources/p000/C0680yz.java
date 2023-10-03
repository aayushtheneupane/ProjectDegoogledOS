package p000;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;

/* renamed from: yz */
/* compiled from: PG */
public final class C0680yz extends ContextWrapper {
    /* renamed from: a */
    public static Context m16188a(Context context) {
        if (!(context instanceof C0680yz) && !(context.getResources() instanceof C0683zb) && !(context.getResources() instanceof C0696zo)) {
            int i = Build.VERSION.SDK_INT;
        }
        return context;
    }

    public final AssetManager getAssets() {
        throw null;
    }

    public final Resources getResources() {
        throw null;
    }

    public final Resources.Theme getTheme() {
        throw null;
    }

    public final void setTheme(int i) {
        throw null;
    }
}
