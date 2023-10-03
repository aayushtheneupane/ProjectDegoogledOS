package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryChargingProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryNotLowProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy$NetworkStateProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy$StorageNotLowProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver;

/* renamed from: aiy */
/* compiled from: PG */
public final class aiy implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Intent f603a;

    /* renamed from: b */
    private final /* synthetic */ Context f604b;

    /* renamed from: c */
    private final /* synthetic */ BroadcastReceiver.PendingResult f605c;

    public aiy(Intent intent, Context context, BroadcastReceiver.PendingResult pendingResult) {
        this.f603a = intent;
        this.f604b = context;
        this.f605c = pendingResult;
    }

    public final void run() {
        try {
            boolean booleanExtra = this.f603a.getBooleanExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", false);
            boolean booleanExtra2 = this.f603a.getBooleanExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", false);
            boolean booleanExtra3 = this.f603a.getBooleanExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", false);
            boolean booleanExtra4 = this.f603a.getBooleanExtra("KEY_NETWORK_STATE_PROXY_ENABLED", false);
            iol.m14231a();
            int i = ConstraintProxyUpdateReceiver.f1178a;
            String.format("Updating proxies: BatteryNotLowProxy enabled (%s), BatteryChargingProxy enabled (%s), StorageNotLowProxy (%s), NetworkStateProxy enabled (%s)", new Object[]{Boolean.valueOf(booleanExtra), Boolean.valueOf(booleanExtra2), Boolean.valueOf(booleanExtra3), Boolean.valueOf(booleanExtra4)});
            amc.m760a(this.f604b, ConstraintProxy$BatteryNotLowProxy.class, booleanExtra);
            amc.m760a(this.f604b, ConstraintProxy$BatteryChargingProxy.class, booleanExtra2);
            amc.m760a(this.f604b, ConstraintProxy$StorageNotLowProxy.class, booleanExtra3);
            amc.m760a(this.f604b, ConstraintProxy$NetworkStateProxy.class, booleanExtra4);
        } finally {
            this.f605c.finish();
        }
    }
}
