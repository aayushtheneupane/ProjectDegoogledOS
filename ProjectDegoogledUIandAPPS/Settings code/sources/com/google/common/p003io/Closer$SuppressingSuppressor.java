package com.google.common.p003io;

import java.lang.reflect.Method;

/* renamed from: com.google.common.io.Closer$SuppressingSuppressor */
final class Closer$SuppressingSuppressor implements Closer$Suppressor {
    static final Closer$SuppressingSuppressor INSTANCE = new Closer$SuppressingSuppressor();
    static final Method addSuppressed = getAddSuppressed();

    Closer$SuppressingSuppressor() {
    }

    private static Method getAddSuppressed() {
        try {
            return Throwable.class.getMethod("addSuppressed", new Class[]{Throwable.class});
        } catch (Throwable unused) {
            return null;
        }
    }
}
