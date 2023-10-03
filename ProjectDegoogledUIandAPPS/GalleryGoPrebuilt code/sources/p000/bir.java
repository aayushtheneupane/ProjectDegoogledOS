package p000;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.List;

/* renamed from: bir */
/* compiled from: PG */
public final class bir implements biq {

    /* renamed from: a */
    private static final Uri f2460a = Uri.parse("file://");

    /* renamed from: b */
    private static final hto f2461b = hto.m12124a("com.amazon.clouddrive.photos", "com.android.chrome", "com.google.android.apps.nbu.files", "com.jiochat.jiochatapp", "com.mobisystems.fileman", "com.mobisystems.office", "com.transsion.phoenix");

    /* renamed from: c */
    private final Context f2462c;

    public bir(Context context) {
        this.f2462c = context;
    }

    /* renamed from: a */
    public final boolean mo2092a() {
        hso hso;
        if (m2625a(this.f2462c.getPackageName())) {
            PackageManager packageManager = this.f2462c.getPackageManager();
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(f2460a, "image/*");
            try {
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
                hsj hsj = new hsj();
                for (ResolveInfo next : queryIntentActivities) {
                    String str = next.activityInfo.packageName;
                    if (!str.equals(this.f2462c.getPackageName()) && !f2461b.contains(str)) {
                        hsj.mo7908c(next);
                    }
                }
                hso = hsj.mo7905a();
            } catch (NullPointerException e) {
                hso = hso.m12047f();
            }
            int size = hso.size();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                if (!m2625a(((ResolveInfo) hso.get(i)).activityInfo.packageName)) {
                    i = i2;
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private final boolean m2625a(String str) {
        try {
            if ((this.f2462c.getPackageManager().getApplicationInfo(str, 0).flags & 1) == 0) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
