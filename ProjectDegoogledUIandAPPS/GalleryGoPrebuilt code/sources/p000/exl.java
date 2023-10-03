package p000;

import android.content.ContentResolver;
import android.content.Context;

/* renamed from: exl */
/* compiled from: PG */
public abstract class exl {

    /* renamed from: a */
    public static ContentResolver f9183a = null;

    /* renamed from: b */
    public final String f9184b;

    /* renamed from: c */
    public final Object f9185c;

    /* renamed from: a */
    public abstract Object mo5369a();

    protected exl(String str, Object obj) {
        this.f9184b = str;
        this.f9185c = obj;
    }

    /* renamed from: a */
    public static void m8321a(Context context) {
        f9183a = context.getContentResolver();
    }

    /* renamed from: a */
    public static exl m8320a(String str, Integer num) {
        return new exk(str, num);
    }

    /* renamed from: a */
    public static exl m8319a(String str) {
        return new exj(str, false);
    }
}
