package p000;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.googlehelp.GoogleHelp;
import java.io.File;
import java.util.List;

/* renamed from: eti */
/* compiled from: PG */
public final class eti implements Runnable {

    /* renamed from: a */
    private final Context f8988a;

    /* renamed from: b */
    private final GoogleHelp f8989b;

    /* renamed from: c */
    private final long f8990c;

    /* renamed from: d */
    private final esv f8991d;

    public eti(Context context, GoogleHelp googleHelp, esv esv, long j, byte[] bArr) {
        this.f8988a = context;
        this.f8989b = googleHelp;
        this.f8991d = esv;
        this.f8990c = j;
    }

    public final void run() {
        List<esk> list;
        Bundle bundle = new Bundle(1);
        try {
            esw esw = new esw();
            esw.mo5224b();
            list = this.f8991d.mo5222b();
            File cacheDir = this.f8988a.getCacheDir();
            if (!list.isEmpty()) {
                if (cacheDir != null) {
                    for (esk esk : list) {
                        esk.f8941a = cacheDir;
                    }
                }
            }
            bundle.putString("gms:feedback:async_feedback_psbd_collection_time_ms", String.valueOf(esw.mo5223a()));
        } catch (Exception e) {
            Log.w("gH_GetAsyncFeedbackPsbd", "Failed to get async Feedback psbd.", e);
            bundle.putString("gms:feedback:async_feedback_psbd_failure", "exception");
            list = null;
        }
        esi a = esi.m8098a(list);
        etu a2 = etd.m8140a(this.f8988a);
        GoogleHelp googleHelp = this.f8989b;
        long j = this.f8990c;
        ekv ekv = a2.f8489f;
        C0652xy.m16066a((ekx) ekv.mo4948a(new eto(ekv, a, bundle, j, googleHelp)));
    }
}
