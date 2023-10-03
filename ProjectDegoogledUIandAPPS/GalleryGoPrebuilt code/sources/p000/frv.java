package p000;

import android.content.Context;
import android.content.res.Resources;

/* renamed from: frv */
/* compiled from: PG */
public final class frv {

    /* renamed from: a */
    private final Context f10335a;

    public frv(Context context) {
        this.f10335a = context;
    }

    /* renamed from: a */
    public final String mo6084a(int i) {
        try {
            return this.f10335a.getResources().getResourceName(i);
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }
}
