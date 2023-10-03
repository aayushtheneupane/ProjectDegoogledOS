package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Build;
import com.google.apps.tiktok.sync.constraints.oncharger.OnChargerConstraintReceiver_Receiver;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* renamed from: hhh */
/* compiled from: PG */
public final class hhh implements hhf {

    /* renamed from: a */
    private static final hgz f12730a = hgz.ON_CHARGER;

    /* renamed from: b */
    private final Set f12731b = new HashSet();

    /* renamed from: c */
    private final Context f12732c;

    /* renamed from: d */
    private final PackageManager f12733d;

    /* renamed from: e */
    private final BatteryManager f12734e;

    /* renamed from: f */
    private final iqk f12735f;

    static {
        new IntentFilter("android.intent.action.BATTERY_CHANGED");
    }

    public hhh(Context context, PackageManager packageManager, iqk iqk) {
        this.f12732c = context;
        this.f12733d = packageManager;
        this.f12735f = iqk;
        int i = Build.VERSION.SDK_INT;
        this.f12734e = (BatteryManager) context.getSystemService("batterymanager");
    }

    /* renamed from: a */
    public final bhr mo7441a(bhr bhr) {
        bhr.mo2046a(4);
        return bhr;
    }

    /* renamed from: a */
    private final void m11496a(boolean z) {
        this.f12733d.setComponentEnabledSetting(new ComponentName(this.f12732c, OnChargerConstraintReceiver_Receiver.class), !z ? 2 : 1, 1);
    }

    /* renamed from: a */
    public final synchronized boolean mo7443a() {
        int i = Build.VERSION.SDK_INT;
        return this.f12734e.isCharging();
    }

    /* renamed from: b */
    public final synchronized void mo7444b(hhg hhg) {
        if (this.f12731b.isEmpty()) {
            m11496a(true);
            int i = Build.VERSION.SDK_INT;
            this.f12734e.isCharging();
        }
        this.f12731b.add(hhg);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final synchronized ieh mo7446b() {
        ArrayList arrayList;
        int i = Build.VERSION.SDK_INT;
        this.f12734e.isCharging();
        Set set = (Set) this.f12735f.mo2097a();
        HashSet<hhg> hashSet = new HashSet<>(set.size() + this.f12731b.size());
        hashSet.addAll(this.f12731b);
        hashSet.addAll(set);
        arrayList = new ArrayList(hashSet.size());
        for (hhg a : hashSet) {
            arrayList.add(a.mo7445a(f12730a));
        }
        return ife.m12895d((Iterable) arrayList);
    }

    /* renamed from: a */
    public final synchronized void mo7442a(hhg hhg) {
        this.f12731b.remove(hhg);
        if (this.f12731b.isEmpty()) {
            m11496a(false);
        }
    }
}
