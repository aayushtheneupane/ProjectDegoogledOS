package p000;

import android.content.Context;

/* renamed from: ake */
/* compiled from: PG */
public final class ake {

    /* renamed from: e */
    private static ake f667e;

    /* renamed from: a */
    public final ajv f668a;

    /* renamed from: b */
    public final ajw f669b;

    /* renamed from: c */
    public final akc f670c;

    /* renamed from: d */
    public final akd f671d;

    private ake(Context context, amz amz) {
        Context applicationContext = context.getApplicationContext();
        this.f668a = new ajv(applicationContext, amz);
        this.f669b = new ajw(applicationContext, amz);
        this.f670c = new akc(applicationContext, amz);
        this.f671d = new akd(applicationContext, amz);
    }

    /* renamed from: a */
    public static synchronized ake m651a(Context context, amz amz) {
        ake ake;
        synchronized (ake.class) {
            if (f667e == null) {
                f667e = new ake(context, amz);
            }
            ake = f667e;
        }
        return ake;
    }
}
