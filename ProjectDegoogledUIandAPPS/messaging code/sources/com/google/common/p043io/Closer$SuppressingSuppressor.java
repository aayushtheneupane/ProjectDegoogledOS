package com.google.common.p043io;

import java.io.Closeable;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.common.io.Closer$SuppressingSuppressor */
final class Closer$SuppressingSuppressor implements Closer$Suppressor {
    static final Closer$SuppressingSuppressor INSTANCE = new Closer$SuppressingSuppressor();

    /* renamed from: pO */
    static final Method f2561pO;

    static {
        Method method;
        try {
            method = Throwable.class.getMethod("addSuppressed", new Class[]{Throwable.class});
        } catch (Throwable unused) {
            method = null;
        }
        f2561pO = method;
    }

    Closer$SuppressingSuppressor() {
    }

    /* renamed from: a */
    public void mo9348a(Closeable closeable, Throwable th, Throwable th2) {
        if (th != th2) {
            try {
                f2561pO.invoke(th, new Object[]{th2});
            } catch (Throwable unused) {
                Logger logger = C1716e.logger;
                Level level = Level.WARNING;
                logger.log(level, "Suppressing exception thrown when closing " + closeable, th2);
            }
        }
    }
}
