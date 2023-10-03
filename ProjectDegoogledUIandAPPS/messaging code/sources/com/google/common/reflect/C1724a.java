package com.google.common.reflect;

/* renamed from: com.google.common.reflect.a */
public final class C1724a extends C1725b {
    private final String className;

    C1724a(String str, ClassLoader classLoader) {
        super(str, classLoader);
        this.className = C1726c.getClassName(str);
    }

    public String toString() {
        return this.className;
    }
}
