package p000;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: eiu */
/* compiled from: PG */
public final class eiu {

    /* renamed from: a */
    private static final Lock f8380a = new ReentrantLock();

    /* renamed from: b */
    private static eiu f8381b;

    /* renamed from: c */
    private final Lock f8382c = new ReentrantLock();

    /* renamed from: d */
    private final SharedPreferences f8383d;

    private eiu(Context context) {
        this.f8383d = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    /* renamed from: a */
    public final String mo4861a(String str) {
        this.f8382c.lock();
        try {
            return this.f8383d.getString(str, (String) null);
        } finally {
            this.f8382c.unlock();
        }
    }

    /* renamed from: a */
    public static eiu m7613a(Context context) {
        abj.m85a((Object) context);
        f8380a.lock();
        try {
            if (f8381b == null) {
                f8381b = new eiu(context.getApplicationContext());
            }
            return f8381b;
        } finally {
            f8380a.unlock();
        }
    }
}
