package p000;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/* renamed from: erb */
/* compiled from: PG */
public final class erb {

    /* renamed from: a */
    public final Context f8865a;

    public erb(Context context) {
        this.f8865a = context;
    }

    /* renamed from: a */
    public final ApplicationInfo mo5177a(String str, int i) {
        return this.f8865a.getPackageManager().getApplicationInfo(str, i);
    }
}
