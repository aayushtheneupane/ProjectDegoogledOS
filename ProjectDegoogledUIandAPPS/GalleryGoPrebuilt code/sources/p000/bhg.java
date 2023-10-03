package p000;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.firebase.jobdispatcher.GooglePlayReceiver;

/* renamed from: bhg */
/* compiled from: PG */
public final class bhg implements bgy {

    /* renamed from: a */
    public final bif f2371a;

    /* renamed from: b */
    public final Context f2372b;

    /* renamed from: c */
    private final PendingIntent f2373c;

    /* renamed from: d */
    private final bhi f2374d = new bhi();

    public bhg(Context context) {
        this.f2372b = context;
        this.f2373c = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        this.f2371a = new bgx(context);
    }

    /* renamed from: a */
    public final bif mo2028a() {
        return this.f2371a;
    }

    /* renamed from: a */
    public final void mo2030a(String str) {
        Context context = this.f2372b;
        Intent b = mo2038b("CANCEL_TASK");
        b.putExtra("tag", str);
        b.putExtra("component", new ComponentName(this.f2372b, GooglePlayReceiver.class));
        context.sendBroadcast(b);
    }

    /* renamed from: b */
    public final Intent mo2038b(String str) {
        Intent intent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("scheduler_action", str);
        intent.putExtra("app", this.f2373c);
        intent.putExtra("source", 8);
        intent.putExtra("source_version", 1);
        return intent;
    }

    /* renamed from: a */
    public final void mo2029a(bhs bhs) {
        int i;
        boolean z;
        synchronized (GooglePlayReceiver.f4796b) {
            C0309lf lfVar = (C0309lf) GooglePlayReceiver.f4796b.get(bhs.f2392a);
            i = 0;
            if (lfVar != null) {
                if (((bht) lfVar.get(bhs.f2393b)) != null) {
                    bhv bhv = new bhv();
                    bhv.f2402a = bhs.f2393b;
                    bhv.f2403b = bhs.f2392a;
                    bhv.f2410i = bhs.f2400i;
                    bhd.m2528a(bhv.mo2059a(), false);
                }
            }
        }
        Context context = this.f2372b;
        Intent b = mo2038b("SCHEDULE_TASK");
        bhi bhi = this.f2374d;
        Bundle extras = b.getExtras();
        extras.putString("tag", bhs.f2393b);
        extras.putBoolean("update_current", bhs.f2398g);
        if (bhs.f2395d == 2) {
            z = true;
        } else {
            z = false;
        }
        extras.putBoolean("persisted", z);
        extras.putString("service", GooglePlayReceiver.class.getName());
        cns cns = bhs.f2400i;
        if (cns == bii.f2452a) {
            extras.putInt("trigger_type", 2);
            extras.putLong("window_start", 0);
            extras.putLong("window_end", 1);
        } else if (cns instanceof bie) {
            bie bie = (bie) cns;
            extras.putInt("trigger_type", 1);
            if (bhs.f2396e) {
                extras.putLong("period", (long) bie.f2445b);
                extras.putLong("period_flex", (long) (bie.f2445b - bie.f2444a));
            } else {
                extras.putLong("window_start", (long) bie.f2444a);
                extras.putLong("window_end", (long) bie.f2445b);
            }
        } else if (cns instanceof bid) {
            bid bid = (bid) cns;
            extras.putInt("trigger_type", 3);
            int size = bid.f2443a.size();
            int[] iArr = new int[size];
            Uri[] uriArr = new Uri[size];
            for (int i2 = 0; i2 < size; i2++) {
                big big = (big) bid.f2443a.get(i2);
                iArr[i2] = big.f2447b;
                uriArr[i2] = big.f2446a;
            }
            extras.putIntArray("content_uri_flags_array", iArr);
            extras.putParcelableArray("content_uri_array", uriArr);
        } else {
            String valueOf = String.valueOf(cns.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 17);
            sb.append("Unknown trigger: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }
        int a = bgv.m2519a(bhs.f2397f);
        extras.putBoolean("requiresCharging", (a & 4) == 4);
        extras.putBoolean("requiresIdle", (a & 8) == 8);
        int i3 = (a & 2) == 2 ? 0 : 2;
        if ((a & 1) != 0) {
            i3 = 1;
        }
        extras.putInt("requiredNetwork", i3);
        bih bih = bhs.f2394c;
        Bundle bundle = new Bundle();
        if (bih.f2449b == 2) {
            i = 1;
        }
        bundle.putInt("retry_policy", i);
        bundle.putInt("initial_backoff_seconds", bih.f2450c);
        bundle.putInt("maximum_backoff_seconds", bih.f2451d);
        extras.putBundle("retryStrategy", bundle);
        Bundle bundle2 = bhs.f2399h;
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        extras.putBundle("extras", bhi.f2376a.mo2057a(bhs, bundle2));
        b.putExtras(extras);
        context.sendBroadcast(b);
    }
}
