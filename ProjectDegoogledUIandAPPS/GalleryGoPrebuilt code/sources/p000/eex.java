package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: eex */
/* compiled from: PG */
public final class eex {

    /* renamed from: a */
    public static final hsu f8124a;

    /* renamed from: b */
    public final PackageManager f8125b;

    /* renamed from: c */
    public final een f8126c;

    /* renamed from: d */
    public final ble f8127d;

    /* renamed from: e */
    public final iel f8128e;

    static {
        hsq hsq = new hsq();
        hsq.mo7932a(6182366498198851268L, 3);
        hsq.mo7932a(5849068713189051213L, 2);
        hsq.mo7932a(-521408846414465507L, 2);
        hsq.mo7932a(-1726407274879977285L, 2);
        hsq.mo7932a(240121433651787857L, 2);
        hsq.mo7932a(1128581378217046016L, 1);
        hsq.mo7932a(6333283808175090068L, 1);
        hsq.mo7932a(3820021508778662276L, 1);
        f8124a = hsq.mo7930a();
    }

    public eex(Context context, een een, ble ble, iel iel) {
        this.f8125b = context.getPackageManager();
        this.f8126c = een;
        this.f8127d = ble;
        this.f8128e = iel;
    }

    /* renamed from: a */
    public final Map mo4760a(Intent intent) {
        List<ResolveInfo> queryIntentActivities = this.f8125b.queryIntentActivities(intent, 0);
        HashMap hashMap = new HashMap();
        for (ResolveInfo next : queryIntentActivities) {
            hashMap.put(new ComponentName(next.activityInfo.packageName, next.activityInfo.name), next);
        }
        return hashMap;
    }

    /* renamed from: a */
    public final ieh mo4759a() {
        return gte.m10771a(this.f8126c.mo4749a(), (icf) new eeu(this), (Executor) this.f8128e);
    }
}
