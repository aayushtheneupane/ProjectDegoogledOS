package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import com.google.apps.tiktok.sync.impl.gcm.SyncGmsPackageUpdatedReceiver_Receiver;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: hju */
/* compiled from: PG */
public final class hju implements hjk {

    /* renamed from: a */
    public final Context f12872a;

    /* renamed from: b */
    public final exm f12873b;

    /* renamed from: c */
    public final Map f12874c;

    /* renamed from: d */
    public final eyc f12875d;

    /* renamed from: e */
    public final PackageManager f12876e;

    /* renamed from: f */
    public final hhs f12877f;

    /* renamed from: g */
    public final hjm f12878g;

    /* renamed from: h */
    public final bhe f12879h;

    /* renamed from: i */
    public final iqk f12880i;

    /* renamed from: j */
    private final iek f12881j;

    public hju(Context context, exm exm, Map map, eyc eyc, PackageManager packageManager, hhs hhs, hjm hjm, bhe bhe, iek iek, iqk iqk) {
        this.f12872a = context;
        this.f12873b = exm;
        this.f12874c = map;
        this.f12875d = eyc;
        this.f12876e = packageManager;
        this.f12877f = hhs;
        this.f12878g = hjm;
        this.f12881j = iek;
        this.f12879h = bhe;
        this.f12880i = iqk;
    }

    /* renamed from: a */
    public final synchronized ieh mo7497a(Set set, long j, Map map) {
        return ibv.m12658a(this.f12878g.mo7499a(set, j, map), hmq.m11744a((icf) new hjt(this, set, j, map)), (Executor) this.f12881j);
    }

    /* renamed from: a */
    public final void mo7500a(boolean z) {
        this.f12876e.setComponentEnabledSetting(new ComponentName(this.f12872a, SyncGmsPackageUpdatedReceiver_Receiver.class), !z ? 2 : 1, 1);
    }
}
