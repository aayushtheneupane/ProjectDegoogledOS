package com.google.common.p043io;

import android.support.p016v4.media.session.C0107q;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/* renamed from: com.google.common.io.f */
public final class C1717f implements Closeable {

    /* renamed from: rO */
    private static final Closer$Suppressor f2562rO;

    /* renamed from: bD */
    private final Deque f2563bD = new ArrayDeque(4);

    /* renamed from: qO */
    private Throwable f2564qO;
    final Closer$Suppressor suppressor;

    static {
        Closer$Suppressor closer$Suppressor;
        if (Closer$SuppressingSuppressor.f2561pO != null) {
            closer$Suppressor = Closer$SuppressingSuppressor.INSTANCE;
        } else {
            closer$Suppressor = Closer$LoggingSuppressor.INSTANCE;
        }
        f2562rO = closer$Suppressor;
    }

    C1717f(Closer$Suppressor closer$Suppressor) {
        if (closer$Suppressor != null) {
            this.suppressor = closer$Suppressor;
            return;
        }
        throw new NullPointerException();
    }

    public static C1717f create() {
        return new C1717f(f2562rO);
    }

    /* renamed from: a */
    public Closeable mo9355a(Closeable closeable) {
        if (closeable != null) {
            this.f2563bD.addFirst(closeable);
        }
        return closeable;
    }

    public void close() {
        Throwable th = this.f2564qO;
        while (!this.f2563bD.isEmpty()) {
            Closeable closeable = (Closeable) this.f2563bD.removeFirst();
            try {
                closeable.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    this.suppressor.mo9348a(closeable, th, th2);
                }
            }
        }
        if (this.f2564qO == null && th != null) {
            C0107q.propagateIfInstanceOf(th, IOException.class);
            C0107q.propagateIfInstanceOf(th, Error.class);
            C0107q.propagateIfInstanceOf(th, RuntimeException.class);
            throw new AssertionError(th);
        }
    }

    /* renamed from: a */
    public RuntimeException mo9356a(Throwable th) {
        if (th != null) {
            this.f2564qO = th;
            C0107q.propagateIfInstanceOf(th, IOException.class);
            C0107q.propagateIfInstanceOf(th, Error.class);
            C0107q.propagateIfInstanceOf(th, RuntimeException.class);
            throw new RuntimeException(th);
        }
        throw new NullPointerException();
    }
}
