package p000;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.system.Os;
import java.io.IOException;

/* renamed from: hfm */
/* compiled from: PG */
public final /* synthetic */ class hfm implements hbg {

    /* renamed from: a */
    private final Context f12656a;

    public hfm(Context context) {
        this.f12656a = context;
    }

    /* renamed from: a */
    public final void mo6819a() {
        Context context = this.f12656a;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            int i = Build.VERSION.SDK_INT;
            if (packageInfo.applicationInfo.targetSdkVersion < 24) {
                int i2 = Build.VERSION.SDK_INT;
                Os.chmod(packageInfo.applicationInfo.dataDir, 448);
            }
        } catch (Exception e) {
            throw new IOException(e);
        } catch (PackageManager.NameNotFoundException e2) {
        } catch (IOException e3) {
        }
    }
}
