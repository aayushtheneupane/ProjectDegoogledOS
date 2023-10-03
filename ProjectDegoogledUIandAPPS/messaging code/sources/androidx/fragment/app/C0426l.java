package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/* renamed from: androidx.fragment.app.l */
public abstract class C0426l {
    @Deprecated
    public C0424j instantiate(Context context, String str, Bundle bundle) {
        return C0424j.instantiate(context, str, bundle);
    }

    public abstract View onFindViewById(int i);

    public abstract boolean onHasView();
}
