package p000;

import android.content.Context;

/* renamed from: erc */
/* compiled from: PG */
public final class erc {

    /* renamed from: b */
    private static final erc f8866b = new erc();

    /* renamed from: a */
    private erb f8867a = null;

    /* renamed from: b */
    private final synchronized erb m8050b(Context context) {
        if (this.f8867a == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.f8867a = new erb(context);
        }
        return this.f8867a;
    }

    /* renamed from: a */
    public static erb m8049a(Context context) {
        return f8866b.m8050b(context);
    }
}
