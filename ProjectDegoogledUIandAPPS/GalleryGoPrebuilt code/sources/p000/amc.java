package p000;

import android.content.ComponentName;
import android.content.Context;

/* renamed from: amc */
/* compiled from: PG */
public final class amc {
    static {
        iol.m14236b("PackageManagerHelper");
    }

    /* renamed from: a */
    public static void m760a(Context context, Class cls, boolean z) {
        String str;
        String str2 = "enabled";
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls.getName()), !z ? 2 : 1, 1);
            iol.m14231a();
            Object[] objArr = new Object[2];
            objArr[0] = cls.getName();
            if (z) {
                str = str2;
            } else {
                str = "disabled";
            }
            objArr[1] = str;
            String.format("%s %s", objArr);
        } catch (Exception e) {
            iol.m14231a();
            Object[] objArr2 = new Object[2];
            objArr2[0] = cls.getName();
            if (!z) {
                str2 = "disabled";
            }
            objArr2[1] = str2;
            String.format("%s could not be %s", objArr2);
            new Throwable[1][0] = e;
        }
    }
}
