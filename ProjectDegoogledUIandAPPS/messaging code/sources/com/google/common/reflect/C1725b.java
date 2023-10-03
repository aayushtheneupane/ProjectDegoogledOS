package com.google.common.reflect;

/* renamed from: com.google.common.reflect.b */
public class C1725b {
    final ClassLoader loader;
    private final String resourceName;

    C1725b(String str, ClassLoader classLoader) {
        if (str != null) {
            this.resourceName = str;
            if (classLoader != null) {
                this.loader = classLoader;
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    static C1725b m4656a(String str, ClassLoader classLoader) {
        if (str.endsWith(".class")) {
            return new C1724a(str, classLoader);
        }
        return new C1725b(str, classLoader);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C1725b)) {
            return false;
        }
        C1725b bVar = (C1725b) obj;
        if (!this.resourceName.equals(bVar.resourceName) || this.loader != bVar.loader) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.resourceName.hashCode();
    }

    public String toString() {
        return this.resourceName;
    }
}
