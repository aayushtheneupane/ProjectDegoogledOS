package p000;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import java.util.Locale;

/* renamed from: cwg */
/* compiled from: PG */
public final class cwg {

    /* renamed from: a */
    private final Context f5794a;

    public cwg(Context context) {
        this.f5794a = context;
    }

    /* renamed from: a */
    public final Locale mo3859a() {
        Configuration configuration = this.f5794a.getResources().getConfiguration();
        int i = Build.VERSION.SDK_INT;
        return ((C0262jm) C0260jk.m14489a(configuration.getLocales()).f15082a).f15083a.get(0);
    }
}
