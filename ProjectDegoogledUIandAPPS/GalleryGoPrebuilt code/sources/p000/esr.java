package p000;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import java.io.File;
import java.util.List;

/* renamed from: esr */
/* compiled from: PG */
public final class esr implements Runnable {

    /* renamed from: a */
    private final Context f8951a;

    /* renamed from: b */
    private final long f8952b;

    /* renamed from: c */
    private final esv f8953c;

    public esr(Context context, esv esv, long j, byte[] bArr) {
        this.f8951a = context;
        this.f8953c = esv;
        this.f8952b = j;
    }

    public final void run() {
        List<esk> list;
        Bundle bundle = new Bundle(1);
        try {
            esw esw = new esw();
            esw.mo5224b();
            list = this.f8953c.mo5222b();
            File cacheDir = this.f8951a.getCacheDir();
            if (!list.isEmpty()) {
                if (cacheDir != null) {
                    for (esk esk : list) {
                        esk.f8941a = cacheDir;
                    }
                }
            }
            bundle.putString("gms:feedback:async_feedback_psbd_collection_time_ms", String.valueOf(esw.mo5223a()));
        } catch (Exception e) {
            Log.w("gF_GetAsyncFeedbackPsbd", "Failed to get async Feedback psbd.", e);
            bundle.putString("gms:feedback:async_feedback_psbd_failure", "exception");
            list = null;
        }
        long j = this.f8952b;
        C0652xy.m16066a(esd.m8091a(esd.m8088a(this.f8951a).f8489f, esi.m8098a(list), bundle, j));
    }
}
