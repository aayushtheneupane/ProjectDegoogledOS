package p000;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: gyw */
/* compiled from: PG */
final class gyw implements gyu {

    /* renamed from: a */
    private final ReadWriteLock f12325a = new ReentrantReadWriteLock();

    /* renamed from: b */
    private final ConcurrentHashMap f12326b = new ConcurrentHashMap(20, 0.8f, 2);

    /* renamed from: c */
    private final gxr f12327c;

    private gyw(gxr gxr) {
        this.f12327c = gxr;
    }

    /* renamed from: a */
    public final boolean mo7193a() {
        return false;
    }

    /* renamed from: b */
    public final void mo7194b() {
        throw new UnsupportedOperationException("Can't change observed values");
    }

    /* renamed from: a */
    static gyu m11062a(Map map, Object obj) {
        return new gyw(gxr.m11010a(map, obj));
    }

    /* renamed from: a */
    public final Object mo7192a(Object obj) {
        Object obj2 = this.f12326b.get(obj);
        if (obj2 != null) {
            return obj2;
        }
        this.f12325a.readLock().lock();
        try {
            Object a = this.f12327c.mo7192a(obj);
            ife.m12869b(a, (Object) "Unregistered experiment!");
            this.f12326b.putIfAbsent(obj, a);
            return a;
        } finally {
            this.f12325a.readLock().unlock();
        }
    }

    /* renamed from: c */
    public final Object mo7196c() {
        this.f12325a.readLock().lock();
        try {
            return this.f12327c.f12270a;
        } finally {
            this.f12325a.readLock().unlock();
        }
    }

    /* renamed from: b */
    public final boolean mo7195b(Map map, Object obj) {
        this.f12325a.writeLock().lock();
        try {
            return this.f12327c.mo7195b(map, obj);
        } finally {
            this.f12325a.writeLock().unlock();
        }
    }
}
