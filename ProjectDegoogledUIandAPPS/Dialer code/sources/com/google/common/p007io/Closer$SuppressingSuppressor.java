package com.google.common.p007io;

/* renamed from: com.google.common.io.Closer$SuppressingSuppressor */
final class Closer$SuppressingSuppressor implements Closer$Suppressor {
    static {
        new Closer$SuppressingSuppressor();
        try {
            Throwable.class.getMethod("addSuppressed", new Class[]{Throwable.class});
        } catch (Throwable unused) {
        }
    }

    Closer$SuppressingSuppressor() {
    }
}
