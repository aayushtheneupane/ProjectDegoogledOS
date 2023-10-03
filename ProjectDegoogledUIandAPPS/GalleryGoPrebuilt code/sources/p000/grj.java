package p000;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: grj */
/* compiled from: PG */
public final class grj {

    /* renamed from: a */
    public static final ConcurrentHashMap f11897a = new ConcurrentHashMap();

    /* renamed from: b */
    public final ReferenceQueue f11898b = new ReferenceQueue();

    /* renamed from: c */
    public final ExecutorService f11899c;

    /* renamed from: d */
    public final AtomicBoolean f11900d = new AtomicBoolean(false);

    public grj(ExecutorService executorService) {
        this.f11899c = executorService;
    }
}
