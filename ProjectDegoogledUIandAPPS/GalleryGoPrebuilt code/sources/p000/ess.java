package p000;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: ess */
/* compiled from: PG */
public final class ess implements Runnable {

    /* renamed from: a */
    private final Context f8954a;

    /* renamed from: b */
    private final long f8955b;

    /* renamed from: c */
    private final esv f8956c;

    public ess(Context context, esv esv, long j, byte[] bArr) {
        this.f8954a = context;
        this.f8956c = esv;
        this.f8955b = j;
    }

    public final void run() {
        List list;
        try {
            esw esw = new esw();
            esw.mo5224b();
            list = this.f8956c.mo5221a();
            try {
                list.add(Pair.create("gms:feedback:async_feedback_psd_collection_time_ms", String.valueOf(esw.mo5223a())));
            } catch (UnsupportedOperationException e) {
                ArrayList arrayList = new ArrayList(list);
                arrayList.add(Pair.create("gms:feedback:async_feedback_psd_collection_time_ms", String.valueOf(esw.mo5223a())));
                list = arrayList;
            }
        } catch (Exception e2) {
            Log.w("gF_GetAsyncFeedbackPsd", "Failed to get async Feedback psd.", e2);
            list = Collections.singletonList(Pair.create("gms:feedback:async_feedback_psd_failure", "exception"));
        }
        ekr a = esd.m8088a(this.f8954a);
        C0652xy.m16066a(esd.m8089a(a.f8489f, esv.m8122a(list), this.f8955b));
    }
}
