package android.support.p000v4.util;

/* renamed from: android.support.v4.util.Pools$Pool */
public interface Pools$Pool<T> {
    T acquire();

    boolean release(T t);
}
