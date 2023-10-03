package p000;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.util.Log;

/* renamed from: fqa */
/* compiled from: PG */
public final class fqa {

    /* renamed from: a */
    private static volatile hpy f10240a = hph.f13219a;

    /* renamed from: b */
    private static final Object f10241b = new Object();

    /* renamed from: a */
    public static boolean m9395a(Context context, Uri uri) {
        String authority = uri.getAuthority();
        boolean z = false;
        if (!"com.google.android.gms.phenotype".equals(authority)) {
            StringBuilder sb = new StringBuilder(String.valueOf(authority).length() + 91);
            sb.append(authority);
            sb.append(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported.");
            Log.e("PhenotypeClientHelper", sb.toString());
            return false;
        } else if (f10240a.mo7646a()) {
            return ((Boolean) f10240a.mo7647b()).booleanValue();
        } else {
            synchronized (f10241b) {
                if (f10240a.mo7646a()) {
                    boolean booleanValue = ((Boolean) f10240a.mo7647b()).booleanValue();
                    return booleanValue;
                }
                if (!"com.google.android.gms".equals(context.getPackageName())) {
                    ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", 0);
                    if (resolveContentProvider != null && "com.google.android.gms".equals(resolveContentProvider.packageName)) {
                    }
                    f10240a = hpy.m11893b(Boolean.valueOf(z));
                    return ((Boolean) f10240a.mo7647b()).booleanValue();
                }
                try {
                    if ((context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & 129) != 0) {
                        z = true;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                }
                f10240a = hpy.m11893b(Boolean.valueOf(z));
                return ((Boolean) f10240a.mo7647b()).booleanValue();
            }
        }
    }
}
