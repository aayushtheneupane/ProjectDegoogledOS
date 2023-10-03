package p000;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/* renamed from: pr */
/* compiled from: PG */
final class C0429pr {

    /* renamed from: a */
    public static C0429pr f15533a;

    /* renamed from: b */
    public final Context f15534b;

    /* renamed from: c */
    public final C0428pq f15535c = new C0428pq();

    /* renamed from: d */
    private final LocationManager f15536d;

    public C0429pr(Context context, LocationManager locationManager) {
        this.f15534b = context;
        this.f15536d = locationManager;
    }

    /* renamed from: a */
    public final Location mo9641a(String str) {
        try {
            if (this.f15536d.isProviderEnabled(str)) {
                return this.f15536d.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
