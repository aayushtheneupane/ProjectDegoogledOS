package p000;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

/* renamed from: avp */
/* compiled from: PG */
public final class avp {

    /* renamed from: a */
    public final ActivityManager f1768a;

    /* renamed from: b */
    public final avr f1769b;

    /* renamed from: c */
    public float f1770c = 1.0f;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public avp(Context context) {
        this.f1768a = (ActivityManager) context.getSystemService("activity");
        this.f1769b = new avq(context.getResources().getDisplayMetrics());
        int i = Build.VERSION.SDK_INT;
        if (avs.m1771a(this.f1768a)) {
            this.f1770c = 0.0f;
        }
    }
}
