package p000;

import android.content.ComponentName;
import android.content.Context;
import androidx.work.impl.background.systemjob.SystemJobService;

/* renamed from: ajg */
/* compiled from: PG */
final class ajg {

    /* renamed from: a */
    public final ComponentName f635a;

    static {
        iol.m14236b("SystemJobInfoConverter");
    }

    public ajg(Context context) {
        this.f635a = new ComponentName(context.getApplicationContext(), SystemJobService.class);
    }
}
