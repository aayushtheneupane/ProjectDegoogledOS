package p000;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/* renamed from: ejx */
/* compiled from: PG */
public class ejx {

    /* renamed from: c */
    public static final int f8457c = ekh.f8469a;

    /* renamed from: d */
    public static final ejx f8458d = new ejx();

    /* renamed from: a */
    public final Intent mo4917a(Context context, int i, String str) {
        if (i == 1 || i == 2) {
            if (context != null) {
                esv.m8128b(context);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("gcore_");
            sb.append(f8457c);
            sb.append("-");
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
            }
            sb.append("-");
            if (context != null) {
                sb.append(context.getPackageName());
            }
            sb.append("-");
            if (context != null) {
                try {
                    erb a = erc.m8049a(context);
                    sb.append(a.f8865a.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                } catch (PackageManager.NameNotFoundException e) {
                }
            }
            return eqc.m8029a("com.google.android.gms", sb.toString());
        } else if (i != 3) {
            return null;
        } else {
            return eqc.m8028a("com.google.android.gms");
        }
    }

    /* renamed from: b */
    public final PendingIntent mo4920b(Context context, int i, String str) {
        Intent a = mo4917a(context, i, str);
        if (a != null) {
            return PendingIntent.getActivity(context, 0, a, 134217728);
        }
        return null;
    }

    /* renamed from: b */
    public final int mo4918b(Context context) {
        return mo4919b(context, f8457c);
    }

    /* renamed from: b */
    public final int mo4919b(Context context, int i) {
        int a = ekh.m7667a(context, i);
        if (ekh.m7673b(context, a)) {
            return 18;
        }
        return a;
    }
}
