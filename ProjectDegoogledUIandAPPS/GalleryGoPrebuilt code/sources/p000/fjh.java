package p000;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/* renamed from: fjh */
/* compiled from: PG */
final class fjh implements hqk {

    /* renamed from: a */
    private final /* synthetic */ Application f9803a;

    /* renamed from: b */
    private final /* synthetic */ fld f9804b;

    public fjh(Application application, fld fld) {
        this.f9803a = application;
        this.f9804b = fld;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2652a() {
        String str;
        int i;
        fjw fjw = new fjw((byte[]) null);
        fjw.f9855a = this.f9803a;
        if (this.f9804b.mo5907b().mo7646a()) {
            fjw.f9856b = ((flp) this.f9804b.mo5907b().mo7647b()).mo5925a();
        }
        Context context = fjw.f9855a;
        hqk hqk = fjw.f9856b;
        String packageName = ((Context) ife.m12898e((Object) context)).getPackageName();
        String e = fom.m9326e(context);
        PackageManager packageManager = context.getPackageManager();
        try {
            str = packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            flw.m9202d("MetricStamper", "Failed to get PackageInfo for: %s", packageName);
            str = null;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (!packageManager.hasSystemFeature("android.hardware.type.watch")) {
            int i3 = Build.VERSION.SDK_INT;
            if (!packageManager.hasSystemFeature("android.software.leanback")) {
                i = 2;
            } else {
                i = 4;
            }
        } else {
            i = 3;
        }
        int i4 = Build.VERSION.SDK_INT;
        return new fjx(packageName, e, str, packageManager.hasSystemFeature("android.hardware.type.automotive") ? 5 : i, 282316422L, new exq(context), hqk);
    }
}
