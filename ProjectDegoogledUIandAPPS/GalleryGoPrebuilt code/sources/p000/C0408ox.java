package p000;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;

/* renamed from: ox */
/* compiled from: PG */
final class C0408ox extends C0410oz {

    /* renamed from: a */
    private final PowerManager f15429a;

    /* renamed from: b */
    private final /* synthetic */ C0416pe f15430b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0408ox(C0416pe peVar, Context context) {
        super(peVar);
        this.f15430b = peVar;
        this.f15429a = (PowerManager) context.getSystemService("power");
    }

    /* renamed from: c */
    public final IntentFilter mo9592c() {
        int i = Build.VERSION.SDK_INT;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        return intentFilter;
    }

    /* renamed from: a */
    public final int mo9590a() {
        int i = Build.VERSION.SDK_INT;
        return this.f15429a.isPowerSaveMode() ? 2 : 1;
    }

    /* renamed from: b */
    public final void mo9591b() {
        this.f15430b.mo9564j();
    }
}
