package p000;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.google.apps.tiktok.sync.impl.SyncBootReceiver_Receiver;
import com.google.apps.tiktok.sync.impl.SyncReceiver_Receiver;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* renamed from: hhs */
/* compiled from: PG */
public final class hhs {

    /* renamed from: a */
    public static final long f12757a = TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS);

    /* renamed from: b */
    public static final long f12758b = TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS);

    /* renamed from: c */
    public final exm f12759c;

    /* renamed from: d */
    public final hjm f12760d;

    /* renamed from: e */
    public final AlarmManager f12761e;

    /* renamed from: f */
    public final Map f12762f;

    /* renamed from: g */
    public final iqk f12763g;

    /* renamed from: h */
    public final Map f12764h = new C0290kn();

    /* renamed from: i */
    private final Context f12765i;

    /* renamed from: j */
    private final PackageManager f12766j;

    /* renamed from: k */
    private final iek f12767k;

    public hhs(Context context, exm exm, PackageManager packageManager, hjm hjm, iek iek, iqk iqk, Map map) {
        this.f12765i = context;
        this.f12759c = exm;
        this.f12766j = packageManager;
        this.f12760d = hjm;
        this.f12767k = iek;
        this.f12763g = iqk;
        this.f12762f = map;
        this.f12761e = (AlarmManager) context.getSystemService("alarm");
    }

    /* renamed from: a */
    public final PendingIntent mo7455a() {
        return PendingIntent.getBroadcast(this.f12765i, 0, new Intent(this.f12765i, SyncReceiver_Receiver.class), 1073741824);
    }

    /* renamed from: a */
    public final ieh mo7456a(Set set, long j, Map map) {
        return ibv.m12657a(this.f12760d.mo7499a(set, j, map), hmq.m11742a((hpr) new hhr(this)), (Executor) this.f12767k);
    }

    /* renamed from: a */
    public final void mo7457a(boolean z) {
        this.f12766j.setComponentEnabledSetting(new ComponentName(this.f12765i, SyncBootReceiver_Receiver.class), !z ? 2 : 1, 1);
    }
}
