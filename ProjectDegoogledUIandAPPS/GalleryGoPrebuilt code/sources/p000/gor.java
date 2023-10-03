package p000;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import com.google.apps.tiktok.concurrent.AndroidFuturesService;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: gor */
/* compiled from: PG */
public final class gor {

    /* renamed from: a */
    public static final hvy f11759a = hvy.m12261a("com/google/apps/tiktok/concurrent/AndroidFuturesServiceCounter");

    /* renamed from: b */
    public static final iev f11760b;

    /* renamed from: c */
    public final Object f11761c = new Object();

    /* renamed from: d */
    public final ConcurrentHashMap f11762d = new ConcurrentHashMap(10, 0.75f, 4);

    /* renamed from: e */
    public final SparseArray f11763e = new SparseArray();

    /* renamed from: f */
    public final SparseArray f11764f = new SparseArray();

    /* renamed from: g */
    public final UUID f11765g = UUID.randomUUID();

    static {
        iev f = iev.m12774f();
        f11760b = f;
        f.mo8346b(new Object());
    }

    public gor(Context context) {
        new AtomicInteger(0);
        new AtomicLong(0);
        new Intent(context, AndroidFuturesService.class);
    }
}
