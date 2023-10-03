package p000;

import android.os.Bundle;

/* renamed from: dgx */
/* compiled from: PG */
public final /* synthetic */ class dgx implements hpr {

    /* renamed from: a */
    public static final hpr f6526a = new dgx();

    private dgx() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Bundle bundle = (Bundle) obj;
        if (bundle != null) {
            return Integer.valueOf(bundle.getInt("version"));
        }
        return 1;
    }
}
