package com.google.common.p043io;

import java.io.Closeable;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.common.io.Closer$LoggingSuppressor */
final class Closer$LoggingSuppressor implements Closer$Suppressor {
    static final Closer$LoggingSuppressor INSTANCE = new Closer$LoggingSuppressor();

    Closer$LoggingSuppressor() {
    }

    /* renamed from: a */
    public void mo9348a(Closeable closeable, Throwable th, Throwable th2) {
        Logger logger = C1716e.logger;
        Level level = Level.WARNING;
        logger.log(level, "Suppressing exception thrown when closing " + closeable, th2);
    }
}
