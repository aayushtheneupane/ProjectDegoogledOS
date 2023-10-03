package p000;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.googlehelp.GoogleHelp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: etj */
/* compiled from: PG */
public final class etj implements Runnable {

    /* renamed from: a */
    private final Context f8992a;

    /* renamed from: b */
    private final GoogleHelp f8993b;

    /* renamed from: c */
    private final long f8994c;

    /* renamed from: d */
    private final esv f8995d;

    public etj(Context context, GoogleHelp googleHelp, esv esv, long j, byte[] bArr) {
        this.f8992a = context;
        this.f8993b = googleHelp;
        this.f8995d = esv;
        this.f8994c = j;
    }

    public final void run() {
        List list;
        try {
            esw esw = new esw();
            esw.mo5224b();
            list = this.f8995d.mo5221a();
            try {
                list.add(Pair.create("gms:feedback:async_feedback_psd_collection_time_ms", String.valueOf(esw.mo5223a())));
            } catch (UnsupportedOperationException e) {
                ArrayList arrayList = new ArrayList(list);
                arrayList.add(Pair.create("gms:feedback:async_feedback_psd_collection_time_ms", String.valueOf(esw.mo5223a())));
                list = arrayList;
            }
        } catch (Exception e2) {
            Log.w("gH_GetAsyncFeedbackPsd", "Failed to get async Feedback psd.", e2);
            list = Collections.singletonList(Pair.create("gms:feedback:async_feedback_psd_failure", "exception"));
        }
        etu a = etd.m8140a(this.f8992a);
        GoogleHelp googleHelp = this.f8993b;
        Bundle a2 = esv.m8122a(list);
        long j = this.f8994c;
        ekv ekv = a.f8489f;
        C0652xy.m16066a((ekx) ekv.mo4948a(new etm(ekv, a2, j, googleHelp)));
    }
}
