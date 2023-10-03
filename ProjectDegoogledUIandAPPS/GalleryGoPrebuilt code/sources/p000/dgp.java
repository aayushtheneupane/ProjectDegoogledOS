package p000;

import android.app.ActivityManager;

/* renamed from: dgp */
/* compiled from: PG */
public final class dgp {

    /* renamed from: a */
    private final ActivityManager f6513a;

    public dgp(ActivityManager activityManager) {
        this.f6513a = activityManager;
    }

    /* renamed from: b */
    public final long mo4123b() {
        return m6092c().availMem;
    }

    /* renamed from: c */
    private final ActivityManager.MemoryInfo m6092c() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        this.f6513a.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    /* renamed from: a */
    public final int mo4122a() {
        return (int) (m6092c().totalMem / 1048576);
    }
}
